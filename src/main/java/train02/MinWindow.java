package train02;

/**
 * @Description:
 * @Author:
 * @Date: 2026-11-14 8:58
 **/
public class MinWindow {
    public String minWindow(String s, String t) {
        int[] map = new int[128];
        int cnt = t.length();
        int len = Integer.MAX_VALUE;
        int start = -1;
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]--;
        }

        for (int l = 0, r = 0; r < s.length(); r++) {
            if (map[s.charAt(r)]++ < 0) {
                cnt--;
            }

            while (cnt == 0) {
                if (len > r - l + 1) {
                    len = r - l + 1;
                    start = l;
                }
                if (map[s.charAt(l++)]-- == 0) {
                    cnt++;
                }
            }
        }

        return start == -1 ? "" : s.substring(start, start + len);
    }
}
