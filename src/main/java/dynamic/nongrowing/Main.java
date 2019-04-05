package dynamic.nongrowing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PrimitiveIterator;

/**
 *  O (n^2) solution =(
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("nongrowing.txt")));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        int[] numbers = new int[n + 1];
        PrimitiveIterator.OfInt iterator = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .iterator();
        for (int i = 1; i <= n; i++) {
            numbers[i] = iterator.nextInt();
        }

        //solution
        int[] nongrowing = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nongrowing[i] = 1;
            for (int j = 1; j < i; j++) {
                if (numbers[j] >= numbers[i] && nongrowing[j] + 1 > nongrowing[i]) {
                    nongrowing[i] = nongrowing[j] + 1;
                }
            }
        }
        int answer = 1;
        int index = 1;
        for (int i = 1; i <= n; i++) {
            if (answer < nongrowing[i]) {
                answer = nongrowing[i];
                index = i;
            }
        }
        System.out.println(answer);
//        for (int i = 1; i <= n; i++) {
//            System.out.print(nongrowing[i] + " ");
//        }
//        System.out.println();
        int biggest = answer;
        int[] result = new int[answer + 1];
        result[answer] = index;
        int resIndex = answer - 1;
        int previusValue = numbers[index];
        for (int i = index - 1; i > 0; i--) {
            if (nongrowing[i] + 1 == biggest && numbers[i] >= previusValue) {
                result[resIndex--] = i;
                biggest = nongrowing[i];
                previusValue = numbers[i];
                if (nongrowing[i] == 1) {
                    break;
                }
            }
        }
        StringBuilder toPrint = new StringBuilder();
        for(int i = 1; i <= answer; i++) {
            toPrint.append(result[i]).append(' ');
        }
        System.out.println(toPrint);
    }
}
