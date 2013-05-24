package com.suhorukov.gubin.calc2.Commands;

import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 18.04.13
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public interface Command {
    void execute(Stack<Double> stack, String[] args, Map<String,Double> defines);

}
