package greedy.priority;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.IntConsumer;

public class Main {

    public static void main(String[] args) {
        new Main().read();
    }

    private int[] heap;

    private int size;

    private int parentIndex(int index) {
        return index/2;
    }

    private int descendantLeft(int index) {
        return 2 * index;
    }

    private int descendantRight(int index) {
        return 2 * index + 1;
    }

    private void insert(int value) {
        heap[++size] = value;
        siftUp(size);
    }

    private void siftUp(int elementIndex) {
        int index = elementIndex;
        int parent = parentIndex(index);
        while (parent != 0 && heap[parent] < heap[index]) {
            swap(index, parent);
            index = parent;
            parent = parentIndex(parent);
            if (parent == 0) {
                break;
            }
        }
        siftDown(index);
    }

    private void extractMax(IntConsumer consumer) {
        int max = heap[1];
        heap[1] = heap[size];
        heap[size] = 0;
        size--;
        siftDown(1);
        consumer.accept(max);
    }

    private void siftDown(int index) {
        while (true) {
            int descendantLeft = descendantLeft(index);
            if (descendantLeft > size) {
                break;
            }
            int descendantRight = descendantRight(index);
            if (descendantRight > size) {
                if (heap[index] >= heap[descendantLeft]) {
                    break;
                }
                swap(index, descendantLeft);
                break;
            } else if (heap[index] >= heap[descendantLeft] && heap[index] >= heap[descendantRight]) {
                break;
            }
            int maxDescIndex = heap[descendantLeft] > heap[descendantRight]
                    ? descendantLeft
                    : descendantRight;
            swap(index, maxDescIndex);
            index = maxDescIndex;
        }
    }

    private void swap(int indexOne, int indexTwo) {
        int temp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    private void read() {
        //new File("input.txt"))
        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            int operationsQuantity = Integer.valueOf(scanner.nextLine());
            heap = new int[operationsQuantity+1];
            while (scanner.hasNextLine()){
                String operation = scanner.nextLine();
                if (operation.startsWith("Insert")) {
                    //insert
                    int insertValue = Integer.valueOf(operation.split(" ")[1]);
                    insert(insertValue);
                } else if ("ExtractMax".equals(operation)) {
                    extractMax(System.out::println);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
