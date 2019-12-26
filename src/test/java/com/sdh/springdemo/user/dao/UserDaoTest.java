package com.sdh.springdemo.user.dao;

import com.sdh.springdemo.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTest {
    User user1;
    User user2;
    User user3;
    UserDaoJdbc dao;
    @Autowired
    ApplicationContext context;
    @Before
    public void getUserDao() {


        dao = context.getBean("userDao", UserDaoJdbc.class);

        String name1 = "test1";
        String name2 = "test2";
        String name3 = "test3";
        this.user1 = new User(name1,name1,name1,1,0, User.Level.BASIC);
        this.user2 = new User(name2,name2,name2, 55, 10, User.Level.SILVER);
        this.user3 = new User(name3,name3,name3, 100,40, User.Level.GOLD);
    }

    @Test
    public void equalityFactory() {
        UserDaoFactory factory = new UserDaoFactory();
        UserDaoJdbc dao1 = factory.userDao();
        UserDaoJdbc dao2 = factory.userDao();

        System.out.println(dao1);
        System.out.println(dao2);
        System.out.println(dao1 == dao2);
    }

    @Test
    public void equalityContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(UserDaoFactory.class);
        UserDaoJdbc dao1 = ac.getBean("userDao", UserDaoJdbc.class);
        UserDaoJdbc dao2 = ac.getBean("userDao", UserDaoJdbc.class);
        System.out.println(dao1);
        System.out.println(dao2);
        System.out.println(dao1 == dao2);
    }

    private void verifyUser(User user1, User user2) {
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getLogin()).isEqualTo(user2.getLogin());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
        assertThat(user1.getRecommend()).isEqualTo(user2.getRecommend());
        assertThat(user1.getLevel()).isEqualTo(user2.getLevel());
    }



    @Test
    public void userAddRAndGet() throws SQLException, ClassNotFoundException {

        String test1 = "test1";
        String test2 = "test2";

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);



        User newUser = dao.get(test1);
        verifyUser(newUser, user1);
//        assertThat(newUser.getId()).isEqualTo(user1.getId());

        newUser = dao.get(test2);
        verifyUser(newUser, user2);
//        assertThat(newUser.getId()).isEqualTo(user2.getId());

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {

        String name = "test";
        List<User> users = Stream.of(user1,user2,user3).collect(Collectors.toList());

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(users.get(0));
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(users.get(1));
        assertThat(dao.getCount()).isEqualTo(2);
        dao.add(users.get(2));
        assertThat(dao.getCount()).isEqualTo(3);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {

        dao.deleteAll();

        assertThat(dao.getCount()).isEqualTo(0);

        User user = dao.get("unkown_id");

    }

    @Test
    public void updateTest() throws SQLException {
        dao.deleteAll();

        dao.add(user1);
        dao.add(user2);

        user1.setName("testt");
        user1.setLevel(User.Level.GOLD);

        dao.update(user1);

        User nu = dao.get(user1.getId());

        verifyUser(nu, user1);

        nu = dao.get(user2.getId());
        verifyUser(user2, nu);

    }

}
