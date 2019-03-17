package structures.base.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        try {
            new Main().input(new LineNumberReader(new InputStreamReader(System.in)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void input(BufferedReader bufferedReader) throws IOException {
        StackMax<Integer> stackMax = new StackMax<>(Comparator.comparingInt(Integer::intValue));

        int requestsQuantity = Integer.valueOf(bufferedReader.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < requestsQuantity; i++) {
            String command = bufferedReader.readLine();
            String[] words = command.split(" ");
            String firstWord = words[0];
            switch (firstWord) {
                case "push":
                    stackMax.push(Integer.valueOf(words[1]));
                    break;
                case "pop":
                    stackMax.pop();
                    break;
                case "max":
                    result.append(stackMax.max()).append('\n');
                    break;
            }
        }
        System.out.print(result);
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

        protected T top() {
            return head == null ? null : head.value;
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
