package train02;

/**
 * @Description:
 * @Author:
 * @Date: 2026-11-14 8:59
 **/
public class CanCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] engry = new int[gas.length];
        for (int i = 0; i < engry.length; i++) {
            engry[i] = gas[i] - cost[i];
        }

        for (int l = 0, r = 0, engrySum = 0; l < gas.length; l = r, engrySum = 0) {
            while (engrySum >= 0) {
                if (r - l == gas.length)
                    return l;
                engrySum += engry[r++ % gas.length];
            }
        }

        return -1;
    }
}
