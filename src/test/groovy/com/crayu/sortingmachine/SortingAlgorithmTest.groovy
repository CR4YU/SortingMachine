package com.crayu.sortingmachine

import com.crayu.sortingmachine.utils.DoubleArrayGenerator
import com.crayu.sortingmachine.utils.IntArrayGenerator
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class SortingAlgorithmTest extends Specification {

    @Shared
    TestParametersGenerator parametersGenerator = new TestParametersGenerator()


    @Unroll
    def "Sort #generator.type() array with size #size using algorithm #algorithm.shortName()"() {
        setup:
        SortingService.setSortingAlgorithm(algorithm)
        Comparable[] array = generator.generate(size)

        when:
        SortingService.sort(array)

        then:
        sortedAscending(array)

        where:
        algorithm << parametersGenerator.algorithms
        generator << parametersGenerator.generators
        size      << parametersGenerator.sizes
    }

    @Unroll
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

        [1, 2, 1]            | false
        [5, 5, 8, 5]         | false
        [10, 9, 8, 7]        | false
    }


    private static boolean sortedAscending(Comparable[] array) {
        array.size() < 2 || (1..array.size()-1).every{ (array[it - 1] <=> array[it]) < 1 }
    }

    static class TestParametersGenerator {
        private final def ALGORITHMS = [new ForkJoinMergeSort(), new InsertionSort()]
        private final def GENERATORS = [new IntArrayGenerator(), new DoubleArrayGenerator()]
        private final def SIZES = [0, 1, 3, 10, 40, 100, 150, 1000, 4000, 10000, 100000]

        def algorithms = []
        def generators = []
        def sizes = []

        TestParametersGenerator() {
            initParameters()
        }

        private void initParameters() {
            ALGORITHMS.each { a ->
                GENERATORS.each { g ->
                    SIZES.each { s ->
                        algorithms << a
                        generators << g
                        sizes << s
                    }
                }
            }
        }
    }

}
