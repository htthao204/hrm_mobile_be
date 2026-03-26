package com.example.hrm.controller;

import com.example.hrm.entity.Account;
import com.example.hrm.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;

    @PostMapping("/login")
    public Account login(@RequestBody Account request) {
        Account acc = accountService.findByUsername(request.getUsername());

        if (!acc.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return acc;
    }
}
