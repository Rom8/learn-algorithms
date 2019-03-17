import java.util.Scanner;

public class Task23Shen {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            final int n = scanner.nextInt();
            double sum = 0;
            int znamen = 0;
            while (sum <= n) {
                sum += 1d/++znamen;
            }
            System.out.println("Znamenatel: " + znamen);
        }
    }
}
