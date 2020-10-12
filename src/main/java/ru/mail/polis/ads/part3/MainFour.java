package ru.mail.polis.ads.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainFour {
    public static void main(String[] args) {
        final FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int q = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; ++i) {
            array[i] = scanner.nextInt();
        }

        for (int i = 0; i < q; ++i) {
            binSearchYes(array, scanner.nextInt());
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

    static void binSearchYes(int[] array, int number) {
        int left = 0;
        int right = array.length - 1;
        int mid = (right - left) / 2;

        while (left < right) {
            if (number > array[mid]) {
                left = mid + 1;
            } else if (number == array[mid]) {
                System.out.println("YES");
                return;
            } else {
                right = mid - 1;
            }
            mid = (right - left) / 2 + left;
        }

        if (number == array[left]) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }
}

