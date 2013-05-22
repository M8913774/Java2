package com.suhorukov.gubin.calcannotation.Commands.Commands;

import com.suhorukov.gubin.calcannotation.Commands.CResource;
import com.suhorukov.gubin.calcannotation.Commands.Command;
import com.suhorukov.gubin.calcannotation.Commands.Type;

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
                System.out.println("First character of variables doesn't digit");

            } else {
                try {

                    defines.put(args[1], Double.parseDouble(args[2]));

                } catch (NumberFormatException ex) {
                    System.out.println("ERROR DEFINE!");
                    //ex.printStackTrace();
                }

            }

        }
    }
}
