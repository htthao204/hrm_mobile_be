package com.example.hrm.service;

import com.example.hrm.dto.request.EmployeeCreateRequest;
import com.example.hrm.dto.response.EmployeeResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAll();

    EmployeeResponse getById(Integer id);

    EmployeeResponse create(
            EmployeeCreateRequest request,
            MultipartFile avatar
    );

    EmployeeResponse update(
            Integer id,
            EmployeeCreateRequest request,
            MultipartFile avatar
    );

    void delete(Integer id);

    List<EmployeeResponse> getByDepartment(Integer departmentId);

    EmployeeResponse findByEmail(String email);
    EmployeeResponse registerFace(
            Integer employeeId,
            MultipartFile image
    );

    boolean hasFace(Integer employeeId);
}