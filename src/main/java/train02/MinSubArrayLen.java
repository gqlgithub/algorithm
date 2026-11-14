package train02;

/**
 * @Description:
 * @Author:
 * @Date: 2026-11-14 8:56
 **/
public class MinSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        int len = Integer.MAX_VALUE;
        int sum = 0;
        for (int l = 0, r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= target) {
                len = Math.min(len, r - l + 1);
                sum -= nums[l++];
            }
        }

        return len == Integer.MAX_VALUE ? 0 : len;
    }
}
