package com.coursework;

import static com.coursework.Main.N;

public class MonitorResourceMX {

    int[][] MX;

    public MonitorResourceMX() {
        this.MX = new int[N][N];
    }

    public synchronized int[][] getMX() {
        return MX;
    }

    public synchronized void setMX(int[][] MX) {
        this.MX = MX;
    }
}
