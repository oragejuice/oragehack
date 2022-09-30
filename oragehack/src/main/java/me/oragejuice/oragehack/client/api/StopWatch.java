package me.oragejuice.oragehack.client.api;

public class StopWatch {

    long time;

    public StopWatch() {
        this.time = System.currentTimeMillis();
    }

    public void reset(){
        this.time = System.currentTimeMillis();
    }

    public boolean passed(long p) {
        return System.currentTimeMillis() - this.time >= p;
    }

    public long getPassed(){
        return System.currentTimeMillis() - this.time;
    }
}
