package com.suhorukov.gubin.calcannotation.Commands.Commands;

import com.suhorukov.gubin.calcannotation.Commands.Command;
import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 18.04.13
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class Div extends UserError implements Command {
    @Override
    public void execute(Stack<Double> stack, String[] args, Map<String, Double> defines) {

        if (userError(stack, args, 1, 2)) {

            try {

                Double x1 = stack.pop();
                Double x2 = stack.pop();
                stack.push(x2 / x1);

            } catch (NumberFormatException ex) {
                System.out.println("ERROR DIV!");
                ex.printStackTrace();
            }
        }
    }
}
