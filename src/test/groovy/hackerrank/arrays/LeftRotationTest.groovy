package hackerrank.arrays

import spock.lang.Specification

class LeftRotationTest extends Specification {

    def rotation = new LeftRotation()
    int[] numbers = [0,1,2,30,4] as int[]

    def "one-element array" () {
        given:
        int[] singleElement = [42] as int[]

        when:
        rotation.rotate(singleElement, 1)

        then:
        singleElement == [42] as int[]
    }

    def "two-element array" () {
        given:
        int[] twoElement = [42, 73] as int[]

        when:
        rotation.rotate(twoElement, 2)

        then:
        twoElement == [42, 73] as int[]

        when:
        rotation.rotate(twoElement, 1)

        then:
        twoElement == [73, 42] as int[]
    }

    def "rotate 6"() {
        given:
        int[] numbers = [0,1,2,3,4,5] as int[]

        when:
        rotation.rotate(numbers, 2)

        then:
        numbers == [2,3,4,5,0,1] as int[]
    }


    def "rotate 5"() {
        when:
        rotation.rotate(numbers, 5)

        then:
        numbers == [0,1,2,30,4] as int[]
    }

    def "rotate 1"() {
        when:
        rotation.rotate(numbers, 1)

        then:
        numbers == [1,2,30,4,0] as int[]
    }

    def "rotate 2"() {
        when:
        rotation.rotate(numbers, 2)

        then:
        numbers == [2,30,4,0,1] as int[]
    }

    def "rotate 3"() {
        when:
        rotation.rotate(numbers, 3)

        then:
        numbers == [30,4,0,1,2] as int[]
    }

    def "rotate 4"() {
        when:
        rotation.rotate(numbers, 4)

        then:
        numbers == [4,0,1,2,30] as int[]
    }
}
