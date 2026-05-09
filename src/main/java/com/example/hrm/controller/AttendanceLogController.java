package com.example.hrm.controller;

import com.example.hrm.dto.request.AttendanceLogRequest;
import com.example.hrm.entity.AttendanceLog;
import com.example.hrm.service.AttendanceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.hrm.dto.response.AttendanceLogResponse;
import com.example.hrm.service.AttendanceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/attendance-logs")
@RequiredArgsConstructor
public class AttendanceLogController {

    private final AttendanceLogService attendanceLogService;

    // ================= CHECK IN / OUT =================
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AttendanceLogResponse> createLog(

            @RequestPart("request")
            AttendanceLogRequest request,

            @RequestPart("image")
            MultipartFile image
    ) {

        return ResponseEntity.ok(
                attendanceLogService.createLog(request, image)
        );
    }

    // ================= ADMIN GET ALL =================
    @GetMapping
    public ResponseEntity<List<AttendanceLogResponse>> getAll() {

        return ResponseEntity.ok(
                attendanceLogService.getAllLogs()
        );
    }

    // ================= GET BY EMPLOYEE =================
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AttendanceLogResponse>> getByEmployee(
            @PathVariable Integer employeeId
    ) {

        return ResponseEntity.ok(
                attendanceLogService.getLogsByEmployee(employeeId)
        );
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {

        attendanceLogService.deleteLog(id);

        return ResponseEntity.noContent().build();
    }
}