package com.example.hrm.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class RequestCreateRequest {
    public Integer employeeId;
    public Integer requestTypeId;

    public LocalDate startDate;
    public LocalDate endDate;

    public LocalTime startTime;
    public LocalTime endTime;

    public String reason;
    public String metadata;
}