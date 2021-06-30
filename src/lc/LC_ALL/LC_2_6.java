package lc.LC_ALL;

import java.util.*;

public class LC_2_6 {
    public int solve(String s, int target) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        boolean[][] dp = new boolean[n + 1][5005];
        dp[0][0]= true;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= Math.max(0, i - 3); j--) {
                int add = Integer.valueOf(s.substring(j, i + 1));
                for (int k = 5000; k >= add; k--) {
                    dp[i][k] |= dp[j][k - add];
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 5002; i++) {
            if (dp[n][i]){
                ans = Math.min(ans, Math.abs(target - i));
            }
        }
        return ans;
    }




    public int calculate(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> note = new Stack<>();
        stack.push(0);
        int val = 0;
        boolean isAdd = true;
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == ')'){
                int p = stack.pop();
                int v = note.pop();
                if (v == 0){
                    p -= val;
                }else{
                    p += val;
                }
                stack.push(p);
            }else if (c == '('){
                stack.push(val);
                note.push(isAdd ? 1 : 0);
                isAdd = true;
                val = 0;
            }else if(c <= '9' && c >= '0'){
//                int v = note.pop();
                if (!isAdd){
                    val -= (c - 'a');
                }else{
                    val += (c - 'a');
                }
            }else if (c == '-'){
                isAdd = false;
//                note.push(0);
            }else if (c == '+'){
                isAdd = true;
//                note.push(1);
            }
        }
        return stack.pop();
    }

    public String convert(String s, int numRows) {
        List<List<Character>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            res.add(new ArrayList<>());
        }
        char[] cs = s.toCharArray();
        int n = cs.length;
        int level = 0;
        for (int i = 0; i < n; i++) {
            if (level < numRows){
                res.get(level).add(cs[i]);
                level++;
            }else{
                int j = i;
                level--;
                while (j < n && level > 0){
                    res.get(level).add(cs[j++]);
                    level --;
                }
                i = j - 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(List<Character> ls : res){
            for(char c : ls){
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public int minCost(int[] houses, int[][] cost, int m, int n, int t) {
        int[][][] dp = new int[n + 1][m + 1][t + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= t; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE / 2;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            int c = houses[i - 1];
            if (c > 0){
                for (int k = 1; k <= Math.min(i, t); k++) {
                    int min = Integer.MAX_VALUE;
                    for (int j = 1; j <= m; j++) {
                        if (c != j) min = Math.min(min, dp[i - 1][j][k - 1]);
                    }
                    dp[i][c][k] = Math.min(dp[i - 1][c][k], min);
                }
                continue;
            }
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k <= Math.min(i, t); k++) {
                    int min = Integer.MAX_VALUE;
                    for (int l = 1; l <= m; l++) {
                        if (j != l) min = Math.min(min, dp[i - 1][l][k - 1]);
                    }
                    dp[i][j][k] = Math.min(dp[i - 1][c][k], min) + cost[i - 1][j];
                }
            }
        }
        int min = 10000000;
        for (int i = 1; i <= m; i++) {
            min = Math.min(min, dp[n][i][t]);
        }
        return min;
    }

    public boolean check(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        int pre = nums[0];
        for (int i = 1; i < n; i++) {
            if (pre > nums[i]){
                cnt++;
            }
            pre = nums[i];
        }
        if(nums[n - 1] > nums[0]) cnt++;
        return cnt <= 1;

    }
    public int maximumScore(int a, int b, int c) {
        int[] arr = new int[]{a, b, c};
        int cnt = 0;
        Arrays.sort(arr);
        while (arr[2] > 0 && arr[1] > 0){
            arr[2]--;
            arr[1]--;
            cnt++;
        }
        return cnt;

    }
    public String largestMerge(String word1, String word2) {
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();
        int n1 = c1.length;
        int n2 = c2.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < n1 || j < n2;) {
            if (i == n1){
                sb.append(c2[j++]);
            }else if (j == n2){
                sb.append(c1[i++]);
            }else if (c1[i] > c2[j]){
                sb.append(c1[i++]);
            }else if (c1[i] > c2[j]){
                sb.append(c2[j++]);
            }else{
                String l1 = word1.substring(i);
                String l2 = word2.substring(j);
                if (l1.compareTo(l2) > 0){
                    sb.append(c1[i++]);
                }else{
                    sb.append(c2[j++]);
                }
            }
        }
        return sb.toString();
    }
//    public int minAbsDifference(int[] nums, int goal) {
//        Arrays.sort(nums);
//        dfs(nums, goal, 0, nums.length, 0L);
//        return (int) dif;
//    }
//    long dif = Integer.MAX_VALUE;
//    void dfs(int[] arr, int goal, int idx, int n, long add){
//        if (idx == n){
//            return;
//        }
//        if (Math.abs(goal - add) > dif) return;
//        dif = Math.abs(goal - add);
//        int pre = Integer.MIN_VALUE;
//        for (int i = idx; i < n; i++) {
//            if (arr[i] == pre) continue;
//            dfs(arr, goal, i + 1, n, add + arr[idx]);
//        }
//    }
public int minAbsDifference(int[] nums, int goal) {
    // Arrays.sort(nums);
    // dfs(nums, goal, 0, nums.length, 0L);
    // return (int) dif;
    Arrays.sort(nums);
    Map<Integer, Set<Long>> mp = new HashMap<>();
    int n = nums.length;
    for(int i = 0; i <= n; i++){
        mp.put(i, new HashSet<>());
    }
    mp.get(0).add(0L);
    for(int i = 1; i <= n; i++){
        int val = nums[i - 1];
        Set<Long> neo = new HashSet<>(mp.get(i - 1));
        Set<Long> nxt = new HashSet<>();
        for (long v : neo){
            nxt.add(v + val);
        }
        nxt.addAll(neo);
        mp.put(i, nxt);
    }
    int res = Integer.MAX_VALUE;
    for (long v : mp.get(n)){
        res = Math.min(res, (int)Math.abs(v - goal));
    }

    return res;
}

    TreeSet<Long> helper(int[] nums){
        int n = nums.length;
        Set<Long> set = new HashSet<>();
        set.add(0L);
        for(int i = 1; i <= n; i++){
            int val = nums[i - 1];
            Set<Long> nxt = new HashSet<>();
            for(long v : set){
                if(set.contains(v + val)) continue;
                nxt.add(v + val);
            }
            set.addAll(nxt);
            System.out.println(set);
        }
        return new TreeSet<>(set);

    }


}
