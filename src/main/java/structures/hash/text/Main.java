package structures.hash.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("text1.txt"));
//        Scanner scanner = new Scanner(System.in);

        String pattern = scanner.nextLine();
        String text = scanner.nextLine();

        String result = new TextFinder().find(pattern, text);
        System.out.println(result);
    }

}

class TextFinder {

    final BigInteger p = BigInteger.valueOf(1_000_000_007L);
    final BigInteger x = BigInteger.valueOf(263L);

    public String find(String pattern, String text) {
        long[] hashes = new long[text.length() - pattern.length() + 1];

        BigInteger biggestXPower = BigInteger.valueOf(pattern.length() - 1);
        BigInteger biggestX = x.modPow(biggestXPower, p);
        long patternHash = hash(pattern, 0, pattern.length());
        hashes[hashes.length - 1] = hash(text, hashes.length - 1, text.length());


        for (int i = hashes.length - 2; i >= 0; i--) {
            int ascii = text.charAt(i + pattern.length());
            BigInteger asciiByModule = BigInteger.valueOf(ascii).mod(p);
            BigInteger multiplication = (biggestX.multiply(asciiByModule)).mod(p);
            long difference = hashes[i + 1] - multiplication.longValueExact();
            difference = (difference + p.longValue()) % p.longValue();
            long firstSummand = ( difference * x.longValue() ) % p.longValue();
            long hash = ( firstSummand + (long) text.charAt(i) ) % p.longValue();
            hashes[i] = hash;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < hashes.length; i++) {
            if (patternHash == hashes[i] && pattern.equals(text.substring(i, i + pattern.length()))) {
                result.append(i).append(' ');
            }
        }
        return result.toString();
    }



    private long hash(String word, int from, int to) {
        BigInteger sum = BigInteger.valueOf(0L);
        for (int i = from; i < to; i++) {
            int ascii = word.charAt(i);
            BigInteger asciiByModule = BigInteger.valueOf(ascii).mod(p);
            BigInteger xPowByModule = x.modPow(BigInteger.valueOf(i - from), p);
            BigInteger multiplication = (asciiByModule.multiply(xPowByModule)) .mod(p);
            sum = (sum.add(multiplication)) .mod(p);
        }
        return sum.longValueExact();
    }

}