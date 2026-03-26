package com.example.hrm.entity;

import com.example.hrm.type.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employee_private_information")
@Getter
@Setter
public class EmployeePrivateInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeInformation employee;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String nationalId;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String address;
}
