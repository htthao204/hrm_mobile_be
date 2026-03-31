package com.example.hrm.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeInformationRequest {

    private String fullName;
    private String email;
    private String phone;

    private Integer departmentId;
    private Integer positionId;
    private Integer accountId;

    private LocalDate hireDate;
}