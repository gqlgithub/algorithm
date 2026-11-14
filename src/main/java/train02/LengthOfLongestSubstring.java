package train02;

import java.util.Arrays;

/**
 * @Description:
 * @Author:
 * @Date: 2026-11-14 8:57
 **/
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        int len = 0;
        Arrays.fill(map, -1);

        for (int l = 0, r = 0; r < s.length(); r++) {
            l = Math.max(map[s.charAt(r)] + 1, l);
            len = Math.max(len, r - l + 1);
            map[s.charAt(r)] = r;
        }

        return len;
    }
}
