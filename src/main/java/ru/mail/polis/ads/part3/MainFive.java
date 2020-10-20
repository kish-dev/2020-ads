package ru.mail.polis.ads.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainFive {
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; ++i) {
            array[i] = scanner.nextInt();
        }

        System.out.print(binSearchYes(array, k));

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

    static boolean check(int[] array, int x, int k) {
        int cows = 1;
        int lastCow = array[0];
        for (int c : array) {
            if (c - lastCow >= x) {
                cows++;
                lastCow = c;
            }
        }
        return cows >= k;
    }

    static int binSearchYes(int[] array, int k) {
        int left = 0;
        int right = array[array.length - 1] - array[0] + 1;

        while (right - left > 1) {
            int mid = (right + left) / 2;
            if (check(array, mid, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}