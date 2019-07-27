package leatcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLength = 0;
        final Map<Character, Integer> letterToPosition = new HashMap<>();
        for (int i = 1; i <= s.length(); i++) {
            final Integer previous = letterToPosition.put(s.charAt(i-1), i);
            if (previous != null) {
                left = previous > left ? previous : left;
            }
            maxLength = Math.max(maxLength, i - left);
        }
        return maxLength;
    }
}
