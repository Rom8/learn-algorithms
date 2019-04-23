package tree;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class SplayTreeSum {

    public static void main(String[] args) throws IOException {
        SplayTree splayTree = new SplayTree();

        Map<String, Command> commands = new HashMap<>();
        commands.put("+", new Insert(splayTree));
        commands.put("-", new Remove(splayTree));
        commands.put("?", new Search(splayTree));
        commands.put("s", new Sum(splayTree));

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("splayTree1.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] commandArgs = br.readLine().split(" ");
            commands.get(commandArgs[0]).execute(commandArgs);
        }

    }
}

class SplayTree {

    private Node root;

    private class Node {
        private long value;

        private Node parent;
        private Node leftChild;
        private Node rightChild;

        public Node(long value, Node parent) {
            this.value = value;
            this.parent = parent;
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
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
            if (rightChild != null) {
                rightChild.setParent(this);
            }
        }
    }

    public void insert(long value) {
        if (root == null) {
            root = new Node(value, null);
            return;
        }
        Node insertedNode = insert(value, root);
        splay(insertedNode);
    }

    private Node insert(long value, Node node) {

        while (value != node.getValue()) {

            if (value < node.getValue()) {
                if (node.getLeftChild() == null) {
                    node.setLeftChild(new Node(value, node));
                    return node.getLeftChild();
                } else {
                    node = node.getLeftChild();
                }
            }
            if (value > node.getValue()) {
                if (node.getRightChild() == null) {
                    node.setRightChild(new Node(value, node));
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
        if (node.getValue() == value) {
            splitRoot(node);
        }
    }

    public boolean search(long value) {
        if (root == null) {
            return false;
        }
        Node node = searchForParentOrNode(value);
        splay(node);
        if (node.getValue() == value) {
            System.out.println("Found");
        } else {
            System.out.println("Not Found");
        }
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

    public void sum(long from, long to) {

    }

    private void splitRoot(Node node) {

    }

    private void splay(Node u) {
        if (u == root) {
            return;
        }
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
        Node treeB = u.getRightChild();
        Node treeC = a.getRightChild();
        u.setRightChild(a);
        a.setLeftChild(treeB);
        a.setRightChild(b);
        b.setLeftChild(treeC);
    }

    private void zigzag(Node u) {
        Node b = u.getParent();
        Node a = b.getParent();
        Node treeB = u.getLeftChild();
        Node treeC = u.getRightChild();
        a.setRightChild(treeB);
        b.setLeftChild(treeC);
        u.setLeftChild(a);
        u.setRightChild(b);
    }

    private void zagzig(Node u) {
        Node b = u.getParent();
        Node a = b.getParent();
        Node treeB = u.getLeftChild();
        Node treeC = u.getRightChild();
        b.setRightChild(treeB);
        a.setLeftChild(treeC);
        u.setLeftChild(b);
        u.setRightChild(a);
    }

    private void zagzag(Node u) {
        Node b = u.getParent();
        Node a = b.getParent();
        Node treeB = b.getLeftChild();
        Node treeC = u.getLeftChild();
        a.setRightChild(treeB);
        b.setRightChild(treeC);
        b.setLeftChild(a);
        u.setLeftChild(b);
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


    Search(SplayTree splayTree) {
        super(splayTree);
    }

    @Override
    protected void execute(long number) {
        splayTree.search(number);
    }

}

class Sum extends CommandBase {

    Sum(SplayTree splayTree) {
        super(splayTree);
    }

    @Override
    protected void execute(long from, long to) {
        splayTree.sum(from, to);
    }

}