package com.suhorukov.gubin.calcannotation.Commands.Commands;

import com.suhorukov.gubin.calcannotation.Commands.Command;
import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 18.04.13
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class Sqrt extends UserError implements Command {
    @Override
    public void execute(Stack<Double> stack, String[] args, Map<String, Double> defines) {

        if (userError(stack, args, 1, 1)) {
            try {

                stack.push(Math.sqrt(stack.pop()));

            } catch (NumberFormatException ex) {
                System.out.println("ERROR SQRT!");
                ex.printStackTrace();
            }
        }
    }
}
