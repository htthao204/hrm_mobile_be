package com.example.hrm.dto.request;

import com.example.hrm.type.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeePrivateInformationRequest {

    private Integer employeeId;

    private LocalDate dateOfBirth;
    private Gender gender;
    private String nationalId;

    private Integer countryId;

    private String address;
}