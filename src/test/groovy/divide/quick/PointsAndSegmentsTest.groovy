package divide.quick

import spock.lang.Specification

import java.util.function.BiPredicate

import static divide.quick.PointsAndSegments.*

class PointsAndSegmentsTest extends Specification {

    def "lessThanQuantity"(int result, int[] points, int key, BiPredicate<Integer, Integer> checker) {
        when:
        def pointsAndSegments = new PointsAndSegments()

        then:
        pointsAndSegments.lessThanQuantity(
                points, key, checker,0, points.length - 1) == result

        where:
        result | points                      | key  | checker
        1      | [0]                         | 0    | CHECKER_LEFT
        0      | [0]                         | 0    | CHECKER_RIGHT
        0      | [5]                         | 4    | CHECKER_LEFT
        1      | [5]                         | 8    | CHECKER_LEFT
        1      | [5,8]                       | 8    | CHECKER_RIGHT
        2      | [5,8]                       | 8    | CHECKER_LEFT
        1      | [5,9]                       | 8    | CHECKER_LEFT
        4      | [1,2,3,4]                   | 4    | CHECKER_LEFT
        3      | [1,2,3,4]                   | 4    | CHECKER_RIGHT
        7      | [1,3,5,7,7,7,7,9]           | 7    | CHECKER_LEFT
        3      | [1,3,5,7,7,7,7,9]           | 7    | CHECKER_RIGHT
        7      | [1,3,5,7,7,7,7,9]           | 8    | CHECKER_LEFT
        8      | [1,3,5,7,7,7,7,9]           | 10   | CHECKER_RIGHT
    }

    def "result test" () {
        given:
        def segments = new PointsAndSegments()
        segments.read(new FileReader(new File("segments.txt")))

        expect:
        segments.result() == [0,4,5,7,6] as int[]
    }
}
