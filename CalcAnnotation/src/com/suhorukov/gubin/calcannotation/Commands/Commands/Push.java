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
public class Push extends UserError implements Command {
    public void execute(Stack<Double> stack, String[] args, Map<String, Double> defines) {

        if (userError(stack, args, 2, 0)) {
            try {

                if (defines.containsKey(args[1])) {
                    stack.push(defines.get(args[1]));

                } else {
                    stack.push(Double.parseDouble(args[1]));
                }

            } catch (NumberFormatException ex) {
                System.out.println("ERROR PUSH!");
                ex.printStackTrace();
            }
        }
    }
}
