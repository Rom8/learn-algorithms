package divide.quick

import spock.lang.Specification

import java.util.concurrent.ThreadLocalRandom

class QuickSort3Test extends Specification {

    def "QuickSort random"() {
        given:
        int size = 100_000_000
        int[] numbers = new int[size]
        for (int i = 0; i < size; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(100)
        }

        when:
        new QuickSort3().quickSort(numbers)

        then:
        check(numbers)
    }
    def "QuickSort already sorted"() {
        given:
        int size = 100_000_000
        int[] numbers = new int[size]
        for (int i = 0; i < size; i++) {
            numbers[i] = i
        }

        when:
        new QuickSort3().quickSort(numbers)

        then:
        check(numbers)
    }
    def "QuickSort already sorted opposite way"() {
        given:
        int size = 100_000_000
        int[] numbers = new int[size]
        int value = size
        for (int i = 0; i < size; i++) {
            numbers[i] = value--
        }

        when:
        new QuickSort3().quickSort(numbers)

        then:
        check(numbers)
    }

    def "QuickSort"(int[] numbers, int[] expected) {
        when:
        new QuickSort3().quickSort(numbers)

        then:
        numbers == expected

        where:
        numbers                         |   expected
        []                              |   []
        [1]                             |   [1]
        [1,2]                           |   [1,2]
        [1,2,3,4,5]                     |   [1,2,3,4,5]
        [5,4,1,1,3,1,2,5]               |   [1,1,1,2,3,4,5,5]
        [5,7,7,5,1,5,1,5,5,5]           |   [1,1,5,5,5,5,5,5,7,7]
        [5,7,7,5,1,5,0,1,6,5,5,5]       |   [0,1,1,5,5,5,5,5,5,6,7,7]
    }

    boolean check(int[] numbers) {
        print "checking..."
        for (int i = 1; i<numbers.length; i++) {
             if(numbers[i-1] > numbers[i]){
                 return false
             }
        }
        return true
    }
}
