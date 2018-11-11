package com.crayu.sortingmachine.statistics;

import com.crayu.sortingmachine.ArraysSort;
import com.crayu.sortingmachine.ForkJoinMergeSort;
import com.crayu.sortingmachine.InsertionSort;
import com.crayu.sortingmachine.utils.IntArrayGenerator;

public class StatisticsClient {

    public static void main(String[] args) {
        Statistics statistics = new Statistics.Builder()
                .initialSize(0)
                .sizeGap(1000)
                .maxSize(10000)
                .repeatCount(5)
                .addSortingAlgorithm(new ForkJoinMergeSort())
                .addSortingAlgorithm(new InsertionSort())
                .addSortingAlgorithm(new ArraysSort())
                .arrayGenerator(new IntArrayGenerator())
                .build();

        StatisticsDisplay statisticsDisplay = new StatisticsDisplayText(statistics);
        statisticsDisplay.displaySortTimes();
    }
}
