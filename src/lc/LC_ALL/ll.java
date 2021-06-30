package lc.LC_ALL;

import java.util.*;

public class ll {

    public String truncateSentence(String s, int k) {
        String[] ss = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(ss[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        int[] ans = new int[k];
        Map<Integer, Set<Integer>> mp = new HashMap<>();
        for(int[] l : logs){
            if(!mp.containsKey(l[0]))mp.put(l[0], new HashSet<>());
            mp.get(l[0]).add(l[1]);
        }
        for(int key : mp.keySet()){
            ans[mp.get(key).size() - 1]++;
        }
        return ans;
    }
    int mod = (int) 1e9 + 7;
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        long res = 0;
        int n = nums1.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            res += Math.abs(nums1[i] - nums2[i]);
            set.add(nums1[i]);
        }
        long al = res;
        for (int i = 0; i < n; i++) {
            int v2 = nums2[i];
            int v1 = nums1[i];
            Integer low = set.ceiling(v2);
            Integer hi = set.floor(v2);
            if (low != null){
                res = Math.min(res, al - Math.abs(v1 - v2) + Math.abs(low - v2));
            }
            if (hi != null){
                res = Math.min(res, al - Math.abs(v1 - v2) + Math.abs(hi - v2));
            }
        }
        res %= mod;
        return (int) res;
    }

    public int countDifferentSubsequenceGCDs(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
        }
        boolean[] dp = new boolean[max + 1];
        for (int i = 0; i < n; i++) {
            boolean[] neo = Arrays.copyOf(dp, max + 1);
            for(int j = max; j >= 1; j++){
                if(dp[j]){
                    neo[gcd(nums[i], j)] = true;
                }
            }
            dp = neo;
        }
        int cnt = 0;
        for (int i = 1; i <= max; i++) {
            if(dp[i]) cnt++;
        }
        return cnt;
    }
    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
}
