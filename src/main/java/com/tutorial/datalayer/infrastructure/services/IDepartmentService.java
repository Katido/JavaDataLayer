package com.tutorial.datalayer.infrastructure.services;

import com.tutorial.datalayer.domain.Department;

import java.io.IOException;

/**
 * Created by estoyanov on 3/20/15.
 */
public interface IDepartmentService {
    public Department[] getAll() throws Exception;
    public Department getByName(String name) throws Exception;
    public Department getById(long id) throws Exception;
    public void add(Department department) throws Exception;
    public void update(Department department) throws Exception;
    public void remove(Department department) throws Exception;
    public void removeById(long id) throws Exception;
}
