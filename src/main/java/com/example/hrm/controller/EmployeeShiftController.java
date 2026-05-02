package com.example.hrm.controller;

import com.example.hrm.entity.EmployeeShift;
import com.example.hrm.service.EmployeeShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee-shifts")
@RequiredArgsConstructor
public class EmployeeShiftController {

    private final EmployeeShiftService employeeShiftService;

    @PostMapping
    public EmployeeShift assignShift(
            @RequestParam Integer employeeId,
            @RequestParam Long shiftId,
            @RequestParam String workDate
    ) {
        return employeeShiftService.assignShift(
                employeeId,
                shiftId,
                LocalDate.parse(workDate)
        );
    }

    @GetMapping("/{employeeId}")
    public List<EmployeeShift> getSchedule(
            @PathVariable Integer employeeId
    ) {
        return employeeShiftService.getEmployeeSchedule(employeeId);
    }
}