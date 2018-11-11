package com.crayu.sortingmachine.statistics;

import com.crayu.sortingmachine.SortingAlgorithm;

import java.util.Map;

public abstract class StatisticsDisplay {

    final Map<SortingAlgorithm, Map<Integer, Long>> sortingTimes;
    private final Statistics statistics;

    public StatisticsDisplay(Statistics statistics) {
        this.statistics = statistics;
        sortingTimes = statistics.getStatistics();
    }

    public abstract void displaySortTimes();


}
