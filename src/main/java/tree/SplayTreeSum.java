package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class SplayTreeSum {

    public static void main(String[] args) throws IOException {
        SplayTree splayTree = new SplayTree("Main tree");

        Map<String, Command> commands = new HashMap<>();
        commands.put("+", new Insert(splayTree));
        commands.put("-", new Remove(splayTree));
        commands.put("?", new Search(splayTree));
        commands.put("s", new Sum(splayTree));

//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("splayTree1.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] commandArgs = br.readLine().split(" ");
            commands.get(commandArgs[0]).execute(commandArgs);
        }

    }
}

class SplayTree {

    private String name;

    private Node root;

    public SplayTree(String name) {
        this(null, name);
    }

    public SplayTree(Node root, String name) {
        this.root = root;
        this.name = name;
    }

    private class Node {
        private long value;

        private long sum;

        private Node parent;
        private Node leftChild;
        private Node rightChild;

        public Node(long value) {
            this.value = value;
            this.sum = value;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public void makeRoot() {
            parent = null;
            root = this;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
            if (leftChild != null) {
                leftChild.setParent(this);
            }
            updateSum();
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
            if (rightChild != null) {
                rightChild.setParent(this);
            }
            updateSum();
        }

        public long getSum() {
            return sum;
        }

        private void updateSum() {
            sum = value;
            if (leftChild != null) {
                sum += leftChild.getSum();
            }
            if (rightChild != null) {
                sum += rightChild.getSum();
            }
        }
    }

    public void insert(long value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node insertedNode = insert(value, root);
        splay(insertedNode);
    }

    private Node insert(long value, Node node) {

        while (value != node.getValue()) {

            if (value < node.getValue()) {
                if (node.getLeftChild() == null) {
                    node.setLeftChild(new Node(value));
                    return node.getLeftChild();
                } else {
                    node = node.getLeftChild();
                }
            }
            if (value > node.getValue()) {
                if (node.getRightChild() == null) {
                    node.setRightChild(new Node(value));
                    return node.getRightChild();
                } else {
                    node = node.getRightChild();
                }
            }
        }
        return node;
    }

    public void remove(long value) {
        if (root == null) {
            return;
        }
        Node node = searchForParentOrNode(value);
        splay(node);
        if (node.getValue() != value) {
            return;
        }
        Node leftChild = node.getLeftChild();
        Node rightChild = node.getRightChild();
        if (leftChild == null && rightChild == null) {
            root = null;
            return;
        }
        if (leftChild == null) {
            rightChild.makeRoot();
            return;
        }
        if (rightChild == null) {
            leftChild.makeRoot();
            return;
        }
        leftChild.makeRoot();
        Node max = max(leftChild);
        splay(max);
        max.setRightChild(rightChild);
    }

    public boolean search(long value) {
        if (root == null) {
            return false;
        }
        Node node = searchForParentOrNode(value);
        splay(node);
        return node.getValue() == value;
    }

    private Node searchForParentOrNode(long value) {
        Node node = root;
        while (value != node.getValue()) {
            if (value < node.getValue()) {
                if (node.getLeftChild() == null) {
                    return node;
                } else {
                    node = node.getLeftChild();
                }
            }
            if (value > node.getValue()) {
                if (node.getRightChild() == null) {
                    return node;
                } else {
                    node = node.getRightChild();
                }
            }
        }
        return node;
    }

    public long sum(long from, long to) {
        if (from > to) {
            throw new IllegalArgumentException("from > to, but has to be from <= to");
        }
        if (root == null) {
            return 0;
        }
        //split into two parts
        Node fromNode = searchForParentOrNode(from);
        splay(fromNode);
        if (fromNode.getValue() > to) {
            return 0;
        }
        //we are interested in a right part
        if (fromNode.getRightChild() == null) {
            if (fromNode.getValue() >= from && fromNode.getValue() <= to) {
                return fromNode.getValue();
            } else {
                return 0;
            }
        }
        //split trees
        Node leftChild = fromNode.getLeftChild();
        fromNode.setLeftChild(null);

        boolean fromRemoved = false;
        if (fromNode.getValue() < from) {
            remove(fromNode.getValue());
            fromRemoved = true;
        }

        //find right border
        Node toNode = searchForParentOrNode(to);
        splay(toNode);
        if (toNode.getValue() < from) {
            return 0;
        }
        long result = 0;
        if (toNode.getValue() <= to) {
            result += toNode.getValue();
        }
        if (toNode.getLeftChild() != null) {
            result += toNode.getLeftChild().getSum();
        }

        //reconnect trees
        if (fromRemoved) {
            fromNode.setLeftChild(leftChild);
            fromNode.setRightChild(toNode);
            fromNode.makeRoot();
        } else {
            Node minRight = min(toNode);
            splay(minRight);
            minRight.setLeftChild(leftChild);
        }
        return result;
    }

