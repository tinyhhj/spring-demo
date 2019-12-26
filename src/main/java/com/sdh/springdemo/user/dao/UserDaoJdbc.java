package com.sdh.springdemo.user.dao;

import com.sdh.springdemo.user.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao{

    JdbcTemplate template;
    DataSource dataSource ;

    public void setDataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public int add(User user) {
//        Class.forName("com.mysql.cj.jdbc.Driver");

        return template.update("insert into users(id, name, password, level,login,recommend) values(?,?,?,?,?,?)",
                user.getId(),user.getName(),user.getPassword(),user.getLevel().intValue(),user.getLogin(),user.getRecommend());
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_demo", "tinyhhj", "admin");
    }

    public User get(String id) {
//        Class.forName("com.mysql.cj.jdbc.Driver");

        return template.query("select id,name,password,level,login,recommend from users where id = ?",user->{
            if( user.next()) {
                return new User(user.getString("id")
                        ,user.getString("name")
                        ,user.getString("password")
                        ,user.getInt("login")
                        ,user.getInt("recommend")
                        , User.Level.valueOf(user.getInt("level"))
                );
            }
            return null;
        }, id);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        template.query("select id, name , password, level, login, recommend from users",(RowCallbackHandler) user->{
            users.add(new User(user.getString("id")
                    ,user.getString("name")
                    ,user.getString("password")
                    ,user.getInt("login")
                    ,user.getInt("recommend")
                    , User.Level.valueOf(user.getInt("level"))
            ));
        });
        return users;
    }

    public int deleteAll() {
        return template.update("delete from users");
    }

    public int getCount() throws SQLException {
        return template.queryForObject("select count(*) from users",Integer.class);
    }

    public int update(User user1) {
        return template.update("update users set name = ?, password = ? , level =?, login = ? , recommend = ? where id = ?"
        ,user1.getName()
        ,user1.getPassword()
        ,user1.getLevel().intValue()
        ,user1.getLogin()
        ,user1.getRecommend()
                ,user1.getId()
        );
    }
}
