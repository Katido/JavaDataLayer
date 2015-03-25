package com.tutorial.datalayer.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.tutorial.datalayer.domain.Department;
import com.tutorial.datalayer.domain.Employee;
import com.tutorial.datalayer.infrastructure.exceptions.ExceptionHelper;
import com.tutorial.datalayer.infrastructure.repositories.IContext;
import com.tutorial.datalayer.infrastructure.repositories.IContextFactory;
import com.tutorial.datalayer.infrastructure.repositories.IRepository;
import com.tutorial.datalayer.infrastructure.services.IEmployeeLocationService;

import java.io.IOException;

/**
 * Created by estoyanov on 3/20/15.
 */
@Singleton
public class EmployeeLocationService implements IEmployeeLocationService {

    private final IContextFactory contextFactory;

    @Inject
    public EmployeeLocationService(IContextFactory factory){
        ExceptionHelper.throwExceptionIfNull(factory);
        this.contextFactory = factory;
    }

    @Override
    public void changeDepartment(long employeeId, long departmentId) throws Exception {
        IContext context = contextFactory.create();
        try {
            IRepository<Department> departmentRepository = context.getRepository(Department.class);
            IRepository<Employee> employeeRepository = context.getRepository(Employee.class);

            Employee employee = employeeRepository.getById(employeeId);
            Department department = departmentRepository.getById(departmentId);

            ExceptionHelper.throwExceptionIfNull(employee);
            ExceptionHelper.throwExceptionIfNull(department);

            employee.setDepartment(department);
        }
        finally {
            context.close();
        }
    }
}
