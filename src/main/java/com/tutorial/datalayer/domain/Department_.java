package com.tutorial.datalayer.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


/**
 * Created by estoyanov on 3/20/15.
 */
@StaticMetamodel(Department.class )
public final class Department_ {
    public static volatile SingularAttribute<Department, Long> id;
    public static volatile SingularAttribute<Department, String> name;
}
