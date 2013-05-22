package com.suhorukov.gubin.calcannotation.Commands;

import com.suhorukov.gubin.calcannotation.Commands.Commands.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class Start {

    static Stack<Double> stack = new Stack<>();
    static Map<String, Command> map = new HashMap<String, Command>();
    static Map<String, Double> variables = new HashMap<String, Double>();
    static Scanner str = new Scanner(System.in);

    /*void Start() {
        map.put("PUSH", new Push());
        map.put("POP", new Pop());
        map.put("+", new Add());
        map.put("-", new Minus());
        map.put("*", new Multiply());
        map.put("/", new Div());
        map.put("SQRT", new Sqrt());
        map.put("PRINT", new Print());
        map.put("#", new Comment());
        map.put("DEFINE", new Define());
    }*/

    public Stack<Double> getStack() {
        return stack;
    }

    public static void main(String[] args) {


        Properties properties = new Properties();
        try (InputStream in = Start.class.getResourceAsStream(args[1])){
            properties.load(in);
        } catch (IOException e) {
            System.out.println("File 'properties' not found." );
        }





        /*try {
            File file = new File(args[0]);
            str = new Scanner(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            String string = str.nextLine();
            String[] s = string.split(" ");

            if (s[0].equals("EXIT")) {
                break;

            } else if (!map.containsKey(s[0])) {
                System.out.println("You entered bad command, please reentered your command correctly.");

            } else {
                map.get(s[0]).execute(stack, s, variables);
            }

            if (!str.hasNextLine()) {
                str = new Scanner(System.in);
            }

        } */

        }
    }
