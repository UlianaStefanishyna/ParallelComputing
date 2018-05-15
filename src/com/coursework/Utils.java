package com.coursework;

import static com.coursework.Main.N;

/**
 * Course Work _ 2
 * Author : Uliana Stefanishyna
 * Group : IO-52
 * Task : MA = MB * min(E * MM) + MO * (MS * MR) * d
 */

public class Utils {

    private int N;

    Utils() {
    }

    public Utils(int N) {
        this.N = N;

    }

    public void printVector(int[] A) {
        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    public synchronized void printMatrix(int[][] MA) {
        if (N > 9)
            return;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(MA[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[] fillVectorBy(int value) {
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = value;
        }
        return A;
    }

    public int[] multVectorMatrix(int[] A, int[][] MA, int Hi, int Hip1) {

        int[] D = new int[N];
        for (int i = Hi; i < Hip1; i++) {
            D[i] = 0;
            for (int j = 0; j < N; j++) {
                D[i] += A[i] * MA[j][i];
            }
        }
        return D;
    }

    public int[][] fillMatrixBy(int value) {
        int[][] MA = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                MA[i][j] = value;
            }
        }
        return MA;
    }

    public int[][] multMatrixMatrix(int[][] MA, int[][] MB, int Hi, int Hip1) {
        int[][] MR = new int[N][N];
        int t = 0;
        for (int i = Hi; i < Hip1; i++) {
            for (int j = 0; j < N; j++) {
                t = 0;
                for (int k = 0; k < N; k++) {
                    t += MA[i][k] * MB[k][j];
                }
                MR[i][j] = t;
            }
        }
        return MR;
    }

    public int[][] multValueMatrix(int a, int[][] MA, int Hi, int Hip1) {
        for (int i = Hi; i < Hip1; i++) {
            for (int j = 0; j < N; j++) {
                MA[i][j] *= a;
            }
        }
        return MA;
    }

    public int[][] addMatrixMatrix(int[][] MA, int[][] MB, int Hi, int Hip1) {
        for (int i = Hi; i < Hip1; i++) {
            for (int j = 0; j < N; j++) {
                MA[i][j] += MB[i][j];
            }
        }
        return MA;
    }

}
