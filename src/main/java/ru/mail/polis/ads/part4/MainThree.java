package ru.mail.polis.ads.part4;

import java.util.Scanner;

public class MainThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int array1[] = new int[n];

        for (int i = 0; i < n; ++i) {
            array1[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();

        int array2[] = new int[m];

        for (int i = 0; i < m; ++i) {
            array2[i] = scanner.nextInt();
        }

        System.out.println(lcs(array1, array2));
    }

    static int lcs(int[] x, int[] y) {
        if (x.length == 0 || y.length == 0) {
            return 0;
        }

        int m = x.length;
        int n = y.length;
        int l = Math.max(m, n);

        int lcs[][] = new int[l + 1][l + 1];
        for (int i = 0; i < m; ++i) {
            lcs[i][0] = 0;
        }
        for (int i = 0; i < n; ++i) {
            lcs[0][i] = 0;
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (x[i] == y[j]) {
                    lcs[i + 1][j + 1] = lcs[i][j] + 1;
                } else {
                    lcs[i + 1][j + 1] = Math.max(lcs[i][j + 1], lcs[i + 1][j]);
                }
            }
        }
        return lcs[m][n];
    }
}