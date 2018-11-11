package com.crayu.sortingmachine.utils;

import java.util.Random;

public class IntArrayGenerator extends ArrayGenerator {

    private final int MAX = Integer.MAX_VALUE;
    private Random rand;

    public IntArrayGenerator() {
        rand = new Random();
    }

    @Override
    Comparable next() {
        return rand.nextInt(MAX);
    }

    @Override
    public String type() {
        return "int";
    }

}
