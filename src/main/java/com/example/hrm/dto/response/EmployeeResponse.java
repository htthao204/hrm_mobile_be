package com.example.hrm.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class EmployeeResponse {

    private Integer id;
    private String fullName;
    private String email;
    private String phone;

    private String departmentName;
    private String positionName;

    private LocalDate hireDate;

    private Integer accountId;
    private String username;

    private String avatarUrl;

    // NEW
    private String faceImageUrl;

    // private info
    private LocalDate dateOfBirth;
    private String gender;
    private String nationalId;
    private String country;
    private String address;
}