package leatcode;

import org.junit.Test;

import java.util.function.IntConsumer;

import static org.junit.Assert.*;

public class ZeroEvenOddTest {

    private ZeroEvenOdd zeroEvenOdd;

    private StringBuffer builder;

    @Test
    public void test0() throws InterruptedException {
        builder = new StringBuffer();
        zeroEvenOdd = new ZeroEvenOdd(0);
        final Thread zero = submit(zeroEvenOdd::zero, "zero");
        final Thread odd = submit(zeroEvenOdd::odd,"odd");
        final Thread even = submit(zeroEvenOdd::even,"even");

        zero.join();
        odd.join();
        even.join();
        assertEquals("", builder.toString());
    }

    @Test
    public void test21() throws InterruptedException {
        builder = new StringBuffer();
        zeroEvenOdd = new ZeroEvenOdd(21);
        final Thread zero = submit(zeroEvenOdd::zero, "zero");
        final Thread even = submit(zeroEvenOdd::even, "even");
        final Thread odd = submit(zeroEvenOdd::odd, "odd");

        zero.join();
        odd.join();
        even.join();
        assertEquals("010203040506070809010011012013014015016017018019020021", builder.toString());
    }
    @Test
    public void test1() throws InterruptedException {
        builder = new StringBuffer();
        zeroEvenOdd = new ZeroEvenOdd(1);
        final Thread zero = submit(zeroEvenOdd::zero, "zero");
        final Thread odd = submit(zeroEvenOdd::odd, "odd");

        zero.join();
        odd.join();
        final Thread even = submit(zeroEvenOdd::even, "even");
        even.join();
        assertEquals("01", builder.toString());
    }















    private Thread submit(ThrowingConsumer<IntConsumer> throwingConsumer, String name) {
        final Thread thread = new Thread(() -> {
            try {
                throwingConsumer.accept(builder::append);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, name);
        thread.start();
        return thread;
    }

    private interface ThrowingConsumer<T> {
        void accept(T t) throws InterruptedException;
    }

}