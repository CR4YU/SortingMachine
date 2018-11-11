package com.crayu.sortingmachine.statistics

import com.crayu.sortingmachine.ArraysSort
import com.crayu.sortingmachine.ForkJoinMergeSort
import com.crayu.sortingmachine.InsertionSort
import com.crayu.sortingmachine.SortingAlgorithm
import com.crayu.sortingmachine.utils.DoubleArrayGenerator
import com.crayu.sortingmachine.utils.IntArrayGenerator
import com.crayu.sortingmachine.utils.StringArrayGenerator
import spock.lang.Specification
import spock.lang.Unroll

class StatisticsTest extends Specification {

    @Unroll
    def "Should properly generate statistics" () {
        setup:
        Statistics statistics = new Statistics.Builder()
            .initialSize(initSize)
            .sizeGap(sizeGap)
            .maxSize(maxSize)
            .repeatCount(repeatCount)
            .addSortingAlgorithms(algorithms)
            .arrayGenerator(generator)
            .build()

        when:
        Map<SortingAlgorithm, Map<Integer, Long>> st = statistics.getStatistics()

        then:
        st.size() == algorithms.size()
        st.every{ k, v -> v.size() == (int)((maxSize - initSize)/sizeGap) + 1 }

        where:
        initSize | maxSize | sizeGap | repeatCount | algorithms                                     | generator
        0        | 10      | 1       | 2           | [new ForkJoinMergeSort(), new InsertionSort()] | new IntArrayGenerator()
        1        | 10      | 2       | 3           | [new InsertionSort()]                          | new StringArrayGenerator()
        0        | 10      | 3       | 2           | [new ArraysSort(), new InsertionSort()]        | new DoubleArrayGenerator()
        0        | 100     | 10      | 1           | [new ForkJoinMergeSort(), new InsertionSort()] | new IntArrayGenerator()
        0        | 100     | 30      | 1           | [new ForkJoinMergeSort(), new InsertionSort()] | new IntArrayGenerator()
        0        | 100     | 15      | 1           | [new ForkJoinMergeSort(), new InsertionSort()] | new IntArrayGenerator()
        0        | 100     | 200     | 1           | [new ForkJoinMergeSort(), new InsertionSort()] | new IntArrayGenerator()
    }
}
