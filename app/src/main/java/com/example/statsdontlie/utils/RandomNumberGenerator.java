package com.example.statsdontlie.utils;

import java.util.Random;

public final class RandomNumberGenerator {

    private static int random1;

    private RandomNumberGenerator() {}

    public static int getRandomNumber1() {
        Random random = new Random();
        random1 = random.nextInt(24);
        return random1;
    }

    public static int getRandomNumber2() {
        Random random = new Random();
        int random2 = random.nextInt(24);
        while (random2 == random1) random2 = random.nextInt(24);
        return random2;
    }

    public static int getRandomNumber() {
        Random random = new Random();
        random1 = random.nextInt(6);
        return random1;
    }
}
