package ABC.ABC190;

import java.io.PrintWriter;
import java.util.*;
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

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        F(sc, pw);
        pw.close();
    }
    static void F(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        SegTree sg = new SegTree(n + 5);
        List<Long> res = new ArrayList<>();
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            sg.modify(arr[i], 1);
            long v = sg.query(0, arr[i]);// how many value from the left appearred
            cnt += Math.max(0, i - v);
        }
        //System.out.println("ddd"+cnt);
        res.add(cnt);
        SegTree sg2 = new SegTree(n + 5);

        for (int i = 0; i < n - 1; i++) {
            //long bf = sg2.query(0, arr[i]);
            long tot = n - arr[i] - 1;
            cnt += (tot - arr[i]);
            res.add(cnt);
        }
        for (long val : res){
            out.println(val);
        }

    }
    static void E(Scanner in, PrintWriter out){
        int n = in.nextInt(), m = in.nextInt();
        int[][] arr = new int[m][2];
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
        }
        int k = in.nextInt();
        int[] cv = new int[k];
        for (int i = 0; i < k; i++) {
            cv[i] = in.nextInt() - 1;
        }
        Set<Integer> arrange = new HashSet<>();
        for (int i = 0; i < k; i++) {
            arrange.add(i);
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] ar : arr){
            int f = ar[0];
            int t = ar[1];
            if (! graph.containsKey(f)) graph.put(f, new ArrayList<>());
            if (! graph.containsKey(t)) graph.put(t, new ArrayList<>());
            graph.get(f).add(t);
            graph.get(t).add(f);
        }
        Map<Integer, Map<Integer, Integer>> mp = new HashMap<>();
        //System.out.println(graph);
        int[][] dis = new int[k + 1][k + 1];
        for (int p = 0; p < k ; p++){
            int start = cv[p];
            Integer[] store = new Integer[n + 1];
            LinkedList<Integer> q = new LinkedList<>();
            q.add(start);
            for (int len = 0; !q.isEmpty(); len++) {
                //System.out.println(q);
                for (int i = q.size(); i > 0 ; i--) {
                    int now = q.poll();
                    if (store[now] != null) continue;
                    store[now] = len;
                    if (graph.containsKey(now)){
                        for(int nxt : graph.get(now)){
                            if (store[nxt] != null) continue;
                            q.add(nxt);
                        }
                    }
                }
            }
            //System.out.println(Arrays.toString(store));
            Arrays.fill(dis[p], Integer.MAX_VALUE / 2);
            for (int i = 0; i < k; i++) {
                //System.out.println(cv[i]+" "+store[cv[i]]);
                dis[p][i] = store[cv[i]] != null ? store[cv[i]] : Integer.MAX_VALUE / 2;
            }
            //System.out.println(Arrays.toString(dis[p]));

        }
        int[][] dp = new int[1 << k][k + 1];
        for (int i = 0; i < 1 << k; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < k; i++) {
            dp[1 << i][i] = 1;
        }
        for (int i = 1; i < (1 << k); i++) {
            for (int j = 0; j < k; j++) {
                if ((i | (1 << j)) == i){
                    int nxt =  i ^ (1 << j);
                    for (int l = 0; l < k; l++) {
                        if (((nxt | (1 << l)) == nxt)){
                            dp[i][j] = Math.min(dp[i][j], dp[nxt][l] + dis[l][j]);
                        }
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE / 2;
        for (int i = 0; i < k; i++) {
            min = Math.min(min, dp[(1 << k) - 1][i]);
        }
        if(min == Integer.MAX_VALUE / 2){
            out.println( -1);
            return;
        }
        out.println(min);
    }
    static void D(Scanner in, PrintWriter out){
        long n = in.nextLong();
        Set<Long> set = new HashSet<>();
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0){
                set.add(i);
                set.add(n / i);
            }
        }
        Set<Long> used = new HashSet<>();
        int cnt = 0;
        for (Long val : set){
            //System.out.println(val);
            long opp = n / val;
            if (opp % 2 == 1){
                cnt += 2;
            }
        }
        out.println(cnt);

    }
    static void C(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] cs = new int[m][];
        for (int i = 0; i < m; i++) {
            cs[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
        }
        int k = in.nextInt();
        int[][] bs = new int[k][];
        for (int i = 0; i < k; i++) {
            bs[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
        }
        int tot = 1 << k;
        int max = 0;
        for (int i = 0; i < tot; i++) {
            int[] status = new int[n + 1];
            for (int j = k - 1; j >= 0 ; j--) {
                if ((i | (1 << j)) == i){
                    status[bs[j][1]]++;
                }else{
                    status[bs[j][0]]++;
                }
            }
            int cnt = 0;
            for (int j = 0; j < m; j++) {
                if (status[cs[j][0]] > 0 && status[cs[j][1]] > 0){
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
        }
        out.println(max);
    }
}
