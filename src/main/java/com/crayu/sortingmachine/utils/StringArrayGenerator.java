package com.crayu.sortingmachine.utils;

import java.util.Random;

public class StringArrayGenerator extends ArrayGenerator {

    private final int WORD_SIZE = 5;
    private Random random;

    public StringArrayGenerator() {
        random = new Random();
    }

    @Override
    Comparable next() {
        return randomWord();
    }

    private String randomWord() {
        StringBuilder b = new StringBuilder(WORD_SIZE);
        for(int i = 0; i < WORD_SIZE; i++) {
            b.append(randomLetter());
        }
        return b.toString();
    }

    private char randomLetter() {
        return (char)(random.nextInt(26) + 'a');
    }

    @Override
    public String type() {
        return "string";
    }
}
