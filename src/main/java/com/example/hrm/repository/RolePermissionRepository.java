package com.example.hrm.repository;

import com.example.hrm.entity.RolePermission;
import com.example.hrm.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository
        extends JpaRepository<RolePermission, RolePermissionId> {

    List<RolePermission> findByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);
}
