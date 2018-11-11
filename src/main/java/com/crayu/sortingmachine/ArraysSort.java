package com.crayu.sortingmachine;

import java.util.Arrays;

public class ArraysSort implements SortingAlgorithm {

    @Override
    public void sort(Comparable[] array) {
        Arrays.sort(array);
    }

    @Override
    public String name() {
        return "Arrays.sort()";
    }
}
