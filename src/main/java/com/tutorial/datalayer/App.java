package com.tutorial.datalayer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tutorial.datalayer.domain.*;
import com.tutorial.datalayer.infrastructure.services.IDepartmentService;
import com.tutorial.datalayer.infrastructure.services.IEmployeeService;

import javax.persistence.EntityManager;
import java.io.IOException;


/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new DependencyConfig());
        IEmployeeService employeeService = injector.getInstance(IEmployeeService.class);
        IDepartmentService departmentService = injector.getInstance(IDepartmentService.class);

        //Clean up database data
        clearDatabase(departmentService, employeeService);

        Department department = new Department();
        department.setName("Lohika Odessa");
        departmentService.add(department);

        Employee employee = new Employee();
        employee.setName("Stoyanov Evgeniy");
        employee.setDepartment(department);
        employeeService.add(employee);


        showDepartments(departmentService);
        showEmplayees(employeeService);
    }

    private static void clearDatabase(IDepartmentService departmentService,IEmployeeService employeeService) throws Exception {

        for (Employee employee: employeeService.getAll()){
            employeeService.remove(employee);
        }

        for (Department department: departmentService.getAll()){
            departmentService.remove(department);
        }

    }

    private static void showDepartments(IDepartmentService departmentService) throws Exception {
        for (Department department: departmentService.getAll()){
            System.out.println(department.getName());
        }
    }

    private  static void showEmplayees(IEmployeeService employeeService) throws Exception {
        for (Employee employee: employeeService.getAll()){
            System.out.println(employee.getName());
        }
    }
}
