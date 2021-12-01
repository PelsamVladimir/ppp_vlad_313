package com.vlad.pp_313.dao;

import com.vlad.pp_313.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getAllRoles() {
        return em.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getRole(int id) {
        return em.find(Role.class, id);
    }

    @Override
    public Set<Role> createRolesSet(int index) {
        Set<Role> rolesSet = new HashSet<>();
        switch (index) {
            case 1:
                rolesSet.add(getRole(1));
                break;
            case 2:
                rolesSet.add(getRole(2));
                break;
            case 3:
                rolesSet.add(getRole(1));
                rolesSet.add(getRole(2));
                break;
            default:
        }
        return rolesSet;
    }
}
