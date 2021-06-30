package lc.dailychallenge;

import java.util.*;

public class June {

    int helper(int[] nums, int h){
        int n = nums.length;
        int cnt = 0;
        int left = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= h){
                cnt += (i - left);
            }else{
                left = i;
            }
        }
        System.out.println(cnt);
        return cnt;
    }
//
    public List<String> generateParenthesis(int n) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < (1 << 2 * n); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 2 * n; j++) {
                if ((i | (1 << j)) == i){
                    sb.append("(");
                }else{
                    sb.append(")");
                }
            }
            if (check(sb.toString(), 2 * n)){
                set.add(sb.toString());
            }
        }
        return new ArrayList<>(set);
    }
    boolean check(String s, int len){
        int l = 0, r = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '('){
                l++;
            }else{
                r++;
            }
            if (l < r) return false;
        }
        return l == r;
    }
    public List<Integer> shortestDistanceColor(int[] cs, int[][] qs) {
        int n = cs.length;
        int[][] dp = new int[n + 1][3];
        int[] ndp = new int[3];
        Arrays.fill(ndp, Integer.MAX_VALUE / 2);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (ndp[j] < Integer.MAX_VALUE / 2) ndp[j]++;
            }
            dp[i] = Arrays.copyOf(ndp, 3);
            dp[i][cs[i] - 1] = 0;
        }
        Arrays.fill(ndp, Integer.MAX_VALUE / 2);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (ndp[j] < Integer.MAX_VALUE / 2) ndp[j]++;
                dp[i][j] = Math.min(dp[i][j], ndp[j]);
            }

        }
        List<Integer> res = new ArrayList<>();
        for(int[] q : qs){
            res.add(dp[q[0]][q[1] - 1]);
        }
        return res;
    }

    public int numSubarrayBoundedMax(int[] arr, int left, int right) {
        int n = arr.length;
        int sm = -1, lg = -1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] < left){
                sm = i;
            }
            if (arr[i] > right){
                lg = i;
            }
            if (arr[i] >= left && arr[i] <= right){
                cnt += i - Math.max(sm, lg);
            }
        }
        return cnt;
    }
}
