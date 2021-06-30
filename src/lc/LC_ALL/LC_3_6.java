package lc.LC_ALL;

import java.math.BigInteger;
import java.util.*;

public class LC_3_6 {

    public boolean checkOnesSegment(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            if (cs[i] == '1'){
                while (j < n && cs[j] == '1') j++;
            }
            if (j - i + 1 > 1) return true;
        }
        return false;
    }
    public int minElements(int[] nums, int l, int g) {
        int n = nums.length;
        long add = 0;
        Arrays.sort(nums);
        for(int x : nums) add += x;
        if (add == g) return 0;
        int res = 0;
        if (add > g){
            int v = l + nums[n - 1];
            long dif = add - g;
            return (int) ((dif - 1) / v + 1);
        }else{
            int v = l - nums[0];
            long dif = g - add;
            return (int) ((dif - 1) / v + 1);
        }
        //return -1;
    }
    public int countRestrictedPaths(int n, int[][] edges) {
        int mod = (int) 1e9 + 7;
        long res = 0;
        int[] seen = new int[n + 1];
        Arrays.fill(seen, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        Map<Integer, List<int[]>> mp = new HashMap<>();
        for(int[] e : edges){
            int a = e[0];
            int b = e[1];
            if (!mp.containsKey(a)) mp.put(a, new ArrayList<>());
            if (!mp.containsKey(b)) mp.put(b, new ArrayList<>());
            mp.get(a).add(new int[]{b, e[2]});
            mp.get(b).add(new int[]{b, e[2]});
        }
        pq.add(new int[]{n, 0});
        while (!pq.isEmpty()){
            int[] arr = pq.poll();
            int idx = arr[0];
            int ct = arr[1];
            if (seen[idx] < ct) continue;
            for(int[] nxt : mp.get(idx)){
                int nidx = nxt[0];
                int c = nxt[1];
                if (seen[nidx] > c + ct){
                    pq.add(new int[]{nidx, c + ct});
                }
            }
        }
        long[] dp = new long[n + 1];
        pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        boolean[] find = new boolean[n + 1];
        pq.add(new int[]{n, 0});
        dp[n] = 1;
        while (!pq.isEmpty()){
            int[] arr = pq.poll();
            int idx = arr[0];
            if (find[idx]) continue;
            find[idx] = true;
            for(int[] nxt : mp.get(idx)){
                int nid = nxt[0];
                if (seen[nid] > seen[idx]){
                    dp[nid] = (dp[nid] + dp[idx]) % mod;
                    pq.add(new int[]{nid, seen[nid]});
                }
            }
        }
        return (int) dp[1];
    }
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int[][] ct = new int[k + 1][1024];
        //int[][] dp = new int[k + 1][1024];
        Map<Integer, Integer>[] arr = new HashMap[k + 1];
        for (int i = 0; i < n; i++) {
            ct[i % k][nums[i]]++;
            if (arr[i % k] == null){
                arr[i % k] = new HashMap<>();
            }
            arr[i % k].put(nums[i], arr[i % k].getOrDefault(nums[i], 0) + 1);
        }
        // dp[i][j] means max elements we need to have for the first i group to get to the state j
        int[] dp = new int[1024];
        for (int i = 0; i < k; i++) {
            int[] neo = new int[1024];
            for (int ke : arr[i].keySet()) {
                int v = arr[i].get(ke);
                for (int j = 0; j < 1024; j++) {
                    neo[j] = Math.max(neo[j], dp[j ^ ke] + v);
                }
            }
            neo = dp;
        }
        return n - dp[0];
    }
    public int solve(int[][] mat, int[][] t) {
        Map<BigInteger, Integer> sm = new HashMap<>();
        Map<BigInteger, Integer> st = new HashMap<>();
        int n = mat.length;
        for(int i = 0; i < n; i++){
            BigInteger bg = new BigInteger("0");
            BigInteger bt = new BigInteger("0");
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) bg.flipBit(j);
                if (t[i][j] == 1) bt.flipBit(j);
            }
            sm.put(bg, sm.getOrDefault(bg, 0) + 1);
            st.put(bt, st.getOrDefault(bt, 0) + 1);
        }
        int res = Integer.MAX_VALUE;
        for(BigInteger x : sm.keySet()){

            for(BigInteger y : st.keySet()){
                Map<BigInteger, Integer> neo = new HashMap<>(st);
                boolean out = false;
                BigInteger jud = x.xor(y);
                BigInteger ct = x.xor(y);
                for(BigInteger fd : sm.keySet()){
                    BigInteger obj = fd.xor(jud);
                    int k = sm.get(fd);
                    if (neo.get(obj) != k){
                        out = true;
                        break;
                    }
                }
                if (!out){
                    res = Math.min(res, ct.bitCount());
                }
            }
        }
        return res;
    }
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        int cnt = 0;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        List<char[]> ls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (c1[i] != c2[i]){
                cnt++;
                if (cnt > 2) return false;
                ls.add(new char[]{c1[i], c2[i]});
            }
        }
        if (cnt == 1) return false;
        if (cnt == 0) return true;
        char[] l = ls.get(0);
        char[] r = ls.get(1);
        if (l[0] == r[1] && l[1] == r[0]) return true;
        return false;
    }
    public int findCenter(int[][] edges) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int[] e : edges) {
            int l = e[0];
            int r = e[1];
            mp.put(l, mp.getOrDefault(l, 0 ) + 1);
            mp.put(r, mp.getOrDefault(r, 0 ) + 1);
        }
        for(int k : mp.keySet()){
            if (mp.get(k) == (mp.size() - 1)) return k;
        }
        return -1;
    }
    public double maxAverageRatio(int[][] classes, int e) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (Double.compare(a[0] * 1.0 / a[1], b[0] * 1.0 / b[1])));
        for (int[] x : classes){
            pq.add(x);
        }
        while (e > 0){
            int[] fk = pq.poll();
            pq.add(new int[]{fk[0] + 1, fk[1] + 1});
        }
        double ans = 0;
        while (!pq.isEmpty()){
            int[] fk = pq.poll();
            ans += (fk[0] * 1.0 / fk[1]);
        }
        ans /= classes.length;
        return ans;
    }



}
