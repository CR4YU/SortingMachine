package com.crayu.sorting;

import java.util.Collection;

interface SortingService {

    SortingAlgorithm DEFAULT_SORTING_ALGORITHM = SortingAlgorithm.FORK_JOIN_MERGE_SORT;

    void setSortingAlgorithm(SortingAlgorithm algorithm);
}
