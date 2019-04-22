package hackerrank.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class LeftRotation {


    public void rotate(int[] numbers, int rotations) {
        int n = numbers.length;

        while (gcd(n, rotations) != 1) {
            rotate(numbers, 1);
            rotations--;
        }

        int rightRotation  = n - rotations;

        int j = 0;
        int insertIndex;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(numbers[j]);

        for (int i = 0; i < n; i++) {
            insertIndex = (j + rightRotation) % n;
            queue.add(numbers[insertIndex]);
            numbers[insertIndex] = queue.poll();
            j = insertIndex;
        }
        String resultToPrint = Arrays.stream(numbers).mapToObj(Integer::toString).collect(Collectors.joining(" "));
        System.out.println(resultToPrint);
    }

    int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if (a > b) {
            return gcd(a % b, b);
        } else {
            return gcd(a, b % a);
        }
    }
}
