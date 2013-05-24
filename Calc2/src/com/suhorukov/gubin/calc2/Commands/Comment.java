package com.suhorukov.gubin.calc2.Commands;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 18.04.13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public final class Comment implements Command {
    @Override
    public void execute(Stack<Double> stack, String[] args, Map<String, Double> defines) {
        System.out.println(Arrays.toString(args));
    }
}
