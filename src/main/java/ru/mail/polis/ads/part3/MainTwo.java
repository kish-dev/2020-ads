package ru.mail.polis.ads.part3;

import java.util.Scanner;

public class MainTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        Heap heap = new Heap(n);

        for(int i = 0; i < n; ++i) {
            int isExtract = scanner.nextInt();
            if(isExtract == 1) {
                heap.extract();
            }
            else {
                int value = scanner.nextInt();
                heap.insert(value);
            }
        }
    }
}

class Heap {

    private int[] array;
    private int lastIndex;

    public Heap(int n) {
        array = new int[n + 1];
        array[0] = -1;
        lastIndex = 1;
    }

    public void insert(int value) {
        array[lastIndex] = value;
        swim(lastIndex);
        ++lastIndex;
    }

    public void extract() {
        System.out.println(array[1]);
        --lastIndex;
        swap(array, lastIndex, 1);
        array[lastIndex] = -1;
        sink(1);
    }

    public void swim(int k) {   //всплытие аргумента, передаем индекс
        while (array[k] > array[k / 2] && k > 1) {
            swap(array, k, k / 2);
            k /= 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= lastIndex) {

            int indexLeft = 2 * k;

            int index;     //индекс ребенка, с которым меняем утопца

            if (indexLeft + 1 <= lastIndex) {
                index = array[indexLeft] > array[indexLeft + 1] ?
                        indexLeft : indexLeft + 1;
            } else {
                index = indexLeft;
            }
            if (array[k] < array[index]) {
                swap(array, k, index);
                k = index;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] a, int child, int parent) {
        int temp = a[child];
        a[child] = a[parent];
        a[parent] = temp;
    }

}
