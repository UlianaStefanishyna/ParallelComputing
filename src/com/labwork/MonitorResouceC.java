package com.labwork;

import static com.labwork.Main.N;

public class MonitorResouceC {

    private int[] C;

    public MonitorResouceC() {
        this.C = new int[N];
    }

    public synchronized int[] getC() {
        return C;
    }

    public synchronized void setC(int[] c) {
        C = c;
    }
}
