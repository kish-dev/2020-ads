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

    private class List<Key, Value> {

        public class Node<Key, Value> {
            public final Key key;
            public Value value;
            public Node<Key, Value> next;

            Node(Key key, Value value, Node<Key, Value> next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }

        private Node<Key, Value> head;
        private Node<Key, Value> tail;
        int size = 0;

        List() {
            head = null;
            tail = null;
        }

        Node get(Key key) {
            Node temp = head;
            while (temp != tail) {
                if (temp.key == key) {
                    return temp;
                }
                temp = temp.next;
            }
            if (tail != null && tail.key == key) {
                return tail;
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
            if (head.key == key) {
                value = head.value;
                head = head.next;
                --size;
            } else {
                Node temp = head;
                Node prev = temp;
                while (temp.next != null) {
                    if (temp.key == key) {
                        value = (Value) temp.value;
                        if (temp == tail) {
                            tail = prev;
                            tail.next = null;
                        } else {
                            prev.next = temp.next;
                        }
                        --size;
                    }
                }
            }
            return value;
        }

        boolean add(Key key, Value value) {
            if (head == null) {
                ++size;
                head = new Node<>(key, value, null);
                tail = head;
                return true;
            } else if (head == tail) {
                if (head.key == key) {
                    head.value = value;
                    tail = head;
                    return false;
                } else {
                    ++size;
                    tail = new Node<>(key, value, null);
                    head.next = tail;
                    return true;
                }
            } else {
                Node temp = head;
                while (temp != tail) {
                    if (temp.key == key) {
                        temp.value = value;
                        return false;
                    }
                    temp = temp.next;
                }
                if (tail != null && tail.key == key) {
                    tail.value = value;
                    return false;
                }
                ++size;
                Node temp1 = tail;
                tail = new Node<>(key, value, null);
                temp1.next = tail;
                return true;
            }
        }

        int size() {
            return size;
        }

        boolean contains(Key key) {
            if (head == null) {
                return false;
            }
            Node temp = head;
            while (temp != tail) {
                if (temp.key == key) {
                    return true;
                }
                temp = temp.next;
            }
            if (tail != null && tail.key == key) {
                return true;
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
            int newM = this.m * 2;
            List<Key, Value>[] prevTable = this.hashTable;
            this.hashTable = new List[newM];
            for (int i = 0; i < m; ++i) {
                this.hashTable[i] = new List<>();
                List.Node temp = prevTable[i].head;
                if (temp == null) {
                    continue;
                }
                while (temp != prevTable[i].tail) {
                    this.put((Key) temp.key, (Value) temp.value);
                }
                if (prevTable[i].tail != null) {
                    this.put(prevTable[i].tail.key, prevTable[i].tail.value);
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
