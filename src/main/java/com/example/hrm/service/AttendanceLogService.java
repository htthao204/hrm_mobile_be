package com.example.hrm.service;

import com.example.hrm.entity.AttendanceLog;
import java.util.List;

public interface AttendanceLogService {

    List<AttendanceLog> getAll();

    AttendanceLog getById(Integer id);

    List<AttendanceLog> getByEmployee(Integer employeeId);

    AttendanceLog create(AttendanceLog attendanceLog);

    void delete(Integer id);
}
