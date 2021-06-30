package Leetcode_weekly_contest;

import java.util.*;

public class WC242 {

    public boolean checkZeroOnes(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] cp = new int[2];
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && cs[i] == cs[j]) j++;
            if (cs[i] == '0'){
                cp[0] = Math.max(cp[0], j - i);
            }else {
                cp[1] = Math.max(cp[1],j - i);
            }
        }
        if (cp[1] > cp[0]) return true;
        return false;
    }
    public int minSpeedOnTime(int[] dist, double h) {
        if (h < dist.length - 1) return -1;
        double l = 0, r = 1e7;
        while (Math.abs(l - r) > 1e-3){
            int tot = 0;
            double mid = (l + r) / 2;
            for(int n : dist){
                tot += n / mid;
            }
            if (tot <= h){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        int ok = (int)Math.floor(r) + 1;
        return ok;
    }

    public int stoneGameVIII(int[] stones) {
        int max = -10000000;
        int n = stones.length;
        int[] right = new int[n + 1];
        int[] sum = new int[n + 1];
        right[n - 1] = stones[n - 1];
        sum[n - 1] = stones[n - 1];
        int add = stones[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            add += stones[i];
            right[i] = Math.min(right[i - 1], add);
            sum[i] = sum[i + 1] + stones[i];
        }
        max = sum[0];
        add = stones[0] + stones[1];
        for (int i = 2; i < n - 1; i++) {
            max = Math.max(max, (sum[i] - right[i]) - right[i]);
            add += stones[i];
        }
        return max;
    }

    public class SegTree {
        private int N;

        // Let UNIQUE be a value which does NOT
        // and will not appear in the segment tree
        private long UNIQUE = 0;

        // Segment tree values
        private long[] tree;

        public SegTree(int size) {
            tree = new long[2 * (N = size)];
            java.util.Arrays.fill(tree, UNIQUE);
        }

        public SegTree(long[] values) {
            this(values.length);
            for (int i = 0; i < N; i++) modify(i, values[i]);
        }

        // This is the segment tree function we are using for queries.
        // The function must be an associative function, meaning
        // the following property must hold: f(f(a,b),c) = f(a,f(b,c)).
        // Common associative functions used with segment trees
        // include: min, max, sum, product, GCD, and etc...
        private long function(long a, long b) {
            if (a == UNIQUE) return b;
            else if (b == UNIQUE) return a;

            return a + b; // sum over a range
            //return (a > b) ? a : b; // maximum value over a range
            //return (a < b) ? a : b; // minimum value over a range
            // return a * b; // product over a range (watch out for overflow!)
        }

        // Adjust point i by a value, O(log(n))
        public void modify(int i, long value) {
            //tree[i + N] = function(tree[i + N], value);
            tree[i + N] = value;

            for (i += N; i > 1; i >>= 1) {
                tree[i >> 1] = function(tree[i], tree[i ^ 1]);
            }

        }

        // Query interval [l, r), O(log(n)) ----> notice the exclusion of r
        public long query(int l, int r) {
            long res = UNIQUE;
            for (l += N, r += N; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) != 0) res = function(res, tree[l++]);
                if ((r & 1) != 0) res = function(res, tree[--r]);
            }
            if (res == UNIQUE) {
                //throw new IllegalStateException("UNIQUE should not be the return value.");
                return 0;
            }
            return res;
        }


    }

    public boolean canReach(String s, int minJump, int maxJump) {
        char[] cs = s.toCharArray();
        int n = s.length();
        if (cs[n - 1] == '1') return false;
        boolean[] dp = new boolean[n + 1];
        int maxminJmp = 0;
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '1') continue;
            if (!dp[i]) continue;
            int mj = i + minJump;
            int mxj = i + maxJump;
            if(mxj >= n - 1) return true;
            maxminJmp = Math.max(maxJump, mj);
            for (int j = maxminJmp; j < mxj; j++) {
                if (cs[i] == '0'){
                    dp[i] = true;
                }
            }
            maxminJmp = mxj;
        }
        return false;
    }

    public int minSkips(int[] dist, int sp, int hb) {
        int[][] dp = new int[1005][1005];
        int n = dist.length;
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        hb *= sp;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + dist[i - 1], ((dp[i][j] + dist[i - 1] - 1) / sp + 1) * sp);
            }
        }
        for (int i = 0; i <= n; i++) {
            if (dp[n][i] <= hb) return i;
        }
        return -1;
    }
    int tot = 0;
    public int totalNQueens(int n) {
         dfs(0, 0, new HashSet<>(), n);
         return n;
    }
    void dfs(int r, int c, Set<Integer>diag, int tar){
        if (r == tar){
            tot++;
            return;
        }
        for (int i = 0; i < tar; i++) {
            if ((c | (1 << i)) != c && !diag.contains(r + i) && !diag.contains(Math.abs(r - i))){
                diag.add(r + i);
                diag.add(Math.abs(r - i));
                dfs(r + 1, c, diag, tar);
                diag.remove(r + i);
                diag.remove(Math.abs(r - i));
            }
        }
    }
    public List<List<String>> suggestedProducts(String[] ps, String sw) {
        List<List<String>> res = new ArrayList<>();
        Arrays.sort(ps);
        node root = new node();
        for(String s : ps){
            node tra = root;
            for(char c : s.toCharArray()){
                if (tra.nxt[c - 'a'] == null) tra.nxt[c - 'a'] = new node();
                tra = tra.nxt[c - 'a'];
                if (tra.ok.size() < 3) tra.ok.add(s);
            }
        }
        for(char c : sw.toCharArray()){
            res.add(root.nxt[c - 'a'].ok);
            root = root.nxt[c - 'a'];
        }
        return res;
    }
    class node{
        List<String> ok = new ArrayList<>();
        node[] nxt = new node[26];
    }
    public int maximumGap(int[] nums) {
        int n = nums.length;
        int[][] bk = new int[n + 1][2];
        for (int i = 0; i < n; i++) {
            bk[i] = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int x : nums){
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        int dist = (max - min) / (n - 1);
        for(int x : nums){
            int idx = (x - min) / dist;
            bk[idx][0] = Math.min(bk[idx][0], x);
            bk[idx][1] = Math.max(bk[idx][1], x);
        }
        int ans = dist;
        for (int i = 0; i < n; i++) {
            if (bk[i + 1][0] == Integer.MAX_VALUE) continue;
            ans = Math.max(ans, -bk[i][1] + bk[i + 1][0]);
        }
        return ans;
    }
    public int connectSticks(int[] ss) {
        int n = ss.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int tot = 0;
        for(int x : ss){
            pq.add(x);
        }
        while (pq.size() > 1){
            int l = pq.poll(), r = pq.poll();
            tot += (l + r);
            pq.add(l + r);
        }
        return tot;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] seen = new boolean[n][m];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (seen[i][j] || grid[i][j] == 0)continue;

                max = Math.max(dfs(i, j, seen, grid), max);
            }
        }
        return max;
    }
    int[][] dirs = new int[][]{{-1, 0},{1, 0}, {0, 1}, {0, -1}};
    int dfs(int i, int j, boolean[][] seen, int[][] grid){
        if (seen[i][j]) return 0;
        seen[i][j] = true;
        int cnt = 1;
        for(int[] dir : dirs){
            int ni = dir[0] + i;
            int nj = dir[1] + j;
            if(ni < 0 || nj < 0 || ni >= seen.length || nj >= seen[0].length || grid[i][j] == 0)continue;
            cnt += dfs(ni, nj, seen, grid);
        }
        return cnt;
    }
}
