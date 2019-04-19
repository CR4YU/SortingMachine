package com.crayu.statistics;

import com.crayu.generator.ArrayGenerator;
import com.crayu.generator.IntArrayGenerator;
import com.crayu.sorting.SortingAlgorithm;
import com.crayu.sorting.SortingService;

import java.util.*;

public final class StatisticsEngine {

    private final SortingService sortingService;
    private final Map<SortingAlgorithm, Map<Number, Number>> avgSortingTimes;
    private final ArrayGenerator arrayGenerator;
    private final int sizeGap;
    private final int initialSize;
    private final int maxSize;
    private final int repeatCount;

    private Map<SortingAlgorithm, Map<Number, Number>> sortingTimesRegressed;

    private StatisticsEngine(Builder builder) {
        sortingService = new SortingService();
        avgSortingTimes = builder.sortingTimes;
        arrayGenerator = builder.arrayGenerator;
        sizeGap = builder.sizeGap;
        initialSize = builder.initialSize;
        maxSize = builder.maxSize;
        repeatCount = builder.repeatCount;

        generateSortingTimes();
    }

    private void generateSortingTimes() {
        avgSortingTimes.forEach((k, v) -> {
            sortingService.setSortingAlgorithm(k);

            int size = initialSize;
            while (size <= maxSize) {
                long time = avgSortTimeForSize(size);
                v.put(size, time);
                size += sizeGap;
            }
        });
    }

    private long avgSortTimeForSize(int size) {
        long sum = 0;
        for(int i = 0; i < repeatCount; i++) {
            sum += sortingService.sortAndGetTimeNano(arrayGenerator.generate(size));
        }
        return sum / repeatCount;
    }

    public Map<SortingAlgorithm, Map<Number, Number>> getSortingTimes() {
        return avgSortingTimes;
    }

    public Map<SortingAlgorithm, Map<Number, Number>> getSortingTimesRegressed() {
        if (sortingTimesRegressed == null) {
            sortingTimesRegressed = new HashMap<>();
            avgSortingTimes.forEach((k, v) -> {
                sortingTimesRegressed.put(k, regress(v));
            });
        }
        return sortingTimesRegressed;
    }

    private Map<Number, Number> regress(final Map<Number, Number> data) {
        Regression regression = new Regression(data);
        Map<Number, Number> result = new HashMap<>();
        data.keySet().forEach( x -> {
            result.put(x, regression.predict(x.doubleValue()));
        });
        return result;
    }

    public static class Builder {
        private Map<SortingAlgorithm, Map<Number, Number>> sortingTimes;
        private ArrayGenerator arrayGenerator = new IntArrayGenerator();
        private int sizeGap = 1;
        private int initialSize = 0;
        private int maxSize = 1;
        private int repeatCount = 1;

        public Builder() {
            sortingTimes = new HashMap<>();
        }

        public Builder arrayGenerator(ArrayGenerator arrayGenerator) {
            this.arrayGenerator = Objects.requireNonNull(arrayGenerator);
            return this;
        }

        public Builder sizeGap(int sizeGap) {
            if (sizeGap < 1) throw new IllegalArgumentException();
            this.sizeGap = sizeGap;
            return this;
        }

        public Builder initialSize(int initialSize) {
            if (initialSize < 0) throw new IllegalArgumentException();
            this.initialSize = initialSize;
            return this;
        }

        public Builder maxSize(int maxSize) {
            if (maxSize < 0) throw new IllegalArgumentException();
            this.maxSize = maxSize;
            return this;
        }

        public Builder repeatCount(int repeatCount) {
            if (repeatCount < 1) throw new IllegalArgumentException();
            this.repeatCount = repeatCount;
            return this;
        }

        public Builder addSortingAlgorithm(SortingAlgorithm algorithm) {
            sortingTimes.put(algorithm, new TreeMap<>());
            return this;
        }

        public Builder addSortingAlgorithms(SortingAlgorithm[] algorithms) {
            for (SortingAlgorithm a : algorithms) {
                sortingTimes.put(a, new TreeMap<>());
            }
            return this;
        }

        public Builder addSortingAlgorithms(List<SortingAlgorithm> algorithms) {
            algorithms.forEach(a -> sortingTimes.put(a, new TreeMap<>()));
            return this;
        }

        public StatisticsEngine build() {
            return new StatisticsEngine(this);
        }
    }
}
