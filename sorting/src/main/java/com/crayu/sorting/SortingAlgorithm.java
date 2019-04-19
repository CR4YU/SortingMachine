package com.crayu.sorting;

import java.util.function.Supplier;

public enum SortingAlgorithm {

    FORK_JOIN_MERGE_SORT(ForkJoinMergeSort::new, "Fork Join Merge Sort"),
    INSERTION_SORT(InsertionSort::new, "Insertion Sort"),
    BUBBLE_SORT(BubbleSort::new, "Bubble Sort"),
    ARRAYS_SORT(ArraysSort::new, "Arrays.sort()"),
    MERGE_SORT(MergeSort::new, "Merge Sort");

    private final Supplier<Sorter> algorithm;
    private final String name;

    SortingAlgorithm(Supplier<Sorter> algorithm, String name) {
        this.algorithm = algorithm;
        this.name = name;
    }

    Sorter createInstance() {
        return algorithm.get();
    }

    public String toString() {
        return name;
    }
}
