package com.example.hrm.service.impl;

import com.example.hrm.entity.Attendance;
import com.example.hrm.entity.AttendanceLog;
import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.repository.AttendanceLogRepository;
import com.example.hrm.repository.AttendanceRepository;
import com.example.hrm.repository.EmployeeInformationRepository;
import com.example.hrm.service.AttendanceService;
import com.example.hrm.type.AttendanceAction;
import com.example.hrm.type.AttendanceSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepo;
    private final AttendanceLogRepository logRepo;
    private final EmployeeInformationRepository employeeRepo;

    @Override
    public void checkIn(Integer employeeId) {
        EmployeeInformation emp = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        AttendanceLog log = new AttendanceLog();
        log.setEmployee(emp);
        log.setLogTime(LocalDateTime.now());
        log.setAction(AttendanceAction.CHECKIN);
        log.setSource(AttendanceSource.FACE_RECOGNITION);

        logRepo.save(log);
    }

    @Override
    public void checkOut(Integer employeeId) {
        EmployeeInformation emp = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        AttendanceLog log = new AttendanceLog();
        log.setEmployee(emp);
        log.setLogTime(LocalDateTime.now());
        log.setAction(AttendanceAction.CHECKOUT);
        log.setSource(AttendanceSource.FACE_RECOGNITION);

        logRepo.save(log);

        Attendance attendance = new Attendance();
        attendance.setEmployee(emp);
        attendance.setDate(LocalDate.now());
        attendance.setWorkingHours(8.0);

        attendanceRepo.save(attendance);
    }

    @Override
    public List<Attendance> getByEmployee(Integer employeeId) {
        return attendanceRepo.findByEmployee_Id(employeeId);
    }

    @Override
    public List<AttendanceLog> getLogs(Integer employeeId) {
        return logRepo.findByEmployee_Id(employeeId);
    }
}
