package ru.mail.polis.ads.part1;

import javax.swing.tree.TreeNode;
import java.io.*;
import java.util.*;

public class MainTwo {

    static class TreeNode {
        TreeNode(Character data, TreeNode left, TreeNode right) {
            this.data_ = data;
            this.left_ = left;
            this.right_ = right;
        }

        TreeNode() {
            this.data_ = null;
            this.left_ = null;
            this.right_ = null;
        }

        Character data_;
        TreeNode left_;
        TreeNode right_;
    }

    private static String horizontalWalk(TreeNode root) {
        QueueForTwo<TreeNode> queue = new QueueForTwo<>();
        StringBuilder resultStringBuilder = new StringBuilder();
        queue.push(root);
        do {
            TreeNode temp = queue.pop();
            if (temp.right_ != null) {
                queue.push(temp.right_);
            }
            if (temp.left_ != null) {
                queue.push(temp.left_);
            }
            resultStringBuilder.insert(0, temp.data_);
        } while (queue.size() != 0);
        return resultStringBuilder.toString();
    }


    private static void solve() {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        for (int i = 0; i < count; ++i) {
            String stackString = in.next();
            StackForTwo<TreeNode> stack = new StackForTwo<>();
            for (int k = 0; k < stackString.length(); ++k) {
                char symbol = stackString.charAt(k);
                if (Character.isLowerCase(symbol)) {
                    stack.push(new TreeNode(symbol, null, null));
                } else {
                    TreeNode left = stack.pop();
                    TreeNode right = stack.pop();
                    TreeNode root = new TreeNode(symbol, left, right);
                    stack.push(root);
                }
            }
            String answer = horizontalWalk(stack.pop());
            System.out.println(answer);
        }
    }

    public static void main(final String[] arg) {
        solve();
    }
}

class StackForTwo<T> {
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


    public StackForTwo() {
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
    }

    public int size() {
        System.out.println(size_);
        return size_;
    }

    public T pop() {       //извлекаем из начала очереди
        if (size_ < 1) {
            throw new EmptyStackException();
        } else {
            Node temp = head_;
            head_ = head_.next_;
            --size_;
            return (T) temp.data_;

        }
    }

    public void clear() {
        head_ = new Node();
        size_ = 0;
    }
}

class QueueForTwo<T> {
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


    public QueueForTwo() {
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
    }

    public int size() {
        return size_;
    }

    public T pop() {       //извлекаем из начала очереди
        if (size_ < 1) {
            throw new EmptyStackException();
        } else {
            Node temp = head_;
            if (head_.next_ != tail_) {
                head_ = head_.next_;
            } else {
                head_ = tail_;
                tail_ = new Node(null, null, head_);
                head_.next_ = tail_;
            }
            --size_;
            return (T) temp.data_;
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