    private Node max(Node tree) {
        Node max = tree;
        while (max.getRightChild() != null) {
            max = max.getRightChild();
        }
        return max;
    }

    private Node min(Node tree) {
        Node min = tree;
        while (min.getLeftChild() != null) {
            min = min.getLeftChild();
        }
        return min;
    }

    private void splay(Node u) {
        while (u != root) {
            if (u.getParent() == root) {
                if (root.getLeftChild() == u) {
                    zig(u);
                } else {
                    zag(u);
                }
            } else {
                Node parent = u.getParent();
                Node grandParent = parent.getParent();
                if (parent.getLeftChild() == u) {
                    if (grandParent.getLeftChild() == parent) {
                        zigzig(u);
                    } else {
                        zigzag(u);
                    }
                } else {
                    if (grandParent.getLeftChild() == parent) {
                        zagzig(u);
                    } else {
                        zagzag(u);
                    }
                }
            }
        }
    }

    private void zig(Node u) {
        Node a = root;
        a.setLeftChild(u.getRightChild());
        u.setRightChild(a);
        u.makeRoot();
    }

    private void zag(Node u) {
        Node a = root;
        a.setRightChild(u.getLeftChild());
        u.setLeftChild(a);
        u.makeRoot();
    }

    private void zigzig(Node u) {
        Node a = u.getParent();
        Node b = a.getParent();
        Consumer<Node> childSetter = childSetter(b);
        Node treeB = u.getRightChild();
        Node treeC = a.getRightChild();
        b.setLeftChild(treeC);
        a.setLeftChild(treeB);
        a.setRightChild(b);
        u.setRightChild(a);
        childSetter.accept(u);
    }

    private void zigzag(Node u) {
        Node b = u.getParent();
        Node a = b.getParent();
        Consumer<Node> childSetter = childSetter(a);
        Node treeB = u.getLeftChild();
        Node treeC = u.getRightChild();
        a.setRightChild(treeB);
        b.setLeftChild(treeC);
        u.setLeftChild(a);
        u.setRightChild(b);
        childSetter.accept(u);
    }

    private void zagzig(Node u) {
        Node b = u.getParent();
        Node a = b.getParent();
        Consumer<Node> childSetter = childSetter(a);
        Node treeB = u.getLeftChild();
        Node treeC = u.getRightChild();
        b.setRightChild(treeB);
        a.setLeftChild(treeC);
        u.setLeftChild(b);
        u.setRightChild(a);
        childSetter.accept(u);
    }

    private void zagzag(Node u) {
        Node b = u.getParent();
        Node a = b.getParent();
        Consumer<Node> childSetter = childSetter(a);
        Node treeB = b.getLeftChild();
        Node treeC = u.getLeftChild();
        a.setRightChild(treeB);
        b.setRightChild(treeC);
        b.setLeftChild(a);
        u.setLeftChild(b);
        childSetter.accept(u);
    }

    private Consumer<Node> childSetter(Node node) {
        Node parent = node.getParent();
        if (parent == null) {
            return Node::makeRoot;
        }
        if (parent.getLeftChild() == node) {
            return parent::setLeftChild;
        } else {
            return parent::setRightChild;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

interface Command {

    void execute(String[] commandArgs);
}

abstract class CommandBase implements Command {

    protected static long lastSumRequest;

    protected final SplayTree splayTree;

    CommandBase(SplayTree splayTree) {
        this.splayTree = splayTree;
    }

    @Override
    public void execute(String[] commandArgs) {
        if (commandArgs.length == 2) {
            execute(modFunc(parseLong(commandArgs[1])));
        } else if (commandArgs.length == 3) {
            execute(modFunc(parseLong(commandArgs[1])),
                    modFunc(parseLong(commandArgs[2])));
        }
    }

    protected void execute(long number) {
    }

    protected void execute(long from, long to) {
    }

    private long modFunc(long input) {
        return (input + lastSumRequest) % 1_000_000_001L;
    }

}

class Insert extends CommandBase {


    Insert(SplayTree splayTree) {
        super(splayTree);
    }

    @Override
    protected void execute(long number) {
        splayTree.insert(number);
    }
}

class Remove extends CommandBase {


    Remove(SplayTree splayTree) {
        super(splayTree);
    }

    @Override
    protected void execute(long number) {
        splayTree.remove(number);
    }

}

class Search extends CommandBase {

    private static final String FOUND = "Found";
    private static final String NOT_FOUND = "Not found";

    Search(SplayTree splayTree) {
        super(splayTree);
    }

    @Override
    protected void execute(long number) {
        boolean found = splayTree.search(number);
        if (found) {
            System.out.println(FOUND);
        } else {
            System.out.println(NOT_FOUND);
        }
    }

}

class Sum extends CommandBase {

    Sum(SplayTree splayTree) {
        super(splayTree);
    }

    @Override
    protected void execute(long from, long to) {
        lastSumRequest = splayTree.sum(from, to);
        System.out.println(lastSumRequest);
    }

}
