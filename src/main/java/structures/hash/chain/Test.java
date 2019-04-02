package structures.hash.chain;

import java.math.BigInteger;

public class Test {

    public static void main(String[] args) {
        System.out.println( (2 / 5) + " " + (2 % 5) );
        System.out.println( (-2 / 5) + " " + (-2 % 5) );
        System.out.println( (2 / -5) + " " + (2 % -5) );
        System.out.println( (-2 / -5) + " " + (-2 % -5) );

        System.out.println("-------");

        System.out.println( BigInteger.valueOf(2).mod(BigInteger.valueOf(5)) );
        System.out.println( BigInteger.valueOf(-2).mod(BigInteger.valueOf(5)) );
//        System.out.println((int)'w');
    }
}
