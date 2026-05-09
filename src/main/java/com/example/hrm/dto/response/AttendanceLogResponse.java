package com.example.hrm.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AttendanceLogResponse {

    private Integer id;
    private Integer employeeId;
    private String employeeName;

    private LocalDateTime logTime;
    private String action;
    private String source;
    private String imageUrl;
    private Double latitude;

    private Double longitude;
}