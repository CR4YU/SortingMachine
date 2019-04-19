package com.crayu.ui.service;

import com.crayu.sorting.SortingAlgorithm;
import com.crayu.statistics.StatisticsEngine;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.List;
import java.util.Map;

public class SortingTimesService extends Service<Map<SortingAlgorithm, Map<Number, Number>>> {

    private int initialSize;
    private int maxSize;
    private int sizeGap;
    private int repeatCount;
    private List<SortingAlgorithm> algorithms;
    private boolean withRegression = true;

    public SortingTimesService(int initialSize, int maxSize, int sizeGap, int repeatCount, List<SortingAlgorithm> algorithms, boolean withRegression) {
        this.initialSize = initialSize;
        this.maxSize = maxSize;
        this.sizeGap = sizeGap;
        this.repeatCount = repeatCount;
        this.algorithms = algorithms;
        this.withRegression = withRegression;
    }

    @Override
    protected Task<Map<SortingAlgorithm, Map<Number, Number>>> createTask() {
        return new Task<>() {
            @Override
            protected Map<SortingAlgorithm, Map<Number, Number>> call() {
                StatisticsEngine statisticsEngine =  new StatisticsEngine
                        .Builder()
                        .initialSize(initialSize)
                        .sizeGap(sizeGap)
                        .maxSize(maxSize)
                        .repeatCount(repeatCount)
                        .addSortingAlgorithms(algorithms)
                        .build();

                return withRegression
                        ? statisticsEngine.getSortingTimesRegressed()
                        : statisticsEngine.getSortingTimes();
            }
        };
    }
}
