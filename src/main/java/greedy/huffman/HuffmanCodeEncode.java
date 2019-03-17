package greedy.huffman;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class HuffmanCodeEncode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        Map<Character, Long> characters = s.chars()
                .mapToObj(a -> (char) a)
                .collect(groupingBy(a -> a, counting()));

        List<Node> nodes = characters
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Entry::getValue))
                .map(entry -> Node.createLeaf(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toCollection(LinkedList::new));
        out: while (nodes.size() > 1) {
            Node branchNode = Node.createBranchNode(nodes.remove(0), nodes.remove(0));
            int i = 0;
            for (; i < nodes.size(); i++) {
                if (branchNode.getTimes() <= nodes.get(i).getTimes()) {
                    nodes.add(i, branchNode);
                    continue out;
                }
            }
            nodes.add(i, branchNode);
        }
        Node root = nodes.remove(0);
        Map<String, Node> codeToNode = new TreeMap<>();
        Map<Character, String> characterToCode = new TreeMap<>();
        if (root.next(0) == null) {
            setBitValues("0", root, codeToNode, characterToCode);
        } else {
            setBitValues("", root, codeToNode, characterToCode);
        }

        StringBuilder resultLineBuilder = new StringBuilder();
        for(char c: s.toCharArray()){
            resultLineBuilder.append(characterToCode.get(c));
        }
        String resultline = resultLineBuilder.toString();


        System.out.println(codeToNode.size() + " " + resultline.length());
        codeToNode.forEach((s1, node) -> System.out.printf("%s: %s\n", node.getCharacter(), s1));
        System.out.println(resultline);
    }

    static void setBitValues(String bitPath, Node node, Map<String, Node> codeToNode, Map<Character, String> characterToCode) {
        if (node.next(0) == null) {
            codeToNode.put(bitPath, node);
            characterToCode.put(node.getCharacter(), bitPath);
        } else {
            setBitValues(bitPath + "0", node.next(0), codeToNode, characterToCode);
            setBitValues(bitPath + "1", node.next(1), codeToNode, characterToCode);
        }
    }

    private static class Node {
        //invariant: both are null XOR both are not null
        private final Node zero;
        private final Node one;

        //has non-empty value if if leaf
        private final Character character;

        // is more than 0 if is a leaf
        private final int times;

        private Node(Node zero, Node one, Character character, int times) {
            this.zero = zero;
            this.one = one;
            this.character = character;
            this.times = times;
        }

        public static Node createLeaf(Character character, int times) {
            return new Node(null, null, character, times);
        }

        public static Node createBranchNode(Node zero, Node one) {
            return new Node(zero, one, null, zero.times + one.times);
        }

        public Character getCharacter() {
            return character;
        }

        public int getTimes() {
            return times;
        }

        public Node next(int bit) {
            return bit == 0 ? zero : one;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return times == node.times &&
                    Objects.equals(zero, node.zero) &&
                    Objects.equals(one, node.one) &&
                    Objects.equals(character, node.character);
        }

        @Override
        public int hashCode() {
            return Objects.hash(zero, one, character, times);
        }

    }
}
