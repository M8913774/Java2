package com.suhorukov.gubin.calcannotation.commandz.commandz;

import com.suhorukov.gubin.calcannotation.commandz.CResource;
import com.suhorukov.gubin.calcannotation.commandz.Command;
import com.suhorukov.gubin.calcannotation.commandz.Type;

import java.util.Stack;

public class Sqrt extends UserError implements Command {
    @CResource(type = Type.STACK)
    private Stack<Double> stack;

    public void execute(String[] args) {

        if (userError(stack, args, 1, 1)) {
            try {

                stack.push(Math.sqrt(stack.pop()));

            } catch (NumberFormatException ex) {
                System.out.println("ERROR IN SQRT!");
                ex.printStackTrace();
            }
        }
    }
}
