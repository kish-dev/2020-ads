package ru.mail.polis.ads.part4;

import java.util.Scanner;

public class MainTwo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int row = scanner.nextInt();
        int column = scanner.nextInt();

        long[][] matrix = new long[row][column];

        for (int i = row - 1; i >= 0; --i) {
            for (int j = 0; j < column; ++j) {
                matrix[i][j] = scanner.nextLong();
            }
        }

        mouseWayPackage(matrix, row, column);
    }

    static void mouseWayPackage(long[][] matrix, int row, int column) {

        StringBuilder answer = new StringBuilder();

        for (int i = 1; i < row; ++i) {
            matrix[i][0] = matrix[i][0] + matrix[i - 1][0];
        }

        for (int i = 1; i < column; ++i) {
            matrix[0][i] = matrix[0][i] + matrix[0][i - 1];
        }

        for (int i = 1; i < row; ++i) {
            for (int j = 1; j < column; ++j) {
                matrix[i][j] = matrix[i][j] + Math.max(matrix[i - 1][j], matrix[i][j - 1]);

            }
        }

        //заполнили весь массив mouseWay ходами, получили в ячейке
        //mouseWay[row-1][column - 1] максимальное число
        //теперь продемся по максимальным путям к 0 и восстановим строку

        int k = row - 1;
        int t = column - 1;

        while (k > 0 || t > 0) {
            if (k > 0 && t > 0) {
                if (matrix[k - 1][t] > matrix[k][t - 1]) {
                    answer.append("F");
                    --k;
                } else {
                    answer.append("R");
                    --t;
                }
            } else if (k == 0) {
                answer.append("R");
                --t;
            } else if (t == 0) {
                answer.append("F");
                --k;
            }
        }

        String reverse = new StringBuffer(answer).reverse().toString();
        System.out.println(reverse);

    }
}