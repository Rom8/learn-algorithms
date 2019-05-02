package concurrent;

import java.util.concurrent.TimeUnit;

public class CheckDaemon {

    public static void main(String[] args) throws InterruptedException {
        Thread daemon = new Thread(new RunningInDaemon());
        daemon.setDaemon(true);
        daemon.start();
        TimeUnit.SECONDS.sleep(3);
        daemon.interrupt();
        TimeUnit.SECONDS.sleep(3);
    }

    static class RunningInDaemon implements Runnable {

        private Object mutex = new Object();

        @Override
        public void run() {
            while (true) {
                System.out.print(".");
                try {
                    synchronized (mutex){
                        mutex.wait();
                    }
//                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //will not be called
                    System.out.println("uuuuu");
                    throw new RuntimeException("iiiiii");
                }
            }
        }
    }
}
