package train01;

public class Code01_AVLTreeMap<K extends Comparable<K>, V> {
    private static class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        int height;

        Node(K k, V v, Node<K, V> l, Node<K, V> r, int h) {
            key = k;
            value = v;
            left = l;
            right = r;
            height = h;
        }
    }

    public Node<K, V> root;
    public int size;

    public Code01_AVLTreeMap() {
        root = null;
        size = 0;
    }

    private Node<K, V> rightRotate(Node<K, V> node) {
        Node<K, V> left = node.left;
        node.left = left.right;
        left.right = node;
        node.height = Math.max((node.left != null ? node.left.height : 0), (node.right != null ? node.right.height : 0)) + 1;
        left.height = Math.max((left.left != null ? left.left.height : 0), node.height) + 1;
        return left;
    }

    private Node<K, V> leftRotate(Node<K, V> node) {
        Node<K, V> right = node.right;
        node.right = right.left;
        right.left = node;
        node.height = Math.max((node.left != null ? node.left.height : 0), (node.right != null ? node.right.height : 0)) + 1;
        right.height = Math.max(right.right != null ? right.right.height : 0, node.height) + 1;
        return right;
    }

    private Node<K, V> maintain(Node<K, V> r) {
        if (r == null) return null;

        int leftHeight = r.left != null ? r.left.height : 0;
        int rightHeight = r.right != null ? r.right.height : 0;
        if (Math.abs(leftHeight - rightHeight) <= 1) {
            return r;
        }

        if (leftHeight > rightHeight) {
            int leftLeftHeight = r.left.left != null ? r.left.left.height : 0;
            int leftRightHeight = r.left.right != null ? r.left.right.height : 0;
            if (leftLeftHeight >= leftRightHeight) {
                r = rightRotate(r);
            } else {
                r.left = leftRotate(r.left);
                r = rightRotate(r);
            }
        } else {
            int rightLeftHeight = r.right.left != null ? r.right.left.height : 0;
            int rightRightHeight = r.right.right != null ? r.right.right.height : 0;
            if (rightRightHeight >= rightLeftHeight) {
                r = leftRotate(r);
            } else {
                r.right = rightRotate(r.right);
                r = leftRotate(r);
            }
        }

        return r;
    }

    private Node<K, V> add(Node<K, V> r, K key, V value) {
        if (r == null) {
            return new Node<>(key, value, null, null, 1);
        }

        if (key.compareTo(r.key) < 0) {
            r.left = add(r.left, key, value);
        } else {
            r.right = add(r.right, key, value);
        }

        r.height = Math.max(r.left != null ? r.left.height : 0, r.right != null ? r.right.height : 0) + 1;
        return maintain(r);
    }

    private Node<K, V> delete(Node<K, V> r, K key) {
        if (key.compareTo(r.key) < 0) {
            r.left = delete(r.left, key);
        } else if (key.compareTo(r.key) > 0) {
            r.right = delete(r.right, key);
        } else {
            if (r.left == null && r.right == null) {
                r = null;
            } else if (r.left == null) {
                r = r.right;
            } else if (r.right == null) {
                r = r.left;
            } else {
                Node<K, V> mostRightLeft = r.right;
                while (mostRightLeft.left != null) {
                    mostRightLeft = mostRightLeft.left;
                }
                mostRightLeft.right = delete(r.right, mostRightLeft.key);
                mostRightLeft.left = r.left;
                r = mostRightLeft;
            }
        }

        if (r != null) {
            r.height = Math.max((r.left != null ? r.left.height : 0), (r.right != null ? r.right.height : 0)) + 1;
        }
        return maintain(r);
    }

    private Node<K, V> findKey(K key) {
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

    private Node<K, V> findLessMostRight(K key) {
        Node<K, V> cur = root;
        Node<K, V> pre = null;

        while (cur != null) {
            if (cur.key.compareTo(key) > 0) {
                cur = cur.left;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }

        return pre;
    }

    private Node<K, V> findBigMostLeft(K key) {
        Node<K, V> cur = root;
        Node<K, V> pre = null;

        while (cur != null) {
            if (cur.key.compareTo(key) < 0) {
                cur = cur.right;
            } else {
                pre = cur;
                cur = cur.left;
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
        return findBigMostLeft(key).key;
    }

    public K floorKey(K key) {
        return findLessMostRight(key).key;
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

    public V get(K key) {
        Node<K, V> node = findKey(key);
        return node != null ? node.value : null;
    }

    public void remove(K key) {
        if (containsKey(key)) {
            size--;
            root = delete(root, key);
        }
    }

    public boolean containsKey(K key) {
        return findKey(key) != null;
    }

    public int size() {
        return size;
    }
}