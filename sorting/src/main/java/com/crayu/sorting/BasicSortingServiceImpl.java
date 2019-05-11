package com.crayu.sorting;

import java.util.Collection;

class BasicSortingServiceImpl implements BasicSortingService {

    private Sorter sorter;

    public BasicSortingServiceImpl() {
        sorter = DEFAULT_SORTING_ALGORITHM.createInstance();
    }

    public BasicSortingServiceImpl(SortingAlgorithm algorithm) {
        this.sorter = algorithm.createInstance();
    }

    @Override
    public <T extends Comparable<? super T>> void sort(T[] array) {
        if (array.length < 2) return;
        sorter.sort(array);
    }

    @Override
    public <T extends Comparable<? super T>> void sort(Collection<T> collection) {
        if (collection.size() < 2) return;
        Comparable[] array = collection.toArray(new Comparable[0]);
        sorter.sort(array);
        collection.clear();
        for (Comparable i : array) {
            collection.add((T)i);
        }
    }

    @Override
    public void setSortingAlgorithm(SortingAlgorithm algorithm) {
        sorter = algorithm.createInstance();
    }
}
