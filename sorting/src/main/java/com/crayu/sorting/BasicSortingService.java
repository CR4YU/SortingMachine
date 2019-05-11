package com.crayu.sorting;

import java.util.Collection;

public interface BasicSortingService extends SortingService {

    <T extends Comparable<? super T>> void sort(T[] array);
    <T extends Comparable<? super T>> void sort(Collection<T> collection);
}
