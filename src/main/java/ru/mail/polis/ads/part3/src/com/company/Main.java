package com.company;

import java.io.*;
import java.util.StringTokenizer;

import java.util.Scanner;

public class Main {
    private static int[][] d;
    private static int[][] s;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        if (!string.isEmpty()) {

            //матрица количества скобок, к которым надо добавить ещё скобочку
            d = new int[string.length()][string.length()];

            //матрица, по которой восстанавливаем индексы скобочек, к
            //которым надо добавить ещё скобочку
            s = new int[string.length()][string.length()];
            divide(string, string.length());
            printSequence(string, 0, string.length() - 1);
        }
    }

    static void divide(String string, int n) {
        for (int i = 0; i < n; ++i) {
            for (int j = i; j >= 0; --j) {
                if (i == j) {
                    d[i][j] = 1;
                } else {
                    int minDynamic = Integer.MAX_VALUE;
                    int minDivide = Integer.MAX_VALUE;

                    if ((string.charAt(j) == '(' &&
                            string.charAt(i) == ')') ||
                            (string.charAt(j) == '[' &&
                                    string.charAt(i) == ']')) {
                        minDynamic = d[j + 1][i - 1];
                    }
                    for (int k = j; k < i; ++k) {
                        if ((d[j][k] + d[k + 1][i]) < minDynamic) {
                            minDivide = k;
                            minDynamic = d[j][k] + d[k + 1][i];
                        }
                    }
                    d[j][i] = minDynamic;
                    s[j][i] = minDivide;
                }
            }
        }
    }

    static void printSingleBracket(char symbol) {
        if (symbol == '(' || symbol == ')') {
            System.out.print("()");
        } else {
            System.out.print("[]");
        }
    }

    static void printIncorrectSequence(String string, int i, int j) {
        System.out.print(string.charAt(i));
        printSequence(string, i + 1, j - 1);
        System.out.print(string.charAt(j));
    }

    static void printCorrectSequence(String string, int i, int j) {
        System.out.print(string.substring(i, j + 1));
    }


    static void printSequence(String string, int i, int j) {
        if (i == j) {
            printSingleBracket(string.charAt(i));
            return;
        }
        if (d[i][j] == 0) {
            printCorrectSequence(string, i, j);
            return;
        }
        if (s[i][j] == Integer.MAX_VALUE) {
            printIncorrectSequence(string, i, j);
            return;
        }
        printSequence(string, i, s[i][j]);
        printSequence(string, s[i][j] + 1, j);
    }

    static boolean isCorrect(char first, char second) {
        return ((first == '(' && second == ')') ||
                (first == '[' && second == ']'));
    }
}


