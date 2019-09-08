package leatcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class PrintFooBarAlternately1115 {

    public static class FooBar {
        private final int n;
        private final Object lockFoo = new Object();
        private volatile boolean lockedFoo = true;
        private final Object lockBar = new Object();
        private volatile boolean lockedBar = true;
        private final CyclicBarrier barrier = new CyclicBarrier(2, this::resetFlags);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                barrierWait();
                synchronized (lockFoo) {
                    while (lockedFoo) {
                        lockFoo.wait();
                    }
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();

                synchronized (lockBar) {
                    lockedBar = false;
                    lockBar.notify();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                barrierWait();
                synchronized (lockFoo) {
                    lockedFoo = false;
                    lockFoo.notify();
                }

                synchronized (lockBar) {
                    while (lockedBar) {
                        lockBar.wait();
                    }
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
            }
        }

        private void barrierWait() throws InterruptedException {
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }

        private void resetFlags() {
            lockedFoo = true;
            lockedBar = true;
        }
    }
}
