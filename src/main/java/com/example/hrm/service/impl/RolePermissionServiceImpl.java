package com.example.hrm.service.impl;

import com.example.hrm.entity.RolePermission;
import com.example.hrm.entity.RolePermissionId;
import com.example.hrm.repository.RolePermissionRepository;
import com.example.hrm.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepo;

    @Override
    public List<RolePermission> getAll() {
        return rolePermissionRepo.findAll();
    }

    @Override
    public List<RolePermission> getByRoleId(Integer roleId) {
        return rolePermissionRepo.findByRoleId(roleId);
    }

    @Override
    public RolePermission assignPermission(RolePermission rolePermission) {
        // Lưu hoặc cập nhật quyền cho Role
        return rolePermissionRepo.save(rolePermission);
    }

    @Override
    public void removePermission(RolePermissionId id) {
        if (!rolePermissionRepo.existsById(id)) {
            throw new RuntimeException("Không tìm thấy liên kết Role-Permission này.");
        }
        rolePermissionRepo.deleteById(id);
    }

    @Override
    @Transactional // Cần có @Transactional khi thực hiện xóa hàng loạt (deleteBy...)
    public void removeAllPermissionsByRole(Integer roleId) {
        rolePermissionRepo.deleteByRoleId(roleId);
    }
}