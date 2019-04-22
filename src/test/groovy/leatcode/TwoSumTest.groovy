package leatcode

import spock.lang.Specification

class TwoSumTest extends Specification {
    def "TwoSum 1"() {
        given:
        TwoSum twoSum = new TwoSum()

        expect:
        twoSum.twoSum([3,2,4] as int[], 6) == [1,2] as int[]
    }

    def "TwoSum 2"() {
        given:
        TwoSum twoSum = new TwoSum()

        expect:
        twoSum.twoSum([3,2,4,8] as int[], 10) == [1,3] as int[]
    }

    def "TwoSum 3 3 6"() {
        given:
        TwoSum twoSum = new TwoSum()

        expect:
        twoSum.twoSum([3,3] as int[], 6) == [0,1] as int[]
    }

}
