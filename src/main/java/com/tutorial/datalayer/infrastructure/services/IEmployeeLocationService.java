package com.tutorial.datalayer.infrastructure.services;

import java.io.IOException;

/**
 * Created by estoyanov on 3/20/15.
 */
public interface IEmployeeLocationService {
    void changeDepartment(long employeeId,long departmentId) throws Exception;
}
