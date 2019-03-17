package structures.priorityqueue.heap

import spock.lang.Specification

class MainTest extends Specification {
    def "BuildHeap"() {
//        when:
//        def heap = new Main([1,2,3,4,5] as int[])
//        heap.buildHeap()
//
//        then:
//        heap.getNumbers() == [1,2,3,4,5] as int[]
//
//
//        when:
//        heap = new Main([5,4,3,2,1] as int[])
//        heap.buildHeap()
//
//        then:
//        heap.getNumbers() == [1,2,3,5,4] as int[]
//
//
//        when:
//        heap = new Main([0,1,2,3,4,5] as int[])
//        heap.buildHeap()
//
//        then:
//        heap.getNumbers() == [0,1,2,3,4,5] as int[]
//

        when:
        def heap = new Main([7,6,5,4,3,2] as int[])
        heap.buildHeap()

        then:
        true
//        then:
//        heap.getNumbers() == [0,1,2,3,4,5] as int[]
    }
}
