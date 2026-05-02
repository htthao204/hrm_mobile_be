package com.example.hrm.controller;

import com.example.hrm.entity.EmployeePrivateInformation;
import com.example.hrm.service.EmployeePrivateInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/employee-private")
@RequiredArgsConstructor
public class EmployeePrivateInformationController {

    private final EmployeePrivateInformationService service;

    @GetMapping("/{employeeId}")
    public EmployeePrivateInformation get(
            @PathVariable Integer employeeId
    ) {
        return service.getByEmployeeId(employeeId);
    }

    // ✅ FIX HERE
    @PostMapping("/{employeeId}")
    public EmployeePrivateInformation create(
            @PathVariable Integer employeeId,
            @RequestBody EmployeePrivateInformation info
    ) {
        return service.create(employeeId, info);
    }

    @PutMapping("/{employeeId}")
    public EmployeePrivateInformation update(
            @PathVariable Integer employeeId,
            @RequestBody EmployeePrivateInformation info
    ) {
        return service.update(employeeId, info);
    }

    @DeleteMapping("/{employeeId}")
    public void delete(
            @PathVariable Integer employeeId
    ) {
        service.delete(employeeId);
    }
}