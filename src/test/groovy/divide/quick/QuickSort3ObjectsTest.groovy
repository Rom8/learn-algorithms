package divide.quick

import spock.lang.Specification

import java.util.function.ToIntFunction

class QuickSort3ObjectsTest extends Specification {

    def "test sorting" (Integer[] input, Integer[] expected) {
        given:
        QuickSort3Objects<Integer> quick = new QuickSort3Objects<>()
        quick.setComparator(Comparator.comparingInt(new ToIntFunction<Integer>() {
            @Override
            int applyAsInt(Integer value) {
                return value.intValue()
            }
        }))

        when:
        quick.quickSort(input)

        then:
        input == expected

        where:
        input       | expected
        [1,2,3]     | [1,2,3]
        [3,2,1]     | [1,2,3]
        [1,2,1,2]   | [1,1,2,2]
        [1,2,1,2,1] | [1,1,1,2,2]
    }
}
