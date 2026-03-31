package com.example.hrm.service.impl;

import com.example.hrm.entity.Role;
import com.example.hrm.repository.RoleRepository;
import com.example.hrm.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    @Override
    public List<Role> getAll() {
        return roleRepo.findAll();
    }

    @Override
    public Role getById(Integer id) {
        return roleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Role không tồn tại với ID: " + id));
    }

    @Override
    public Role getByName(String name) {
        return roleRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role không tồn tại với tên: " + name));
    }

    @Override
    public Role create(Role role) {
        // Kiểm tra nếu tên Role đã tồn tại thì báo lỗi
        if (roleRepo.existsByName(role.getName())) {
            throw new RuntimeException("Tên vai trò '" + role.getName() + "' đã tồn tại!");
        }
        return roleRepo.save(role);
    }

    @Override
    public Role update(Integer id, Role roleDetails) {
        Role role = getById(id);

        // Cập nhật các trường thông tin
        role.setName(roleDetails.getName());
        // role.setDescription(roleDetails.getDescription()); // Nếu có trường này

        return roleRepo.save(role);
    }

    @Override
    public void delete(Integer id) {
        Role role = getById(id);
        roleRepo.delete(role);
    }
}