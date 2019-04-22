package concurrent;

import java.util.concurrent.TimeUnit;

public class CheckDaemon {

    public static void main(String[] args) throws InterruptedException {
        Thread daemon = new Thread(new RunningInDaemon());
        daemon.setDaemon(true);
        daemon.start();
        TimeUnit.SECONDS.sleep(5);
    }

    static class RunningInDaemon implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
