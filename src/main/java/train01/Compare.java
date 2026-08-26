package train01;

import java.util.TreeMap;

public class Compare {

    public static void functionTest() {
        Code01_AVLTreeMap<Integer, Integer> avlTreeMap = new Code01_AVLTreeMap<>();
        Code02_SizeBalanceTreeMap<Integer, Integer> sizeBalancedTreeMap = new Code02_SizeBalanceTreeMap<>();
        Code03_SkipListMap<Integer, Integer> skipListMap = new Code03_SkipListMap<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        int keyMax = 1000;
        int valueMax = 5000;
        int testCount = 1_000_000;

        for (int i = 0; i < testCount; i++) {
            int key = (int) (Math.random() * keyMax);
            int value = (int) (Math.random() * valueMax);

            avlTreeMap.put(key, value);
            sizeBalancedTreeMap.put(key, value);
            skipListMap.put(key, value);
            treeMap.put(key, value);

            int removeKey = (int) (Math.random() * keyMax);
            avlTreeMap.remove(removeKey);
            sizeBalancedTreeMap.remove(removeKey);
            skipListMap.remove(removeKey);
            treeMap.remove(removeKey);

            int getKey = (int) (Math.random() * keyMax);

            boolean contains0 = avlTreeMap.containsKey(getKey);
            boolean contains1 = sizeBalancedTreeMap.containsKey(getKey);
            boolean contains2 = skipListMap.containsKey(getKey);
            boolean contains3 = treeMap.containsKey(getKey);
            if (!(contains0 == contains1
                    && contains1 == contains2
                    && contains2 == contains3)) {
                System.out.println(" ======= containsKey ========== ");
                System.out.println(contains0);
                System.out.println(contains1);
                System.out.println(contains2);
                System.out.println(contains3);
                break;
            }

            if (treeMap.containsKey(getKey)) {
                int avl = avlTreeMap.get(getKey);
                int sizeBalanced = sizeBalancedTreeMap.get(getKey);
                int skipList = skipListMap.get(getKey);
                int treeMapValue = treeMap.get(getKey);

                if (!(avl == sizeBalanced
                        && sizeBalanced == skipList
                        && skipList == treeMapValue)) {
                    System.out.println(" ======= getKey ========== ");
                    System.out.println(avl);
                    System.out.println(sizeBalanced);
                    System.out.println(skipList);
                    System.out.println(treeMapValue);
                    break;
                }

                Integer v0 = avlTreeMap.firstKey();
                Integer v1 = sizeBalancedTreeMap.firstKey();
                Integer v2 = skipListMap.firstKey();
                Integer v3 = treeMap.firstKey();

                if (v0 == null && (
                        v1 != null
                                || v2 != null
                                || v3 != null)) {
                    System.out.println(" ======= firstKey ========== ");
                    System.out.println(v0);
                    System.out.println(v1);
                    System.out.println(v2);
                    System.out.println(v3);
                    break;
                }

                if (v0 != null && (v1 == null
                        || v2 == null
                        || v3 == null)) {
                    System.out.println(" ======= firstKey ========== ");
                    System.out.println(v0);
                    System.out.println(v1);
                    System.out.println(v2);
                    System.out.println(v3);
                    break;
                }

                if (v0 != null) {
                    int i0 = v0;
                    int i1 = v1;
                    int i2 = v2;
                    int i3 = v3;
                    if (!(i0 == i1
                            && i1 == i2
                            && i2 == i3)) {
                        System.out.println(" ======= firstKey ========== ");
                        System.out.println(i0);
                        System.out.println(i1);
                        System.out.println(i2);
                        System.out.println(i3);
                        break;
                    }
                }

                v0 = avlTreeMap.lastKey();
                v1 = sizeBalancedTreeMap.lastKey();
                v2 = skipListMap.lastKey();
                v3 = treeMap.lastKey();

                if (v0 == null && (v1 != null || v2 != null || v3 != null)) {
                    System.out.println(" ======= lastKey ========== ");
                    System.out.println(v0);
                    System.out.println(v1);
                    System.out.println(v2);
                    System.out.println(v3);
                    break;
                }

                if (v0 != null && (v1 == null || v2 == null || v3 == null)) {
                    System.out.println(" ======= lastKey ========== ");
                    System.out.println(v0);
                    System.out.println(v1);
                    System.out.println(v2);
                    System.out.println(v3);
                    break;
                }

                if (v0 != null) {
                    int i0 = v0;
                    int i1 = v1;
                    int i2 = v2;
                    int i3 = v3;
                    if (!(i0 == i1 && i1 == i2 && i2 == i3)) {
                        System.out.println(" ======= lastKey ========== ");
                        System.out.println(i0);
                        System.out.println(i1);
                        System.out.println(i2);
                        System.out.println(i3);
                        break;
                    }
                }

                v0 = avlTreeMap.ceilingKey(getKey);
                v1 = sizeBalancedTreeMap.ceilingKey(getKey);
                v2 = skipListMap.ceilingKey(getKey);
                v3 = treeMap.ceilingKey(getKey);

                if (v0 == null && (v1 != null || v2 != null || v3 != null)) {
                    System.out.println(" ======= ceilingKey ========== ");
                    System.out.println(v0);
                    System.out.println(v1);
                    System.out.println(v2);
                    System.out.println(v3);
                    break;
                }

                if (v0 != null && (v1 == null || v2 == null || v3 == null)) {
                    System.out.println(" ======= ceilingKey ========== ");
                    System.out.println(v0);
                    System.out.println(v1);
                    System.out.println(v2);
                    System.out.println(v3);
                    break;
                }

                if (v0 != null) {
                    int i0 = v0;
                    int i1 = v1;
                    int i2 = v2;
                    int i3 = v3;
                    if (!(i0 == i1 && i1 == i2 && i2 == i3)) {
                        System.out.println(" ======= ceilingKey ========== ");
                        System.out.println(i0);
                        System.out.println(i1);
                        System.out.println(i2);
                        System.out.println(i3);
                        break;
                    }
                }

                v0 = avlTreeMap.floorKey(getKey);
                v1 = sizeBalancedTreeMap.floorKey(getKey);
                v2 = skipListMap.floorKey(getKey);
                v3 = treeMap.floorKey(getKey);

                if (v0 == null && (v1 != null || v2 != null || v3 != null)) {
                    System.out.println(" ======= floorKey ========== ");
                    System.out.println(v0);
                    System.out.println(v1);
                    System.out.println(v2);
                    System.out.println(v3);
                    break;
                }

                if (v0 != null && (v1 == null || v2 == null || v3 == null)) {
                    System.out.println(" ======= floorKey ========== ");
                    System.out.println(v0);
                    System.out.println(v1);
                    System.out.println(v2);
                    System.out.println(v3);
                    break;
                }

                if (v0 != null) {
                    int i0 = v0;
                    int i1 = v1;
                    int i2 = v2;
                    int i3 = v3;
                    if (!(i0 == i1 && i1 == i2 && i2 == i3)) {
                        System.out.println(" ======= floorKey ========== ");
                        System.out.println(i0);
                        System.out.println(i1);
                        System.out.println(i2);
                        System.out.println(i3);
                        break;
                    }
                }
            }

            int avlTreeSize = avlTreeMap.size();
            int sizeBalancedTreeSize = sizeBalancedTreeMap.size();
            int skipListSize = skipListMap.size();
            int treeMapSize = treeMap.size();

            if (!(avlTreeSize == sizeBalancedTreeSize
                    && sizeBalancedTreeSize == skipListSize
                    && skipListSize == treeMapSize)) {
                System.out.println(" ======= getSize ========== ");
                System.out.println(avlTreeSize);
                System.out.println(sizeBalancedTreeSize);
                System.out.println(skipListSize);
                System.out.println(treeMapSize);
                break;
            }
        }
    }

