package train02;

/**
 * @Description:
 * @Author:
 * @Date: 2026-11-14 8:59
 **/
public class BalancedString {
    public int balancedString(String s) {
        char[] str = s.toCharArray();
        int len = Integer.MAX_VALUE;
        int cnt = 0;
        int[] map = new int[4];
        for (int i = 0; i < s.length(); i++) {
            if (str[i] == 'Q')
                str[i] = 0;
            else if (s.charAt(i) == 'W')
                str[i] = 1;
            else if (s.charAt(i) == 'E')
                str[i] = 2;
            else
                str[i] = 3;
            map[str[i]]++;
        }
        for (int i = 0; i < 4; i++) {
            map[i] = s.length() / 4 - map[i];
            if (map[i] < 0)
                cnt -= map[i];
        }

        if(cnt == 0) return 0;


        for(int l = 0,r=0;r < s.length();r++){
            if(map[str[r]]++ < 0){
                cnt--;
            }

            while(cnt == 0){
                len = Math.min(len, r -l + 1);
                if(map[str[l++]]-- <= 0){
                    cnt++;
                }
            }
        }

        return len;
    }
}
