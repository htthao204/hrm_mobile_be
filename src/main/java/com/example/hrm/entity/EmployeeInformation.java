package com.example.hrm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_information")
@Getter
@Setter
public class EmployeeInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(unique = true)
    private String email;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}