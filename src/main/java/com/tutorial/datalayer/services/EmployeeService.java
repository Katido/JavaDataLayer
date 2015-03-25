package com.tutorial.datalayer.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tutorial.datalayer.domain.Employee;
import com.tutorial.datalayer.domain.Employee_;
import com.tutorial.datalayer.infrastructure.exceptions.ExceptionHelper;
import com.tutorial.datalayer.infrastructure.repositories.IContext;
import com.tutorial.datalayer.infrastructure.repositories.IContextFactory;
import com.tutorial.datalayer.infrastructure.repositories.IRepository;
import com.tutorial.datalayer.infrastructure.services.IEmployeeService;
import javassist.NotFoundException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.NotSupportedException;
import java.io.IOException;
import java.util.List;

/**
 * Created by estoyanov on 3/20/15.
 */
@Singleton
public class EmployeeService implements IEmployeeService {

    private final IContextFactory contextFactory;

    @Inject
    public EmployeeService(IContextFactory factory) throws NotSupportedException {
        ExceptionHelper.throwExceptionIfNull(factory);
        this.contextFactory = factory;
    }


    @Override
    public Employee[] getAll() throws Exception {

        IContext context = contextFactory.create();
        try {
            IRepository<Employee> repository = context.getRepository(Employee.class);
            CriteriaQuery<Employee> query = repository.createQuery();
            Root<Employee> root = query.from(Employee.class);
            List<Employee> employees = repository.executeQuery(query.select(root)).getResultList();

            if (employees == null || employees.size() == 0) {
                return new Employee[0];
            }

            Employee[] result = new Employee[employees.size()];
            employees.toArray(result);
            return result;
        }
        finally {
            context.close();
        }
    }


    @Override
    public Employee getByName(String name) throws Exception {
        IContext context = contextFactory.create();
        try {
            IRepository<Employee> repository = context.getRepository(Employee.class);

            CriteriaBuilder builder = context.getCriteriaBuilder();
            CriteriaQuery<Employee> query = repository.createQuery();

            Root<Employee> root = query.from(Employee.class);
            query.where(builder.equal(root.get(Employee_.name),name));
            return (Employee)repository.executeQuery(query).getSingleResult();
        }
        finally {
            context.close();
        }
    }


    @Override
    public Employee getById(int id) throws Exception {
        IContext context = contextFactory.create();
        try {
            IRepository<Employee> repository = context.getRepository(Employee.class);
            return  repository.getById(id);
        }
        finally {
            context.close();
        }
    }


    @Override
    public void add(Employee employee) throws Exception {
        ExceptionHelper.throwExceptionIfNull(employee);
        IContext context = contextFactory.create();
        try {
            IRepository<Employee> repository = context.getRepository(Employee.class);
            repository.add(employee);
            context.save();
        }
        finally {
            context.close();
        }
    }


    @Override
    public void update(Employee employee) throws Exception {
        ExceptionHelper.throwExceptionIfNull(employee);
        IContext context = contextFactory.create();
        try {
            IRepository<Employee> repository = context.getRepository(Employee.class);
            Employee dbEmployee = repository.getById(employee.getId());
            if(dbEmployee == null){
                throw new NotFoundException("Employee was not found");
            }

            dbEmployee.setName(employee.getName());
            dbEmployee.setDepartment(employee.getDepartment());
            context.save();
        }
        finally {
            context.close();
        }

    }


    @Override
    public void remove(Employee employee) throws Exception {
        ExceptionHelper.throwExceptionIfNull(employee);
        removeById(employee.getId());
    }


    @Override
    public void removeById(long id) throws Exception {
        IContext context = contextFactory.create();
        try {
            IRepository<Employee> repository = context.getRepository(Employee.class);
            Employee dbEmployee = repository.getById(id);
            if(dbEmployee == null){
                throw new NotFoundException("Employee was not found");
            }

            repository.remove(dbEmployee);
            context.save();
        }
        finally {
            context.close();
        }
    }
}
