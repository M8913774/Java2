package com.suhorukov.gubin.calcannotation.commands.commands;

import com.suhorukov.gubin.calcannotation.commands.CResource;
import com.suhorukov.gubin.calcannotation.commands.Command;
import com.suhorukov.gubin.calcannotation.commands.Type;

import java.util.Stack;


public class Print extends UserError implements Command {
    @CResource(type = Type.STACK)
    private Stack<Double>stack;

    public void execute(String[] args) {

        if (userError(stack, args, 1, 1)) {
            try {

                System.out.println(stack.peek());

            } catch (Exception ex) {
                System.out.println("ERROR IN PRINT!");
                ex.printStackTrace();
            }
        }
    }
}
