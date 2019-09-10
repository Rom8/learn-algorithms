package leatcode

import spock.lang.Specification

class ContainerWithMostWater11Test extends Specification {

    ContainerWithMostWater11 container = new ContainerWithMostWater11()

    def "empty or one"() {
        expect:
        0 == container.maxArea([] as int[])
        0 == container.maxArea([5] as int[])
    }

    def "two"() {
        expect:
        2 == container.maxArea([2,6] as int[])
    }

    def "leet code test input"() {
        expect:
        49 == container.maxArea([1,8,6,2,5,4,8,3,7] as int[])
    }

    def "my test"() {
        expect:
        10 == container.maxArea([2,3,7,6,5,2] as int[])
        12 == container.maxArea([2,4,7,6,5,2] as int[])
        2 == container.maxArea([3,2,1] as int[])
        6 == container.maxArea([3,2,1,2,1,1] as int[])
    }

}
