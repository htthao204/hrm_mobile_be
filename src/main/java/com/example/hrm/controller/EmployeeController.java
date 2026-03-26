package com.example.hrm.controller;

import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeInformation> getAll() {
        return employeeService.getAll();
    }

    @PostMapping
    public EmployeeInformation create(@RequestBody EmployeeInformation emp) {
        return employeeService.create(emp);
    }

    @GetMapping("/{id}")
    public EmployeeInformation getById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }
}
