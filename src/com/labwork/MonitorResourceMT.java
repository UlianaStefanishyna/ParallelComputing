package com.labwork;

import static com.labwork.Main.N;

public class MonitorResourceMT {
    int[][] MT;

    public MonitorResourceMT() {
        this.MT = new int[N][N];
    }

    public synchronized int[][] getMR() {
        return MT;
    }

    public synchronized void setMR(int[][] MK, int Hi, int Hip) {

        for (int i = Hi, k = 0; i < Hip; i++, k++) {
            for (int j = 0; j < N; j++) {
                this.MT[i][j] = MK[i][j];
            }
        }
    }
}
