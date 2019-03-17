package divide.inversions

import spock.lang.Specification

import java.util.concurrent.atomic.AtomicInteger

class MainTest extends Specification {

    def "merge sort"() {
        expect:
            new Main().mergeSort([5] as int[]) == [5] as int[]
            new Main().mergeSort([1,2] as int[]) == [1,2] as int[]
            new Main().mergeSort([2,1] as int[]) == [1,2] as int[]
            new Main().mergeSort([2,0,1,4,7,-1,-2] as int[]) == [-2,-1,0,1,2,4,7] as int[]
    }

    def "inversions"(int[] input, int inversionsExpected) {
        when:
        AtomicInteger inversions = new AtomicInteger()
        new Main().inversions(input, inversions)

        then:
        inversions.get() == inversionsExpected

        where:
        input               | inversionsExpected
        [5]                 | 0
        [1,2]               | 0
        [2,1]               | 1
        [2,1,0]             | 3
        [2,1,0,3]           | 3
        [2,1,0,3,0,3]       | 6
        [2, 3, 9, 2, 9]     | 2
    }


}
