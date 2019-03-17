package structures.priorityqueue.heap;

import java.util.Scanner;

/**
 * Мин куча
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }
        new Main(numbers).buildHeap();
    }

    private final int[] numbers;
    private int swaps;
    private StringBuilder output = new StringBuilder();

    public Main(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void buildHeap() {
        for (int i = (numbers.length/2) - 1; i >=0; i--) {
            siftDown(i);
        }
        System.out.println(swaps + output.toString());
    }

    private void siftDown(int i) {
        do {
            int minIndex = i;
            int l = leftChild(i);
            if (l < numbers.length && numbers[l] < numbers[minIndex]) {
                minIndex = l;
            }
            int r = rightChild(i);
            if (r < numbers.length && numbers[r] < numbers[minIndex]) {
                minIndex = r;
            }
            if (i != minIndex) {
                swap(i, minIndex);
                swaps++;
                output.append('\n')
                        .append(i)
                        .append(' ')
                        .append(minIndex);
                i = minIndex;
            } else {
                break;
            }
        } while (true);
    }

    private void swap(int i, int minIndex) {
        int temp = numbers[i];
        numbers[i] = numbers[minIndex];
        numbers[minIndex] = temp;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2*i + 2;
    }

}
