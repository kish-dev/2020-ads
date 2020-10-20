package ru.mail.polis.ads.part1;

import java.util.Scanner;

public class MainFive {
    public static void main(String[] args) {

        QueueFiveTask<Integer> queue = new QueueFiveTask<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String string = new String(scanner.next());
            switch (string) {
                case "push":
                    int value = scanner.nextInt();
                    queue.push(value);
                    break;
                case "pop":
                    queue.pop();
                    break;
                case "front":
                    queue.front();
                    break;
                case "size":
                    queue.size();
                    break;
                case "clear":
                    queue.clear();
                    break;
                case "exit":
                    System.out.println("bye");
                    return;
            }
        }

    }
}

class QueueFiveTask<T> {
    private class Node<T> {
        Node() {
            this.data_ = null;
            this.next_ = null;
            this.prev_ = null;
        }

        Node(T data, Node<T> next, Node<T> prev) {
            this.data_ = data;
            this.next_ = next;
            this.prev_ = prev;
        }

        T data_ = null;
        Node next_ = null;
        Node prev_ = null;

    }

    private Node head_;
    private Node tail_;
    private int size_;


    public QueueFiveTask() {
        head_ = new Node();
        tail_ = new Node(null, null, head_);
        head_.next_ = tail_;
        size_ = 0;
    }

    public void push(T object) {        //в конец очереди
        if (head_.data_ == null) {
            head_.data_ = object;
        } else if (tail_.data_ == null) {
            tail_.data_ = object;
        } else {
            Node temp = tail_;
            tail_ = new Node(object, null, temp);
            temp.next_ = tail_;
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
            if (head_.next_ != tail_) {
                head_ = head_.next_;
            } else {
                head_ = tail_;
                tail_ = new Node(null, null, head_);
                head_.next_ = tail_;
            }
            System.out.println((T) temp.data_);
            --size_;
            return;
        }
    }

    public void front() {
        if (size_ < 1) {
            System.out.println("error");
            return;
        }
        System.out.println(head_.data_);
    }

    public void clear() {
        head_ = new Node();
        tail_ = new Node(null, null, head_);
        head_.next_ = tail_;
        size_ = 0;
        System.out.println("ok");
    }
}