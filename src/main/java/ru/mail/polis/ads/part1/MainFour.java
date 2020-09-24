package ru.mail.polis.ads.part1;

import java.util.Scanner;

public class MainFour {
    public static void main(String[] args){

        StackFourTask<Integer> stack = new StackFourTask<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String string = new String(scanner.next());
            switch (string) {
                case "push":
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    stack.pop();
                    break;
                case "back":
                    stack.back();
                    break;
                case "size":
                    stack.size();
                    break;
                case "clear":
                    stack.clear();
                    break;
                case "exit":
                    System.out.println("bye");
                    return;
            }
        }

    }
}

class StackFourTask<T> {
    private class Node<T> {
        Node() {
            this.data_ = null;
            this.next_ = null;
        }

        Node(T data, Node<T> next) {
            this.data_ = data;
            this.next_ = next;
        }

        T data_ = null;
        Node next_ = null;

    }

    private Node head_;
    private int size_;


    public StackFourTask() {
        head_ = new Node();
        size_ = 0;
    }

    public void push(T object) {        //в конец очереди
        if (size_ < 1) {
            head_ = new Node(object, null);
        } else {
            Node temp = new Node(object, head_);
            head_ = temp;
        }
        ++size_;
        System.out.println("ok");
    }

    public int size() {
        System.out.println(size_);
        return size_;
    }

    public void pop() {       //извлекаем из начала очереди
        if (size_ < 1) {
            System.out.println("error");
            return;
        } else {
            Node temp = head_;
            head_ = head_.next_;
            --size_;
            System.out.println((T) temp.data_);
            return;

        }
    }

    public void back() {
        if (size_ < 1) {
            System.out.println("error");
            return;
        }
        System.out.println(head_.data_);
    }

    public void clear() {
        head_ = new Node();
        size_ = 0;
        System.out.println("ok");
    }
}