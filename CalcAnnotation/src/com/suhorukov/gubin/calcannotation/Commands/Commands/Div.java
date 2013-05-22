package com.suhorukov.gubin.calcannotation.Commands.Commands;

import com.suhorukov.gubin.calcannotation.Commands.CResource;
import com.suhorukov.gubin.calcannotation.Commands.Command;
import com.suhorukov.gubin.calcannotation.Commands.Type;

import java.util.Stack;

public class Div extends UserError implements Command {

    @CResource(type = Type.STACK)
    private Stack<Double> stack;

    public void execute(String[] args) {

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
