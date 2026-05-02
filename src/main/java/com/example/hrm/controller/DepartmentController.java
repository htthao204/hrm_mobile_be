package com.example.hrm.controller;

import com.example.hrm.dto.request.DepartmentRequest;
import com.example.hrm.entity.Department;
import com.example.hrm.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // =========================
    // Get all departments
    // =========================
    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    // =========================
    // Get department by id
    // =========================
    @GetMapping("/{id}")
    public Department getById(@PathVariable Integer id) {
        return departmentService.getById(id);
    }

    // =========================
    // Create department
    // =========================
    @PostMapping
    public Department create(@RequestBody DepartmentRequest request) {
        return departmentService.create(request);
    }

    // =========================
    // Update department ✅ FIX
    // =========================
    @PutMapping("/{id}")
    public Department update(
            @PathVariable Integer id,
            @RequestBody DepartmentRequest request
    ) {
        return departmentService.update(id, request);
    }

    // =========================
    // Delete department
    // =========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        departmentService.delete(id);
    }
}