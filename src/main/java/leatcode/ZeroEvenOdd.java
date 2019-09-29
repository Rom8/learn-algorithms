package leatcode;

import java.util.function.IntConsumer;

//1116. Print Zero Even Odd
public class ZeroEvenOdd {

    private final Object lock = new Object();
    private ValueNext valueNext = ValueNext.ZERO_A;
    private boolean stop = false;

    private final int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        if (n <= 0) {
            return;
        }
        out:
        while (true) {
            synchronized (lock) {
                while (valueNext == ValueNext.ODD || valueNext == ValueNext.EVEN) {
                    lock.wait();
                    if (stop) {
                        break out;
                    }
                }
                printNumber.accept(0);
                valueNext = valueNext == ValueNext.ZERO_A
                        ? ValueNext.ODD
                        : ValueNext.EVEN;
                lock.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        next(printNumber, 0, ValueNext.EVEN, ValueNext.ZERO_A);
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        next(printNumber, -1, ValueNext.ODD, ValueNext.ZERO_B);
    }

    private void next(IntConsumer printNumber, int i, ValueNext waitCondition, ValueNext valueNext) throws InterruptedException {
        if (n <= 0) {
            return;
        }
        int number = i;
        out:
        while (true) {
            synchronized (lock) {
                while (this.valueNext != waitCondition) {
                    if (stop) {
                        System.out.println("BREAK from thread " + Thread.currentThread().getName());
                        break out;
                    }
                    lock.wait();
                }
                number += 2;
                printNumber.accept(number);
                if (number == n) {
                    stop = true;
                    lock.notifyAll();
                    break;
                }
                this.valueNext = valueNext;
                lock.notifyAll();
            }
        }
    }

    private enum ValueNext {
        ZERO_A,
        ODD,
        ZERO_B,
        EVEN
    }
}
