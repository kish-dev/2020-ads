package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    private Value get(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int compare = root.key.compareTo(key);
        if (compare > 0) {
            return get(root.left, key);
        }
        if (compare < 0) {
            return get(root.right, key);
        }
        return root.value;
    }

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);

    }

    private Node put(Node root, Key key, Value value) {
        if (root == null) {
            ++size;
            return new Node(key, value, 1);
        }
        int compare = root.key.compareTo(key);
        if (compare > 0) {
            root.left = put(root.left, key, value);
        } else if (compare < 0) {
            root.right = put(root.right, key, value);
        } else {
            root.value = value;
        }
        fixHeight(root);
        root = balance(root);
        return root;
    }


    Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (factor(x) == -1) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }

    Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    Node rotateLeft(Node y) {
        Node x = y.right;
        y.right = x.left;
        x.left = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }


    void fixHeight(Node root) {
        root.height = 1 + Math.max(height(root.left), height(root.right));
    }

    int factor(Node x) {
        return height(x.left) - height(x.right);
    }


    private Node deleteMin(Node root) {
        if (root.left == null) {
            --size;
            return root.right;
        }
        root.left = deleteMin(root.left);
        return root;
    }

    private Node innerDelete(Node root) {
        if (root.right == null) {
            --size;
            return root.left;
        }
        if (root.left == null) {
            --size;
            return root.right;
        }
        Node temp = root;
        root = minNode(temp.right);
        root.right = deleteMin(temp.right);
        root.left = temp.left;

        return root;
    }

    private Node delete(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int compare = root.key.compareTo(key);
        if (compare > 0) {
            root.left = delete(root.left, key);
        } else if (compare < 0) {
            root.right = delete(root.right, key);
        } else if (root.key.compareTo(key) == 0) {
            root = innerDelete(root);
        }
        return root;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value value = get(key);
        root = delete(root, key);
        return value;
    }

    private Node minNode(Node root) {
        if (root == null) {
            return null;
        }
        Node temp = root;
        Node ret = temp;
        while (temp.left != null) {
            ret = temp;
            temp = temp.left;
        }
        if (ret.left == null) {
            return ret;
        }
        return ret.left;
    }

    private Node maxNode(Node root) {
        if (root == null) {
            return null;
        }
        Node temp = root;
        Node ret = temp;
        while (temp.right != null) {
            ret = temp;
            temp = temp.right;
        }
        if (ret.right == null) {
            return ret;
        }
        return ret.right;
    }

    @Override
    public Key min() {
        Node temp = minNode(root);
        if (temp == null) {
            return null;
        }
        return temp.key;
    }

    @Override
    public Value minValue() {
        Node temp = minNode(root);
        if (temp == null) {
            return null;
        }
        return temp.value;
    }

    @Override
    public Key max() {
        Node temp = maxNode(root);
        if (temp == null) {
            return null;
        }
        return temp.key;
    }

    @Override
    public Value maxValue() {
        Node temp = maxNode(root);
        if (temp == null) {
            return null;
        }
        return temp.value;
    }

    private Node floorNode(Node node, Key key) {
        if (size(node) == 0) {
            return null;
        }

        int compare = root.key.compareTo(key);
        if (compare == 0)
            return node;

        if (compare < 0)
            return floorNode(node.left, key);

        Node right = floorNode(node.right, key);

        return right == null ? node : right;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node temp = floorNode(root, key);
        if (temp == null) {
            return null;
        }
        return temp.key;
    }

    private Node ceilNode(Node node, Key key) {
        if (node == null)
            return null;

        if (key == node.key){
            return node;
        }

        if (key.compareTo(node.key) > 0)
            return ceilNode(node.right, key);

        Node left = ceilNode(node.left, key);

        return left == null ? node : left;
    }

    private int size(Node root) {
        if (root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node temp = ceilNode(root, key);
        if (temp == null) {
            return null;
        }
        return temp.key;
    }

    @Override
    public int size() {
        return size;
    }

    private int height(Node root) {
        return root == null ? 0 : root.height;
    }

    @Override
    public int height() {
        return height(root);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
