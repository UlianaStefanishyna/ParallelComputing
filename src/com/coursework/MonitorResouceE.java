package com.coursework;

import static com.coursework.Main.N;

public class MonitorResouceE {

    private int[] E;

    public MonitorResouceE(){
        this.E = new int[N];
    }

    public synchronized int[] getE() {
        return E;
    }

    public synchronized void setE(int[] e) {
        E = e;
    }
}
