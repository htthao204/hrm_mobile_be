package com.example.hrm.service.impl;

import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.entity.EmployeeShift;
import com.example.hrm.entity.Shift;
import com.example.hrm.repository.EmployeeInformationRepository;
import com.example.hrm.repository.EmployeeShiftRepository;
import com.example.hrm.repository.ShiftRepository;
import com.example.hrm.service.EmployeeShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeShiftServiceImpl
        implements EmployeeShiftService {

    private final EmployeeShiftRepository employeeShiftRepository;
    private final EmployeeInformationRepository employeeRepository;
    private final ShiftRepository shiftRepository;

    @Override
    public EmployeeShift assignShift(
            Integer employeeId,
            Long shiftId,
            LocalDate workDate
    ) {

        EmployeeInformation employee =
                employeeRepository.findById(employeeId)
                        .orElseThrow(() ->
                                new RuntimeException("Employee not found"));

        Shift shift =
                shiftRepository.findById(shiftId)
                        .orElseThrow(() ->
                                new RuntimeException("Shift not found"));

        EmployeeShift employeeShift = new EmployeeShift();
        employeeShift.setEmployee(employee);
        employeeShift.setShift(shift);
        employeeShift.setWorkDate(workDate);

        return employeeShiftRepository.save(employeeShift);
    }

    @Override
    public List<EmployeeShift> getEmployeeSchedule(Integer employeeId) {
        return employeeShiftRepository.findByEmployee_Id(employeeId);
    }

    @Override
    public EmployeeShift getShiftOfDay(Integer employeeId, LocalDate date) {
        return employeeShiftRepository
                .findByEmployee_IdAndWorkDate(employeeId, date);
    }

    @Override
    public void delete(Long id) {
        employeeShiftRepository.deleteById(id);
    }
}