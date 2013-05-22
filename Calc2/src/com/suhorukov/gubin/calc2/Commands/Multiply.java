package com.suhorukov.gubin.calc2.Commands;

import com.suhorukov.gubin.calc2.Command;

import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 06.05.13
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
public class Multiply extends UserError implements Command {
    @Override
    public void execute(Stack<Double> stack, String[] args, Map<String, Double> defines) {

        if (userError(stack, args, 1, 2)) {
            try {

                Double x1 = stack.pop();
                Double x2 = stack.pop();
                stack.push(x1 * x2);

            } catch (NumberFormatException ex) {
                System.out.println("ERROR MULTIPLY!");
                ex.printStackTrace();
            }
        }
    }
}
