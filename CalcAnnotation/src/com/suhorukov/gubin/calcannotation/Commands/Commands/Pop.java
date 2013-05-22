package com.suhorukov.gubin.calcannotation.Commands.Commands;

import com.suhorukov.gubin.calcannotation.Commands.Command;
import java.util.Map;
import java.util.Stack;

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

