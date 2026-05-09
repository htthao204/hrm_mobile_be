package com.example.hrm.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component
public class SecurityUtil {

    public Integer getCurrentEmployeeId() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserPrincipal user) {
            return user.getEmployeeId();
        }

        throw new RuntimeException(
                "Principal is not CustomUserPrincipal. Actual type: "
                        + principal.getClass().getName()
        );
    }
}