package leatcode

import spock.lang.Specification

class LongestPalindromicSubstringTest extends Specification {

    LongestPalindromicSubstring u = new LongestPalindromicSubstring()

    def "tests"() {
        expect:
        u.longestPalindrome("") == ""
        u.longestPalindrome("a") == "a"
        u.longestPalindrome("aa") == "aa"
        u.longestPalindrome("ab") == "a"
        u.longestPalindrome("abc") == "a"
        u.longestPalindrome("abcd") == "a"

        u.longestPalindrome("aac") == "aa"
        u.longestPalindrome("abb") == "bb"
        u.longestPalindrome("abbb") == "bbb"
        u.longestPalindrome("aacc") == "aa"
        u.longestPalindrome("aaacca") == "acca"
        u.longestPalindrome("aaacddca") == "acddca"
        u.longestPalindrome("aaacddcafff") == "acddca"

        u.longestPalindrome("aca") == "aca"
        u.longestPalindrome("gaca") == "aca"
        u.longestPalindrome("avvavva") == "avvavva"
        u.longestPalindrome("aaacdldcafff") == "acdldca"
        u.longestPalindrome("aaacdldcafffxxxkkkxxx") == "xxxkkkxxx"
        u.longestPalindrome("aaacdldcafffxxxkkkxxxx") == "xxxkkkxxx"
        u.longestPalindrome("aaacdldcafffxxxkkkxxxxx") == "xxxkkkxxx"
        u.longestPalindrome("aaacdldcafffxxxkkkxxxxxvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvxk") == "xvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvx"

        u.longestPalindrome("babad") == "bab"
        u.longestPalindrome("cbbd") == "bb"
    }
}
