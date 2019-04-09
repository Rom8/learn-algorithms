package tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class CheckTreeCommon {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("checkBinaryCommonCorrect.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("checkBinaryCommonInCorrect.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        BinaryTree binaryTree = new BinaryTree(n);
        for (int i = 0; i < n; i++) {
            String[] nodeLine = br.readLine().split(" ");
            binaryTree.nodes[i] = new Node(
                    parseInt(nodeLine[0]),
                    parseInt(nodeLine[1]),
                    parseInt(nodeLine[2]));
        }

        //check all nodes
        for (int i = 0; i < n; i++) {
            if (!binaryTree.isInCorrectPosition(i, 0, binaryTree.nodes[i].key)) {
                System.out.println("INCORRECT");
                return;
            }
        }
        System.out.println("CORRECT");
    }

    static class BinaryTree {

        final Node[] nodes;

        BinaryTree(int n) {
            this.nodes = new Node[n];
        }

        public boolean isInCorrectPosition(int searchIndex, int index, int key) {
            if (index == -1) {
                return false;
            }
            if (key < nodes[index].key) {
                return isInCorrectPosition(searchIndex, nodes[index].left, key);
            } else if (nodes[index].key < key) {
                return isInCorrectPosition(searchIndex, nodes[index].right, key);
            } else {
                if (searchIndex == index) {
                    return true;
                } else {
                    return isInCorrectPosition(searchIndex, nodes[index].right, key);
                }
            }
        }
    }

    static class Node {
        int key;
        int left;
        int right;

        public Node(int key, int left, int right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }
}
