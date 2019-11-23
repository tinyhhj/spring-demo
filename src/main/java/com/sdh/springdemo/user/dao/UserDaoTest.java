package com.sdh.springdemo.user.dao;

import com.sdh.springdemo.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker cm = new SimpleConnectionMaker();
        UserDao dao = new UserDao(cm);

        User user = new User();
        user.setId("whiteship");
        user.setName("박기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공!");

        User user2 = dao.get(user.getId());

        System.out.println(user2.getId() + " 등록 성공!");
    }
}
