package tree

import spock.lang.Specification

import java.util.concurrent.ThreadLocalRandom

class SplayTreeTest extends Specification {

    ThreadLocalRandom random = ThreadLocalRandom.current()
    def tree = new SplayTree()

    def "empty tree"() {
        expect:
        !tree.search(0)
        !tree.search(random.nextLong())
    }

    def "single element tree"() {
        when:
        tree.insert(5)

        then:
        tree.search(5)
    }

    def "added a few elements check"() {
        when:
        tree.insert(15)
        tree.insert(20)
        tree.insert(10)
        tree.insert(11)
        tree.insert(5)
        tree.insert(19)
        tree.insert(25)

        then:
        tree.search(25)
        tree.search(15)
        tree.search(20)
        tree.search(10)
        tree.search(11)
        tree.search(19)
        tree.search(5)

        !tree.search(100)   //not found, but splay has to be called for 25
        tree.root.value == 25
        !tree.search(4)     //not found, but splay has to be called for 5
        tree.root.value == 5
    }

    def "single element tree - remove"() {
        when:
        tree.insert(15)

        then:
        tree.search(15)

        when:
        tree.remove(15)

        then:
        !tree.search(15)
    }


    def "two element tree - remove"() {
        when:
        tree.insert(15)
        tree.insert(20)
        tree.remove(15)

        then:
        !tree.search(15)
        tree.search(20)
    }

    def "a few elements insert remove search"() {
        when:
        tree.insert(1)
        tree.insert(2)
        tree.insert(10)
        tree.insert(10)
        tree.insert(3)
        tree.remove(10)

        then:
        tree.search(2)
        tree.search(3)
        !tree.search(10)
        tree.search(1)
    }

    def "a lot of elements check"() {
        given:
        def integers = new ArrayList<Long>()
        int halfSize  = 5_000_000
        int size = halfSize * 2

        when:
        for (int i = 0; i < size; i++) {
            integers.add(i)
            tree.insert(integers.get(i))
        }

        then:
        integers.every {
            tree.search(it)
        }

        when:
        def firstHalf = integers.subList(0, halfSize)
        for (int i = 0; i < halfSize; i++) {
            tree.remove(firstHalf.get(i))
        }
        def secondHalf = integers.subList(halfSize + 1, size)

        then:
        firstHalf.every {
            !tree.search(it)
        }
        secondHalf.every {
            tree.search(it)
        }
    }

    def "check sum"() {
        when:
        tree.insert(1)
        tree.insert(2)
        tree.insert(3)
        tree.insert(4)
        tree.insert(5)

        then:
        tree.root.getSum() == 15
    }
}
