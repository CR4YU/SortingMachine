package com.crayu.sorting

import com.crayu.generator.ArrayGenerator
import com.crayu.generator.DoubleArrayGenerator
import com.crayu.generator.IntArrayGenerator
import com.crayu.generator.StringArrayGenerator
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static com.crayu.sorting.SortingAlgorithm.*;

class SorterTest extends Specification {

    @Shared parameters

    @Shared service

    def setupSpec() {
        parameters = new TestParameters()
        service = new SortingService()
    }

    @Unroll
    def "Sort #generator.type() array with size #size using algorithm #algorithm"() {
        setup:
        service.setSortingAlgorithm(algorithm)
        Comparable[] array = generator.generate(size)
        List<Comparable> list = array.toList()

        when:
        service.sort(array)
        service.sort(list)

        then:
        array.length == size
        list.size() == size
        sortedAscending(array)
        sortedAscending(list)

        where:
        algorithm << parameters.algorithms
        generator << parameters.generators
        size      << parameters.sizes
    }

    def "sortedAscending() should work properly"() {
        when:
        def result = sortedAscending(array)

        then:
        result == expected

        where:
        array                | expected
        [1, 2, 3]            | true
        [0, 5, 1000]         | true
        [-4, 0, 0, 9999]     | true
        [1, 1, 1, 1, 1]      | true
        [0.0, 5.3, 1000.45]  | true
        [4.0, 16, 18, 20.5]  | true
        ["a", "b", "c"]      | true

        [1, 2, 1]            | false
        [5, 5, 8, 5]         | false
        [10, 9, 8, 7]        | false
        ["a", "c", "b"]      | false
    }


    private static boolean sortedAscending(array) {
        array.size() < 2 || (1..array.size()-1).every{ (array[it - 1] <=> array[it]) < 1 }
    }


    private static class TestParameters {
        private final List<SortingAlgorithm> ALGORITHMS =
                [FORK_JOIN_MERGE_SORT, INSERTION_SORT, ARRAYS_SORT, BUBBLE_SORT, MERGE_SORT, QUICK_SORT]

        private final List<ArrayGenerator> GENERATORS =
                [new IntArrayGenerator(), new DoubleArrayGenerator(), new StringArrayGenerator()]

        private final List<Integer> SIZES =
                [0, 1, 2, 3, 10, 40, 100, 150, 1000, 4000, 10000]

        private def algorithms = []
        private def generators = []
        private def sizes = []

        TestParameters() {
            initParameters()
        }

        private void initParameters() {
            ALGORITHMS.each { a ->
                GENERATORS.each { g ->
                    SIZES.each { s ->
                        algorithms << a
                        generators << g
                        sizes      << s
                    }
                }
            }
        }
    }

}
