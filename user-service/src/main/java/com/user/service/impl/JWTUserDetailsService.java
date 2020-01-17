package com.user.service.impl;

import com.user.dao.UserDao;
import com.user.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("JWTUSerDetailsService.loadUserByUsername");
        User byUsername = userDao.findByUsername(username);
        org.springframework.security.core.userdetails.User
                user = new org.springframework.security.core.userdetails.User(
                byUsername.getUsername(),
                byUsername.getPassword(),
                new ArrayList<>());
        return user;
    }
}
