package com.suhorukov.gubin.calcannotation.commands.commands;

import com.suhorukov.gubin.calcannotation.commands.Command;

import java.util.Arrays;


public final class Comment implements Command {

    public void execute(String[] args) {
        System.out.println(Arrays.toString(args));
    }
}
