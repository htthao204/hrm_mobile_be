package com.example.hrm.service;
import com.example.hrm.entity.Attendance;
import com.example.hrm.entity.AttendanceLog;

import java.util.List;
public interface AttendanceService {

    void checkIn(Integer employeeId);

    void checkOut(Integer employeeId);

    List<Attendance> getByEmployee(Integer employeeId);

    List<AttendanceLog> getLogs(Integer employeeId);
}
