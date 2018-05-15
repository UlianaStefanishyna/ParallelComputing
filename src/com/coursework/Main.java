package com.coursework;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Course Work _ 2
 * Author : Uliana Stefanishyna
 * Group : IO-52
 * Task : MA = MB * min(E * MM) + MO * (MS * MX) * d
 */

public class Main extends Thread {

    static final int P = 4;
    static final int N = 1000;
    private static final int H = N / P;

    private int[][] MS, MM, MB, MO, MA;
    private AtomicInteger d, m;
    private AtomicInteger count;
    private AtomicLong time;


    private MonitorResourceMX monitorResourceMX = new MonitorResourceMX();
    private MonitorResourceMR monitorResourceMR = new MonitorResourceMR();
    private MonitorResouceE monitorResouceE = new MonitorResouceE();
    private MonitorSynchronization monitorSynchronization = new MonitorSynchronization();

    public Main() {
        this.MS = new int[N][N];
        this.MM = new int[N][N];
        this.MB = new int[N][N];
        this.MO = new int[N][N];
        this.d = new AtomicInteger(0);
        this.count = new AtomicInteger(1);
        this.m = new AtomicInteger(Integer.MAX_VALUE);
        this.time = new AtomicLong(System.currentTimeMillis());

        for (int i = 0; i < P; i++) {
            Thread thread = new Thread();
            thread.start();
        }
    }

    class Thread extends java.lang.Thread {

        int tid;
        Utils utils = new Utils(N);

        public Thread() {
            this.tid = count.getAndIncrement();
        }

        @Override
        public void run() {
            System.out.println("thread #" + tid + " has started");

            final int Hip1 = tid * H;
            final int Hi = (tid - 1) * H;

            switch (tid) {
                case 1:
                    MS = utils.fillMatrixBy(1);
                    MM = utils.fillMatrixBy(1);
                    d.getAndSet(1);
                    int[] E = utils.fillVectorBy(1);
                    monitorResouceE.setE(E);
                    monitorSynchronization.signalByInput();
                    break;
                case P:
                    MB = utils.fillMatrixBy(1);
                    MO = utils.fillMatrixBy(1);
                    int[][] MX = utils.fillMatrixBy(1);
                    monitorResourceMX.setMX(MX);
                    monitorSynchronization.signalByInput();
                    break;
            }

            //synchronization by input
            monitorSynchronization.waitByInput();

            //copy
            int[][] MXi = monitorResourceMX.getMX();

            int[][] MRi = utils.multMatrixMatrix(MS, MXi, Hi, Hip1);

            monitorResourceMR.setMR(MRi, Hi, Hip1);


            int[] Ei = monitorResouceE.getE();
            int[] Ki = utils.multVectorMatrix(Ei, MM, Hi, Hip1);

            int mi = Integer.MAX_VALUE;
            for (int i = Hi; i < Hip1; i++) {
                if (Ki[i] < mi) {
                    mi = Ki[i];
                }
            }

            m.compareAndSet(m.get(), mi < m.get() ? mi : m.get());

            monitorSynchronization.signalByMin();
            monitorSynchronization.waitByMin();

            MA = utils.addMatrixMatrix(
                    utils.multValueMatrix(m.get(), MB, Hi, Hip1),
                    utils.multMatrixMatrix(MO, monitorResourceMR.getMR(), Hi, Hip1),
                    Hi, Hip1);


            monitorSynchronization.signalByEndCalc();
            monitorSynchronization.waitByEndCalc();


            if (tid == 1) {
                utils.printMatrix(MA);

                long tim = System.currentTimeMillis() - time.get();

                System.out.println("==========");
                NumberFormat formatter = new DecimalFormat("#0.00000");
                System.out.println("Execution time is " + formatter.format(tim / 1000d) + " seconds");

            }

            System.out.println("thread #" + tid + " has finished");
        }
    }


    public static void main(String[] args) {
        new Main();
    }

}
