package com.example.hrm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee_bank_accounts")
@Getter
@Setter
public class EmployeeBankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeInformation employee;

    private String bankName;
    private String accountNumber;
    private String owner;
}
