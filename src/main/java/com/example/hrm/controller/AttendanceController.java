package com.example.hrm.controller;

import com.example.hrm.entity.Attendance;
import com.example.hrm.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // ======================================================
    // TODAY ATTENDANCE
    // ======================================================
    @GetMapping("/today/{employeeId}")
    public Attendance getTodayAttendance(@PathVariable Integer employeeId) {
        return attendanceService.getTodayAttendance(employeeId);
    }

    // ======================================================
    // ATTENDANCE BY DATE
    // ======================================================
    @GetMapping("/{employeeId}/{date}")
    public Attendance getByDate(
            @PathVariable Integer employeeId,
            @PathVariable String date
    ) {
        return attendanceService.getByEmployeeAndDate(
                employeeId,
                LocalDate.parse(date)
        );
    }

    // ======================================================
    // HISTORY
    // ======================================================
    @GetMapping("/{employeeId}")
    public List<Attendance> getHistory(@PathVariable Integer employeeId) {
        return attendanceService.getByEmployee(employeeId);
    }
}