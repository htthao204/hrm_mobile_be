package com.example.hrm.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private Integer id;
    private String username;
    private Integer employeeId;
    private String role;

    private Boolean firstLogin;

    private String accessToken;
    private String refreshToken;
}