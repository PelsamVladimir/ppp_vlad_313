package com.vlad.pp_313.dao;

import com.vlad.pp_313.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> getAllRoles();

    Role getRole(int id);

    Set<Role> createRolesSet(int index);
}
