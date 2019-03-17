package divide.quick;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort3 {

    public void quickSort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
    }

    private void quickSort(int[] numbers, int left, int right) {
        while (left < right) {
            Segment segment = partition(numbers, left, right);
            quickSort(numbers, left, segment.getLeft() - 1);
            left = segment.getRight() + 1;
        }
    }

    private Segment partition(int[] numbers, int left, int right) {
        int randomIndex = ThreadLocalRandom.current().nextInt(left, right + 1);
        swap(numbers, left, randomIndex);
        int partitionElement = numbers[left];
        int partitionIndexLeft = left;
        int partitionIndexRight = right;
        for (int i = left + 1; i <= partitionIndexRight; ) {
            if (numbers[i] > partitionElement) {
                swap(numbers, partitionIndexRight--, i);
            } else if (numbers[i] < partitionElement) {
                swap(numbers, ++partitionIndexLeft, i++);
            } else {
                i++;
            }

        }
        swap(numbers, left, partitionIndexLeft);
        return new Segment(partitionIndexLeft, partitionIndexRight);
    }

    private void swap(int[] numbers, int a, int b){
        int temp = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = temp;
    }

}
