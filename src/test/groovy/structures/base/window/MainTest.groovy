package structures.base.window

import spock.lang.Specification

class MainTest extends Specification {

    def "max in window"(int numbersSize, int windowSize, int[] numbers, int[] result) {
        expect:
            new Main(numbersSize, windowSize, numbers).calculate() == result

        where:
        numbersSize |   windowSize  |   numbers         |   result
            1       |       1       |       5           |   5
            3       |       3       |   [6,7,5]         |   7
            3       |       2       |   [2,1,3]         |   [2,3]
            4       |       2       |   [2,1,3,2]       |   [2,3,3]
            6       |       3       |   [10,2,3,11,5,12] |   [10,11,11,12]
    }
}
