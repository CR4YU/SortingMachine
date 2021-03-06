package com.crayu.generator;

public abstract class ArrayGenerator {

    public Comparable[] generate(int size) {
        if(size < 0)
            throw new IllegalArgumentException("Size cannot be less than 0");

        Comparable[] result = new Comparable[size];
        while (size-- > 0) {
            result[size] = next();
        }
        return result;
    }

    abstract String type();

    abstract Comparable next();

}