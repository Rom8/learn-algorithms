package divide.noncomporison

import spock.lang.Specification

import java.util.concurrent.ThreadLocalRandom

class MainTest extends Specification {
    def "CountSort"() {
        given:
        int size = 10_000
        int[] numbers = new int[size]
        for (int i = 0; i < size; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(1, 11)
        }

        when:
        int[] sorted = new Main().countSort(numbers)

        then:
        check(sorted)
    }

    boolean check(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i-1]) {
                return false
            }
        }
        return true
    }
}
