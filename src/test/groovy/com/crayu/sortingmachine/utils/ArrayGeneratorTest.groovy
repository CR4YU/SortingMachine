package com.crayu.sortingmachine.utils

import spock.lang.Specification

class ArrayGeneratorTest extends Specification {


    def "Should properly generate array with correct type" () {
        setup:
        def array

        when:
        array = generator.generate(size)

        then:
        array.length == size
        correctType(array, type)

        where:
        size   | generator                  | type
        0      | new IntArrayGenerator()    | Integer.class
        5      | new IntArrayGenerator()    | Integer.class
        50     | new DoubleArrayGenerator() | Double.class
        1000   | new DoubleArrayGenerator() | Double.class
        16000  | new StringArrayGenerator() | String.class
        100000 | new StringArrayGenerator() | String.class

    }

    private static boolean correctType(Comparable[] array, Class type) {
        array.length == 0 || array.every{o -> o.class == type}
    }

}
