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
}
