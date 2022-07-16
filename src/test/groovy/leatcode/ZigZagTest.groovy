package leatcode

import spock.lang.Specification

class ZigZagTest extends Specification {

    def "1 row"() {
        given:
        ZigZag zigZag = new ZigZag()

        expect:
        zigZag.convert("a", 1) == "a"
        zigZag.convert("abcd", 1) == "abcd"
    }

    def "2 rows"() {
        given:
        ZigZag zigZag = new ZigZag()

        expect:
        zigZag.convert("ab", 2) == "ab"
        zigZag.convert("aba", 2) == "aab"
        zigZag.convert("abab", 2) == "aabb"
        zigZag.convert("ababa", 2) == "aaabb"
        zigZag.convert("ababab", 2) == "aaabbb"
        zigZag.convert("abababa", 2) == "aaaabbb"
        zigZag.convert("abababab", 2) == "aaaabbbb"
        zigZag.convert("ababababa", 2) == "aaaaabbbb"
    }

    def "3 rows"() {
        given:
        ZigZag zigZag = new ZigZag()

        expect:
        zigZag.convert("abc", 3) == "abc"
        zigZag.convert("abcd", 3) == "abdc"
        zigZag.convert("abcda", 3) == "aabdc"
        zigZag.convert("abcdab", 3) == "aabdbc"
        zigZag.convert("abcdabc", 3) == "aabdbcc"
        zigZag.convert("abcdabcd", 3) == "aabdbdcc"
        zigZag.convert("abcdabcda", 3) == "aaabdbdcc"
        zigZag.convert("abcdabcdab", 3) == "aaabdbdbcc"
        zigZag.convert("abcdabcdabc", 3) == "aaabdbdbccc"
    }

    def "4 rows"() {
        given:
        ZigZag zigZag = new ZigZag()

        expect:
        zigZag.convert("abcd", 4) == "abcd"
        zigZag.convert("abcde", 4) == "abced"
        zigZag.convert("abcdef", 4) == "abfced"
//        zigZag.convert("abcdefa", 4) == "aad"
//        zigZag.convert("abcdefab", 4) == "aad"
//        zigZag.convert("abcdefabc", 4) == "aad"
//        zigZag.convert("abcdefabcd", 4) == "aadd"
//        zigZag.convert("abcdefabcde", 4) == "aadd"
//        zigZag.convert("abcdefabcdef", 4) == "aadd"
        zigZag.convert("abcdefabcdefa", 4) == "aaabfbfcecedd"
        zigZag.convert("PAYPALISHIRING", 4) == "PINALSIGYAHRPI"
        zigZag.convert("PAYPALISHIRING12", 4) == "PINALSIGYAHR1PI2"
    }

}
