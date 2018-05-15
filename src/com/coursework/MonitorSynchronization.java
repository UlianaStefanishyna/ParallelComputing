package com.coursework;

import java.util.concurrent.atomic.AtomicInteger;

import static com.coursework.Main.P;

public class MonitorSynchronization {

    private AtomicInteger F1;
    private AtomicInteger F2;
    private AtomicInteger F3;

    public MonitorSynchronization() {
        this.F1 = new AtomicInteger(0);
        this.F2 = new AtomicInteger(0);
        this.F3 = new AtomicInteger(0);
    }

    public synchronized void signalByInput() {
        F1.getAndIncrement();

        if (F1.intValue() == 2) {
            notifyAll();
        }
    }

    public synchronized void signalByMin() {
        F2.getAndIncrement();

        if (F2.intValue() == P) {
            notifyAll();
        }
    }

    public synchronized void signalByEndCalc() {
        F3.getAndIncrement();

        if (F3.intValue() == P) {
            notifyAll();
        }
    }

    public synchronized void waitByInput() {
        while (F1.intValue() < 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void waitByMin() {
        while (F2.intValue() < P) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void waitByEndCalc() {
        while (F3.intValue() < P) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
