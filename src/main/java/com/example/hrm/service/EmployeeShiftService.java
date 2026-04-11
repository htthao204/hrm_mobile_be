package com.example.hrm.service;

import com.example.hrm.entity.EmployeeShift;

import java.time.LocalDate;
import java.util.List;
public interface EmployeeShiftService {

    EmployeeShift assignShift(
            Integer employeeId,
            Long shiftId,
            LocalDate workDate
    );

    List<EmployeeShift> getEmployeeSchedule(Integer employeeId);

    EmployeeShift getShiftOfDay(
            Integer employeeId,
            LocalDate date
    );

    void delete(Long id);
}