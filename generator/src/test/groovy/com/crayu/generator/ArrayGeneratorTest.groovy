package com.crayu.generator

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
        0      | new IntArrayGenerator()    | Integer
        5      | new IntArrayGenerator()    | Integer
        50     | new DoubleArrayGenerator() | Double
        1000   | new DoubleArrayGenerator() | Double
        16000  | new StringArrayGenerator() | String
        100000 | new StringArrayGenerator() | String

    }

    def correctType(Comparable[] array, Class type) {
        array.length == 0 || array.every{o -> o.class == type}
    }

    def "Should throw exception when negative size" () {
        when:
        generator.generate(size)

        then:
        thrown IllegalArgumentException

        where:
        generator                  | size
        new IntArrayGenerator()    | -1
        new DoubleArrayGenerator() | -50
        new StringArrayGenerator() | -200
    }

}
