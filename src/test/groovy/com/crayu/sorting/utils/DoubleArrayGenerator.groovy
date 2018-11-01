package com.crayu.sorting.utils

class DoubleArrayGenerator implements Generator {

    private final double MAX = Double.MAX_VALUE
    private Random rand

    DoubleArrayGenerator() {
        rand = new Random()
    }

    @Override
    Comparable[] generate(int size) {
        def array = []
        (1..size).each {array << rand.nextDouble() * MAX}
        return array
    }

    @Override
    String type() {
        "double"
    }
}
