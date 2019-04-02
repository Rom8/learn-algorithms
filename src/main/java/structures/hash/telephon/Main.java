package structures.hash.telephon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("telephone.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int operationsCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < operationsCount; i++) {
            String[] command = br.readLine().split(" ");
            switch (command[0]) {
                case "add":
                    telephoneContacts[index(command[1])] = command[2];
                    break;
                case "del":
                    telephoneContacts[index(command[1])] = null;
                    break;
                case "find":
                    String output = telephoneContacts[index(command[1])];
                    System.out.println(output == null ? "not found" : output);
                    break;
            }
        }
    }

    private static String[] telephoneContacts = new String[10_000_000];

    private static int index(String number) {
        return Integer.parseInt(number);
    }
}
