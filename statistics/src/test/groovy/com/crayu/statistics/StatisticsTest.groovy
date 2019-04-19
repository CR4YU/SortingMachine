package com.crayu.statistics


import com.crayu.generator.DoubleArrayGenerator
import com.crayu.generator.IntArrayGenerator
import com.crayu.generator.StringArrayGenerator
import spock.lang.Specification
import spock.lang.Unroll

import static com.crayu.sorting.SortingAlgorithm.*;


class StatisticsTest extends Specification {

    @Unroll
    def "Should properly generate statistics" () {
        setup:
        StatisticsEngine statistics = new StatisticsEngine.Builder()
            .initialSize(initSize)
            .sizeGap(sizeGap)
            .maxSize(maxSize)
            .repeatCount(repeatCount)
            .addSortingAlgorithms(algorithms)
            .arrayGenerator(generator)
            .build()

        when:
        def stats = statistics.getSortingTimes()

        then:
        stats.size() == algorithms.size()
        stats.every{ k, v -> v.size() == (int)((maxSize - initSize)/sizeGap) + 1 }

        where:
        initSize | maxSize | sizeGap | repeatCount | algorithms                              | generator
        0        | 10      | 1       | 2           | [FORK_JOIN_MERGE_SORT, INSERTION_SORT]  | new IntArrayGenerator()
        1        | 10      | 2       | 3           | [BUBBLE_SORT]                           | new StringArrayGenerator()
        0        | 10      | 3       | 2           | [ARRAYS_SORT, BUBBLE_SORT]              | new DoubleArrayGenerator()
        0        | 100     | 10      | 1           | [BUBBLE_SORT, INSERTION_SORT]           | new IntArrayGenerator()
        0        | 100     | 30      | 1           | [FORK_JOIN_MERGE_SORT, ARRAYS_SORT]     | new IntArrayGenerator()
        3        | 100     | 15      | 1           | [BUBBLE_SORT, ARRAYS_SORT]              | new DoubleArrayGenerator()
        10       | 100     | 200     | 1           | [FORK_JOIN_MERGE_SORT]                  | new IntArrayGenerator()
        15       | 100     | 10      | 2           | [FORK_JOIN_MERGE_SORT, BUBBLE_SORT]     | new StringArrayGenerator()

    }

    def "Should throw IllegalArgumentException" () {
        when:
        new StatisticsEngine.Builder()
            .initialSize(initSize)
            .sizeGap(sizeGap)
            .maxSize(maxSize)
            .repeatCount(repeatCount)
            .build()

        then:
        thrown IllegalArgumentException

        where:
        initSize | maxSize | sizeGap | repeatCount
        -5       | 10      | 1       | 2
        1        | -10     | 2       | 3
        0        | 10      | -3      | 2
        0        | 100     | 0       | 1
        0        | 100     | 30      | 0
        0        | 100     | 15      | -5
    }
}
