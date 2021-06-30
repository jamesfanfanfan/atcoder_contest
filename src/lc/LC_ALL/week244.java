package lc.LC_ALL;

import java.util.Arrays;
import java.util.TreeSet;

public class week244 {
    int mod = (int) 1e9 + 7;


    public int minWastedSpace(int[] packages, int[][] boxes) {
        int n = packages.length;
        int m = boxes.length;
        int mx = 0;
        for(int[] bx : boxes){
            for(int x : bx){
                mx = Math.max(mx, x);
            }
        }
        TreeSet<Integer> set = new TreeSet<>();
        Arrays.sort(packages);
        if (mx < packages[n - 1]) return -1;
        set.add(0);
        for (int i = 0; i < n; i++) {
            set.add(packages[i]);
        }
        long ans = 0;
        long[] pres = new long[(int) 1e5 + 5];
        long[] ct = new long[(int) 1e5 + 5];
        long presum = 0;
        for (int i = 0; i < n; i++) {
            presum += packages[i];
            pres[packages[i]] = presum;
            ct[packages[i]] = i + 1;
        }
        for(int[] bx : boxes){
            Arrays.sort(bx);
            int pre = 0;
            long fk = 0;
            for (int i = 0; i < bx.length; i++) {
                Integer le = set.floor(pre);
                Integer ri = set.floor(bx[i]);
                long sum = packages[ri] - packages[le];
                long over = ct[ri] - ct[le];
                fk += (over * bx[i] - sum);
            }
            ans = Math.max(ans, fk);
        }
        ans %= mod;
        return (int) ans;
    }

    public int minFlips(String s) {
        char[] cs = s.toCharArray();
        int n = s.length();
        int cnt = 0;
        int[][] le = new int[n + 2][2];
        int[][] ri = new int[n + 2][2];
        int st = 0;
        for (int i = 1; i <= n; i++) {
            int v = cs[i - 1] - '0';
            le[i][0] = le[i - 1][0];
            if (v != st) le[i][0]++;
            st = 1 - st;
        }
        st = 1;
        for (int i = 1; i <= n; i++) {
            int v = cs[i - 1] - '0';
            le[i][1] = le[i - 1][1];
            if (v != st) le[i][1]++;
            st = 1 - st;
        }
        st = 0;
        for(int j = n - 1; j >= 0; j--){
            int v = cs[j] - '0';
            ri[j][0] = ri[j + 1][0];
            if (v != st) ri[j][0]++;
            st = 1 - st;
        }
        st = 1;
        for(int j = n - 1; j >= 0; j--){
            int v = cs[j] - '0';
            ri[j][1] = ri[j + 1][1];
            if (v != st) ri[j][1]++;
            st = 1 - st;
        }
        int[] fk = new int[]{le[n][0], le[n][1], ri[0][0], ri[0][1]};
        Arrays.sort(fk);
        int ans = fk[0];
        for (int i = 0; i < n; i++) {
            int rem = n - i - 2;
            int as = 0;
            if (rem % 2 == 1){
                as = Math.min(ri[i + 1][1] + le[i + 1][0], ri[i + 1][0] + le[i + 1][1]);
            }else {
                as = Math.min(ri[i + 1][0] + le[i + 1][0], ri[i + 1][1] + le[i + 1][1]);
            }
            ans = Math.min(as, ans);
        }

        return ans;
    }
}
