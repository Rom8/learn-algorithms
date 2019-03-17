package divide.inversions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Main {


    public int[] mergeSort(int[] input) {
        if (input.length == 1) {
            return input;
        }
        int middle = input.length / 2;
        int[] left = new int[middle];
        int[] right = new int[input.length - middle];
        System.arraycopy(input, 0, left, 0, middle);
        System.arraycopy(input, middle, right, 0, input.length - middle);
        int[] result = merge(mergeSort(left), mergeSort(right));
        return result;
    }

    private int[] merge(int[] left, int[] right) {
        int resultLength = left.length + right.length;
        int[] result = new int[resultLength];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < resultLength; i++) {
            if (leftIndex == left.length) {
                result[i] = right[rightIndex++];
                continue;
            }
            if (rightIndex == right.length) {
                result[i] = left[leftIndex++];
                continue;
            }
            if (left[leftIndex] < right[rightIndex]) {
                result[i] = left[leftIndex++];
            } else {
                result[i] = right[rightIndex++];
            }
        }
        return result;
    }


    public int[] inversions(int[] input, AtomicLong counter) {
        if (input.length == 1) {
            return input;
        }
        int middle = input.length / 2;
        int[] left = new int[middle];
        int[] right = new int[input.length - middle];
        System.arraycopy(input, 0, left, 0, middle);
        System.arraycopy(input, middle, right, 0, input.length - middle);
        return merge(inversions(left, counter), inversions(right, counter), counter);
    }

    private int[] merge(int[] left, int[] right, AtomicLong counter) {
        int resultLength = left.length + right.length;
        int[] result = new int[resultLength];
        int leftIndex = 0;
        int rightIndex = 0;
        int inversions = 0;
        for (int i = 0; i < resultLength; i++) {
            if (leftIndex == left.length) {
                result[i] = right[rightIndex++];
                continue;
            }
            if (rightIndex == right.length) {
                result[i] = left[leftIndex++];
                counter.addAndGet(inversions);
                continue;
            }
            if (left[leftIndex] <= right[rightIndex]) {
                result[i] = left[leftIndex++];
                counter.addAndGet(inversions);
            } else {
                result[i] = right[rightIndex++];
                inversions++;
            }
        }
        return result;
    }

    public void input() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        String inputLine = bufferedReader.readLine();
        int[] inputArray = Stream.of(inputLine.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        AtomicLong inversions = new AtomicLong();
        inversions(inputArray, inversions);
        System.out.println(inversions.longValue());
    }

    public static void main(String[] args) throws IOException {
        new Main().input();
    }
}
