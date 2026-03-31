package com.example.hrm.controller;

import com.example.hrm.dto.request.LoginRequest;
import com.example.hrm.dto.response.LoginResponse;
import com.example.hrm.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return accountService.login(
                request.getUsername(),
                request.getPassword()
        );
    }
}