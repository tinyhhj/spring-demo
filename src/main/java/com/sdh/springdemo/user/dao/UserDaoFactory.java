package com.sdh.springdemo.user.dao;

import sun.java2d.pipe.SpanShapeRenderer;

public class UserDaoFactory {
    UserDao userDao() {
        // db접속 방식을 담당하는 SimpleConnectionMaker를 결정해서 userDao를 생성하는 책임을 갖는다.
        SimpleConnectionMaker maker = new SimpleConnectionMaker();
        return new UserDao(maker);
    }
}
