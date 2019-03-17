package divide.quick;

import java.util.Comparator;

public class QuickSort3Objects<T> {

    private Comparator<T> comparator;

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void quickSort(T[] objects) {
        quickSort(objects, 0, objects.length - 1);
    }

    private void quickSort(T[] elements, int left, int right) {
        while (left < right) {
            Segment segment = partition(elements, left, right);
            quickSort(elements, left, segment.getLeft() - 1);
            left = segment.getRight() + 1;
        }
    }

    private Segment partition(T[] elements, int left, int right) {
        T partitionElement = elements[left];
        int partitionIndexLeft = left;
        int partitionIndexRight = left;
        for (int i = left + 1; i <= right; i++) {
            if (comparator.compare(elements[i], partitionElement) < 0) {
                swap(elements, ++partitionIndexLeft, i);
                partitionIndexRight++;
            } else if (comparator.compare(elements[i], partitionElement) == 0) {
                swap(elements, ++partitionIndexRight, i);
            }
        }
        swap(elements, left, partitionIndexLeft);
        return new Segment(partitionIndexLeft, partitionIndexRight);
    }

    private void swap(T[] elements, int a, int b){
        T temp = elements[a];
        elements[a] = elements[b];
        elements[b] = temp;
    }

}
