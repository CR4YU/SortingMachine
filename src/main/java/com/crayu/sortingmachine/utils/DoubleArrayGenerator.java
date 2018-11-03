package com.crayu.sortingmachine.utils;

import java.util.Random;

public class DoubleArrayGenerator implements Generator {

    private final double MAX = Double.MAX_VALUE;
    private Random rand;

    public DoubleArrayGenerator() {
        rand = new Random();
    }

    @Override
    public Comparable[] generate(int size) {
        Comparable[] result = new Comparable[size];
        while (size-- > 0) {
            result[size] = rand.nextDouble() * MAX;
        }
        return result;
    }

    @Override
    public String type() {
        return "double";
    }

}
