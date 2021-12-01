package com.vlad.pp_313.services;

import com.vlad.pp_313.dao.RoleDao;
import com.vlad.pp_313.dao.UserDao;
import com.vlad.pp_313.model.Role;
import com.vlad.pp_313.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class UserService {
    private final UserDao dao;
    private final RoleDao roleDao;

    public UserService(UserDao dao, RoleDao roleDao) {
        this.dao = dao;
        this.roleDao = roleDao;
    }

    @Transactional
    public void save(User user) {
        dao.save(user);
    }

    @Transactional
    public List<User> index() {
        return dao.index();
    }

    @Transactional
    public User getUser(Long id) {
        return dao.getUser(id);
    }

    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }

    @Transactional
    public void update(User user) {
        dao.update(user);
    }

    @Transactional
    public User getUserByName(String name) {
        return dao.getUserByName(name);
    }

    @Transactional
    public boolean isAllowed(Long id, Principal principal) {
        return dao.isAllowed(id, principal);
    }

    @Transactional
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}

