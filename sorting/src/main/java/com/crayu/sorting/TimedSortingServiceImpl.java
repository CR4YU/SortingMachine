package com.crayu.sorting;

import java.util.Collection;

class TimedSortingServiceImpl implements TimedSortingService {

    private Sorter sorter;

    public TimedSortingServiceImpl() {
        sorter = DEFAULT_SORTING_ALGORITHM.createInstance();
    }

    public TimedSortingServiceImpl(SortingAlgorithm algorithm) {
        this.sorter = algorithm.createInstance();
    }

    @Override
    public <T extends Comparable<? super T>> long sortAndGetTimeNano(T[] array) {
        if (array.length < 2) return 0;
        long start = System.nanoTime();
        sorter.sort(array);
        long end = System.nanoTime();
        return end - start;
    }

    @Override
    public <T extends Comparable<? super T>> long sortAndGetTimeNano(Collection<T> collection) {
        if (collection.size() < 2) return 0;
        Comparable[] array = collection.toArray(new Comparable[0]);
        long start = System.nanoTime();
        sorter.sort(array);
        long end = System.nanoTime();
        collection.clear();
        for (Comparable i : array) {
            collection.add((T)i);
        }
        return end - start;
    }

    @Override
    public void setSortingAlgorithm(SortingAlgorithm algorithm) {
        sorter = algorithm.createInstance();
    }
}
