package com.example.hrm.service.impl;

import com.example.hrm.dto.request.DepartmentRequest;
import com.example.hrm.entity.Department;
import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.repository.DepartmentRepository;
import com.example.hrm.repository.EmployeeInformationRepository;
import com.example.hrm.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepo;
    private final EmployeeInformationRepository employeeRepo; // inject repository

    @Override
    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    @Override
    public Department getById(Integer id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Phòng ban không tồn tại với ID: " + id));
    }

    @Override
    public Department create(DepartmentRequest request) {

        Department department = new Department();
        department.setName(request.getName());

        if (request.getManagerId() != null) {
            EmployeeInformation manager =
                    employeeRepo.findById(request.getManagerId())
                            .orElseThrow(() -> new RuntimeException("Manager not found"));

            department.setManager(manager);
        }

        return departmentRepo.save(department);
    }
    @Override
    public Department update(Integer id, Department departmentDetails) {
        Department department = getById(id);

        // Cập nhật các thông tin của phòng ban
        department.setName(departmentDetails.getName());
        // department.setDescription(departmentDetails.getDescription()); // Nếu có trường mô tả
        // department.setManager(departmentDetails.getManager()); // Nếu có quản lý trực thuộc

        return departmentRepo.save(department);
    }

    @Override
    public void delete(Integer id) {
        Department department = getById(id);
        departmentRepo.delete(department);
    }
}