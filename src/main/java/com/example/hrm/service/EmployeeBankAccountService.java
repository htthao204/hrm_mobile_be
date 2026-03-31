package com.example.hrm.service;

import com.example.hrm.entity.EmployeeBankAccount;
import java.util.List;

public interface EmployeeBankAccountService {

    List<EmployeeBankAccount> getAll();

    List<EmployeeBankAccount> getByEmployeeId(Integer employeeId);

    EmployeeBankAccount getById(Integer id);

    EmployeeBankAccount create(EmployeeBankAccount bankAccount);

    EmployeeBankAccount update(Integer id, EmployeeBankAccount bankAccount);

    void delete(Integer id);
}