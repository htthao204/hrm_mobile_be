package com.example.hrm.controller;

import com.example.hrm.dto.request.EmployeeCreateRequest;
import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.repository.*;
import com.example.hrm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeInformationController {

    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepo;
    private final PositionRepository positionRepo;
    private final AccountRepository accountRepo;

    // ================= CREATE =================
    @PostMapping
    public EmployeeInformation create(
            @RequestBody EmployeeCreateRequest request) {

        EmployeeInformation employee = new EmployeeInformation();

        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setHireDate(request.getHireDate());

        // Department
        if (request.getDepartmentId() != null) {
            employee.setDepartment(
                    departmentRepo.findById(request.getDepartmentId())
                            .orElseThrow(() -> new RuntimeException("Department not found"))
            );
        }

        // Position
        employee.setPosition(
                positionRepo.findById(request.getPositionId())
                        .orElseThrow(() -> new RuntimeException("Position not found"))
        );

        // Account
        employee.setAccount(
                accountRepo.findById(request.getAccountId())
                        .orElseThrow(() -> new RuntimeException("Account not found"))
        );

        return employeeService.create(employee);
    }

    // ================= GET ALL =================
    @GetMapping
    public List<EmployeeInformation> getAll() {
        return employeeService.getAll();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public EmployeeInformation getById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public EmployeeInformation update(
            @PathVariable Integer id,
            @RequestBody EmployeeCreateRequest request) {

        EmployeeInformation employee = new EmployeeInformation();

        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setHireDate(request.getHireDate());

        // Department
        if (request.getDepartmentId() != null) {
            employee.setDepartment(
                    departmentRepo.findById(request.getDepartmentId())
                            .orElseThrow(() -> new RuntimeException("Department not found"))
            );
        }

        // Position
        employee.setPosition(
                positionRepo.findById(request.getPositionId())
                        .orElseThrow(() -> new RuntimeException("Position not found"))
        );

        // Account
        employee.setAccount(
                accountRepo.findById(request.getAccountId())
                        .orElseThrow(() -> new RuntimeException("Account not found"))
        );

        return employeeService.update(id, employee);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        employeeService.delete(id);
        return "Employee deleted successfully";
    }

    // ================= FILTER BY DEPARTMENT =================
    @GetMapping("/department/{departmentId}")
    public List<EmployeeInformation> getByDepartment(
            @PathVariable Integer departmentId) {
        return employeeService.getByDepartment(departmentId);
    }

    // ================= FIND BY EMAIL =================
    @GetMapping("/email")
    public EmployeeInformation getByEmail(
            @RequestParam String email) {
        return employeeService.findByEmail(email);
    }
}