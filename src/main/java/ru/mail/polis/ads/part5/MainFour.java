package ru.mail.polis.ads.part5;


import java.util.Scanner;

public class MainFour {


    public static void main(String[] arg) {
        Scanner in = new Scanner(System.in);
        String string = in.next();
        String secondString = in.next();
        if (string.equals(secondString)) {
            System.out.println("YES");
        }
        else if (string.length() == 0 && secondString.length() == 0) {
            System.out.println("YES");
        } else if (patternWord(string, secondString) || patternWord(secondString, string)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static boolean patternWord(String string, String secondString) {
        boolean[][] d = new boolean[string.length() + 1][secondString.length() + 1];

        d[0][0] = true;

        for (int i = 1; i <= string.length(); ++i) {
            for (int j = 1; j <= secondString.length(); ++j) {
                char topR = secondString.charAt(j - 1);
                char leftB = string.charAt(i - 1);
                if (topR == leftB || leftB == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (leftB == '*') {
                    d[i][j] = d[i - 1][j - 1] || d[i - 1][j] || d[i][j - 1];
                }
            }
        }
        return d[string.length()][secondString.length()];
    }
}
