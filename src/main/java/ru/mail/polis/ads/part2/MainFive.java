package ru.mail.polis.ads.part2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class MainFive {
    static void merge(Number arr[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        Number L[] = new Number[n1];
        Number R[] = new Number[n2];

        for (int i = 0; i < n1; ++i)    //выделяем массивы для слития
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {      //сливаем, пока не закончится 1 массив
            if (R[j].compareTo(L[i]) < 0) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {    // добавляем оставшиеся
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {    // добавляем оставшиеся
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void sort(Number arr[], int l, int r)
    {
        if (l < r) {
            int m = (l + r) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    static class Number {

        Number(int first, int second)
        {
            first_ = first;
            second_ = second;
        }
        int first_;
        int second_;

        int compareTo(Number number)
        {
            if (first_ > number.first_)
            {
                return 1;
            }
            else if(first_ == number.first_)
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }
    }

    public static void main(final String[] arg) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();


        Number[] array = new Number[n];
        for(int i = 0 ; i < n; ++i)
        {
            int first = scanner.nextInt();
            int second = scanner.nextInt();
            array[i] = new Number(first, second);
        }

        sort(array, 0, array.length - 1);

        for(int i = array.length - 1; i >= 0; --i)
        {
            System.out.println(array[i].first_ + " " + array[i].second_);
        }
    }
}
