package com.sdh.springdemo.user.dao;

import com.sdh.springdemo.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

public class UserTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(UserDaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDaoJdbc dao = context.getBean("userDao" , UserDaoJdbc.class);

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
