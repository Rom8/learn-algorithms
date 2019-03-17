package divide.quick

import spock.lang.Specification

class QuickSortTest extends Specification {

    def "QuickSort"(int[] numbers, int[] expected) {
        when:
        new QuickSort().quickSort(numbers)

        then:
        numbers == expected

        where:
        numbers         |   expected
        []              |   []
        [1]             |   [1]
        [1,2]           |   [1,2]
        [1,2,3,4,5]     |   [1,2,3,4,5]
        [5,4,1,3,2]     |   [1,2,3,4,5]
    }
}
