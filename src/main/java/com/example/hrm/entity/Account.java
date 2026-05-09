package com.example.hrm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // ===== SECURITY =====
    private Boolean active = true;

    // bắt đổi password lần đầu
    private Boolean firstLogin = true;

    // audit
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    @OneToOne(mappedBy = "account")
    private EmployeeInformation employee;

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}