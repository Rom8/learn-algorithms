package leatcode;

public class H2O {

    private final Object lock = new Object();
    private Atom atom = Atom.H1;

    public H2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        synchronized (lock) {
            while (atom == Atom.O) {
                lock.wait();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            atom = (atom == Atom.H1)
                    ? Atom.O
                    : Atom.H1;
            lock.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        synchronized (lock) {
            while (atom != Atom.O) {
                lock.wait();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            atom = Atom.H2;
            lock.notifyAll();
        }
    }

    private enum Atom {
        H1,
        O,
        H2
    }
}
