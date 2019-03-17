public class LoadOS {

    public static void main(String[] args) {
        int counter = 0;
        for (int i = 0; i < Math.pow(2, 16); i++) {
            for (int j = 0; j < Math.pow(2, 16); j++) {
                if((i*16 + j) % Math.pow(2, 20) == 31744) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}
