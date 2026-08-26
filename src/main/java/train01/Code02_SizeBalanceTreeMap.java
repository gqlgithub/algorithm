package train01;

public class Code02_SizeBalanceTreeMap<K extends Comparable<K>, V> {
    private static class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        int size;

        Node(K k, V v, Node<K, V> l, Node<K, V> r, int sz) {
            key = k;
            value = v;
            left = l;
            right = r;
            size = sz;
        }
    }

    Node<K, V> root;
    int size;

    private Node<K, V> rightRote(Node<K, V> node) {
        Node<K, V> left = node.left;
        node.left = left.right;
        left.right = node;
        node.size = (node.left != null ? node.left.size : 0) + (node.right != null ? node.right.size : 0) + 1;
        left.size = (left.left != null ? left.left.size : 0) + node.size + 1;
        return left;
    }

    private Node<K, V> leftRote(Node<K, V> node) {
        Node<K, V> right = node.right;
        node.right = right.left;
        right.left = node;
        node.size = (node.left != null ? node.left.size : 0) + (node.right != null ? node.right.size : 0) + 1;
        right.size = node.size + (right.right != null ? right.right.size : 0) + 1;
        return right;
    }

    private Node<K, V> maintain(Node<K, V> node) {
        if (node == null) return null;

        int leftSize = node.left != null ? node.left.size : 0;
        int rightSize = node.right != null ? node.right.size : 0;
        int leftLeftSize = node.left != null && node.left.left != null ? node.left.left.size : 0;
        int leftRightSize = node.left != null && node.left.right != null ? node.left.right.size : 0;
        int rightLeftSize = node.right != null && node.right.left != null ? node.right.left.size : 0;
        int rightRightSize = node.right != null && node.right.right != null ? node.right.right.size : 0;

        if (rightSize < leftLeftSize) {
            node = rightRote(node);
            node.right = maintain(node.right);
            node = maintain(node);
        } else if (rightSize < leftRightSize) {
            node.left = leftRote(node.left);
            node = rightRote(node);
            node.left = maintain(node.left);
            node.right = maintain(node.right);
            node = maintain(node);
        } else if (leftSize < rightRightSize) {
            node = leftRote(node);
            node.left = maintain(node.left);
            node = maintain(node);
        } else if (leftSize < rightLeftSize) {
            node.right = rightRote(node.right);
            node = leftRote(node);
            node.left = maintain(node.left);
            node.right = maintain(node.right);
            node = maintain(node);
        }

        return node;
    }

    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (node == null) return new Node<>(key, value, null, null, 1);

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else {
            node.right = add(node.right, key, value);
        }

        node.size = (node.left != null ? node.left.size : 0) + (node.right != null ? node.right.size : 0) + 1;
        return maintain(node);
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if (key.compareTo(node.key) < 0) {
            node.left = delete(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                Node<K, V> cur = node.right;
                Node<K, V> pre = null;
                while (cur.left != null) {
                    pre = cur;
                    cur.size--;
                    cur = cur.left;
                }
                if (pre != null) {
                    pre.left = cur.right;
                }
                cur.left = node.left;
                if (cur != node.right) {
                    cur.right = node.right;
                }
                node = cur;
            }
        }

        if (node != null) {
            node.size = (node.left != null ? node.left.size : 0) + (node.right != null ? node.right.size : 0) + 1;
        }
        return node;
    }

    public Node<K, V> findKey(K key) {
        Node<K, V> cur = root;

        while (cur != null) {
            if (key.compareTo(cur.key) < 0) {
                cur = cur.left;
            } else if (key.compareTo(cur.key) > 0) {
                cur = cur.right;
            } else {
                return cur;
            }
        }
        return null;
    }

    public V get(K key) {
        Node<K, V> node = findKey(key);
        return node != null ? node.value : null;
    }

    private Node<K, V> getLessMostRight(K key) {
        Node<K, V> cur = this.root;
        Node<K, V> pre = null;
        while (cur != null) {
            if (cur.key.compareTo(key) <= 0) {
                pre = cur;
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return pre;
    }

    private Node<K, V> getBigMostLeft(K key) {
        Node<K, V> cur = root;
        Node<K, V> pre = null;
        while (cur != null) {
            if (cur.key.compareTo(key) >= 0) {
                pre = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return pre;
    }

    public K firstKey() {
        Node<K, V> cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.key;
    }

    public K lastKey() {
        Node<K, V> cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.key;
    }

    public K ceilingKey(K key) {
        Node<K, V> bigMostLeft = getBigMostLeft(key);
        return bigMostLeft != null ? bigMostLeft.key : null;
    }

    public K floorKey(K key) {
        Node<K, V> lessMostRight = getLessMostRight(key);
        return lessMostRight != null ? lessMostRight.key : null;
    }

    public boolean containsKey(K key) {
        Node<K, V> node = findKey(key);
        return node != null;
    }

    public void put(K key, V value) {
        Node<K, V> node = findKey(key);
        if (node != null) {
            node.value = value;
        } else {
            size++;
            root = add(root, key, value);
        }
    }

    public void remove(K key) {
        if (findKey(key) != null) {
            size--;
            root = delete(root, key);
        }
    }

    public int size() {
        return size;
    }
}