    public static void performanceTest() {
        Code01_AVLTreeMap<Integer, Integer> avlTreeMap = new Code01_AVLTreeMap<>();
        Code02_SizeBalanceTreeMap<Integer, Integer> sizeBalanceTreeMap = new Code02_SizeBalanceTreeMap<>();
        Code03_SkipListMap<Integer, Integer> skipListMap = new Code03_SkipListMap<>();
        TreeMap<Object, Object> treeMap = new TreeMap<>();

        int testCount = 10_000_000;
        long start;

        System.out.println("==  递增PUT   ==");
        start = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            avlTreeMap.put(i, i);
        }
        System.out.println("AVLTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            sizeBalanceTreeMap.put(i, i);
        }
        System.out.println("SizeBalanceTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            skipListMap.put(i, i);
        }
        System.out.println("SkipList 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            treeMap.put(i, i);
        }
        System.out.println("TreeMap 用时：" + (System.currentTimeMillis() - start));

        System.out.println("==  递增REMOVE  ==");
        start = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            avlTreeMap.remove(i);
        }
        System.out.println("AVLTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            sizeBalanceTreeMap.remove(i);
        }
        System.out.println("SizeBalanceTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            skipListMap.remove(i);
        }
        System.out.println("SkipList 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            treeMap.remove(i);
        }
        System.out.println("TreeMap 用时：" + (System.currentTimeMillis() - start));


        System.out.println("==  递减PUT   ===");
        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            avlTreeMap.put(i, i);
        }
        System.out.println("AVLTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            sizeBalanceTreeMap.put(i, i);
        }
        System.out.println("SizeBalanceTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            skipListMap.put(i, i);
        }
        System.out.println("SkipList 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            treeMap.put(i, i);
        }
        System.out.println("TreeMap 用时：" + (System.currentTimeMillis() - start));

        System.out.println("==  递减REMOVE    ==");
        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            avlTreeMap.remove(i);
        }
        System.out.println("AVLTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            sizeBalanceTreeMap.remove(i);
        }
        System.out.println("SizeBalanceTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            skipListMap.remove(i);
        }
        System.out.println("SkipList 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            treeMap.remove(i);
        }
        System.out.println("TreeMap 用时：" + (System.currentTimeMillis() - start));


        System.out.println("==  随机PUT   ==");
        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            avlTreeMap.put((int)(i *Math.random()), i);
        }
        System.out.println("AVLTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            sizeBalanceTreeMap.put((int)(i *Math.random()), i);
        }
        System.out.println("SizeBalanceTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            skipListMap.put((int)(i *Math.random()), i);
        }
        System.out.println("SkipList 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            treeMap.put((int)(i *Math.random()), i);
        }
        System.out.println("TreeMap 用时：" + (System.currentTimeMillis() - start));

        System.out.println("==  随机REMOVE    ==");
        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            avlTreeMap.remove((int)(i *Math.random()));
        }
        System.out.println("AVLTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            sizeBalanceTreeMap.remove((int)(i *Math.random()));
        }
        System.out.println("SizeBalanceTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            skipListMap.remove((int)(i *Math.random()));
        }
        System.out.println("SkipList 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            treeMap.remove((int)(i *Math.random()));
        }
        System.out.println("TreeMap 用时：" + (System.currentTimeMillis() - start));


        for (int i = 0; i < testCount; i++) {
            avlTreeMap.put(i, i);
        }
        for (int i = 0; i < testCount; i++) {
            sizeBalanceTreeMap.put(i, i);
        }
        for (int i = 0; i < testCount; i++) {
            skipListMap.put(i, i);
        }
        for (int i = 0; i < testCount; i++) {
            treeMap.put(i, i);
        }

        System.out.println("==  随机Query ==");
        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            avlTreeMap.get((int)(i *Math.random()));
        }
        System.out.println("AVLTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            sizeBalanceTreeMap.get((int)(i *Math.random()));
        }
        System.out.println("SizeBalanceTree 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            skipListMap.get((int)(i *Math.random()));
        }
        System.out.println("SkipList 用时：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = testCount; i >= 0; i--) {
            treeMap.get((int)(i *Math.random()));
        }
        System.out.println("TreeMap 用时：" + (System.currentTimeMillis() - start));

    }

    public static void main(String[] args) {
        System.out.println("======= start functionTest  =========");
        functionTest();
        System.out.println("======= end functionTest    =========");

        System.out.println("======= start performanceTest  =========");
        performanceTest();
        System.out.println("======= end performanceTest    =========");
    }
}
