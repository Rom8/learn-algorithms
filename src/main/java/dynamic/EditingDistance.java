package dynamic;

import java.io.*;

public class EditingDistance {

    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("editingdistance.txt")));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String from = bufferedReader.readLine();
        String to = bufferedReader.readLine();

        int n = from.length() + 1;
        int m = to.length() + 1;

        int[][] d = new int[n][m];
        for(int i = 0; i < n; i++) {
            d[i][0] = i;
        }
        for(int i = 0; i < m; i++) {
            d[0][i] = i;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int c = diff(from.charAt(i - 1), to.charAt(j - 1));
                d[i][j] = min(d[i-1][j] + 1, d[i][j-1] + 1, d[i-1][j-1] + c);
            }
        }
        System.out.println(d[n-1][m-1]);
    }

    private static int diff(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int... values) {
        int min = values[0];
        for (int i = 1; i < values.length; i++) {
            min = Math.min(min, values[i]);
        }
        return min;
    }
}
