package dynamic.nongrowing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PrimitiveIterator;

/**
 *  O (n^2) solution =(
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("nongrowing.txt")));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        int[] a = new int[n];
        PrimitiveIterator.OfInt iterator = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .iterator();
        for (int i = 0; i < n; i++) {
            a[i] = iterator.nextInt();
        }

        //N*logN
        int[] d = new int[n + 1];
        d[0] = Integer.MIN_VALUE;
        for (int i=1; i<=n; ++i) {
            d[i] = Integer.MAX_VALUE;
        }
        for (int i=0; i<n; i++) {
            for (int j=1; j<=n; j++) {
                if (d[j-1] < a[i] && a[i] < d[j]) {
                    d[j] = a[i];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(d[i] + " ");
        }

    }
}
