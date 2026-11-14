package train02;

/**
 * @Description:
 * @Author:
 * @Date: 2026-11-14 9:01
 **/
public class LongestSubstring {
    public int longestSubstring(String s, int k) {
        int len = -1;

        for (int i = 1; i <= 26; i++) {
            int[] map = new int[26];
            int cnt = 0;
            int req = cnt;
            for (int l = 0, r = 0; r < s.length(); r++) {
                if (map[s.charAt(r) - 'a']++ == 0) {
                    cnt++;
                    req++;
                }
                if (map[s.charAt(r) - 'a'] == k) {
                    req--;
                }

                while (cnt > i) {
                    if (map[s.charAt(l) - 'a'] == k) {
                        req++;
                    }
                    if (--map[s.charAt(l++) - 'a'] == 0) {
                        cnt--;
                        req--;
                    }
                }
                if (req <= 0 && cnt == i)
                    len = Math.max(len, r - l + 1);
            }
        }

        return len == -1?0:len;
    }
}
