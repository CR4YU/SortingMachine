package com.crayu.sorting;

import java.util.Collection;

@SuppressWarnings("unchecked")
public class SortingService {

    private static SortingAlgorithm DEFAULT_SORTING_ALGORITHM = SortingAlgorithm.FORK_JOIN_MERGE_SORT;

    private Sorter sorter;

    public SortingService() {
        sorter = DEFAULT_SORTING_ALGORITHM.createInstance();
    }

    public SortingService(SortingAlgorithm algorithm) {
        this.sorter = algorithm.createInstance();
    }

    public <T extends Comparable<? super T>> void sort(T[] array) {
        sorter.sort(array);
    }

    public <T extends Comparable<? super T>> void sort(Collection<T> collection) {
        Comparable[] array = collection.toArray(new Comparable[0]);
        sort(array);
        collection.clear();
        for (Comparable i : array) {
            collection.add((T)i);
        }
    }

    public <T extends Comparable<? super T>> long sortAndGetTimeNano(T[] array) {
        long start = System.nanoTime();
        sort(array);
        long end = System.nanoTime();
        return end - start;
    }

    public <T extends Comparable<? super T>> long sortAndGetTimeNano(Collection<T> collection) {
        Comparable[] array = collection.toArray(new Comparable[0]);
        long start = System.currentTimeMillis();
        sort(array);
        long end = System.currentTimeMillis();
        collection.clear();
        for (Comparable i : array) {
            collection.add((T)i);
        }
        return end - start;
    }

    public void setSortingAlgorithm(SortingAlgorithm algorithm) {
        sorter = algorithm.createInstance();
    }
}
