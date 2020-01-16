package com.user.service.impl;

import com.user.controller.model.request.ReqModelCreateNewUser;
import com.user.dao.UserDao;
import com.user.dao.entity.User;
import com.user.service.UserManageService;
import com.user.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserManageServiceImpl extends UUID implements UserManageService {


    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ReqModelCreateNewUser createNewUser(ReqModelCreateNewUser reqModelCreateNewUser) {
        System.out.println("reqModelCreateNewUser = " + reqModelCreateNewUser);
        reqModelCreateNewUser.setPassword(passwordEncoder.encode(reqModelCreateNewUser.getPassword()));
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(reqModelCreateNewUser, User.class);

        System.out.println("user " + user);
        User save = userDao.save(user);
        ReqModelCreateNewUser createNewUser = mapper.map(save, ReqModelCreateNewUser.class);
        return createNewUser;
    }
}
