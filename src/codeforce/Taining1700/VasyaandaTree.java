package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VasyaandaTree {
    static class SegTree {
        private int N;

        // Let UNIQUE be a value which does NOT
        // and will not appear in the segment tree
        private long UNIQUE = 0;

        // Segment tree values
        private long[] tree;

        public SegTree(int size) {
            tree = new long[2 * (N = size)];
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
        }

        // Adjust point i by a value, O(log(n))
        public void modify(int i, long value) {
            //tree[i + N] = function(tree[i + N], value);
            tree[i + N] += value;
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
                return 0;
            }
            return res;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static long[] ans;
    static class node{
        List<int[]> qs = new ArrayList<>();
        List<Integer> nxt = new ArrayList<>();
        int idx;
        public node(int idx) {
            this.idx = idx;
        }
    }
    static node[] nodes = new node[300005];
    static SegTree sg = new SegTree(300005);
    static int maxDepth;
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[][] arr = new int[n - 1][];
        ans = new long[n];
        nodes = new node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new node(i);
        }
        maxDepth = n + 2;
        for (int i = 0; i < n - 1; i++) {
            arr[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
            nodes[arr[i][0]].nxt.add(arr[i][1]);
            nodes[arr[i][1]].nxt.add(arr[i][0]);
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1, d = in.nextInt(), x = in.nextInt();
            nodes[v].qs.add(new int[]{d, x});
        }
        dfs(0, -1, 0);
        for(long x : ans){
            out.print(x+" ");
        }
        out.println();
    }
    static void dfs(int idx, int par, int dep){
        List<int[]> qs = nodes[idx].qs;
        for(int[] q : qs){
            sg.modify(Math.min(maxDepth, dep + q[0]), q[1]);
        }
        ans[idx] = sg.query(dep, maxDepth + 1);
        for(int nxt : nodes[idx].nxt){
            if (par == nxt) continue;
            dfs(nxt, idx, dep + 1);
        }
        for(int[] q : qs){
            sg.modify(Math.min(maxDepth, dep + q[0]), -q[1]);
        }
    }
}
