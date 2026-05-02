package com.example.hrm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "requests")
@Getter
@Setter
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeInformation employee;

    @ManyToOne
    @JoinColumn(name = "request_type_id")
    private RequestType requestType;

    private LocalDate startDate;
    private LocalDate endDate;

    private LocalTime startTime;
    private LocalTime endTime;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @Column(columnDefinition = "TEXT")
    private String metadata;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime approvedAt;

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private EmployeeInformation approver;

    @Column(columnDefinition = "TEXT")
    private String rejectReason;
    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<RequestAttachment> attachments;
}