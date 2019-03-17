package structures.priorityqueue.parallel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLineArgs = br.readLine().split(" ");
        int processors = Integer.parseInt(firstLineArgs[0]);
        long[] times = Stream.of(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        System.out.println(new Main(processors, times).calculate());
    }

    private final long[] times;
    private final Processor[] heap;

    public Main(int processors, long[] times) {
        this.times = times;
        heap = new Processor[processors];
    }

    public String calculate() {
        for (int i = 0; i < heap.length; i++) {
            heap[i] = new Processor(i);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times.length; i++) {
            Processor min = heap[0];
            result.append(min.getProcessorIndex())
                    .append(' ')
                    .append(min.getTaskTime())
                    .append('\n');
            min.setTaskTime(times[i] + min.getTaskTime());
            siftDown(0);
        }
        return result.toString();
    }

    private void siftDown(int i) {
        Comparator<Processor> comparator = Comparator
                .comparingLong(Processor::getTaskTime)
                .thenComparing(Processor::getProcessorIndex);
        do {
            int minIndex = i;
            int l = leftChild(i);
            if (l < heap.length && comparator.compare(heap[l], heap[minIndex]) < 0) {
                minIndex = l;
            }
            int r = rightChild(i);
            if (r < heap.length && comparator.compare(heap[r], heap[minIndex]) < 0) {
                minIndex = r;
            }
            if (i != minIndex) {
                swap(i, minIndex);
                i = minIndex;
            } else {
                break;
            }
        } while (true);
    }

    private void swap(int i, int minIndex) {
        Processor temp = heap[i];
        heap[i] = heap[minIndex];
        heap[minIndex] = temp;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2*i + 2;
    }

}

class Processor {

    private final int processorIndex;
    private long taskTime;

    public Processor(int processorIndex) {
        this.processorIndex = processorIndex;
    }

    public int getProcessorIndex() {
        return processorIndex;
    }

    public long getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(long taskTime) {
        this.taskTime = taskTime;
    }
}