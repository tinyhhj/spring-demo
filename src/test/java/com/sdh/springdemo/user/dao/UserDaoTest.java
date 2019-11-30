package com.sdh.springdemo.user.dao;

import com.sdh.springdemo.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserDaoTest {
    @Test
    public void equalityFactory() {
        UserDaoFactory factory = new UserDaoFactory();
        UserDao dao1 = factory.userDao();
        UserDao dao2 = factory.userDao();

        System.out.println(dao1);
        System.out.println(dao2);
        System.out.println(dao1 == dao2);
    }

    @Test
    public void equalityContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(UserDaoFactory.class);
        UserDao dao1 = ac.getBean("userDao", UserDao.class);
        UserDao dao2 = ac.getBean("userDao", UserDao.class);
        System.out.println(dao1);
        System.out.println(dao2);
        System.out.println(dao1 == dao2);
    }



    @Test
    public void userAddAndGet() throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean("userDao", UserDao.class);
        User user = new User();
        String test1 = "테스트1";
        String test2 = "test2";
        user.setName(test1);
        user.setPassword(test1);
        user.setId(test1);
        User user2 = new User(test2,test2,test2);


        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);



        User newUser = dao.get(test1);
        assertThat(newUser.getId()).isEqualTo(user.getId());

        newUser = dao.get(test2);
        assertThat(newUser.getId()).isEqualTo(user2.getId());

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao userDao = ac.getBean("userDao", UserDao.class);
        String name = "test";
        List<User> users = new ArrayList();
        for( int i = 0 ; i < 3 ; i++) {
            users.add(new User(name+i,name+i,name+i));
        }

        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        userDao.add(users.get(0));
        assertThat(userDao.getCount()).isEqualTo(1);

        userDao.add(users.get(1));
        assertThat(userDao.getCount()).isEqualTo(2);
        userDao.add(users.get(2));
        assertThat(userDao.getCount()).isEqualTo(3);

    }

}
