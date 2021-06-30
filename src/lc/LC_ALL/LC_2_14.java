package lc.LC_ALL;

import java.util.*;

public class LC_2_14 {
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

            //return a + b; // sum over a range
            return (a > b) ? a : b; // maximum value over a range
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
//    public static void main(String[] args){
//        SegTree sc = new SegTree(10);
//        sc.modify(1,22);
//        sc.query(0,12);
//        System.out.println(sc.query(0,12));
//        sc.modify(1,23);
//        sc.query(0,12);
//        System.out.println(sc.query(0,12));
//    }

    }

    public int solve(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];
        for (int i = 0; i < m; i++) {
            dp[0][i] = matrix[0][i];
        }
        for (int i = 1; i < n; i++) {
            PriorityQueue<int[]> r = new PriorityQueue<>((a, b) -> {
                return b[1] - a[1];
            });
            for (int j = 0; j < m; j++) {
                r.add(new int[]{j, matrix[i - 1][j] - j});
            }
            PriorityQueue<int[]> l = new PriorityQueue<>((a, b) -> {
                return b[1] - a[1];
            });
            for (int j = 0; j < m; j++) {
                while (!r.isEmpty() && r.peek()[0] < j) {
                    int[] get = r.poll();
                    l.add(new int[]{get[0], get[1] - get[0] + m - get[0]});
                }
                int lmx = l.isEmpty()? Integer.MIN_VALUE : l.peek()[1] + (m - l.peek()[0]) - Math.abs(l.peek()[0] - j);
                int rmx = r.isEmpty()? Integer.MIN_VALUE : r.peek()[1] + (r.peek()[0])  - Math.abs(r.peek()[0] - j);
                dp[i][j] = Math.max(lmx, rmx);
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            res = Math.max(res, dp[n - 1][i]);
        }
        return res;
    }




    public int[] solve(int[] heights, int k) {

        int n = heights.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        List<Integer> res = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            while (pq.size() > k && pq.peek()[1] - k > i) pq.poll();
            if (!pq.isEmpty() && pq.poll()[0] < heights[i]){
                res.add(i);
            }else if (pq.isEmpty()){
                res.add(i);
            }
            pq.add(new int[]{heights[i], i});
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        Arrays.sort(ans);
        return ans;
    }

    public int solve(int[] a, int[] b, int k) {
        Arrays.sort(a);
        Arrays.sort(b);
        int n = a.length;
        int m = b.length;
        Map<Integer, Set<Integer>> mp = new HashMap<>();
        int[] ini = new int[]{n - 1, m - 1};
        int[] ini1 = new int[]{0, 0};
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, b1) -> {

            return Integer.compare(a[b1[0]] * b[b1[1]], a[a1[0]] * b[a1[1]]);
        });
        pq.add(ini);
        pq.add(ini1);
        int cnt = 0;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, - 1}};
        while (!pq.isEmpty()){
            int[] arr = pq.poll();
            if (cnt == k){
                return arr[0] * arr[1];
            }
            k++;
            for(int[] dir : dirs){
                int na = arr[0] + dir[0];
                int nb = arr[1] + dir[1];
                if (mp.containsKey(na) && mp.get(na).contains(nb)) continue;
                if (!mp.containsKey(na))  mp.put(na, new HashSet<>());
                mp.get(na).add(nb);
                pq.add(new int[]{na, nb});
            }

        }
        return -1;

    }
    int cmp = 1000000;
    public int minTrioDegree(int n, int[][] edges) {
        Map<Integer, Integer> mp = new HashMap<>();
        Map<Integer, Set<Integer>> gra = new HashMap<>();
        for(int[] e : edges){
            int f = e[0] - 1;
            int t = e[1] - 1;
            mp.put(f, mp.getOrDefault(f, 0) + 1);
            mp.put(t, mp.getOrDefault(t, 0) + 1);
            if (!gra.containsKey(f)) gra.put(f, new HashSet<>());
            if (!gra.containsKey(t)) gra.put(t, new HashSet<>());
            gra.get(f).add(t);
            gra.get(t).add(f);
        }
        for (int i = 0; i < n; i++) {
            Set<Integer> set = gra.get(i);
            for(int nxt : set){
                dfs(i, nxt, mp, gra);
            }
        }
        if (cmp == 1000000) return -1;
        return cmp;

    }
    void dfs(int now, int par, Map<Integer, Integer> mp, Map<Integer, Set<Integer>> gra){
        Set<Integer> set = gra.get(now);
        for (int nxt : set){
            if (gra.get(nxt).contains(par)){
                int cnt = mp.get(now) + mp.get(par) + mp.get(nxt);
                cnt -= 6;
                cmp = Math.min(cmp, cnt);
            }
        }
    }

    public int minOperations(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[][] dp = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            int v = cs[i - 1] - '0';
            dp[i][v] = dp[i - 1][1 - v];
            dp[i][1 - v] = dp[i - 1][v] + 1;
        }
        return Math.min(dp[n][0], dp[n][1]);
    }


    public int countHomogenous(String s) {
        int mod = (int) 1e9 + 7;
        long cnt = 0;
        char[] cs = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int j = i;
            long ct = 0;
            while (j < n && cs[i] == cs[j]){
                ct += (j - i + 1);
                j++;
                ct %= mod;
            }
            cnt = (cnt + ct) % mod;
            i = j - 1;
        }

        cnt %= mod;
        return (int) cnt;
    }

    public int minimumSize(int[] nums, int mx) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0;
        int r = nums[n - 1];
        while (l < r){
            int mid = (l + r) / 2;
            int cnt = 0;
            for(int x : nums){
                if (x < mid) continue;
                cnt += ((x - 1) / mid) + 1;
            }
            if (cnt < mx){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        return l;
    }
}
