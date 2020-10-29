package ru.mail.polis.ads.part5;

import java.util.Scanner;

public class MainOne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double c = scanner.nextDouble();
        System.out.println(getX(c));

    }

    static double getX(double c) {
        double right = c;
        double left = 1;
        double temp = 0;
        while (Math.abs(c - temp) > 0.0000001) {
            double mid = (left + right) / 2;
            temp = (mid * mid + Math.sqrt(mid));
            if (c <= temp) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
