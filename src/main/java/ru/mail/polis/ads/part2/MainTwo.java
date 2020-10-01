package ru.mail.polis.ads.part2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class MainTwo {

    public static void trickySort(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array.length; ++j) {
                if (array[i] % 10 < array[j] % 10) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                } else if (array[i] % 10 == array[j] % 10) {
                    if (array[i] < array[j]) {
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = scanner.nextInt();
        }


        trickySort(array);

        if (array.length > 1) {

            System.out.print(array[0]);

            for (int i = 1; i < array.length; ++i) {
                System.out.print(" " + array[i]);
            }
        } else if (array.length == 1) {

            System.out.print(array[0]);
        }
    }
}
