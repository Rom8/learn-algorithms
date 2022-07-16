package leatcode;

public class ZigZag {

    public String convert(String s, int numRows) {
        numRows = Math.min(s.length(), numRows);
        if (numRows == 1) {
            return s;
        }
        if (numRows == 2) {
            return firstRow(s, numRows) + lastRow(s, numRows);
        }
        StringBuilder result = new StringBuilder(firstRow(s, numRows));
        for (int i = 2; i < numRows; i++) {
            result.append(middleLine(s, numRows, i));
        }
        result.append(lastRow(s, numRows));
        return result.toString();
    }

    private String firstRow(String s, int numRows) {
        int between = getBetween(numRows);
        char[] chars = new char[(s.length() + between - 1) / between];
        int index = 0;
        return findLine(s, between, chars, index);
    }

    private String lastRow(String s, int numRows) {
        int between = getBetween(numRows);
        char[] chars = new char[(s.length() + between - numRows) / between];
        int index = numRows - 1;
        return findLine(s, between, chars, index);
    }

    private int getBetween(int numRows) {
        return (numRows - 1) * 2;
    }

    private String findLine(String s, int between, char[] chars, int index) {
        for (int i = 0; i < chars.length; i++) {
            chars[i] = s.charAt(index);
            index += between;
        }
        return new String(chars);
    }

    private void populateChars(char[] chars, String s, int between, int index) {
        for (int i = 0; i < chars.length; i++) {
            chars[i] = s.charAt(index);
            index += between;
        }
    }

    private char[] middleLine(String s, int numRows, int lineNumber) {
        int between = getBetween(numRows);
        char[] middleChars = new char[(s.length() + between - numRows - (numRows - lineNumber)) / between];
        {
            int index = numRows - 1 + (numRows - lineNumber);
            populateChars(middleChars, s, between, index);
        }
        char[] chars = new char[(s.length() + between - lineNumber) / between];
        {
            int index = lineNumber - 1;
            populateChars(chars, s, between, index);
        }
        char lastCharacter = chars.length == middleChars.length
                ? middleChars[middleChars.length - 1]
                : chars[chars.length - 1];
        char[] merge = new char[middleChars.length + chars.length];
        for (int i = 0; i < middleChars.length * 2; i++) {
            merge[i] = i % 2 == 0 ? chars[i / 2] : middleChars[i / 2];
        }
        merge[merge.length - 1] = lastCharacter;
        return merge;
    }
}

//Input: s = "PAYPALISHIRING12", numRows = 4
//Output: "PINALSIGYAHR1PI2"
//Explanation:
//P     I    N
//A   L S  I G
//Y A   H R  1
//P     I    2

//