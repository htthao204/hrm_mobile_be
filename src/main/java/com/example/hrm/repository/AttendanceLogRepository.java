package com.example.hrm.repository;

import com.example.hrm.entity.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceLogRepository
        extends JpaRepository<AttendanceLog, Integer> {

    List<AttendanceLog> findByEmployee_Id(Integer employeeId);
}