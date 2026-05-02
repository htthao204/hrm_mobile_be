package com.example.hrm.service;

import com.example.hrm.dto.response.LoginResponse;
import com.example.hrm.entity.Account;

import java.util.List;

public interface AccountService {

    Account findByUsername(String username);

    Account create(Account account);

    List<Account> getAll();

    void delete(Integer id);

    LoginResponse login(String username, String password);
    void changePassword(String username, String oldPassword, String newPassword);
}