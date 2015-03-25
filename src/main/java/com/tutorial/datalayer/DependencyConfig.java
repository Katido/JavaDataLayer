package com.tutorial.datalayer;

import com.google.inject.AbstractModule;
import com.tutorial.datalayer.infrastructure.repositories.IContextFactory;
import com.tutorial.datalayer.infrastructure.services.IDepartmentService;
import com.tutorial.datalayer.infrastructure.services.IEmployeeLocationService;
import com.tutorial.datalayer.infrastructure.services.IEmployeeService;
import com.tutorial.datalayer.repositories.ContextFactory;
import com.tutorial.datalayer.services.DepartmentService;
import com.tutorial.datalayer.services.EmployeeLocationService;
import com.tutorial.datalayer.services.EmployeeService;

/**
 * Created by estoyanov on 3/20/15.
 */
public class DependencyConfig extends AbstractModule {
    @Override
    protected void configure() {
        bind(IContextFactory.class).to(ContextFactory.class);
        bind(IEmployeeService.class).to(EmployeeService.class);
        bind(IDepartmentService.class).to(DepartmentService.class);
        bind(IEmployeeLocationService.class).to(EmployeeLocationService.class);
    }
}
