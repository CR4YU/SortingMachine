package com.crayu.sortingmachine.utils;

import java.util.Random;

public class IntArrayGenerator implements Generator {

    private final int MAX = Integer.MAX_VALUE;
    private Random rand;

    public IntArrayGenerator() {
        rand = new Random();
    }

    @Override
    public Comparable[] generate(int size) {
        Comparable[] result = new Comparable[size];
        while (size-- > 0) {
            result[size] = rand.nextInt(MAX);
        }
        return result;
    }

    @Override
    public String type() {
        return "int";
    }

}
