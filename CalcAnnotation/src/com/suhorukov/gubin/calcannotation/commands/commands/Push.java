package com.suhorukov.gubin.calcannotation.commands.commands;

import com.suhorukov.gubin.calcannotation.commands.CResource;
import com.suhorukov.gubin.calcannotation.commands.Command;
import com.suhorukov.gubin.calcannotation.commands.Type;

import java.util.Map;
import java.util.Stack;


public class Push extends UserError implements Command {
    @CResource(type = Type.STACK)
    private Stack<Double> stack;
    @CResource(type = Type.DEFINE)
    private Map<String, Double> defines;

    public void execute(String[] args) {

        if (userError(stack, args, 2, 0)) {
            try {

                if (defines.containsKey(args[1])) {
                    stack.push(defines.get(args[1]));

                } else {
                    stack.push(Double.parseDouble(args[1]));
                }

            } catch (NumberFormatException ex) {
                System.out.println("ERROR IN PUSH!");
                ex.printStackTrace();
            }
        }
    }
}
