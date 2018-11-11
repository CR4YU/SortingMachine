package com.crayu.sortingmachine.statistics;

import com.crayu.sortingmachine.SortingAlgorithm;

import java.util.Map;

public class StatisticsDisplayText extends StatisticsDisplay {

    public StatisticsDisplayText(Statistics statistics) {
        super(statistics);
    }

    @Override
    public void displaySortTimes() {
        sortingTimes.forEach((k, v) -> {
            printName(k);
            printSortingTimes(v);
        });
    }

    private void printName(SortingAlgorithm a) {
        System.out.format("### %s ###\n", a.name());
    }

    private void printSortingTimes(Map<Integer, Long> times) {
        times.forEach((k, v) -> {
            System.out.format("\tArray size: %d Average time: %d ms\n", k, v);
        });
        System.out.println();
    }


}
