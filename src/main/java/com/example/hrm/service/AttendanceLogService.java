package com.example.hrm.service;

import com.example.hrm.dto.request.AttendanceLogRequest;
import com.example.hrm.dto.response.AttendanceLogResponse;
import com.example.hrm.entity.AttendanceLog;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttendanceLogService {

    // create check-in / check-out
    AttendanceLogResponse createLog(
            AttendanceLogRequest request,
            MultipartFile image
    );

    List<AttendanceLogResponse> getAllLogs();

    List<AttendanceLogResponse> getLogsByEmployee(Integer employeeId);

    void deleteLog(Integer id);

}