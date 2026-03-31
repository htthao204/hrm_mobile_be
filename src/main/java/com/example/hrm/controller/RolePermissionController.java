package com.example.hrm.controller;

import com.example.hrm.entity.RolePermission;
import com.example.hrm.entity.RolePermissionId;
import com.example.hrm.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role-permissions")
@RequiredArgsConstructor
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    @GetMapping
    public List<RolePermission> getAll() {
        return rolePermissionService.getAll();
    }

    @GetMapping("/role/{roleId}")
    public List<RolePermission> getByRole(@PathVariable Integer roleId) {
        return rolePermissionService.getByRoleId(roleId);
    }

    // =========================
    // Assign permission to role
    // =========================
    @PostMapping
    public RolePermission assignPermission(
            @RequestBody RolePermission rolePermission
    ) {
        return rolePermissionService.assignPermission(rolePermission);
    }

    // =========================
    // Remove one permission from role
    // =========================
    @DeleteMapping
    public void removePermission(@RequestBody RolePermissionId id) {
        rolePermissionService.removePermission(id);
    }

    // =========================
    // Remove all permissions of role
    // =========================
    @DeleteMapping("/role/{roleId}")
    public void removeAllByRole(@PathVariable Integer roleId) {
        rolePermissionService.removeAllPermissionsByRole(roleId);
    }
}