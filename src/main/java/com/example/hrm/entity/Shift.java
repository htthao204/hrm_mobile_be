package com.example.hrm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "shifts")
@Getter
@Setter
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MORNING, NIGHT...
    @Column(unique = true)
    private String code;

    private String name;

    private LocalTime startTime;
    private LocalTime endTime;

    private LocalTime breakStart;
    private LocalTime breakEnd;

    // tổng giờ công
    private Double workingHours;

    // cho phép đi trễ (phút)
    private Integer allowLateMinutes;

    // cho phép về sớm
    private Integer allowEarlyLeaveMinutes;

    private Boolean isActive = true;
}