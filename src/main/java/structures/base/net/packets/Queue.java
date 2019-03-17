package structures.base.net.packets;

public class Queue<T> {


    private Node<T> back;
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


    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
