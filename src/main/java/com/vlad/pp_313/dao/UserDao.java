package com.vlad.pp_313.dao;

import com.vlad.pp_313.model.User;

import java.security.Principal;
import java.util.List;

public interface UserDao {
    void save(User user);

    List<User> index();

    User getUser(Long id);

    void delete(Long id);

    void update(User user);

    User getUserByName(String name);

    boolean isAllowed(Long id, Principal principal);
}
