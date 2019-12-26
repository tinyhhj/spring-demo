package com.sdh.springdemo.user.dao;

import com.sdh.springdemo.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserServicetest {
    @Autowired
    UserService userService;
    List<User> users;
    @Autowired
    UserDao dao;
    @Before
    public void init() {
        users = Arrays.asList(
                new User("test1","test1","test1",49, 0, User.Level.BASIC),
                new User("test2","test1","test1",50, 0, User.Level.BASIC),
                new User("test3","test1","test1",50, 29, User.Level.SILVER),
                new User("test4","test1","test1",50, 30, User.Level.SILVER),
                new User("test5","test1","test1",50, 30, User.Level.GOLD)
        );
    }

    @Test
    public void upgradeLevelTest() {
        dao.deleteAll();
        for(User user : users) dao.add(user);
        userService.upgradeLevels();

        checkLevel(users.get(0).getId(), User.Level.BASIC);
        checkLevel(users.get(1).getId(), User.Level.SILVER);
        checkLevel(users.get(2).getId(), User.Level.SILVER);
        checkLevel(users.get(3).getId(), User.Level.GOLD);
        checkLevel(users.get(4).getId(), User.Level.GOLD);

    }

    @Test
    public void add() {
        dao.deleteAll();

        userService.add(users.get(4));
        users.get(0).setLevel(null);
        userService.add(users.get(0));

        User user1 = dao.get(users.get(4).getId());
        User user2 = dao.get(users.get(0).getId());

        assertThat(user1.getLevel()).isEqualTo(User.Level.GOLD);
        assertThat(user2.getLevel()).isEqualTo(User.Level.BASIC);
    }

    private void checkLevel(String id, User.Level lv) {
        User user = dao.get(id);
        assertThat(user.getLevel()).isEqualTo(lv);
    }

}
