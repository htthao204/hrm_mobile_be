package com.example.hrm.controller;

import com.example.hrm.dto.request.EmployeeCreateRequest;
import com.example.hrm.dto.response.EmployeeResponse;
import com.example.hrm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeInformationController {

    private final EmployeeService employeeService;

    // CREATE
    @PostMapping
    public EmployeeResponse create(
            @RequestBody EmployeeCreateRequest request) {
        return employeeService.create(request);
    }

    // GET ALL
    @GetMapping
    public List<EmployeeResponse> getAll() {
        return employeeService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public EmployeeResponse update(
            @PathVariable Integer id,
            @RequestBody EmployeeCreateRequest request) {
        return employeeService.update(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        employeeService.delete(id);
        return "Employee deleted successfully";
    }

    // FILTER
    @GetMapping("/department/{departmentId}")
    public List<EmployeeResponse> getByDepartment(
            @PathVariable Integer departmentId) {
        return employeeService.getByDepartment(departmentId);
    }

    // FIND EMAIL
    @GetMapping("/email")
    public EmployeeResponse getByEmail(
            @RequestParam String email) {
        return employeeService.findByEmail(email);
    }
}