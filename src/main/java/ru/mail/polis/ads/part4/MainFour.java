package ru.mail.polis.ads.part4;

import java.util.Scanner;

public class MainFour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int array[] = new int[n + 2];

        for (int i = 1; i < n + 1; ++i) {
            array[i] = scanner.nextInt();
        }

        int k = scanner.nextInt();  //максимальный шаг

        System.out.println(stairway(array, k));
    }

    static int stairway(int[] array, int k) {
        int summary[] = new int[array.length];

        for (int i = 1; i < array.length; ++i) {
            int max = Integer.MIN_VALUE;
            for (int j = i >= k ? i - k : 0; j < i; ++j) {  //предыдущий ход
                if(summary[j] > max) {
                    max = summary[j];
                }
            }
            summary[i] = array[i] + max;    //прибавляем все ступеньки, так как в цикле найдем
            //наибольший
        }

        return summary[array.length - 1];
    }
}
