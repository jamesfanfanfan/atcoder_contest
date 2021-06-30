package ABC.ABC_186;

import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        //int t = sc.nextInt();
        Main main = new Main();
        //main.E(sc, pw);
        main.F(sc, pw);
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

            return a + b; // sum over a range
            //return (a > b) ? a : b; // maximum value over a range
            //return (a < b) ? a : b; // minimum value over a range
            // return a * b; // product over a range (watch out for overflow!)
        }

        // Adjust point i by a value, O(log(n))
        public void modify(int i, long value) {
            tree[i + N] = function(tree[i + N], value);
            //tree[i + N] = value;
            for (i += N; i > 1; i >>= 1) {
                tree[i >> 1] = function(tree[i], tree[i ^ 1]);
            }
        }

        // Query interval [l, r), O(log(n)) ----> notice the exclusion of r
        public long query(int l, int r) {
            long res = 0;
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


    void F(Scanner sc, PrintWriter out){
        int h = sc.nextInt();
        int v = sc.nextInt();
        int m = sc.nextInt();
        //System.out.println(h +" "+v +" "+m);
        int[][] arr = new int[m][2];
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{sc.nextInt() - 1, sc.nextInt() - 1};
        }
        //System.out.println("sfsddfsd");
        //long tot = 0;
        boolean[] sv = new boolean[v];
        boolean[] sh = new boolean[h];
        Arrays.sort(arr, (a,b) -> {
            if(a[0] == b[0]){
                return a[1] - b[1];
            }else{
                return a[0] - b[0];
            }
        });
        long res = (long)h * (long)v;
        //System.out.println(res);
        SegTree sg1 = new SegTree(v + 1);
        SegTree sg2 = new SegTree(h + 1);

        for(int[] x : arr){
            int i = x[0];
            int j = x[1];
            res -= 1;
            long get = 0;
            if (!sh[i]){// when left is not blocked
                //System.out.println("i:"+i+" j:"+j+" res");
                get = sg1.query(j + 1, v);
                res -= get;
            }
            System.out.println("i:"+i+" j:"+j+" res:"+res+" get:"+get);
            sh[i] = true;
            sg1.modify(j, 1);
        }
        Arrays.sort(arr, (a,b) -> {
            if(a[1] == b[1]){
                return a[0] - b[0];
            }else{
                return a[1] - b[1];
            }
        });
//        for(int[] x : arr){
//            int i = x[0];
//            int j = x[1];
//            res -= 1;
//            long get = 0;
//            if (!sv[j]){// when left is not blocked
//                //System.out.println("v:"+v+" i:"+i);
//                get = sg2.query(i + 1, v);
//                res -= get;
//            }
//            System.out.println("i:"+i+" j:"+j+" res:"+res+" get:"+get);
//            sv[j] = true;
//
//            sg2.modify(i, 1);
//        }

        out.println((res));
    }
    void E(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int s = n - in.nextInt();
        int k = in.nextInt();
        int gcd = gcd(n, k);
        if (s % gcd != 0){
            out.println(-1);
        }else{

            long res = (n - 1) / k + 1;
            int mod = s % k;
            res *= (mod / gcd);
            //out.println("mod:"+mod);
            res += Math.max(0, (s - mod) / k);
            out.println(res);
        }
    }
    int gcd(int a1, int a2){
        if (a2 == 0){
            return a1;
        }
        return gcd(a2, a1 % a2);
    }
}
