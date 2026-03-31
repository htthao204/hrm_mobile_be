package com.example.hrm.service;

import com.example.hrm.entity.RolePermission;
import com.example.hrm.entity.RolePermissionId;
import java.util.List;

public interface RolePermissionService {

    List<RolePermission> getAll();

    List<RolePermission> getByRoleId(Integer roleId);

    RolePermission assignPermission(RolePermission rolePermission);

    void removePermission(RolePermissionId id);

    void removeAllPermissionsByRole(Integer roleId);
}
