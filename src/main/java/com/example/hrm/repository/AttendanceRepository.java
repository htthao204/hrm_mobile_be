package com.example.hrm.repository;

import com.example.hrm.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByEmployee_Id(Integer employeeId);
}