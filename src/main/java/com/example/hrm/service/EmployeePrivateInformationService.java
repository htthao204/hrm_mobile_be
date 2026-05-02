package com.example.hrm.service;

import com.example.hrm.entity.EmployeePrivateInformation;

public interface EmployeePrivateInformationService {

    EmployeePrivateInformation getByEmployeeId(Integer employeeId);

    EmployeePrivateInformation create(
            Integer employeeId,
            EmployeePrivateInformation info
    );

    EmployeePrivateInformation update(
            Integer employeeId,
            EmployeePrivateInformation info
    );

    void delete(Integer employeeId);
}