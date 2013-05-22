package com.suhorukov.gubin.calc2.Commands;

import com.suhorukov.gubin.calc2.Command;

import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 18.04.13
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class Print extends UserError implements Command {
    @Override
    public void execute(Stack<Double> stack, String[] args, Map<String, Double> defines) {

        if (userError(stack, args, 1, 1)) {
            try {

                System.out.println(stack.peek());

            } catch (Exception ex) {
                System.out.println("ERROR PRINT!");
                ex.printStackTrace();
            }
        }
    }
}
