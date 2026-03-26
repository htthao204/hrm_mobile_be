package com.example.hrm.service;
import com.example.hrm.entity.Role;

import java.util.List;
public interface RoleService {

    List<Role> getAll();

    Role getById(Integer id);

    Role getByName(String name);

    Role create(Role role);

    Role update(Integer id, Role role);

    void delete(Integer id);
}
