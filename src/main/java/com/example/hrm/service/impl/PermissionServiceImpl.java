package com.example.hrm.service.impl;

import com.example.hrm.entity.Permission;
import com.example.hrm.repository.PermissionRepository;
import com.example.hrm.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepo;

    @Override
    public List<Permission> getAll() {
        return permissionRepo.findAll();
    }

    @Override
    public Permission getById(Integer id) {
        return permissionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Quyền (Permission) không tồn tại với ID: " + id));
    }

    @Override
    public Permission create(Permission permission) {
        // Bạn có thể thêm logic kiểm tra trùng mã quyền (permission code) tại đây nếu cần
        return permissionRepo.save(permission);
    }

    @Override
    public void delete(Integer id) {
        Permission permission = getById(id);
        permissionRepo.delete(permission);
    }
}