package com.example.hrm.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private Integer id;
    private String username;
    private String role;
    private String accessToken;
}