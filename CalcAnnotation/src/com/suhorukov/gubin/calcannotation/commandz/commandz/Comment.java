package com.suhorukov.gubin.calcannotation.commandz.commandz;

import com.suhorukov.gubin.calcannotation.commandz.Command;

import java.util.Arrays;


public final class Comment implements Command {

    public void execute(String[] args) {
        System.out.println(Arrays.toString(args));
    }
}
