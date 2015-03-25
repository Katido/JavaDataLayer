package com.tutorial.datalayer.infrastructure.services;

import com.tutorial.datalayer.domain.Employee;
import javassist.NotFoundException;

import javax.transaction.NotSupportedException;
import java.io.IOException;

/**
 * Created by estoyanov on 3/19/15.
 */
public interface IEmployeeService {
    public Employee[] getAll() throws Exception;
    public Employee getByName(String name) throws Exception;
    public Employee getById(int id) throws Exception;
    public void add(Employee employee) throws Exception;
    public void update(Employee employee) throws Exception;
    public void remove(Employee employee) throws Exception;
    public void removeById(long id) throws Exception;
}
