package com.example.hrm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "request_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RequestTypeCode code;

    @Column(nullable = false)
    private String name;
}