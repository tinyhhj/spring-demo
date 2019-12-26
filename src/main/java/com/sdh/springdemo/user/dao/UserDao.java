package com.sdh.springdemo.user.dao;

import com.sdh.springdemo.user.domain.User;

import java.util.List;

public interface UserDao {
    User get(String id);
    List<User> getAll();
    int update(User user);
    int add(User user);
    int deleteAll();
}
