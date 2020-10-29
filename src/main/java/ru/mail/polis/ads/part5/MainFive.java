package ru.mail.polis.ads.part5;

import java.util.Scanner;

public class MainFive {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = i + 1;
        }
        if (n == 1) {
            System.out.println(1);
        } else {
            printC(array, factorial(n, 1));
        }

    }

    static int factorial(int n, int acc) {
        if (n > 1) {
            acc *= n;
            return factorial(n - 1, acc);
        }
        return acc;
    }

    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static void reverseArray(int[] array, int i, int j) {
        if ((j - i) % 2 == 0) {
            while (i != j) {
                swap(array, i, j);
                ++i;
                --j;
            }
        } else {
            while ((i + 1) != j) {
                swap(array, i, j);
                ++i;
                --j;
            }
            swap(array, i, j);
        }
    }


    static void printC(int[] array, int factorial) {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.print("\n");
        int lasts = 1;  //количество хвостовых элементов
        int count = 1;
        while (lasts != array.length && count != factorial) {
            for (int i = array.length - 1; i > 0; --i) {
                if (array[i] < array[i - 1]) {
                    ++lasts;
                } else {
                    break;
                }
            }
            int indexForSwap = 0;
            for (int i = array.length - 1; i > 0; --i) {
                if (array[i] > array[array.length - 1 - lasts]) {
                    indexForSwap = i;
                    break;
                }
            }
            swap(array, indexForSwap, array.length - 1 - lasts);
            if (lasts != 1) {
                int i = array.length - lasts;
                int j = array.length - 1;
                reverseArray(array, i, j);
            }
            for (int i : array) {
                System.out.print(i + " ");
            }
            System.out.print("\n");
            lasts = 1;
            ++count;
        }
    }
}