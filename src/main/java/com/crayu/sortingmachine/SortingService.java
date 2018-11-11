package com.crayu.sortingmachine;

public class SortingService{

    public static SortingAlgorithm DEFAULT_SORTING_ALGORITHM = new ForkJoinMergeSort();

    private static SortingAlgorithm sortingAlgorithm = DEFAULT_SORTING_ALGORITHM;

    private SortingService() {
        throw new AssertionError();
    }

    public static void sort(Comparable[] array) {
        sortingAlgorithm.sort(array);
    }

    public static long sortAndGetTimeMillis(Comparable[] array) {
        long start = System.currentTimeMillis();
        sortingAlgorithm.sort(array);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void setSortingAlgorithm(SortingAlgorithm algorithm) {
        sortingAlgorithm = algorithm;
    }
}
