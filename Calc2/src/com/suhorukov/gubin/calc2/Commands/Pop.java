package com.suhorukov.gubin.calc2.Commands;

import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 06.05.13
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class Pop extends UserError implements Command {
    @Override
    public void execute(Stack<Double> stack, String[] args, Map<String, Double> defines) {

        if (userError(stack, args, 1, 0)) {
            try {

                Double pop = stack.pop();

            } catch (NumberFormatException ex) {
                System.out.println("ERROR POP!");
                ex.printStackTrace();
            }
        }
    }
}

