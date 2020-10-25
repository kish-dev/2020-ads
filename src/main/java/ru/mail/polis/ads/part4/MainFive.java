package ru.mail.polis.ads.part4;

import java.util.Scanner;

public class MainFive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] array = new int[n];

        for (int i = 0; i < n; ++i) {
            array[i] = scanner.nextInt();
        }

        System.out.println(countInv(array, 0, array.length - 1));

    }

    static int countInv(int[] array, int left, int right) {

        if (right <= left) {
            return 0;
        }
        int mid = (left + right) / 2;
        return countInv(array, left, mid) +
                countInv(array, mid + 1, right) +
                countInvSplit(array, left, mid, right);


    }


    static int countInvSplit(int[] arr, int l, int m, int r) {

        int countSplitInv = 0;
        int leftPart = l;
        int rightPart = m + 1;

        int[] mergeArray = new int[r - l + 1];

        int k = 0;
        while (leftPart <= m && rightPart <= r) {      //сливаем, пока не закончится 1 массив
            if (arr[leftPart] <= arr[rightPart]) {
                mergeArray[k++] = arr[leftPart++];
            } else {
                mergeArray[k++] = arr[rightPart++];
                countSplitInv += (m - leftPart + 1);
            }
        }

        while (leftPart <= m) {    // добавляем оставшиеся
            mergeArray[k++] = arr[leftPart++];
        }

        while (rightPart <= r) {    // добавляем оставшиеся
            mergeArray[k++] = arr[rightPart++];
        }

        System.arraycopy(mergeArray, 0, arr, l, mergeArray.length);

        return countSplitInv;
    }
}