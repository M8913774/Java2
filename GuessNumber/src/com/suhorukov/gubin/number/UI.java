package com.suhorukov.gubin.number;

import java.util.Scanner;

public class UI {

    public static void main(String[] args) {
        int num = 0;
        System.out.println("Hello, I'm game to guess the number. Let's play?");
        Alg number = new Alg();

        System.out.println("I guessed a number between 0 and 99, you've got 8 attempts. Guess.");
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < 8; i++) {
            try {
                num = in.nextInt();
            } catch (Exception e) {
                System.out.println("ERROR! Invalid format of input data.");
                in = new Scanner(System.in);
            }
                switch (number.compareRand(num)) {
                    case COINCIDENCE:
                        System.out.println("You guessed it, congratulations. Bye.");
                        System.exit(1);
                        break;
                    case OUTOFRANGE:
                        System.out.println("You have entered a number out of range.");
                        i--;
                        break;
                    case GREAT:
                        System.out.println("You do not have guessed. Your number is" +
                                " greater than the hidden number.");
                        break;
                    case LESS:
                        System.out.println("You do not have guessed. Your number is " +
                                "less than the hidden number.");
                        break;
                    case ERROR:
                        System.out.println("Unknown ERROR.");
                        i--;
                }
            System.out.println("You've got " + (7 - i) + " attempts.");
        }
        System.out.println("All attempts have been exhausted, it may be lucky next time. Bye.");
        System.out.println("The guessed number is: " + number.getRand());
    }
}

