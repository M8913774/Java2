package com.suhorukov.gubin.calcannotation.commands.commands;

import com.suhorukov.gubin.calcannotation.commands.CResource;
import com.suhorukov.gubin.calcannotation.commands.Command;
import com.suhorukov.gubin.calcannotation.commands.Type;

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
