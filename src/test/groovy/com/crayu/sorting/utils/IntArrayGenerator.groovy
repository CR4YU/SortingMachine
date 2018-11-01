package com.crayu.sorting.utils

class IntArrayGenerator implements Generator {

    private final int MAX = Integer.MAX_VALUE
    private Random rand

    IntArrayGenerator() {
        rand = new Random()
    }

    @Override
    Comparable[] generate(int size) {
        def array = []
        (1..size).each {array << rand.nextInt(MAX)}
        return array
    }

    @Override
    String type() {
        "int"
    }
}
