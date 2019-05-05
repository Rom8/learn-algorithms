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

    def "long line"() {
        given:
        final int length = 100_000_000
        Rope rope = new Rope(randomAlphaNumeric(length))

        when:
        long before = System.currentTimeMillis()
        for(int i =0; i < 1_000; i++){
            rope.cutAndInsert(20_000_000, 40_000_000, 10_000_000)
        }
        long after = System.currentTimeMillis()

        then:
        rope.getLine().length() == length
        print("time spent for single cutInsert: " + (after - before)/1000)

    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

    static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder()
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length())
            builder.append(ALPHA_NUMERIC_STRING.charAt(character))
        }
        return builder.toString()

    }
}
