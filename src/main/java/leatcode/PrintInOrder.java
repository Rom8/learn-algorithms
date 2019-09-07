package leatcode;

public class PrintInOrder {
    class Foo {
        private final Object lockFirst = new Object();
        private volatile boolean locked1 = true;

        private final Object lockSecond = new Object();
        private volatile boolean locked2 = true;

        private final Object lockThird = new Object();
        private volatile boolean locked3 = true;

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (lockFirst) {
                while (locked1) {
                    lockFirst.wait();
                }
            }

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();

            synchronized (lockSecond) {
                locked2 = false;
                lockSecond.notify();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (lockFirst) {
                locked1 = false;
                lockFirst.notify();
            }

            synchronized (lockSecond) {
                while (locked2) {
                    lockSecond.wait();
                }
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();

            synchronized (lockThird) {
                locked3 = false;
                lockThird.notify();
            }

        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (lockThird) {
                while (locked3) {
                    lockThird.wait();
                }
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
