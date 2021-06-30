package ABC.ABC185;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner((System.in));
        PrintWriter pw = new PrintWriter(System.out);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        if(m == 0){
//            pw.println(1);
//        }else{
//
//            int[] arr = new int[m];
//            int min = n;
//            List<Integer> res = new ArrayList<>();
//            for (int i = 0; i < m; i++) {
//                arr[i] = sc.nextInt();
//            }
//            Arrays.sort(arr);
//            if(arr[0] > 2) res.add(arr[0] - 1);
//            for (int i = 1; i < m; i++) {
//                min = Math.min(min, arr[i] - arr[i - 1] - 1);
//                res.add(arr[i] - arr[i - 1] - 1);
//            }
//            if(arr[m - 1] != n) res.add(n - arr[m - 1]);
//            Collections.sort(res);
//            min = res.size() > 0? res.get(0) : 0;
//            //System.out.println(res);
//            int ans = n - m;
//            for (int i = 2; i <= min; i++) {
//                int cnt = 0;
//                for(int x : res){
//                    if(x % i == 0){
//                        cnt += x / i;
//                    }else{
//                        cnt += x / i + 1;
//                    }
//                }
//                //System.out.println("div:"+i +" cnt:"+cnt);
//                ans = Math.min(ans, cnt);
//            }
//            pw.println(ans);
//        }
        Main main = new Main();
        main.solveE(sc, pw);
        pw.close();

    }
    public class SegTree {
        private int N;

        // Let UNIQUE be a value which does NOT
        // and will not appear in the segment tree
        private long UNIQUE = 8123572096793136074L;

        // Segment tree values
        private long[] tree;

        public SegTree(int size) {
            tree = new long[2 * (N = size)];
            java.util.Arrays.fill(tree, 0);
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

            return a ^ b; // sum over a range
            //return (a > b) ? a : b; // maximum value over a range
            //return (a < b) ? a : b; // minimum value over a range
            // return a * b; // product over a range (watch out for overflow!)
        }

        // Adjust point i by a value, O(log(n))
        public void modify(int i, long value) {
            //tree[i + N] = function(tree[i + N], value);
            tree[i + N] ^= value;
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

     void solveE(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[][] qs = new int[q][4];
        for (int i = 0; i < q; i++) {
            qs[i] = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
        }
        int[] dp = new int[n + 1];
         for (int i = 1; i <= n; i++) {
             dp[i] = dp[i - 1] ^ arr[i - 1];
         }
        SegTree sg = new SegTree(n + 5);
        for (int i = 0; i < q; i++) {
            int t = qs[i][0], x = qs[i][1], y = qs[i][2];
            if(t == 1){
                sg.modify(x, y);
            }else{
                int val = dp[y] ^ dp[x - 1];
                val ^= sg.query(x, y  + 1);
                pw.println(val);
            }
        }
    }
    static void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }
        Integer[][] dp = new Integer[1001][1001];
        pw.println(dp(0, 0, a ,b, dp));

    }
    static int dp(int i, int j, int[] a, int[] b, Integer[][] dp){
        if(i == a.length && j == b.length) return 0;
        if(i == a.length) return b.length - j;
        if(j == b.length) return a.length - i;
        if (dp[i][j] != null) return dp[i][j];
        int l = dp(i, j + 1, a, b, dp) + 1; // remove j
        int r = dp(i + 1, j, a, b, dp) + 1; // remove i
        int mid = dp(i + 1, j + 1, a, b, dp) + (a[i] == b[j] ? 0 : 1);
        //System.out.println("i:"+i+" j:"+j+" l:"+l+" r:"+r+" mid:"+mid);
        int[] arr = {l, r, mid};
        Arrays.sort(arr);
        return dp[i][j] = arr[0];
    }
    //Long[][][] dp = new Long[201][201][13];
    static long dfs(int len, int c, Long[][] dp){
        if (c == 0) return 1;
        if (len <= c) return 0;
        if (dp[len][c] != null) return dp[len][c];
        long res = 0;
        for (int i = 1; i < len; i++) {
            res += dfs(len - i, c - 1, dp);
        }
        return dp[len][c] = res;
    }
}
