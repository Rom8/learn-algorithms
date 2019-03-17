package divide.noncomporison;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.stream.Stream;

/**
 * Первая строка содержит число 1≤n≤10^4, вторая — n натуральных чисел, не превышающих 10.
 * Выведите упорядоченную по неубыванию последовательность этих чисел.
 *
 * сортировка подсчётом
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Reader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        int n = Integer.parseInt(br.readLine());
        String numbersLine = br.readLine();
        int[] numbers = Stream.of(numbersLine.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] sorted = new Main().countSort(numbers);
        StringBuilder result = new StringBuilder();
        for (int num: sorted) {
            result.append(num).append(' ');
        }
        System.out.print(result);
    }

    private static final int M = 10;

    public int[] countSort(int[] numbers) {
        int[] counted = new int[M + 1];
        for (int number : numbers) {
            counted[number]++;
        }
        for (int i = 2; i <= M; i++) {
            counted[i] += counted[i-1];
        }
        int[] result = new int[numbers.length];
        for (int i = numbers.length - 1; i >= 0; i--) {
            result[--counted[numbers[i]]] = numbers[i];
        }
        return result;
    }
}
