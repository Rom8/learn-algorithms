package structures.base.net.packets;

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
