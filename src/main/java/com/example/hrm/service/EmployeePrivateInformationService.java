package com.example.hrm.service;

import com.example.hrm.entity.EmployeePrivateInformation;
import java.util.Optional;

public interface EmployeePrivateInformationService {

    EmployeePrivateInformation getByEmployeeId(Integer employeeId);

    EmployeePrivateInformation createOrUpdate(Integer employeeId, EmployeePrivateInformation info);

    void deleteByEmployeeId(Integer employeeId);
}