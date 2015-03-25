package com.tutorial.datalayer.domain;

import com.tutorial.datalayer.domain.Department;
import com.tutorial.datalayer.domain.Employee;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employee.class )
public final class Employee_ {
    public static volatile SingularAttribute<Employee, Long> id;
    public static volatile SingularAttribute<Employee, String> name;
    public static volatile SingularAttribute<Employee, Department> department;
}
