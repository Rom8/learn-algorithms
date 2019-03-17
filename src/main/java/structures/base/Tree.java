package structures.base;

import java.util.Scanner;

public class Tree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = scanner.nextInt();
        }
        int maxHeight = 1;
        for(int i = 0; i < n; i++) {
            int height = 1;
            int address = nodes[i];
            while (address != -1) {
                address = nodes[address];
                height++;
            }
            maxHeight = maxHeight > height ? maxHeight : height;
        }
        System.out.println(maxHeight);
    }
}
