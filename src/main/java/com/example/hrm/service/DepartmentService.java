package com.example.hrm.service;

import com.example.hrm.dto.request.DepartmentRequest;
import com.example.hrm.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();

    Department getById(Integer id);

    Department create(DepartmentRequest request);

    Department update(Integer id, DepartmentRequest request);

    void delete(Integer id);
}