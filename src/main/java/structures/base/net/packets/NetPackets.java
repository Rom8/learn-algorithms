package structures.base.net.packets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class NetPackets {

    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println("-----0-----");
//        new NetPackets().input("hell0.txt");
//
//        System.out.println("-----1-----");
//        new NetPackets().input("hell1.txt");
//
//        System.out.println("-----2-----");
//        new NetPackets().input("hell2.txt");
//
//        System.out.println("-----3-----");
//        new NetPackets().input("hell3.txt");
//
//        System.out.println("-----4-----");
//        new NetPackets().input("hell4.txt");

        System.out.println("-----5-----");
        new NetPackets().input("hell5.txt");
    }

    private int bufferSize;
    private int pocketsQuantity;
    private Pocket[] allPockets;
    private Buffer<Pocket> buffer;

    private AtomicInteger clock = new AtomicInteger();

    public void input(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        bufferSize = scanner.nextInt();
        pocketsQuantity = scanner.nextInt();
        allPockets = new Pocket[pocketsQuantity];
        buffer = new Buffer<>(bufferSize);
        for (int i = 0; i < pocketsQuantity; i++) {
            allPockets[i] = new Pocket(scanner.nextInt(), scanner.nextInt());
        }
        work();
        print();
    }

    public void work() {
        int index = 0;
        while (true) {
            process();
            index = addToBufferWithTime(index, clock.get());
            process();
            if (index == pocketsQuantity && buffer.isEmpty()) {
                return;
            }
            clock.incrementAndGet();
        }
    }

    public void print() {
        for (Pocket pocket: allPockets) {
            System.out.println(pocket.getResult());
        }
    }

    private int addToBufferWithTime(int startingFrom, int arrival) {
        for (int i = startingFrom; i < pocketsQuantity; i++) {
            Pocket pocket = allPockets[i];
            if (pocket.getArrival() > arrival) {
                return i;
            }
            if (pocket.getArrival() == arrival) {
                if (!buffer.pushBack(pocket)) {
                    pocket.throwOut();
                } else {
                    pocket.putInBuffer();
                }
            }
        }
        return pocketsQuantity;
    }

    private void process() {
        if (!buffer.isEmpty()) {
            Pocket pocket = buffer.topFront();
            boolean calculated = pocket.process(clock.get());
            if (calculated) {
                buffer.popFront();
            }
        }
    }
}
