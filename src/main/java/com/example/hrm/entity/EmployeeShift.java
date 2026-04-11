package com.example.hrm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employee_shifts")
@Getter
@Setter
public class EmployeeShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nhân viên
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeInformation employee;

    // ca làm việc
    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;

    // ngày áp dụng
    private LocalDate workDate;

    // override (nếu đổi ca riêng)
    private Boolean isOverride = false;

    // ghi chú
    private String note;
}