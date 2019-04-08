package tree;

import java.io.*;

import static java.lang.Integer.parseInt;

public class CheckTree {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("checkBinary1.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        BinaryTree binaryTree = new BinaryTree(n);
        for (int i = 0; i < n; i++) {
            String[] nodeLine = br.readLine().split(" ");
            binaryTree.nodes[i] = new Node2(parseInt(nodeLine[0]),
                    parseInt(nodeLine[1]),
                    parseInt(nodeLine[2]));
        }

        //check all nodes
        for (int i = 0; i < n; i++) {
            if (!binaryTree.contains(binaryTree.nodes[i].key)) {
                System.out.println("INCORRECT");
                return;
            }
        }
        System.out.println("CORRECT");
    }
}

class BinaryTree {

    final Node2[] nodes;

    BinaryTree(int n) {
        this.nodes = new Node2[n];
    }

    boolean contains(int key) {
        return contains(0, key);
    }

    private boolean contains(int index, int key) {
        if (index == -1) {
            return false;
        } else if (nodes[index].key == key) {
            return true;
        } else {
            if (key < nodes[index].key) {
                return contains(nodes[index].left, key);
            } else {
                return contains(nodes[index].right, key);
            }
        }
    }
}

class Node2 {
    int key;
    int left;
    int right;

    public Node2(int key, int left, int right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
}