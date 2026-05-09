package com.example.hrm.dto.request;

import com.example.hrm.type.AttendanceAction;
import com.example.hrm.type.AttendanceSource;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AttendanceLogRequest {

    @NotNull
    private Integer employeeId;

    @NotNull
    private AttendanceAction action;

    @NotNull
    private AttendanceSource source;

    private LocalDateTime logTime;
    private Double latitude;

    private Double longitude;
}
