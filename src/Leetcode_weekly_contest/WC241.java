package Leetcode_weekly_contest;

import java.util.HashMap;
import java.util.Map;

public class WC241 {

    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int add = 0;
        for (int i = 0; i < 1 << n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i | (1 << j)) == i){
                    sum ^= nums[j];
                }
            }
            add += sum;
        }
        return add;
    }
    public int minSwaps(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] a1 = new int[2];
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0'){
                a1[0]++;
            }else{
                a1[1]++;
            }
        }
        if (Math.abs(a1[0] - a1[1]) > 1) return -1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0){
                if (cs[i] == '1'){
                    cnt++;
                }
            }else{
                if (cs[i] == '0') cnt++;
            }
        }
        int rem = n - cnt;
        if (rem % 2 ==  1) rem = 100000000;
        int min = rem / 2;
        cnt = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 1){
                if (cs[i] == '1'){
                    cnt++;
                }
            }else{
                if (cs[i] == '0') cnt++;
            }
        }
        rem = n - cnt;
        min = Math.min(rem / 2, min);
        return min;
    }

    class FindSumPairs {
        Map<Integer, Integer> mp = new HashMap<>();
        int[] arr;
        int[] nums;
        public FindSumPairs(int[] nums1, int[] nums2) {
            int n = nums1.length, m = nums2.length;
            for (int i = 0; i < m; i++) {
                mp.put(nums2[i], mp.getOrDefault(nums2[i], 0) + 1);
            }
            arr = nums1;
            nums = nums2;
        }

        public void add(int idx, int val) {
            int origin = nums[idx];
            mp.put(origin, mp.get(origin) - 1);
            if (mp.get(origin) == 0) mp.remove(origin);
            mp.put(origin + val, mp.getOrDefault(origin + val, 0) + 1);
        }

        public int count(int tot) {
            int cnt = 0;
            for (int i = 0; i < arr.length; i++) {
                int v = arr[i];
                int tar = tot - v;
                if (mp.containsKey(tar)) cnt += mp.get(tar);
            }
            return cnt;
        }
    }
    int mod = (int) 1e9 + 7;

    public int rearrangeSticks(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
//        return dfs(0, 0, n, k);
        int[] pre = new int[n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] += dp[i - 1][j - 1];
                dp[i][j] += pre[i - 1];
                pre[i] += dp[i][j];
            }
        }
        return dp[n][k];
    }

//    int dfs(int idx, int k, int n, int tar){
//        if (idx == n && k == tar) return 1;
//        if (idx == n) return 0;
//        if (dp[idx][k] != null) return dp[idx][k];
//        int res = 0;
//        res = (res + dfs(idx + 1, k + 1, n, tar)) % mod;
//        res = (res + dfs(idx, k - 1, n, tar)) % mod;
//        return dp[idx][k] = res;
//    }



}
