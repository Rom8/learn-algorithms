package dynamic.divisible;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("divisible.txt")));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();


        //solution
        int[] divisible = new int[n];
        for (int i = 0; i < n; i++) {
            divisible[i] = 1;
            for (int j = 0; j < i; j++) {
                if (numbers[i] % numbers[j] == 0 && divisible[j] + 1 > divisible[i]) {
                    divisible[i] = divisible[j] + 1;
                }
            }
        }
        int answer = 1;
        for (int k: divisible) {
            answer = Math.max(answer, k);
        }
        System.out.println(answer);
    }
}
