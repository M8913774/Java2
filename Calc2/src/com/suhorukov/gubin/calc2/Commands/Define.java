package com.suhorukov.gubin.calc2.Commands;

import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 18.04.13
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public final class Define extends UserError implements Command {
    @Override
    public void execute(Stack<Double> stack, String[] args, Map<String, Double> defines) {

        if (userError(stack, args, 3, 0)) {

            if (Character.isDigit(args[1].charAt(0))) {
                System.out.println("First character of variables doesn't digit");

            } else {
                try {

                    defines.put(args[1], Double.parseDouble(args[2]));

                } catch (NumberFormatException ex) {
                    System.out.println("ERROR DEFINE!");
                    ex.printStackTrace();
                }

            }

        }
    }
}
