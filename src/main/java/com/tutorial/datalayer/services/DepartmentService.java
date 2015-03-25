package com.tutorial.datalayer.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tutorial.datalayer.domain.Department;
import com.tutorial.datalayer.domain.Department_;
import com.tutorial.datalayer.infrastructure.exceptions.ExceptionHelper;
import com.tutorial.datalayer.infrastructure.repositories.IContext;
import com.tutorial.datalayer.infrastructure.repositories.IContextFactory;
import com.tutorial.datalayer.infrastructure.repositories.IRepository;
import com.tutorial.datalayer.infrastructure.services.IDepartmentService;
import javassist.NotFoundException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;

/**
 * Created by estoyanov on 3/20/15.
 */
@Singleton
public class DepartmentService implements IDepartmentService {

    private final IContextFactory contextFactory;

    @Inject
    public DepartmentService(IContextFactory factory) {
        ExceptionHelper.throwExceptionIfNull(factory);
        this.contextFactory = factory;
    }

    @Override
    public Department[] getAll() throws Exception {
        IContext context = contextFactory.create();
        try {
            IRepository<Department> repository = context.getRepository(Department.class);
            CriteriaQuery<Department> query = repository.createQuery();
            Root<Department> root = query.from(Department.class);
            List<Department> departments = repository.executeQuery(query.select(root)).getResultList();

            if (departments == null || departments.size() == 0) {
                return new Department[0];
            }

            Department[] result = new Department[departments.size()];
            departments.toArray(result);
            return result;
        }
        finally {
            context.close();
        }
    }

    @Override
    public Department getByName(String name) throws Exception {
        IContext context = contextFactory.create();
        try {
            IRepository<Department> repository = context.getRepository(Department.class);

            CriteriaBuilder builder = context.getCriteriaBuilder();
            CriteriaQuery<Department> query = repository.createQuery();

            Root<Department> root = query.from(Department.class);
            query.where(builder.equal(root.get(Department_.name),name));
            return (Department)repository.executeQuery(query).getSingleResult();
        }
        finally {
            context.close();
        }
    }

    @Override
    public Department getById(long id) throws Exception {
        IContext context = contextFactory.create();
        try {
            IRepository<Department> repository = context.getRepository(Department.class);
            return repository.getById(id);
        }
        finally {
            context.close();
        }
    }

    @Override
    public void add(Department department) throws Exception {
        ExceptionHelper.throwExceptionIfNull(department);
        IContext context = contextFactory.create();
        try {
            IRepository<Department> repository = context.getRepository(Department.class);
            repository.add(department);
            context.save();
        }
        finally {
            context.close();
        }
    }

    @Override
    public void update(Department department) throws Exception {
        ExceptionHelper.throwExceptionIfNull(department);
        IContext context = contextFactory.create();
        try {
            IRepository<Department> repository = context.getRepository(Department.class);
            Department dbDepartment = repository.getById(department.getId());
            if(dbDepartment == null){
                throw new NotFoundException("Department was not found");
            }

            dbDepartment.setName(department.getName());
            dbDepartment.setEmployees(department.getEmployees());
            context.save();
        }
        finally {
            context.close();
        }
    }

    @Override
    public void remove(Department department) throws Exception {
        ExceptionHelper.throwExceptionIfNull(department);
        removeById(department.getId());
    }

    @Override
    public void removeById(long id) throws Exception {
        IContext context = contextFactory.create();
        try {
            IRepository<Department> repository = context.getRepository(Department.class);
            Department dbDepartment = repository.getById(id);
            if(dbDepartment == null){
                throw new NotFoundException("Department was not found");
            }

            repository.remove(dbDepartment);
            context.save();
        }
        finally {
            context.close();
        }
    }
}
