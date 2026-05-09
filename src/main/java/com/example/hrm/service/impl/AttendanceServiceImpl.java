package com.example.hrm.service.impl;
import com.example.hrm.entity.Attendance;
import com.example.hrm.entity.AttendanceLog;
import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.repository.AttendanceLogRepository;
import com.example.hrm.repository.EmployeeInformationRepository;
import com.example.hrm.service.AttendanceService;
import com.example.hrm.type.AttendanceAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceLogRepository logRepo;
    private final EmployeeInformationRepository employeeRepo;

    // ======================================================
    // TODAY ATTENDANCE
    // ======================================================
    @Override
    public Attendance getTodayAttendance(Integer employeeId) {
        return buildAttendance(employeeId, LocalDate.now());
    }

    // ======================================================
    // BY DATE
    // ======================================================
    @Override
    public Attendance getByEmployeeAndDate(Integer employeeId, LocalDate date) {
        return buildAttendance(employeeId, date);
    }

    // ======================================================
    // HISTORY (list days from logs)
    // ======================================================
    @Override
    public List<Attendance> getByEmployee(Integer employeeId) {

        List<LocalDate> dates = logRepo.findByEmployee_Id(employeeId)
                .stream()
                .map(l -> l.getLogTime().toLocalDate())
                .distinct()
                .toList();

        return dates.stream()
                .map(date -> buildAttendance(employeeId, date))
                .toList();
    }

    // ======================================================
    // CORE: BUILD FROM LOGS
    // ======================================================
    private Attendance buildAttendance(Integer employeeId, LocalDate date) {

        EmployeeInformation emp = getEmployee(employeeId);

        List<AttendanceLog> logs = logRepo.findByEmployee_Id(employeeId)
                .stream()
                .filter(l -> l.getLogTime().toLocalDate().equals(date))
                .toList();

        AttendanceLog firstCheckIn = logs.stream()
                .filter(l -> l.getAction() == AttendanceAction.CHECK_IN)
                .min(Comparator.comparing(AttendanceLog::getLogTime))
                .orElse(null);

        AttendanceLog lastCheckOut = logs.stream()
                .filter(l -> l.getAction() == AttendanceAction.CHECK_OUT)
                .max(Comparator.comparing(AttendanceLog::getLogTime))
                .orElse(null);

        Attendance attendance = new Attendance();
        attendance.setEmployee(emp);
        attendance.setDate(date);

        LocalDateTime checkInTime = null;
        LocalDateTime checkOutTime = null;

        if (firstCheckIn != null) {
            checkInTime = firstCheckIn.getLogTime();
            attendance.setCheckInTime(checkInTime);
        }

        if (lastCheckOut != null) {
            checkOutTime = lastCheckOut.getLogTime();
            attendance.setCheckOutTime(checkOutTime);
        }

        // =========================
        // WORKING HOURS
        // =========================
        if (checkInTime != null && checkOutTime != null) {
            long minutes = Duration.between(checkInTime, checkOutTime).toMinutes();
            attendance.setWorkingHours(minutes / 60.0);
        } else {
            attendance.setWorkingHours(0.0);
        }

        // =========================
        // STATUS
        // =========================
        if (checkInTime == null) {
            attendance.setStatus("ABSENT");

        } else if (isLate(checkInTime)) {
            attendance.setStatus("LATE");

        } else {
            attendance.setStatus("PRESENT");
        }

        return attendance;
    }

    // ======================================================
    // HELPER
    // ======================================================
    private EmployeeInformation getEmployee(Integer employeeId) {
        return employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    private boolean isLate(LocalDateTime checkInTime) {
        return checkInTime.toLocalTime().isAfter(java.time.LocalTime.of(8, 30));
    }
}