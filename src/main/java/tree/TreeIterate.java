package tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.*;

public class TreeIterate {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("binaryTree1.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            String[] nodeLine = br.readLine().split(" ");
            nodes[i] = new Node(parseInt(nodeLine[0]),
                                parseInt(nodeLine[1]),
                                parseInt(nodeLine[2]));
        }
        StringBuilder inOrder = new StringBuilder();
        inOrder(nodes, 0, inOrder);

        StringBuilder preOrder = new StringBuilder();
        preOrder(nodes, 0, preOrder);

        StringBuilder postOrder = new StringBuilder();
        postOrder(nodes, 0, postOrder);

        System.out.println(inOrder);
        System.out.println(preOrder);
        System.out.println(postOrder);
    }

    private static void inOrder(Node[] nodes, int index, StringBuilder result) {
        if (index == -1) {
            return;
        }
        inOrder(nodes, nodes[index].left, result);
        result.append(nodes[index].key).append(' ');
        inOrder(nodes, nodes[index].right, result);
    }

    private static void preOrder(Node[] nodes, int index, StringBuilder result) {
        if (index == -1) {
            return;
        }
        result.append(nodes[index].key).append(' ');
        preOrder(nodes, nodes[index].left, result);
        preOrder(nodes, nodes[index].right, result);
    }

    private static void postOrder(Node[] nodes, int index, StringBuilder result) {
        if (index == -1) {
            return;
        }
        postOrder(nodes, nodes[index].left, result);
        postOrder(nodes, nodes[index].right, result);
        result.append(nodes[index].key).append(' ');
    }
}

class Node {
    int key;
    int left;
    int right;

    public Node(int key, int left, int right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
}