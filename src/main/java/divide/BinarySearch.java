package divide;

import java.util.Scanner;
import java.util.function.IntFunction;

public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        binarySearch.read();
        int[] result = binarySearch.find();
        for (int i :
                result) {
            System.out.print(i + " ");
        }
    }

    private int [] searchNumbers;
    private int [] keyNumbers;

    private void read() {
        //Paths.get("binarySearch.txt")
        try (Scanner scanner = new Scanner(System.in)) {

            populateLine(scanner, size -> {
                searchNumbers = new int[size];
                return searchNumbers;
            });

            populateLine(scanner, size -> {
                keyNumbers = new int[size];
                return keyNumbers;
            });
        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void populateLine(Scanner scanner, IntFunction<int[]> constructor) {
        String[] line = scanner.nextLine().split(" ");
        int size = Integer.valueOf(line[0]);
        int[] numbers = constructor.apply(size);
        for (int i = 0; i < size; i++) {
            numbers[i] = Integer.valueOf(line[i+1]);
        }
    }

    private int[] find() {
        int[] result = new int[keyNumbers.length];
        for (int i = 0; i < keyNumbers.length; i++) {
            result[i] = find(keyNumbers[i]);
        }
        return result;
    }

    private int find(int key){
        int left = 0;
        int right = searchNumbers.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (searchNumbers[middle] == key) {
                return middle + 1;
            }
            if (searchNumbers[middle] > key) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
