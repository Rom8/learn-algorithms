package tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Rope {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rope1.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        int n = parseInt(br.readLine());
        for (int l = 0; l < n; l++) {
            String[] lineNums = br.readLine().split(" ");
            int i = parseInt(lineNums[0]);
            int j = parseInt(lineNums[1]);
            int k = parseInt(lineNums[2]);
        }

    }
}
