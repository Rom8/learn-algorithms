package fibonacci;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int m = scanner.nextInt();

        int a = 0;
        int b = 1;

        for (long i = 2; i <= n; i++) {
            int c = (a + b) % m;
            a = b;
            b = c;
            if (a == 0 && b == 1) {
                //period found!
                long period = i - 1;
                a = 0;
                b = 1;
                i = 1;
                n = n % period;
//                System.out.println("period = " + period);
                if (n == 0) {
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(b % m);
    }
}
