package structures.base.net.packets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        new Main().input();


        System.out.println("-----0-----");
        new Main().inputCheck("hell0.txt");

        System.out.println("-----1-----");
        new Main().inputCheck("hell1.txt");

        System.out.println("-----2-----");
        new Main().inputCheck("hell2.txt");

        System.out.println("-----3-----");
        new Main().inputCheck("hell3.txt");

        System.out.println("-----4-----");
        new Main().inputCheck("hell4.txt");

        System.out.println("-----5-----");
        new Main().inputCheck("hell5.txt");

        System.out.println("-----6-----");
        new Main().inputCheck("hell6.txt");

        System.out.println("-----7-----");
        new Main().inputCheck("hell7.txt");
    }

    private int bufferSize;
    private int pocketsQuantity;
    private Packet[] allPockets;
    private Buffer<Packet> buffer;

    public void inputCheck(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        bufferSize = scanner.nextInt();
        pocketsQuantity = scanner.nextInt();
        allPockets = new Packet[pocketsQuantity];
        buffer = new PocketBuffer(bufferSize);
        for (int i = 0; i < pocketsQuantity; i++) {
            allPockets[i] = new Packet(scanner.nextInt(), scanner.nextInt());
        }
        work();
        print();
    }

    public void input()  {
        Scanner scanner = new Scanner(System.in);
        bufferSize = scanner.nextInt();
        pocketsQuantity = scanner.nextInt();
        allPockets = new Packet[pocketsQuantity];
        buffer = new PocketBuffer(bufferSize);
        for (int i = 0; i < pocketsQuantity; i++) {
            allPockets[i] = new Packet(scanner.nextInt(), scanner.nextInt());
        }
        work();
        print();
    }

    public void work() {
        for (Packet packet: allPockets) {
            while (!buffer.isEmpty() && buffer.topFront().getFinish() <= packet.getArrival()) {
                buffer.popFront();
            }
            if (buffer.pushBack(packet)) {
                packet.setResult(packet.getStart());
            } else {
                packet.throwOut();
            }
        }
    }

    public void print() {
        for (Packet pocket: allPockets) {
            System.out.println(pocket.getResult());
        }
    }

    public class PocketBuffer extends Buffer<Packet> {

        public PocketBuffer(int maxSize) {
            super(maxSize);
        }

        @Override
        public boolean pushBack(Packet pocket) {
            if (getSize() == 0) {
                pocket.setStart(pocket.getArrival());
            } else {
                pocket.setStart(back.getValue().getFinish());
            }
            pocket.setFinish(pocket.getStart() + pocket.getDuration());
            return super.pushBack(pocket);
        }
    }

    public class Buffer<T> extends Queue<T> {

        int maxSize;

        public Buffer(int maxSize) {
            this.maxSize = maxSize;
        }

        @Override
        public boolean pushBack(T t) {
            if (getSize() == maxSize) {
                return false;
            }
            return super.pushBack(t);
        }

        public int getMaxSize() {
            return maxSize;
        }
    }


    public class Queue<T> {

        protected Node<T> back;
        private Node<T> front;
        private int size;

        /**
         *
         * @param t
         * @return true if an element was added to the queue
         */
        public boolean pushBack(T t) {
            Node<T> node = new Node<>(t);
            if (isEmpty()) {
                front = node;
            } else {
                back.setNext(node);
            }
            back = node;
            size++;
            return true;
        }

        public T popFront() {
            T result = topFront();
            front = front.next;
            size--;
            return result;
        }

        public T topFront() {
            return front.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int getSize() {
            return size;
        }


        private class Node<T> {
            private T value;
            private Node<T> next;

            public Node(T value) {
                this.value = value;
            }

            public void setNext(Node<T> next) {
                this.next = next;
            }

            public T getValue() {
                return value;
            }
        }
    }


    public class Packet {

        private final int arrival;
        private final int duration;
        private Integer result;
        private int start;
        private int finish;

        public Packet(int arrival, int duration) {
            this.arrival = arrival;
            this.duration = duration;
        }

        public int getArrival() {
            return arrival;
        }

        public int getDuration() {
            return duration;
        }

        public int getResult() {
            return result;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getFinish() {
            return finish;
        }

        public void setFinish(int finish) {
            this.finish = finish;
        }

        public void throwOut() {
            result = -1;
        }

        public void setResult(Integer result) {
            this.result = result;
        }
    }

}