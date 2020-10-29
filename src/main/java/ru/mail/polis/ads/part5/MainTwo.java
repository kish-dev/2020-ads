package ru.mail.polis.ads.part5;

import java.util.Scanner;

public class MainTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long w = scanner.nextInt();
        long h = scanner.nextInt();
        long n = scanner.nextInt();
        System.out.println(getDesk(w, h, n));

    }

    static long getDesk(long width, long height, long n) {
        //ответ - длина стороны доски
        long right = Math.max(width, height) * n;
        long left = Math.max(width, height);
        long count = 0;
        while (left < right) {
            long mid = (left + right) / 2;
            count = (mid / height) * (mid / width);
            if (n <= count) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
