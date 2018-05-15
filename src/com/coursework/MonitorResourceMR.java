package com.coursework;

import static com.coursework.Main.N;

public class MonitorResourceMR {
    int[][] MR;

    public MonitorResourceMR() {
        this.MR = new int[N][N];
    }

    public synchronized int[][] getMR() {
        return MR;
    }

    public synchronized void setMR(int[][] MK, int Hi, int Hip) {

//        System.out.println("Hi = " + Hi + "   Hip1 = " + Hip);
        for (int i = Hi, k = 0; i < Hip; i++, k++) {
            for (int j = 0; j < N; j++) {
                this.MR[i][j] = MK[i][j];
//                System.out.print(this.MR[i][j] + " ");
            }
//            System.out.println();
        }
//        System.out.println();
    }
}
