package greedy;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Summands {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int sum = 0;
        int i = 1;
        for (; sum <= n; i++) {
            sum +=i;
        }
        sum -= --i;
        sum -= --i;

        int last = n - sum;
        System.out.println(i);
        IntStream.rangeClosed(1, i-1)
                .forEach( num -> System.out.print(num + " "));
        System.out.print(last);
    }
}
