package structures.base.window;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Main windowSizes = input(new Scanner(System.in));
        windowSizes.calculate();
        windowSizes.printResult();
    }

    private int numbersSize;
    private int windowSize;
    private int[] numbers;
    private int[] result;

    public Main(int numbersSize, int windowSize, int[] numbers) {
        this.numbersSize = numbersSize;
        this.windowSize = windowSize;
        this.numbers = numbers;
        this.result = new int[numbersSize - windowSize + 1];
    }

    private static Main input(Scanner scanner) {
        int numbersSize = Integer.valueOf(scanner.nextLine());
        String numbersLine = scanner.nextLine();
        int[] numbers = Stream.of(numbersLine.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        int windowSize = Integer.valueOf(scanner.nextLine());
        return new Main(numbersSize, windowSize, numbers);
    }

    private void printResult() {
        StringBuilder toPrint = new StringBuilder();
        for (int i: result) {
            toPrint.append(i).append(' ');
        }
        System.out.println(toPrint);
    }

    public int[] calculate() {
        StackMax<Integer> stackLeft = new StackMax<>(Comparator.comparingInt(Integer::intValue));
        StackMax<Integer> stackRight = new StackMax<>(Comparator.comparingInt(Integer::intValue));

        int counter = 0;
        int resultCounter = 0;
        for (int i = 0; i < numbersSize; i++) {
            stackLeft.push(numbers[i]);
            stackRight.pop();
            counter++;
            if (counter % windowSize == 0) {
                result[resultCounter++] = stackLeft.max();
                moveToRight(stackLeft, stackRight);
            } else if (!isEmpty(stackRight)){
                result[resultCounter++] = stackLeft.max() > stackRight.max() ? stackLeft.max() : stackRight.max();
            }
        }

        return result;
    }

    private void moveToRight(StackMax<Integer> stackLeft, StackMax<Integer> stackRight){
        while (!isEmpty(stackLeft)) {
            stackRight.push(stackLeft.pop());
        }
    }

    private boolean isEmpty(StackMax<Integer> stack) {
        return stack.top() == null;
    }

    private static class Stack<T> {

        private Node head;

        public void push(T element) {
            head = new Node(element, head);
        }

        public T pop() {
            T result = head == null ? null : head.value;
            head = head == null ? null : head.next;
            return result;
        }

        public T top() {
            return head == null ? null : head.value;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            Node node = head;
            while (node != null) {
                result.append(node.value).append(' ');
                node = node.next;
            }
            return result.toString();
        }

        private class Node {
            private final T value;

            private final Node next;

            private Node(T value, Node next) {
                this.value = value;
                this.next = next;
            }

        }
    }

    private static class StackMax<T> extends Stack<T> {

        private final Stack<T> maxStack = new Stack<>();

        private final Comparator<T> comparator;

        private StackMax(Comparator<T> comparator) {
            this.comparator = comparator;
        }

        @Override
        public void push(T element) {
            super.push(element);
            if (maxStack.top() == null || comparator.compare(element, maxStack.top()) > 0) {
                maxStack.push(element);
            } else {
                maxStack.push(maxStack.top());
            }
        }

        @Override
        public T pop() {
            maxStack.pop();
            return super.pop();
        }

        public T max() {
            return maxStack.top();
        }
    }
}
