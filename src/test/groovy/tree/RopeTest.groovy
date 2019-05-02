package tree

import spock.lang.Specification

class RopeTest extends Specification {

    def "simple check 1"() {
        given:
        def rope = new Rope("hlelowrold")

        when:
        rope.cutAndInsert(1,1,2)

        then:
        rope.getLine() == "hellowrold"

        when:
        rope.cutAndInsert(6,6,7)

        then:
        rope.getLine() == "helloworld"
    }

    def "simple check 2"() {
        given:
        def rope = new Rope("abcdef")

        when:
        rope.cutAndInsert(0,1,1)

        then:
        rope.getLine() == "cabdef"

        when:
        rope.cutAndInsert(4,5,0)

        then:
        rope.getLine() == "efcabd"
    }

    def "simple check 3"() {
        given:
        def rope = new Rope("aaabba")

        when:
        rope.cutAndInsert(3,4,0)

        then:
        rope.getLine() == "bbaaaa"
    }

    def "simple check 4"() {
        given:
        def rope = new Rope("aaabba")

        when:
        rope.cutAndInsert(3,4,4)

        then:
        rope.getLine() == "aaaabb"
    }
}
