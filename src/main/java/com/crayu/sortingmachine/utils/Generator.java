package com.crayu.sortingmachine.utils;

interface Generator {

    Comparable[] generate(int size);
    String type();
}