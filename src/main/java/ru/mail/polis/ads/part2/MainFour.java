package ru.mail.polis.ads.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainFour {

    static class QuickSort
    {
        int partition(BigInteger arr[], int low, int high)
        {
            BigInteger pivot = arr[high];
            int i = (low-1);
            for (int j=low; j<high; j++)
            {
                if (arr[j].compareTo(pivot) < 0)
                {
                    i++;

                    // swap arr[i] and arr[j]
                    BigInteger temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            BigInteger temp = arr[i+1];
            arr[i+1] = arr[high];
            arr[high] = temp;

            return i+1;
        }
        void sort(BigInteger arr[], int low, int high)
        {
            if (low < high)
            {
                int pi = partition(arr, low, high);

                sort(arr, low, pi-1);
                sort(arr, pi+1, high);
            }
        }
    }

    public static void bubbleSort(ArrayList<BigInteger> array) {
        for (int i = 0; i < array.size() - 1; ++i) {
            for (int j = i + 1; j < array.size(); ++j) {
                if (array.get(i).compareTo(array.get(j)) == 1) {
                    BigInteger temp = array.get(i);
                    array.set(i, array.get(j));
                    array.set(j, temp);
                }
            }
        }
    }

    public static void main(final String[] arg) {
        Scanner scanner = new Scanner(System.in);
        String string = new String();
        string = scanner.nextLine();

        int k = Integer.parseInt(string);
        BigInteger[] array;


        string = scanner.nextLine();
        String[] stringArray = string.split(" ");

        array = new BigInteger[stringArray.length];
        for (int i = 0; i < stringArray.length; ++i) {

            array[i] = (new BigInteger(stringArray[i]));

        }
        QuickSort quickSort = new QuickSort();
        quickSort.sort(array, 0, array.length-1);

        System.out.print(array[stringArray.length - k]);
    }
}
