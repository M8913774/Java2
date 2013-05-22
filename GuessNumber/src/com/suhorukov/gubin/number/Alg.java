package com.suhorukov.gubin.number;

import java.util.Random;

public class Alg {

    private int rand = 100;
    private Random random = new Random();
    int number = random.nextInt(rand);
    public int getRand() {
        return number;
    }
    public enum Code { OUTOFRANGE, COINCIDENCE, LESS, GREAT, ERROR }

    public Code compareRand(int a) {
        if ((a >= rand) || (a < 0)) {
            return Code.OUTOFRANGE;
        } else if (a == number) {
            return Code.COINCIDENCE;
        } else if (a > number && a < rand) {
            return Code.GREAT;
        } else if (a < number && a > 0) {
            return Code.LESS;
        } else return Code.ERROR;
    }
}

