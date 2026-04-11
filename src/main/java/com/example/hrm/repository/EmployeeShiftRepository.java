package com.example.hrm.repository;

import com.example.hrm.entity.EmployeeShift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeShiftRepository
        extends JpaRepository<EmployeeShift, Long> {

    List<EmployeeShift> findByEmployee_Id(Integer employeeId);

    EmployeeShift findByEmployee_IdAndWorkDate(
            Integer employeeId,
            LocalDate workDate
    );

    // ⭐ FIXED
    List<EmployeeShift> findByEmployee_IdAndWorkDateBetween(
            Integer employeeId,
            LocalDate from,
            LocalDate to
    );
}