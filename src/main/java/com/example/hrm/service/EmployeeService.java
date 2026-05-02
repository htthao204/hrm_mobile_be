package com.example.hrm.service;

import com.example.hrm.dto.request.EmployeeCreateRequest;
import com.example.hrm.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAll();

    EmployeeResponse getById(Integer id);

    EmployeeResponse create(EmployeeCreateRequest request);

    EmployeeResponse update(Integer id, EmployeeCreateRequest request);

    void delete(Integer id);

    List<EmployeeResponse> getByDepartment(Integer departmentId);

    EmployeeResponse findByEmail(String email);
}