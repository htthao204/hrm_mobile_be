package com.example.hrm.security;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class CustomUserPrincipal extends User {

    private final Integer employeeId;

    public CustomUserPrincipal(
            String username,
            String password,
            Integer employeeId
    ) {
        super(username, password, Collections.emptyList());
        this.employeeId = employeeId;
    }
}