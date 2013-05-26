package com.suhorukov.gubin.calcannotation.commandz.commandz;

import java.util.Stack;

class UserError {

    final boolean userError(Stack stack, String[] args, int commandLength, int stackSize) {

        if (args.length != commandLength) {
            System.out.println("You entered bad command, please reentered your command correctly.");
            return false;

        } else if (stack.size() >= stackSize) {
            return true;

        } else {
            System.out.println(
                    "Error: stack is not full! You made ​​a mistake entering the sequence of commandz.");
            return false;
        }
    }

}

