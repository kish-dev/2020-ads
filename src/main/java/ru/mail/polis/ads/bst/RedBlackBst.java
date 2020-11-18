package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private Node root;
    private int size = 0;

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && !isRed(x.right)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        return x;
    }

    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int compare = x.key.compareTo(key);

        if (compare == 0) {
            return x;
        }
        if (compare > 0) {
            return get(x.left, key);
        }
        return get(x.right, key);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node x = get(root, key);
        if (x != null) {
            return x.value;
        }
        return null;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            ++size;
            return new Node(key, value, RED);
        }
        int compare = x.key.compareTo(key);
        if (compare > 0) {
            x.left = put(x.left, key, value);
        } else if (compare < 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    Node deleteMax(Node x) {
        if (isRed(x.left)) {
            x = rotateRight(x);
        }
        if (x.right == null) {
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x);
        }
        x.right = deleteMax(x.right);
        return fixUp(x);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Value buffer = get(key);
        if (buffer != null) {
            --size;
            root = remove(root, key);
        }
        return buffer;
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int compareResult = key.compareTo(x.key);
        if (compareResult < 0 && x.left != null) {
            if (!isRed(x.left) && !isRed(x.left.left)) {
                x = moveRedLeft(x);
            }
            x.left = remove(x.left, key);
        } else if (isRed(x.left)) {
            x = rotateRight(x);
            x.right = remove(x.right, key);
        } else if (compareResult == 0 && x.right == null) {
            return null;
        } else {
            if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                x = moveRedRight(x);
            }
            if (key.compareTo(x.key) == 0) {
                Node min = min(x.right);
                x.key = min.key;
                x.value = min.value;
                x.right = deleteMin(x.right);
            } else {
                x.right = remove(x.right, key);
            }
        }
        return fixUp(x);
    }

    private Node deleteMin(Node x) {
        if (x == null || x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    @Nullable
    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        return min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        if (root == null) {
            return null;
        }
        return min(root).value;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    @Nullable
    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        return max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        return max(root).value;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int compare = x.key.compareTo(key);

        if (compare == 0) {
            return x;
        }
        if (compare > 0) {
            return floor(x.left, key);
        }

        Node returnNode = floor(x.right, key);
        if (returnNode == null) {
            return x;
        }
        return returnNode;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int compare = x.key.compareTo(key);

        if (compare == 0) {
            return x;
        }
        if (compare < 0) {
            return ceil(x.right, key);
        }

        Node returnNode = ceil(x.left, key);
        if (returnNode == null) {
            return x;
        }
        return returnNode;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Node x = ceil(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
