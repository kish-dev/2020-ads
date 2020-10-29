package ru.mail.polis.ads.part5;

import java.util.Scanner;


public class MainThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = scanner.nextInt();
        }
        System.out.println(getLengthOfLongestDivisibleSubSequence(array, n));


    }

    static int getLengthOfLongestDivisibleSubSequence(int[] array, int n) {
        int D[] = new int[n];

        for (int i = 0; i < n; ++i) {
            D[i] = 1;
            for (int j = 0; j <= (i - 1); ++j) {
                if ((array[j] != 0) &&
                        (array[i] % array[j]) == 0 && (D[j] + 1 > D[i])) {
                    D[i] = D[j] + 1;
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < n; ++i) {
            if (ret < D[i]) {
                ret = D[i];
            }
        }
        return ret;
    }
}
