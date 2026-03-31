package com.example.hrm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequest {

    private String name;
    private Integer managerId;
}