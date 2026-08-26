package train01;

import java.util.ArrayList;
import java.util.Random;

public class Code03_SkipListMap<K extends Comparable<K>, V> {
    private static class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        ArrayList<Node<K, V>> nextNodes;

        Node() {
        }

        Node(K k, V v, ArrayList<Node<K, V>> nextNodes) {
            key = k;
            value = v;
            this.nextNodes = nextNodes;
        }
    }

    Node<K, V> head;
    int maxLevel;
    int size;
    static final double SEED = 0.5;

    public Code03_SkipListMap() {
        head = new Node<>();
        head.nextNodes = new ArrayList<>();
        head.nextNodes.add(null);
        maxLevel = 0;
        size = 0;
    }

    private Node<K, V> findLessMostRight(K key) {
        int level = maxLevel;
        Node<K, V> pre = head;
        while (level >= 0) {
            pre = findLessMostRightInLevel(key, pre, level--);
        }
        return pre;
    }

    private Node<K, V> findLessMostRightInLevel(K key, Node<K, V> pre, int level) {
        Node<K, V> next = pre.nextNodes.get(level);
        while (next != null && next.key.compareTo(key) < 0) {
            pre = next;
            next = next.nextNodes.get(level);
        }
        return pre;
    }

    public V get(K key) {
        Node<K, V> node = findLessMostRight(key).nextNodes.get(0);
        return node.key.compareTo(key) == 0 ? node.value : null;
    }

    public K firstKey() {
        return head.nextNodes.get(0).key;
    }

    public K lastKey() {
        int level = maxLevel;
        Node<K, V> cur = head;
        while (level >= 0) {
            Node<K, V> next = cur.nextNodes.get(level);
            while (next != null) {
                cur = next;
                next = next.nextNodes.get(level);
            }
            level--;
        }
        return cur.key;
    }

    public K ceilingKey(K key) {
        Node<K, V> lessMostRight = findLessMostRight(key);
        Node<K, V> cur = lessMostRight.nextNodes.get(0);
        return cur != null && cur.key.compareTo(key) >= 0 ? cur.key : null;
    }

    public K floorKey(K key) {
        Node<K, V> lessMostRight = findLessMostRight(key);
        Node<K, V> next = lessMostRight.nextNodes.get(0);
        return next != null && next.key.compareTo(key) == 0 ? next.key : lessMostRight.key;
    }

    public void put(K key, V value) {
        Node<K, V> node = findLessMostRight(key).nextNodes.get(0);
        if (node != null && node.key.compareTo(key) == 0) {
            node.value = value;
        } else {
            add(key, value);
        }
    }

    public boolean containsKey(K key) {
        Node<K, V> node = findLessMostRight(key).nextNodes.get(0);
        return node != null && node.key.compareTo(key) == 0;
    }

    private void add(K key, V value) {
        size++;

        int level = 0;
        Random random = new Random();
        while (random.nextDouble() > SEED) {
            level++;
        }
        while (maxLevel < level) {
            head.nextNodes.add(null);
            maxLevel++;
        }
        Node<K, V> node = new Node<>(key, value, new ArrayList<>());
        while (node.nextNodes.size() <= level) {
            node.nextNodes.add(null);
        }

        int curLevel = maxLevel;
        Node<K, V> pre = head;
        while (curLevel >= 0) {
            pre = findLessMostRightInLevel(key, pre, curLevel);
            if (curLevel <= level) {
                node.nextNodes.set(curLevel, pre.nextNodes.get(curLevel));
                pre.nextNodes.set(curLevel, node);
            }
            curLevel--;
        }
    }

    public void delete(K key) {
        if (containsKey(key)) {
            size--;

            int level = maxLevel;
            Node<K, V> pre = head;
            while (level >= 0) {
                pre = findLessMostRightInLevel(key, pre, level);
                Node<K, V> next = pre.nextNodes.get(level);
                if (next != null && next.key.compareTo(key) == 0) {
                    pre.nextNodes.set(level, next.nextNodes.get(level));
                }
                if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                    head.nextNodes.remove(level);
                    maxLevel--;
                }
                level--;
            }
        }
    }

    public void remove(K key) {
        delete(key);
    }

    public int size() {
        return size;
    }
}
