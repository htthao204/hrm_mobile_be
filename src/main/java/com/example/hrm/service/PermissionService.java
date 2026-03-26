package com.example.hrm.service;

import com.example.hrm.entity.Permission;
import java.util.List;
public interface PermissionService {

    List<Permission> getAll();

    Permission getById(Integer id);

    Permission create(Permission permission);

    void delete(Integer id);
}
