package com.example.hrm.dto.request;

import com.example.hrm.entity.Country;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeCreateRequest {

    // ===== Thông tin cơ bản =====
    private String fullName;
    private String email;
    private String phone;

    // ===== Thông tin cá nhân =====
    private LocalDate dateOfBirth;
    private String gender;        // MALE / FEMALE / OTHER
    private String nationalId;    // CCCD/CMND
    private Country country;
    private String address;
    // ===== Công việc =====
    private Integer departmentId;
    private Integer positionId;
    private Integer accountId;

    private LocalDate hireDate;

    // ===== Thông tin bổ sung (HRM thực tế hay có) =====
    private String maritalStatus; // SINGLE / MARRIED
    private String emergencyContact;
    private String emergencyPhone;

    // ===== Thông tin hợp đồng =====
    private String employmentType; // FULL_TIME / PART_TIME / INTERN
}