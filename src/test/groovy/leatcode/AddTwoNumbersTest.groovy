package leatcode

import spock.lang.Specification

class AddTwoNumbersTest extends Specification {

    def addTwoNumbers = new AddTwoNumbers()

    def "zero values"() {
        expect:
        addTwoNumbers.addTwoNumbers(new ListNode(0), new ListNode(0))
    }

    def "numbers of the same length without overflow"() {
        given:
        def first = new ListNode(1)
        first.next = new ListNode(2)
        first.next.next = new ListNode(3)

        def second = new ListNode(4)
        second.next = new ListNode(5)
        second.next.next = new ListNode(6)

        def expected = new ListNode(5)
        expected.next = new ListNode(7)
        expected.next.next = new ListNode(9)

        when:
        def twoNumbers = addTwoNumbers.addTwoNumbers(first, second)

        then:
        twoNumbers == expected
    }

    def "numbers of different length without overflow"() {
        given:
        def first = new ListNode(1)
        first.next = new ListNode(2)

        def second = new ListNode(4)
        second.next = new ListNode(5)
        second.next.next = new ListNode(6)
        second.next.next.next = new ListNode(3)

        def expected = new ListNode(5)
        expected.next = new ListNode(7)
        expected.next.next = new ListNode(6)
        expected.next.next.next = new ListNode(3)

        when:
        def twoNumbers = addTwoNumbers.addTwoNumbers(first, second)

        then:
        twoNumbers == expected
    }

    def "numbers of different length with overflow"() {
        given:
        def first = new ListNode(1)
        first.next = new ListNode(2)

        def second = new ListNode(4)
        second.next = new ListNode(9)

        def expected = new ListNode(5)
        expected.next = new ListNode(1)
        expected.next.next = new ListNode(1)

        when:
        def twoNumbers = addTwoNumbers.addTwoNumbers(first, second)

        then:
        twoNumbers == expected
    }

    def "1 + 999"() {
        given:
        def first = new ListNode(1)

        def second = new ListNode(9)
        second.next = new ListNode(9)
        second.next.next = new ListNode(9)

        def expected = new ListNode(0)
        expected.next = new ListNode(0)
        expected.next.next = new ListNode(0)
        expected.next.next.next = new ListNode(1)

        when:
        def twoNumbers = addTwoNumbers.addTwoNumbers(first, second)

        then:
        twoNumbers == expected
    }
}
