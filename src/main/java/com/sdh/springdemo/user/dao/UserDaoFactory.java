package com.sdh.springdemo.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class UserDaoFactory {
    @Bean
    UserDaoJdbc userDao() {
        // db접속 방식을 담당하는 SimpleConnectionMaker를 결정해서 userDao를 생성하는 책임을 갖는다.
        UserDaoJdbc dao = new UserDaoJdbc();
        dao.setDataSource(dataSource());
        return dao;
    }

    @Bean
    DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_demo");
        dataSource.setUsername("tinyhhj");
        dataSource.setPassword("admin");
        return dataSource;
    }

    @Bean
    ConnectionMaker getConnection() {
        return new SimpleConnectionMaker();
    }
}
