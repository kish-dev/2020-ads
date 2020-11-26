package ru.mail.polis.ads.hash;

import org.checkerframework.checker.units.qual.K;
import org.checkerframework.checker.units.qual.Length;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.management.ValueExp;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Table<Key, Value> implements HashTable<Key, Value> {

    private class Node<Key, Value> {
        public final Key key;
        public Value value;
        public Node<Key, Value> next;

        Node(Key key, Value value, Node<Key, Value> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private class List<Key, Value> {


        private Node<Key, Value> head;

        List() {
            head = null;
        }

        Node get(Key key) {
            Node temp = head;
            while (temp != null) {
                if (temp.key.equals(key)) {
                    return temp;
                }
                temp = temp.next;
            }
            return null;
        }

        Value getValue(Key key) {
            Node temp = get(key);
            return temp == null ? null : (Value) temp.value;
        }

        Key getKey(Key key) {
            Node temp = get(key);
            return temp == null ? null : (Key) temp.key;
        }

        Value remove(Key key) {
            Value value = null;
            if (head == null) {
                return null;
            }
            if (head.key.equals(key)) {
                value = head.value;
                head = head.next;
            } else {
                Node temp = head;
                Node prev = temp;
                while (temp.next != null) {
                    if (temp.key.equals(key)) {
                        value = (Value) temp.value;
                        prev.next = temp.next;
                    }
                }
            }
            return value;
        }

        boolean add(Key key, Value value) {
            if (head == null) {
                head = new Node<>(key, value, null);
                return true;
            } else if (head.next == null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    return false;
                } else {
                    head.next = new Node<>(key, value, null);
                    return true;
                }
            } else {
                Node temp = head;
                while (temp != null) {
                    if (temp.key.equals(key)) {
                        temp.value = value;
                        return false;
                    }
                    temp = temp.next;
                }

                temp = head;
                head = new Node<>(key, value, temp);
                return true;
            }
        }

        boolean contains(Key key) {
            if (head == null) {
                return false;
            }
            Node temp = head;
            while (temp != null) {
                if (temp.key == key) {
                    return true;
                }
                temp = temp.next;
            }
            return false;
        }
    }

    private final double loadFactor = 0.75;
    private int n = 0;  //количество элементов
    private int m = 0;  //размер таблицы
    private List<Key, Value>[] hashTable;

    Table() {
        this(16);
    }

    Table(int size) {
        m = size;
        hashTable = new List[m];
        for (int i = 0; i < m; ++i) {
            hashTable[i] = new List();
        }
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int hash = hashFunction(key);
        int index = index(hash);
        Value value;

        value = hashTable[index].getValue(key);

        return value;
    }

    @Override
    public boolean containsKey(@NotNull Key key) {
        int hash = hashFunction(key);
        int index = index(hash);
        return hashTable[index].contains(key);
    }

    void resizeIfNeed() {
        if (loadFactor() > loadFactor) {
            int oldM = m;
            List<Key, Value>[] prevTable = hashTable;
            n = 0;
            m *= 2;
            hashTable = new List[m];
            for (int i = 0; i < m; ++i) {
                hashTable[i] = new List<>();
            }
            for (int i = 0; i < oldM; ++i) {
                if (prevTable[i] != null) {
                    Node<Key, Value> temp = prevTable[i].head;
                    while (temp != null) {
                        this.put(temp.key, temp.value);
                        temp = temp.next;
                    }
                }
            }
        }
    }

    int hashFunction(Key key) {
        return key.hashCode();
    }

    int index(int hash) {
        return (hash & 0x7fffffff) % m;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        resizeIfNeed();
        int hash = hashFunction(key);
        int index = index(hash);
        if (hashTable[index].add(key, value)) {
            ++this.n;
        }

    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int hash = hashFunction(key);
        int index = index(hash);
        Value value = hashTable[index].remove(key);
        if (value != null) {
            --this.n;
        }
        return value;
    }

    private double loadFactor() {
        return (double) n / (double) m;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }
}
