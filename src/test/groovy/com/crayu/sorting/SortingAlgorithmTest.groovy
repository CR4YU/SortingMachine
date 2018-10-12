package com.crayu.sorting

import com.crayu.sorting.utils.IntArrayGenerator
import spock.lang.Specification
import spock.lang.Unroll

class SortingAlgorithmTest extends Specification {

    @Unroll
    def "should properly sort #type array"() {
        setup:
        SortingService.setSortingAlgorithm(algorithm)
        Comparable[] array = generator.generate(size)

        when:
        SortingService.sort(array)

        then:
        true

        where:
        type  | algorithm               | generator               | size
        'int' | new ForkJoinMergeSort() | new IntArrayGenerator() | 1000
    }

    private static boolean sortedAscending(Comparable[] array) {
        int i
        while(i < array.length - 2) {
            if((array[i] <=> array[i+1]) == 1) return false
            i++
        }
        return true
    }
}
