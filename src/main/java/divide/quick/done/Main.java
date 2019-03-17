package divide.quick.done;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiPredicate;

public class Main {
    public static void main(String[] args) throws IOException {
        PointsAndSegments pointsAndSegments = new PointsAndSegments();
        Reader reader = new InputStreamReader(System.in);
        pointsAndSegments.read(reader);
        StringBuilder print = new StringBuilder();
        int[] result = pointsAndSegments.result();
        for (int i = 0; i < result.length; i++) {
            print.append(result[i]).append(' ');
        }
        System.out.print(print);
        reader.close();
    }
}

class PointsAndSegments {

    private int segmentsQuantity;
    private int pointsQuantity;
    private int[] segmentsBegin;
    private int[] segmentsEnd;
    private int[] points;

    public static final BiPredicate<Integer, Integer> CHECKER_LEFT = (key, p) -> key >= p;
    public static final BiPredicate<Integer, Integer> CHECKER_RIGHT = (key, p) -> key > p;

    public void read(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        String[] firstLine = br.readLine().split(" ");
        segmentsQuantity = Integer.parseInt(firstLine[0]);
        segmentsBegin = new int[segmentsQuantity];
        segmentsEnd = new int[segmentsQuantity];
        pointsQuantity = Integer.parseInt(firstLine[1]);
        points = new int[pointsQuantity];
        for (int i = 0; i < segmentsQuantity; i++) {
            String[] borders = br.readLine().split(" ");
            segmentsBegin[i] = Integer.parseInt(borders[0]);
            segmentsEnd[i] = Integer.parseInt(borders[1]);
        }
        String[] pointsLine = br.readLine().split(" ");
        for (int i = 0; i < pointsQuantity; i++) {
            points[i] = Integer.parseInt(pointsLine[i]);
        }
        br.close();
    }

    public int[] result() {
        int[] resultPoints = new int[pointsQuantity];
        new QuickSort3().quickSort(segmentsBegin);
        new QuickSort3().quickSort(segmentsEnd);
        for (int i = 0; i < pointsQuantity; i++) {
            resultPoints[i] = lessThanByLeft(points[i]) - lessThanByRight(points[i]);
        }
        return resultPoints;
    }

    private int lessThanByLeft(int point) {
        return lessThanQuantity(segmentsBegin, point, CHECKER_LEFT,
                0, segmentsQuantity - 1);
    }

    private int lessThanByRight(int point) {
        return lessThanQuantity(segmentsEnd, point, CHECKER_RIGHT,
                0, segmentsQuantity - 1);
    }

    private int lessThanQuantity(int[] segmentPoints, int key, BiPredicate<Integer, Integer> checker,
                                 int left, int right) {
        if (left == right) {
            return checker.test(key, segmentPoints[left]) ? left + 1 : left;
        }
        int middle = (left + right) / 2;
        if (checker.test(key, segmentPoints[middle])) {
            return lessThanQuantity(segmentPoints, key, checker, middle + 1, right);
        } else {
            return lessThanQuantity(segmentPoints, key, checker, left, middle);
        }
    }

}

class QuickSort3 {

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

    private void swap(int[] numbers, int a, int b) {
        int temp = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = temp;
    }

}

class Segment {

    private final int left;
    private final int right;

    public Segment(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

}
