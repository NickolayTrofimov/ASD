public class Stopwatch {

    private long startTime;
    private long elapsedNanos;
    private boolean running;

    public void start() {
        if (!running) {
            startTime = System.nanoTime();
            running = true;
        }
    }

    public long stop() {
        if (running) {
            elapsedNanos = System.nanoTime() - startTime;
            running = false;
        }
        return elapsedNanos;
    }

    public double getMicroseconds() {
        long nanos = running ? System.nanoTime() - startTime : elapsedNanos;
        return nanos / 1_000.0;
    }

    public double getMilliseconds() {
        return getMicroseconds() / 1_000.0;
    }
}