package codeforce.cf702;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class D {
    static class SegTree {
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        Map<Integer, Integer> mp = new HashMap<>();
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt() - 1;
        }
        SegTree sg = new SegTree(n + 5);
        for (int i = 0; i < n; i++) {
            sg.modify(i, arr[i]);
            mp.put(arr[i], i);
        }
        int[] res = new int[n];
        dfs(0, n - 1, sg, mp, res, 0);
        for (int i = 0; i < n; i++) {
            out.print(res[i]+" ");
        }
        out.println();

    }
    static void dfs(int l, int r, SegTree sg, Map<Integer, Integer> mp, int[] ans, int dep){
        if (l > r) return;
        if (l == r){
            ans[l] = dep;
            return;
        }

        int max = (int)sg.query(l, r + 1);
        int idx = mp.get(max);
        ans[idx] = dep;
        //System.out.println(l+" "+r+" "+max);
        dfs(l, idx  - 1, sg, mp, ans, dep + 1);
        dfs(idx + 1, r, sg, mp, ans, dep + 1);

    }
    static boolean isPrime(long n)
    {
        // Corner cases
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }

    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // method to return LCM of two numbers
    static long lcm(long a, long b)
    {
        return (a / gcd(a, b)) * b;
    }
}
