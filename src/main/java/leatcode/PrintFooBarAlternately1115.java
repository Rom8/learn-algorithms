package leatcode;


public class PrintFooBarAlternately1115 {

    public static class FooBar {
        private final int n;
        private volatile boolean lockedFoo = true;
        private volatile boolean lockedBar = true;
        private final Object lock = new Object();

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    while (lockedFoo) {
                        lock.wait();
                    }
                    lockedFoo = true;
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();

                synchronized (lock) {
                    lockedBar = false;
                    lock.notify();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    lockedFoo = false;
                    lock.notify();
                    while (lockedBar) {
                        lock.wait();
                    }
                    lockedBar = true;
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
            }
        }
    }
}
