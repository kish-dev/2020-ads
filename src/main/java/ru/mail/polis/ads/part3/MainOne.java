package ru.mail.polis.ads.part3;

import java.util.Scanner;


public class MainOne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n + 1];
        array[0] = 0;
        for (int i = 1; i < n + 1; ++i) {
            array[i] = scanner.nextInt();
        }

        for (int i = 1; i < n + 1; ++i) {
            if (2 * i <= n) {
                if (array[2 * i] < array[i]) {
                    System.out.println("NO");
                    return;
                }
            }
            if (2 * i + 1 <= n) {
                if (array[2 * i + 1] < array[i]) {
                    System.out.println("NO");
                    return;
                }
            }
            if (2 * i >= n) {
                System.out.println("YES");
                return;
            }
        }
    }
}