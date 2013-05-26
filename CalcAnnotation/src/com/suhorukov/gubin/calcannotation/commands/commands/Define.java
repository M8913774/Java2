package com.suhorukov.gubin.calcannotation.commands.commands;

import com.suhorukov.gubin.calcannotation.commands.CResource;
import com.suhorukov.gubin.calcannotation.commands.Command;
import com.suhorukov.gubin.calcannotation.commands.Type;

import java.util.Map;
import java.util.Stack;

public final class Define extends UserError implements Command {
    @CResource(type = Type.STACK)
    private Stack<Double> stack;
    @CResource(type = Type.DEFINE)
    private Map<String, Double> defines;

    public void execute(String[] args) {

        if (userError(stack, args, 3, 0)) {

            if (Character.isDigit(args[1].charAt(0))) {
                System.out.println("Isn't a digit");

            } else {
                try {

                    defines.put(args[1], Double.parseDouble(args[2]));

                } catch (NumberFormatException ex) {
                    System.out.println("ERROR IN DEFINE!");
                    //ex.printStackTrace();
                }

            }

        }
    }
}
