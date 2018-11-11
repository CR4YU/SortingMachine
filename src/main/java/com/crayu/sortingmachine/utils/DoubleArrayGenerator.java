package com.crayu.sortingmachine.utils;

import java.util.Random;

public class DoubleArrayGenerator extends ArrayGenerator {

    private final double MAX = Double.MAX_VALUE;
    private Random rand;

    public DoubleArrayGenerator() {
        rand = new Random();
    }

    @Override
    Comparable next() {
        return rand.nextDouble() * MAX;
    }

    @Override
    public String type() {
        return "double";
    }

}
