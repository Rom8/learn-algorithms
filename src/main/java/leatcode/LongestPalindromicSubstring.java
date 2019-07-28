package leatcode;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        if (s.length() == 2) {
            return s.charAt(0) == s.charAt(1) ? s : s.substring(0, 1);
        }
        String result = s.substring(0, 1);
        for (int i = 1; i < s.length(); i++) {
            result = getResult(s, result, i, 2, 0);
            result = getResult(s, result, i, 3, 1);
        }
        return result;
    }

    private String getResult(String s, String result, int i, int minLength, int rightOffset) {
        int offset = 0;
        while (isPalindromeCenter(s, i - offset - 1, i + offset + rightOffset)) {
            offset++;
        }
        offset--;
        if (offset >= 0) {
            result = result.length() >= minLength + 2 * offset ? result : s.substring(i - offset - 1, i + offset + 1 + rightOffset);
        }
        return result;
    }

    private boolean isPalindromeCenter(String inputLine, int left, int right) {
        if (left < 0 || right >= inputLine.length()) {
            return false;
        }
        return inputLine.charAt(left) == inputLine.charAt(right);
    }
}
