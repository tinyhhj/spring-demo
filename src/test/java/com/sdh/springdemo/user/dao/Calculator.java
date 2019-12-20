package com.sdh.springdemo.user.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {


    public int calc(String filepath, CalculatorTest.LineCallback calcOperation, int initVal)  {
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(filepath));
            String line = null;
            int res = initVal;
            while((line = reader.readLine()) != null) {
                res = calcOperation.doOperation(line, res);
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
