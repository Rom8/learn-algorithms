package greedy;

import java.util.*;

public class SegmentAnPoints {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int segmentsCount = scanner.nextInt();
        Set<Segment> segments = new TreeSet<>();

        int counter = 0;
        do {
            int first = scanner.nextInt();
            int second = scanner.nextInt();
            segments.add(new Segment(first, second));
            counter += 2;
        } while (scanner.hasNextLine() && counter < segmentsCount*2);

        Deque<Integer> answerPoints = new LinkedList<>();
        for (Iterator<Segment> iterator = segments.iterator(); iterator.hasNext();) {
            Segment segment = iterator.next();
            if (answerPoints.isEmpty()) {
                answerPoints.addLast(segment.second);
                continue;
            }
            Integer pointsLast = answerPoints.getLast();
            if (!(pointsLast >= segment.first && pointsLast <= segment.second)) {
                answerPoints.addLast(segment.second);
            }
        }
        System.out.println(answerPoints.size());
        while (!answerPoints.isEmpty()) {
            System.out.print(answerPoints.pollFirst() + " ");
        }
    }

    static class Segment implements Comparable<Segment> {
        private int first;
        private int second;

        public Segment(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Segment segment = (Segment) o;
            return first == segment.first &&
                    second == segment.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public int compareTo(Segment o) {
            int secondDiff = second - o.second;
            return secondDiff != 0
                    ? secondDiff
                    : first - o.first;
        }
    }
}
