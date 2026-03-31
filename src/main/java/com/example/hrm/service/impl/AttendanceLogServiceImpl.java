package com.example.hrm.service.impl;

import com.example.hrm.entity.AttendanceLog;
import com.example.hrm.repository.AttendanceLogRepository;
import com.example.hrm.service.AttendanceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceLogServiceImpl implements AttendanceLogService {

    private final AttendanceLogRepository attendanceLogRepo;

    @Override
    public List<AttendanceLog> getAll() {
        return attendanceLogRepo.findAll();
    }

    @Override
    public AttendanceLog getById(Integer id) {
        return attendanceLogRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhật ký chấm công với ID: " + id));
    }

    @Override
    public List<AttendanceLog> getByEmployee(Integer employeeId) {
        return attendanceLogRepo.findByEmployee_Id(employeeId);
    }

    @Override
    public AttendanceLog create(AttendanceLog attendanceLog) {
        // Bạn có thể thêm logic kiểm tra thời gian check-in hợp lệ tại đây
        return attendanceLogRepo.save(attendanceLog);
    }

    @Override
    public void delete(Integer id) {
        AttendanceLog log = getById(id);
        attendanceLogRepo.delete(log);
    }
}