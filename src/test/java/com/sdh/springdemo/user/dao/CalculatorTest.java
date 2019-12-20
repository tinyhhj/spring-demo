package com.sdh.springdemo.user.dao;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {
    Calculator cal;
    String url;
    @Before
    public void init() {
        cal = new Calculator();
        url = getClass().getResource("/input.txt").getPath();
    }
    @Test
    public void test() throws IOException {

        int res = cal.calc(url, new LineCallback() {
            @Override
            public int doOperation(String line, int res) {
                return res + Integer.valueOf(line);
            }

        }, 0);
        assertThat(res).isEqualTo(29);
    }

    @Test
    public void test2() {
        int res = cal.calc(url, new LineCallback() {
            @Override
            public int doOperation(String line, int res) {
                return res * Integer.valueOf(line);
            }
        }, 1);
        assertThat(res).isEqualTo(2400);
    }




    interface CalcOperation {
        int doOperation(BufferedReader br) throws IOException;
    }

    interface LineCallback {
        int doOperation(String line, int res);
    }
}
