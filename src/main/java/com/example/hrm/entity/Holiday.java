package com.example.hrm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "holidays")
@Getter
@Setter
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tên ngày lễ
    private String name;

    // Ngày nghỉ
    private LocalDate holidayDate;

    // Nghỉ toàn công ty ?
    private Boolean isGlobal = true;

    // Có hưởng lương không
    private Boolean isPaid = true;

    // Ghi chú
    @Column(columnDefinition = "TEXT")
    private String description;
}
