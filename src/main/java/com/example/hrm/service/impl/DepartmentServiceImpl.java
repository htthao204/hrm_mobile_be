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
    private final EmployeeInformationRepository employeeRepo;

    @Override
    public List<Department> getAll() {
        List<Department> list = departmentRepo.findAll();

        System.out.println("SIZE = " + list.size());

        return list;
    }

    @Override
    public Department getById(Integer id) {
        return departmentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found: " + id));
    }

    // ================= CREATE =================
    @Override
    public Department create(DepartmentRequest request) {

        // ⭐ tránh trùng tên phòng ban
        if (departmentRepo.existsByName(request.getName())) {
            throw new RuntimeException("Department already exists");
        }

        Department department = new Department();
        department.setName(request.getName());

        if (request.getManagerId() != null) {

            EmployeeInformation manager =
                    employeeRepo.findById(request.getManagerId())
                            .orElseThrow(() ->
                                    new RuntimeException("Manager not found"));

            department.setManager(manager);
        }

        return departmentRepo.save(department);
    }

    // ================= UPDATE =================
    @Override
    public Department update(Integer id, DepartmentRequest request) {

        Department department = getById(id);

        department.setName(request.getName());

        if (request.getManagerId() != null) {
            EmployeeInformation manager =
                    employeeRepo.findById(request.getManagerId())
                            .orElseThrow(() ->
                                    new RuntimeException("Manager not found"));

            department.setManager(manager);
        } else {
            department.setManager(null);
        }

        return departmentRepo.save(department);
    }

    // ================= DELETE =================
    @Override
    public void delete(Integer id) {

        Department department = getById(id);

        // ⭐ tránh FK crash khi còn nhân viên
        boolean hasEmployee =
                employeeRepo.existsByDepartment_Id(id);

        if (hasEmployee) {
            throw new RuntimeException(
                    "Cannot delete department because employees still belong to it"
            );
        }

        departmentRepo.delete(department);
    }
}