package com.sdh.springdemo.user.domain;

public class User {
    String id;
    String name;
    String password;

    int login;
    int recommend;
    Level level;

    public User(String id, String name, String password, int login, int recommend, Level level) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.login = login;
        this.recommend = recommend;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public User() {
    }

    public enum Level {
        GOLD(3,null),SILVER(2,GOLD),BASIC(1,SILVER);
        private final int lv;
        private final Level next;
        Level(int lv, Level next) {
            this.lv = lv;this.next = next;
        }
        public Level nextLevel() { return next;}

        public int intValue() {
            return lv;
        }

        public static Level valueOf(int value) {
            switch(value) {
                case 1: return BASIC;
                case 2: return SILVER;
                case 3: return GOLD;
                default: throw new AssertionError("unknown value");
            }
        }
    }

    public void upgradLevel() {
        if( this.level == Level.GOLD) {
            throw new IllegalArgumentException("cannot upgrade level");
        }
        setLevel(this.level.nextLevel());
    }
}

