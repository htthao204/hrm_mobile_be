package com.example.hrm.service;
import com.example.hrm.entity.Attendance;
import com.example.hrm.entity.AttendanceLog;

import java.time.LocalDate;
import java.util.List;

import com.example.hrm.entity.Attendance;
import com.example.hrm.entity.AttendanceLog;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    Attendance getTodayAttendance(Integer employeeId);

    Attendance getByEmployeeAndDate(Integer employeeId, LocalDate date);

    List<Attendance> getByEmployee(Integer employeeId);
}