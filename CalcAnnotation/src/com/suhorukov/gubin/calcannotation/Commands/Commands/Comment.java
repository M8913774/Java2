package com.suhorukov.gubin.calcannotation.Commands.Commands;

import com.suhorukov.gubin.calcannotation.Commands.Command;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;


public final class Comment implements Command {

    public void execute(String[] args) {
        System.out.println(Arrays.toString(args));
    }
}
