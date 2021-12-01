package com.vlad.pp_313.services;

import com.vlad.pp_313.dao.UserDao;
import com.vlad.pp_313.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao dao;

    public UserDetailsServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = dao.getUserByName(name);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User name '%s' not found", name));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                user.getAuthorities());
    }
}
