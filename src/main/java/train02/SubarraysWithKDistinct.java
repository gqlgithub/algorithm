package train02;

/**
 * @Description:
 * @Author:
 * @Date: 2026-11-14 9:00
 **/
public class SubarraysWithKDistinct {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return process(nums, k) - process(nums, k - 1);
    }

    public int process(int[] nums, int k) {
        int ans = 0;
        int[] map = new int[nums.length + 1];
        int cnt = 0;
        for (int l = 0, r = 0; r < nums.length; r++) {
            if (map[nums[r]]++ == 0)
                cnt++;
            while (cnt > k) {
                if (--map[nums[l++]] == 0)
                    cnt--;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
