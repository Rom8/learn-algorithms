package divide.quick;

public class QuickSort {

    public void quickSort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
    }

    private void quickSort(int[] numbers, int left, int right) {
        while (left < right) {
            int middle = partition(numbers, left, right);
            quickSort(numbers, left, middle - 1);
            left = middle + 1;
        }
    }

    private int partition(int[] numbers, int left, int right) {
        int partitionElement = numbers[left];
        int partitionIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (numbers[i] <= partitionElement) {
                swap(numbers, ++partitionIndex, i);
            }
        }
        swap(numbers, left, partitionIndex);
        return partitionIndex;
    }

    private void swap(int[] numbers, int a, int b){
        int temp = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = temp;
    }

}
