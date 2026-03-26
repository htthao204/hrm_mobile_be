package com.example.hrm.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class RolePermissionId implements Serializable {
    private Integer roleId;
    private Integer permissionId;
}