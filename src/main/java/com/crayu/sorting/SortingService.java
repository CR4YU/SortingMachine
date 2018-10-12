package com.crayu.sorting;

public class SortingService{

    public static SortingAlgorithm DEFAULT_SORTING_ALGORITHM = new ForkJoinMergeSort();

    private static SortingAlgorithm sortingAlgorithm = DEFAULT_SORTING_ALGORITHM;

    public static void sort(Comparable[] array, SortingAlgorithm algorithm) {
        algorithm.sort(array);
    }

    public static void sort(Comparable[] array) {
        sortingAlgorithm.sort(array);
    }

    public static void setSortingAlgorithm(SortingAlgorithm algorithm) {
        sortingAlgorithm = algorithm;
    }
}
