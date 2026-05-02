package com.example.hrm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "holidays")
@Getter
@Setter
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean isGlobal = true;
    private Boolean isPaid = true;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private HolidayType type;

    @OneToMany(
            mappedBy = "holiday",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<HolidayTimeline> timelines;
}