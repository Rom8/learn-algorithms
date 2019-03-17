package structures.base.net.packets;

public class Pocket {

    private final int arrival;
    private final int duration;
    private State state = State.BEFORE_BUFFER;
    private int result = -10;

    public Pocket(int arrival, int duration) {
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

    public void throwOut() {
        state = State.THROWN;
        result = -1;
    }

    public void putInBuffer() {
        state = State.IN_BUFFER;
    }

    public boolean process(int currentTime) {
        state = State.PROCESSING;
        if (result == -10) {
            this.result = currentTime;
        }
        return currentTime - result == duration;
    }

    public void calculated() {
        state = State.CALCULATED;
    }

    enum State {
        BEFORE_BUFFER,
        THROWN,
        IN_BUFFER,
        PROCESSING,
        CALCULATED
    }
}
