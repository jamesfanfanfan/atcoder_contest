package Leetcode_weekly_contest;


import java.util.*;

public class WC240 {
    public int maximumPopulation(int[][] logs) {

        int[] arr = new int[3000];
        for(int[] l : logs){
            arr[l[0]] ++;
            arr[l[1]] --;
        }
        int min = 10000000, idx = -1;
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            cnt += arr[i];
            if (arr[i] > min){
                idx = i;
                min = arr[i];
            }
        }
        return idx;
    }
    public int maxDistance(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums1.length;
        int cnt = 0;
        for(int i = 0, j = 0; j < m; j++){
            while (i < n && nums1[i] > nums2[j]) i++;
            if (i < n){
                cnt = Math.max(cnt, j - i);
            }
        }
        return cnt;
    }

    public int maxSumMinProduct(int[] nums) {
        int mod = (int) 1e9 + 7;
        int n = nums.length;
        int[] le = new int[n + 1];
        int[] ri = new int[n + 1];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && nums[i] < nums[stack.peek()]) stack.pop();
            le[i] = stack.peek();
            stack.push(i);
        }
        stack.clear();
        stack.push(n);
        for (int i = n - 1; i >= 0 ; i--) {
            while (stack.size() > 1 && nums[i] < nums[stack.peek()]) stack.pop();
            ri[i] = stack.peek();
            stack.push(i);
        }
        long aka = 0;
        for (int i = 0; i < n; i++) {
            int l = le[i], r = ri[i];
            long mut = pre[r] - pre[l + 1];
            aka = Math.max(aka, mut * nums[i]);
        }
        return (int) (aka % mod);
    }

    public int largestPathValue(String s, int[][] es) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        Map<Integer, List<Integer>> mp = new HashMap<>();
        int[] par = new int[n];
        int[][] dp = new int[n][26];
        for(int[] e : es){
            if (!mp.containsKey(e[1])) mp.put(e[1], new ArrayList<>());
            mp.get(e[1]).add(e[0]);
            par[e[0]]++;
        }
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (par[i] == 0) q.add(i);
        }
        int max = 0;
        int cnt = 0;
        while (!q.isEmpty()){
            int id = q.poll();
            int c = cs[id] - 'a';
            cnt++;
            int[] up = dp[id];
            up[c]++;
            for (int i = 0; i < 26; i++) {
                max = Math.max(max, up[i]);
            }
            for(int p : mp.get(c)){
                for (int i = 0; i < 26; i++) {
                    dp[p][i] = Math.max(dp[p][i], up[i]);
                }
                par[p]--;
                if (par[p] == 0){
                    q.add(p);
                }
            }
        }
        if (cnt != cs.length) return -1;
        return max;
    }


    public int sumOfFlooredPairs(int[] nums) {
        int mod = (int) 1e9 + 7;
        int n = nums.length;
        SegTree sg = new SegTree(100005);
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        for(int x : nums){
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        }
        for(int x : mp.keySet()){
            sg.modify(x, mp.get(x));
        }
        long ans = 0;
        for(int x : mp.keySet()){
            for (int i = 2; i * x <= mp.lastKey(); i++) {
                long tot = 0;
                if (i == 2){
                    tot = sg.query((i - 1) * x + 1, i * x);
                }else{
                    tot = sg.query((i - 1) * x, i * x);
                }
                ans += (tot * (i - 1));
            }
            ans += mp.get(x) * (long)mp.get(x);
            ans %= mod;
        }
        return (int) ans;
    }

     class SegTree {
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


    public char[][] rotateTheBox(char[][] bx) {
        int n = bx.length, m = bx[0].length;
        char[][] ans = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int k = j;
                int cnt = 0;
                while(k < m && bx[i][k] != '*'){
                    if (bx[i][k] == '#'){
                        cnt++;
                    }
                }
                if (k < m){
                    ans[i][k] = '#';
                }
                int v = k;
                k--;
                while (k >= j){
                    if (cnt > 0){
                        ans[i][k] = '#';
                        cnt--;
                    }else{
                        ans[i][k] = '.';
                    }
                }
                j = v - k;
            }
        }
        char[][] res = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = ans[j][m - i - 1];
            }
        }
        return res;
    }


}
