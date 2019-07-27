package leatcode

import spock.lang.Specification

class LongestSubstringWithoutRepeatingCharactersTest extends Specification {

    LongestSubstringWithoutRepeatingCharacters longest = new LongestSubstringWithoutRepeatingCharacters()

    def "all letters are unique"() {
        expect:
        longest.lengthOfLongestSubstring("") == 0
        longest.lengthOfLongestSubstring("a") == 1
        longest.lengthOfLongestSubstring("ab") == 2
        longest.lengthOfLongestSubstring("abc") == 3
        longest.lengthOfLongestSubstring("abcd") == 4
        longest.lengthOfLongestSubstring("abcde") == 5
    }

    def "non unique"() {
        expect:
        longest.lengthOfLongestSubstring("baaaabcde") == 5
        longest.lengthOfLongestSubstring("aaaabcdeeqwertyuie") == 8
    }

    def "from leetcode"() {
        expect:
        longest.lengthOfLongestSubstring("abcabcbb") == 3
        longest.lengthOfLongestSubstring("bbbbb") == 1
        longest.lengthOfLongestSubstring("pwwkew") == 3
    }

}
