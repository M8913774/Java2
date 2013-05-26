package com.suhorukov.gubin.calcannotation.commandz;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Stack;

import static org.junit.Assert.assertEquals;


public class StartFactoryTest {
    StartFactory test = new StartFactory();

    @Test
    public void test() throws Exception {
        test.input("DEFINE A 1");
        test.input("DEFINE B -2");
        test.input("DEFINE C -3");
        test.input("DEFINE B2 2");
        test.input("PUSH B2");
        test.input("PUSH B");
        test.input("PUSH B");
        test.input("*");
        test.input("PUSH 4");
        test.input("PUSH A");
        test.input("PUSH C");
        test.input("*");
        test.input("*");
        test.input("-");
        test.input("SQRT");
        test.input("-");
        test.input("PUSH 2");
        test.input("PUSH A");
        test.input("*");
        test.input("/");
        test.input("PRINT");

        double expected = -1.0;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Stack<Double> stack = test.getStack();
        byte[] b = stack.peek().toString().getBytes();

        try {
            outputStream.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        double result = Double.parseDouble(outputStream.toString());
        assertEquals(expected, result, 0.000001);
    }
}
