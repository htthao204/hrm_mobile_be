package com.example.hrm.entity;

import com.example.hrm.type.AttendanceAction;
import com.example.hrm.type.AttendanceSource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_logs")
@Getter
@Setter
public class AttendanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeInformation employee;

    private LocalDateTime logTime;

    @Enumerated(EnumType.STRING)
    private AttendanceAction action;

    @Enumerated(EnumType.STRING)
    private AttendanceSource source;
}