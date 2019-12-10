package com.sdh.springdemo.user.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class JUnitTest {

    @Autowired
    ApplicationContext ac;
    public static Set<ApplicationContext> context = new HashSet<>();
    public static Set<JUnitTest> obj = new HashSet<>();
    @Test
    public void test1() {
        assertThat(obj.add(this)).isTrue();
        assertThat(context.isEmpty() || !context.add(ac)).isTrue();

    }

    @Test
    public void test2() {
        assertThat(obj.add(this)).isTrue();
        assertThat(context.isEmpty() || !context.add(ac)).isTrue();
    }

    @Test
    public void test3() {
        assertThat(obj.add(this)).isTrue();
        assertThat(context.isEmpty() || !context.add(ac)).isTrue();
    }
}
