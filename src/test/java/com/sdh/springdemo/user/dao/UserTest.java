package com.sdh.springdemo.user.dao;

import com.sdh.springdemo.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;


public class UserTest {

    User user;
    @Before
    public void init() {
        user = new User();
    }

    @Test
    public void upgradLevel() {
        User.Level[] levels = User.Level.values();
        for( User.Level lv : levels) {
            if( lv.nextLevel() == null) continue;
            user.setLevel(lv);
            user.upgradLevel();
            assertThat(user.getLevel()).isEqualTo(lv.nextLevel());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullUpgradLevel() {
        User.Level[] levels = User.Level.values();
        for( User.Level lv : levels) {
            if( lv.nextLevel() != null) continue;
            user.setLevel(lv);
            user.upgradLevel();
        }
    }
}
