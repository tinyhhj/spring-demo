package com.sdh.springdemo.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker implements ConnectionMaker{
    Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_demo","tinyhhj","admin");
    }

    @Override
    public Connection makeConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_demo","tinyhhj","admin");
    }
}
