package com.example.hrm.repository;

import com.example.hrm.entity.EmployeeBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeBankAccountRepository
        extends JpaRepository<EmployeeBankAccount, Integer> {

    List<EmployeeBankAccount> findByEmployee_Id(Integer employeeId);
}
