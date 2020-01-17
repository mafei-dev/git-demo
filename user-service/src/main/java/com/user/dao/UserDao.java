package com.user.dao;

import com.user.dao.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, String> {
    User findByUsername(String username);

}
