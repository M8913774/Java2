package com.suhorukov.gubin.calcannotation.Commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.reflect.Proxy.*;


public class StartFactory {
    private Stack<Double> stack = new Stack<>();
    Factory factoryObject;

    public StartFactory() {
        Map<String, Double> defines = new HashMap<>();
        factoryObject = new Factory(stack, defines);
    }

    public Stack<Double> getStack() {
        return stack;
    }

    public static void main(String[] args) {

        StartFactory calcObject = new StartFactory();

        InputStream in = System.in;
        if(args.length>0){
            try{
                in = new FileInputStream(args[0]);
            }catch (FileNotFoundException e) {
                System.out.println("File not found, type the commands below:");
            }
        }

        try(Scanner scanner = new Scanner(in)) {
            System.out.println("Available operators:");
            System.out.println(calcObject.factoryObject.getCmdNames());
            System.out.println();

            String nextLine;
            while (scanner.hasNextLine()) {
                nextLine = scanner.nextLine();
                try{calcObject.input(nextLine);
                } catch(Exception e){
                    System.out.println("Command not found.");
                    System.exit(0);
                }
            }
        }
    }

    public void input(String nextLine) {

        String[] values = nextLine.split(" ");
        Command cmd = (Command) Proxy.newProxyInstance(Command.class.getClassLoader(), new Class[]{Command.class},
                new Handler(factoryObject.classesMap.get(values[0])));
        cmd.execute(values);
    }
}