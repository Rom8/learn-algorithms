package structures.base;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class Parentheses {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        new Parentheses()
                .new ParenthesesProcessor().analyzeLine(line, System.out::println);
    }

    class ParenthesesProcessor {

        Stack stack = new Stack();
        int position;

        public void analyzeLine(String line, Consumer<Object> consumer) {
            for (char character: line.toCharArray()) {
                int result = read(character);
                if (result != 0) {
                    consumer.accept(result);
                    return;
                }
            }
            Stack.Element element = null;
            while (!stack.isEmpty()) {
                element = stack.pop();
            }
            if (element != null) {
                consumer.accept(element.position);
                return;
            }
            consumer.accept("Success");
        }

        private int read(char character) {
            position++;
            if (Bracket.pairForLeft(character).isPresent()) {
                stack.push(character, position);
                return 0;
            }
            Optional<Character> left = Bracket.pairForRight(character);
            if (!left.isPresent()) {
                return 0;
            }
            if (stack.isEmpty()) {
                return position;
            }
            Stack.Element element = stack.pop();
            if (element.bracket != left.get()) {
                return position;
            }
            return 0;
        }
    }

    private enum Bracket {

        FIRST('[', ']'),
        SECOND('(', ')'),
        THIRD('{', '}');

        final char left;
        final char right;

        Bracket(char left, char right) {
            this.left = left;
            this.right = right;
        }

        static Optional<Character> pairForLeft(char character) {
            return bracket(character, bracket -> bracket.left, bracket -> bracket.right);
        }

        static Optional<Character> pairForRight(char character) {
            return bracket(character, bracket -> bracket.right, bracket -> bracket.left);
        }

        private static Optional<Character> bracket(char character,
                                                   Function<Bracket, Character> compare,
                                                   Function<Bracket, Character> resulting) {
            for (Bracket bracket: values()) {
                if (compare.apply(bracket) == character) {
                    return Optional.of(resulting.apply(bracket));
                }
            }
            return Optional.empty();
        }
    }

    private class Stack {
        Element head;

        void push(char bracket, int position) {
            head = new Stack.Element(bracket, position, head);
        }

        Element pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty.");
            }
            Element result = head;
            head = head.next;
            return result;
        }

        Element top() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty.");
            }
            return head;
        }

        boolean isEmpty() {
            return head == null;
        }

        class Element {
            char bracket;
            int position;
            Element next;

            Element(char bracket, int position, Element next) {
                this.bracket = bracket;
                this.position = position;
                this.next = next;
            }
        }
    }

}
