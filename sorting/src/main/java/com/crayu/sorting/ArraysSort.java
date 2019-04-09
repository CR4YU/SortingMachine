package com.crayu.sorting;

import java.util.Arrays;

class ArraysSort implements Sorter {

    ArraysSort() {
    }

    @Override
    public void sort(Comparable[] array) {
        Arrays.sort(array);
    }

}
