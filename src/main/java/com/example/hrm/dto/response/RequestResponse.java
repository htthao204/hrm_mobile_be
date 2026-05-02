package com.example.hrm.dto.response;

import java.time.LocalDate;

public class RequestResponse {

    public Integer id;

    public Integer employeeId;
    public String employeeName;

    public Integer requestTypeId;
    public String requestTypeCode;   // ✅ FIX
    public String requestTypeName;

    public LocalDate startDate;
    public LocalDate endDate;
    public String reason;
    public String status;
}