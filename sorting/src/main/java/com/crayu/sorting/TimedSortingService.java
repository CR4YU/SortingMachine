package com.crayu.sorting;

import java.util.Collection;

public interface TimedSortingService extends SortingService {

    <T extends Comparable<? super T>> long sortAndGetTimeNano(T[] array);
    <T extends Comparable<? super T>> long sortAndGetTimeNano(Collection<T> collection);
}
