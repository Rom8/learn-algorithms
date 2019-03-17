package greedy.huffman;

import java.util.*;

public class HuffmanCodeDencode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lettersQuantity = scanner.nextInt();
        int lineLength = scanner.nextInt();

        Node root = new Node();

        for (int i = 0; i < lettersQuantity; i++) {
            String line = scanner.next();
            String[] split = line.split(": ");
            char character = split[0].charAt(0);
            char[] bitLine = scanner.next().toCharArray();
            Node node = root;
            for (char bit: bitLine) {
                node = node.createNextNode(Character.getNumericValue(bit));
            }
            node.setCharacter(character);
        }

        char[] encodedLine = scanner.next().toCharArray();
        StringBuilder resultLine = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        for (char bit: encodedLine) {
            Node node = stack.peek().readNext(Character.getNumericValue(bit));
            if (node == null) {
                resultLine.append(stack.peek().character);
                stack.clear();
                stack.push(root);
                node = stack.peek().readNext(Character.getNumericValue(bit));
            }
            stack.push(node);
        }
        resultLine.append(stack.peek().character);

        System.out.println(resultLine);
    }

    private static class Node {
        //invariant: both are null XOR both are not null
        private Node zero;
        private Node one;

        //has non-empty value if if leaf
        private Character character;

        private int bit;

        //for root node
        public Node() {
        }

        private Node(int bit, Node parent) {
            this.bit = bit;
            if (bit == 0) {
                parent.zero = this;
            } else {
                parent.one = this;
            }
        }

        public Node createNextNode(int bit) {
            return bit == 0
                    ? ( zero == null
                        ? new Node(0, this)
                        : zero)
                    : ( one == null
                        ? new Node(1, this)
                        : one) ;
        }

        public Node readNext(int bit) {
            return bit == 0 ? zero : one;
        }

        public void setCharacter(Character character) {
            this.character = character;
        }

        public Character getCharacter() {
            return character;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(zero, node.zero) &&
                    Objects.equals(one, node.one) &&
                    Objects.equals(character, node.character);
        }

        @Override
        public int hashCode() {
            return Objects.hash(zero, one, character);
        }

    }
}
