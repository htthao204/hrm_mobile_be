package com.example.hrm.mapper;

import com.example.hrm.dto.response.RequestResponse;
import com.example.hrm.entity.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {

    public RequestResponse toDto(Request r) {

        RequestResponse dto = new RequestResponse();

        dto.id = r.getId();

        dto.employeeId = r.getEmployee().getId();
        dto.employeeName = r.getEmployee().getFullName();

        dto.requestTypeId = r.getRequestType().getId();
        dto.requestTypeCode = r.getRequestType().getCode().name(); // ✅ FIX
        dto.requestTypeName = r.getRequestType().getName();

        dto.startDate = r.getStartDate();
        dto.endDate = r.getEndDate();
        dto.reason = r.getReason();
        dto.status = r.getStatus().name();

        return dto;
    }
}