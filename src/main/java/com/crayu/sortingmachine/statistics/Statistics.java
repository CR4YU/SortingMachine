package com.crayu.sortingmachine.statistics;

import com.crayu.sortingmachine.SortingAlgorithm;
import com.crayu.sortingmachine.SortingService;
import com.crayu.sortingmachine.utils.ArrayGenerator;
import com.crayu.sortingmachine.utils.IntArrayGenerator;

import java.util.*;

public final class Statistics {

    private final Map<SortingAlgorithm, Map<Integer, Long>> avgSortingTimes;
    private final ArrayGenerator arrayGenerator;
    private final int sizeGap;
    private final int initialSize;
    private final int maxSize;
    private final int repeatCount;

    private Statistics(Builder builder) {
        avgSortingTimes = builder.sortingTimes;
        arrayGenerator = builder.arrayGenerator;
        sizeGap = builder.sizeGap;
        initialSize = builder.initialSize;
        maxSize = builder.maxSize;
        repeatCount = builder.repeatCount;

        generateStatistics();
    }

    private void generateStatistics() {
        avgSortingTimes.forEach((k, v) -> {
            SortingService.setSortingAlgorithm(k);

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
            sum += SortingService.sortAndGetTimeMillis(arrayGenerator.generate(size));
        }
        return sum / repeatCount;
    }

    public Map<SortingAlgorithm, Map<Integer, Long>> getStatistics() {
        return avgSortingTimes;
    }

    public static class Builder {
        private Map<SortingAlgorithm, Map<Integer, Long>> sortingTimes;
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
            if(sizeGap < 1) throw new IllegalArgumentException();
            this.sizeGap = sizeGap;
            return this;
        }

        public Builder initialSize(int initialSize) {
            if(initialSize < 0) throw new IllegalArgumentException();
            this.initialSize = initialSize;
            return this;
        }

        public Builder maxSize(int maxSize) {
            if(maxSize < 0) throw new IllegalArgumentException();
            this.maxSize = maxSize;
            return this;
        }

        public Builder repeatCount(int repeatCount) {
            if(repeatCount < 1) throw new IllegalArgumentException();
            this.repeatCount = repeatCount;
            return this;
        }

        public Builder addSortingAlgorithm(SortingAlgorithm algorithm) {
            sortingTimes.put(algorithm, new TreeMap<>());
            return this;
        }

        public Builder addSortingAlgorithms(SortingAlgorithm[] algorithms) {
            for(SortingAlgorithm a : algorithms) {
                sortingTimes.put(a, new TreeMap<>());
            }
            return this;
        }

        public Builder addSortingAlgorithms(List<SortingAlgorithm> algorithms) {
            algorithms.forEach(a -> sortingTimes.put(a, new TreeMap<>()));
            return this;
        }

        public Statistics build() {
            return new Statistics(this);
        }
    }
}
