package kickStart.round_B_2021.Kick_start_2019_D;



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


}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            C(sc, pw, i + 1);
        }
        pw.close();
    }
    static void C(Scanner in, PrintWriter out, int t){
        int k = in.nextInt(), n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = in.nextInt();
        }
        int res = 0;
        int[][] sq = new int[n][2];
        int[][] rq = new int[n][2];
        for (int i = 0; i < n; i++) {
            sq[i] = new int[]{arr[i], cost[i]};
            rq[i] = new int[]{arr[n - i - 1], cost[n - i - 1]};
        }
        Arrays.sort(sq, (a, b) -> (a[0] - b[0]));
        long max = helper(sq, n, k);
        out.println("Case #"+t+": "+max);

    }
    static long helper(int[][] arr, int n, int k){
        long ans = 0;
        Map<Integer, Integer> mpl = new HashMap<>();
        Map<Integer, Integer> mpr = new HashMap<>();
        SegTree le = new SegTree(n + 10);// find the smallest k number sum in the left
        PriorityQueue<int[]> pqle = new PriorityQueue<>((a,b) -> {
            return b[1] - a[1];
        });
        SegTree ri = new SegTree(n + 10);// keep the smallest k number from the right of the warehouse
        PriorityQueue<int[]> pqri = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        int l = k / 2;
        //0 means the position 1 means the cost
        int pos = arr[l][0];
        long add = arr[l][1];
        for (int i = 0; i < l; i++) {
            int ct = arr[i][1] + pos - arr[i][0];
            pqle.add(new int[]{i, ct});
            mpl.put(i, i);
            le.modify(i, ct);
        }
        for (int i = l + 1; i < n; i++) {
            int ct = arr[i][1] + arr[i][0] - pos;
            pqri.add(new int[]{i, ct});
        }
        int cnt = 0;
        cnt = pqri.size();
        for (int i = 0; i < (k - l); i++) {
            int[] get = pqri.poll();
            mpr.put(get[0], i);
            ri.modify(i, get[1]);
        }
        int rem = k - l;
        //System.out.println(rem+" "+mpr);
        ans = add + le.query(0,n) + ri.query(0, n);
        Set<Integer> rm = new HashSet<>();
        for (int i = l + 1; cnt > rem; i++, cnt--) {
            long lpPos = arr[i - 1][0];
            long lpAdd = arr[i - 1][1];
            int cst = (int)(pos - lpPos + lpAdd);
            if (!pqle.isEmpty() && cst < pqle.peek()[1]){
                int[] get = pqle.poll();
                int idx = mpl.get(get[0]);
                le.modify(idx, cst);
                mpl.put(i, idx);
                mpl.remove(get[0]);
                pqle.add(new int[]{i, cst});
            }
            int aja = i;
            rm.add(aja);
            if (mpr.containsKey(aja)){
                while (!pqri.isEmpty() && rm.contains(pqri.peek()[0])){
                    pqri.poll();
                }
                int[] get = pqri.poll();
                int idx = mpr.get(aja);
                ri.modify(idx, get[1]);
                mpr.remove(aja);
                mpr.put(get[0], idx);
            }
            ans = Math.min(ans, le.query(0, n) + ri.query(0, n) + arr[i][1] + (l - rem) * (long) (arr[i][0] - pos));
            //System.out.println(ans+" "+i+" "+mpr+" query:"+ ri.query(0,n)+"  "+aja);
        }

        return ans;
    }

    static void B(Scanner in, PrintWriter out, int t){
        int n = in.nextInt(), g = in.nextInt(), m = in.nextInt();
        int[][] arr = new int[g][3];
        LinkedList<int[]> qc = new LinkedList<>();
        LinkedList<int[]> qa = new LinkedList<>();
        int[] par = new int[g];
        Arrays.fill(par, -1);
        Map<Integer, List<Integer>> mpc = new HashMap<>();
        Map<Integer, List<Integer>> mpa = new HashMap<>();

        for (int i = 0; i < g; i++) {
            int h = in.nextInt() - 1;
            int c = in.next().equals("A") ? 1 : 0;
            arr[i] = new int[]{i, h, c};
            if (c == 1){
                int add = ((((h - m) % n) + n) % n);
                ///qa.add(new int[]{i, add});
                if (!mpa.containsKey(add)) mpa.put(add, new ArrayList<>());
                mpa.get(add).add(i);
            }else{
                int add = ((h + m) % n);
               // qc.add(new int[]{i, (h + m) % n});
                if (!mpc.containsKey(add)) mpc.put(add, new ArrayList<>());
                mpc.get(add).add(i);
            }
        }

        for(int k : mpa.keySet()){
            List<Integer> get = mpa.get(k);
            int fs = get.get(0);
            for (int i = 1; i < get.size(); i++) {
                union(fs, get.get(i), par);
            }
            qa.add(new int[]{find(par, fs), k});
            //System.out.println(Arrays.toString(new int[]{fs, k}));
        }
        for(int k : mpc.keySet()){
            List<Integer> get = mpc.get(k);
            int fs = get.get(0);
            for (int i = 1; i < get.size(); i++) {
                union(fs, get.get(i), par);
            }
            qc.add(new int[]{find(par, fs), k});
            //System.out.println(Arrays.toString(new int[]{fs, k}));
        }

        Set<Integer> setc = new HashSet<>();
        Set<Integer> seta = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            seta.add(i);
            setc.add(i);
            set.add(i);
        }
        int[] ans = new int[g];

        while ((seta.size() > 0 && setc.size() > 0) && m >= 0){

            for(int[] qq : qc){
                if (set.contains(qq[1])) ans[qq[0]]++;
                //set.remove(qq[1]);
            }
            for(int[] qq : qa){
                if (set.contains(qq[1])) ans[qq[0]]++;
                //set.remove(qq[1]);
            }
            for(int[] qq : qc){
                //if (set.contains(qq[1])) ans[qq[0]]++;
                set.remove(qq[1]);
            }
            for(int[] qq : qa){
                //if (set.contains(qq[1])) ans[qq[0]]++;
                set.remove(qq[1]);
            }
            // setc means this pos is covered by same direction shit so stop
            // if set contans but setc does do contain which means covered by reverse direction, we move again
            qc = helper(qc, -1, n, ans, setc, set);
            qa = helper(qa, 1, n, ans, seta, set);

            //System.out.println(set+" "+Arrays.toString(ans));
            m--;
        }
        for (int i = 0; i < g; i++) {
            int pi = find(par, i);
            //System.out.println(pi);
            ans[i] = ans[pi];
        }
        //System.out.println(Arrays.toString(par)+" par");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < g; i++) {
            sb.append(ans[i]);
            sb.append(" ");
        }
        out.println("Case #"+t+": "+sb.toString());
    }
    static LinkedList<int[]> helper(LinkedList<int[]> q, int dir, int n, int[] ans, Set<Integer> set, Set<Integer> fk){
        Map<Integer, List<Integer>> mp = new HashMap<>();// store the pos and the index
        // if we query some index that could go to this position, we kick
        Set<Integer> covered = new HashSet<>();// this is the position we covered

        for(int sc = q.size(); sc > 0; sc --){
            int[] v = q.poll();
            int idx = v[0];
            int end = v[1];
            //System.out.println("idx:"+idx+" end:"+end);

            set.remove(end);
            int nxt = (end + dir + n) % n;
            covered.add(end);
            if (!covered.contains(nxt) && set.contains(nxt)){
                if (!mp.containsKey(nxt)) mp.put(nxt, new ArrayList<>());
                mp.get(nxt).add(idx);
            }
        }
        for(int x : mp.keySet()){
            for(int y : mp.get(x)){
                q.add(new int[]{y, x});
            }
        }
        //System.out.println(mp+" "+Arrays.toString(ans));
        return q;
    }
    static int find(int[] par, int x){
        if (par[x] < 0) return x;
        return par[x] = find(par, par[x]);
    }

    static void union(int i, int j, int[] par){
        int pi = find(par, i);
        int pj = find(par, j);
        if (pi == pj) return;
        if (par[pi] < par[pj]){
            par[pi] += par[pj];
            par[pj] = pi;
        }else{
            par[pj] += par[pi];
            par[pi] = pj;
        }
    }









    static void solve(Scanner in, PrintWriter out, int t){
        int n = in.nextInt(), q = in.nextInt();
        int[] arr = new int[n];
        int[][] qs = new int[q][];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < q; i++) {
            qs[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        TreeSet<Integer> set = new TreeSet<>();
        int v = 0;
        for (int i = 0; i < n; i++) {
            if (Integer.bitCount(arr[i]) % 2 != 0){
                set.add(i);
            }
            //out.println(Integer.toBinaryString(arr[i]));
            v ^= arr[i];
        }
        StringBuilder sb = new StringBuilder();
        for(int[] x : qs){
            int idx = x[0];
            int val = x[1];
            int bc = Integer.bitCount(val);
            if (bc % 2 == 1){
                set.add(idx);
            }else{
                set.remove(idx);
            }
            v ^= arr[idx];
            v ^= val;
            arr[idx] = val;

            //out.println(Integer.toBinaryString(v)+" "+set);
            if (set.size() % 2 == 0){
                sb.append(n);
            }else{
                Integer l = set.first();
                Integer r = set.last();
                sb.append(Math.max(n - l - 1, r));
            }
            sb.append(" ");
        }
        out.println("Case #"+t+": "+sb.toString());
    }



}
