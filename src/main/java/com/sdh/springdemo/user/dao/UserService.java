package com.sdh.springdemo.user.dao;

import com.sdh.springdemo.user.domain.User;

import java.util.List;

public class UserService {
    UserDao dao;

    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    public void upgradeLevels() {
        List<User> users = dao.getAll();
        for( User user : users) {
            if( canLevelUp(user)) {
                upgradeLevel(user);
            }
        }
    }

    private void upgradeLevel(User user) {
        user.upgradLevel();
        dao.update(user);
    }

    private boolean canLevelUp(User user) {
        User.Level level = user.getLevel();
        switch(level) {
            case BASIC: return user.getLogin() >=50;
            case SILVER: return user.getRecommend() >= 30;
            case GOLD: return false;
            default: throw new IllegalArgumentException("unknown level: " + level);
        }
    }

    public void add(User user) {
        if(user.getLevel() == null) {
            user.setLevel(User.Level.BASIC);
        }
        dao.add(user);
    }
}
