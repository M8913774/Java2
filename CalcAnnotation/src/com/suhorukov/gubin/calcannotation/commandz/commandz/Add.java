package com.suhorukov.gubin.calcannotation.commandz.commandz;

import com.suhorukov.gubin.calcannotation.commandz.CResource;
import com.suhorukov.gubin.calcannotation.commandz.Command;
import com.suhorukov.gubin.calcannotation.commandz.Type;

import java.util.Stack;


public final class Add extends UserError implements Command {
    @CResource(type = Type.STACK)
    private Stack<Double> stack;

    public void execute(String[] args) {
        if (userError(stack, args, 1, 2)) {
            try {

                Double x1 = stack.pop();
                Double x2 = stack.pop();
                stack.push((x1 + x2));

            } catch (NumberFormatException ex) {
                System.out.println("ERROR IN PLUS!");
                ex.printStackTrace();
            }
        }

    }
}
