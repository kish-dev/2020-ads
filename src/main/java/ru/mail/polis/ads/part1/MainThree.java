package ru.mail.polis.ads.part1;
import java.io.*;
import java.util.Scanner;

public class MainThree {

    public static void main(String[] args) throws IOException {

        Stack<Character> stack = new Stack<>();
        stack.isSequencesRight(stack);

    }
}


class Stack<T> {
    private class Node<T> {
        Node() {
            this.data_ = null;
            this.next_ = null;
        }

        Node(T data, Node next) {
            this.data_ = data;
            this.next_ = next;
        }

        T data_ = null;
        Node next_ = null;

    }

    private Node head_;
    private int size_;


    public Stack() {
        head_ = new Node();
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
        return size_;
    }

    public Character pop() {       //извлекаем из начала очереди
        if (size_ < 1) {
            return ')';
        } else {
            Node temp = head_;
            head_ = head_.next_;
            --size_;
            return (Character) temp.data_;
        }
    }

    public void printStack() throws IOException {
        Node temp = head_;
        StringBuilder stringBuilder = new StringBuilder();
        while (temp.next_ != null) {
            stringBuilder.append(temp.data_);
            temp = temp.next_;
        }
        if (temp.data_ != null) {
            stringBuilder.append(temp.data_);
        }
        String string = new String(stringBuilder);
        System.out.println(string);
    }

    public void isSequencesRight(Stack<Character> stack) {
        Scanner in = new Scanner(System.in);
        String string = new String(in.next());

        for (int i = 0; i < string.length(); ++i) {
            Character character = string.charAt(i);
            if(character == '(') {
                stack.push(character);
            }
            else {
                char current = ')';
                current = stack.pop();
                if(current != '(')
                {
                    System.out.println("NO");
                    return;
                }
            }
        }
        if(stack.size() == 0) {
            System.out.println("YES");
            return;
        }


        System.out.println("NO");
    }
}