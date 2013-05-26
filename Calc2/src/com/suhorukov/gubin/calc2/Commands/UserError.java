package com.suhorukov.gubin.calc2.Commands;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 11.05.13
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
class UserError {

    final boolean userError(Stack<Double> stack, String[] args, int commandLength, int stackSize) {

        if (args.length != commandLength) {
            System.out.println("You entered bad command, please reentered your command correctly.");
            return false;

        } else if (stack.size() >= stackSize) {
            return true;

        } else {
            System.out.println(
                  "Error: stack is not full! You made ​​a mistake entering the sequence of commands.");
            return false;
        }
    }

}

