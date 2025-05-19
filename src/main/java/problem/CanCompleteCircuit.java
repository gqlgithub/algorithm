package problem;

import java.util.LinkedList;

/**
 * @Description:
 * @Author:
 * @Date: 2025-02-12 10:22
 **/
public class CanCompleteCircuit {

    public static int canCompleteCircuit1(int[] gas, int[] cost) {
        LinkedList<Integer> queue = new LinkedList<>();
        int[] nums = new int[gas.length << 1];
        boolean[] result = new boolean[gas.length];

        nums[0] = gas[0] - cost[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + gas[i % gas.length] - cost[i % gas.length];
        }

        for (int right = 0; right < gas.length; right++) {
            while (!queue.isEmpty() && queue.peekLast() > nums[right]) {
                queue.pollLast();
            }
            queue.add(nums[right]);
        }

        for (int offset = 0, left = 0, right = gas.length; left < gas.length; offset = nums[left++], right++) {
            if (queue.peekFirst() - offset >= 0)
                result[left] = true;

            if (queue.peekFirst() == nums[left])
                queue.pollFirst();

            while (!queue.isEmpty() && queue.peekLast() > nums[right]) {
                queue.pollLast();
            }
            queue.add(nums[right]);
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] canCompleteCircuit(int[] gas, int[] cost) {
        int start;
        int end;
        int need = 0;
        int reset = 0;
        int init = -1;

        for (int i = 0; i < gas.length; i++) {
            gas[i] = gas[i] - cost[i];
            if (gas[i] >= 0) init = i;
        }
        if (init == -1) return new boolean[gas.length];
        start = init;
        end = nextNode(init, gas.length);
        boolean[] result = new boolean[gas.length];

        do {
            if (start != init && start == lastNode(end, gas.length)) break;

            if (gas[start] < need) {
                need -= gas[start];
            } else {
                reset += gas[start] - need;
                need = 0;

                while (end != start && reset >= 0) {
                    reset += gas[end];
                    end = nextNode(end, gas.length);
                }
                if (reset >= 0) {
                    result[start] = true;
                    connectGoodStation(lastNode(start, gas.length), init, gas, result);
                    break;
                }
            }
            start = lastNode(start, gas.length);
        } while (start != init);

        return result;
    }

    public static void connectGoodStation(int start, int init, int[] gas, boolean[] result) {
        int need = 0;
        while (start != init) {
            if (gas[start] < need) {
                need -= gas[start];
            } else {
                need = 0;
                result[start] = true;
            }
            start = lastNode(start, gas.length);
        }
    }

    public static int lastNode(int start, int size) {
        return start == 0 ? size - 1 : start - 1;
    }

    public static int nextNode(int end, int size) {
        return end == size - 1 ? 0 : end + 1;
    }

    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        int p1 = canCompleteCircuit1(gas, cost);
        int p2 = -1;
        boolean[] booleans = canCompleteCircuit(gas, cost);

        for (int i = 0; i < booleans.length; i++) {
            if (booleans[i]) {
                p2 = i;
                break;
            }
        }
        System.out.println(p1);
        System.out.println(p2);

    }
}
