package com.example.hrm.controller;

import com.example.hrm.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/check-in/{employeeId}")
    public String checkIn(@PathVariable Integer employeeId) {
        attendanceService.checkIn(employeeId);
        return "Checked in";
    }

    @PostMapping("/check-out/{employeeId}")
    public String checkOut(@PathVariable Integer employeeId) {
        attendanceService.checkOut(employeeId);
        return "Checked out";
    }
}
