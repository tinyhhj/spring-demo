package com.sdh.springdemo.user.dao;

import com.sdh.springdemo.user.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUserDao extends AbstractUserDao {
    @Override
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_demo", "tinyhhj", "admin");
    }
}
