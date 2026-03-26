package com.example.hrm.service;
import com.example.hrm.entity.EmployeeInformation;

import java.util.List;
public interface EmployeeService {

    List<EmployeeInformation> getAll();

    EmployeeInformation getById(Integer id);

    EmployeeInformation create(EmployeeInformation employee);

    EmployeeInformation update(Integer id, EmployeeInformation employee);

    void delete(Integer id);

    List<EmployeeInformation> getByDepartment(Integer departmentId);

    EmployeeInformation findByEmail(String email);
}
