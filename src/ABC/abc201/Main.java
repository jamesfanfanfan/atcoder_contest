package ABC.abc201;

import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
public class Main {
    static int mod = (int) 1e9 + 7;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            D(sc, pw);
        }
        pw.close();
    }
    static void C(Scanner in, PrintWriter out){
        char[] cs = in.next().toCharArray();
        int[] arr = new int[2];
        for (int i = 0; i < 10; i++) {
            if (cs[i] == 'o'){
                arr[0]++;
            }else if (cs[i] == '?'){
                arr[1]++;
            }
        }

        if (arr[0] > 4 || arr[0] + arr[1] == 0){
            out.println(-1);
            return;
        }else{
            int[][] dp = new int[5][5];
            int fk = nCr(arr[1], 4 - arr[0], dp);
            System.out.println(fk);
            int add = 1;
            for (int i = 4; i > (4 - arr[0]) ; i--) {
                add *= i;
            }
            out.println(((fk * 4 * 3 * 2 * 1)));
        }
    }

    //Initialise array elements with zero
    static int nCr(int n, int r, int[][] dp)
    {
        if(n==r) return dp[n][r] = 1; //Base Case
        if(r==0) return dp[n][r] = 1; //Base Case
        if(r==1) return dp[n][r] = n;
        if(dp[n][r] != 0) return dp[n][r]; // Using Subproblem Result
        return dp[n][r] = nCr(n-1,r, dp) + nCr(n-1,r-1, dp);
    }
    static void D(Scanner in, PrintWriter out){
        int h = in.nextInt(), w = in.nextInt();
        char[][] cs = new char[h][];
        for (int i = 0; i < h; i++) {
            cs[i] = in.next().toCharArray();
        }
        int get = dfs(0, 0, cs, 0, new Integer[h][w][2]);
        if (get > 0){
            out.println("Takahashi");
        }else if (get < 0){
            out.println("Aoki");
        }else{
            out.println("Draw");
        }
    }
    static int dfs( int i, int j, char[][] cs, int who, Integer[][][] dp){
        if (i == cs.length - 1 && j == cs[0].length - 1){
            return 0;
        }
        if (dp[i][j][who] != null) return dp[i][j][who];
        if (who == 0){
            int max = -100000000;
            if (j < cs[0].length - 1){
                int point = cs[i][j + 1] == '+' ? 1 : -1;
//                System.out.println(point);
                max = Math.max(max, dfs(i, j + 1, cs, 1 - who, dp) + point);
            }
            if (i < cs.length - 1){
                int point = cs[i + 1][j] == '+' ? 1 : -1;
                max = Math.max(max, dfs(i + 1, j, cs, 1 - who, dp) + point);
            }
            return dp[i][j][who] = max;
        }else{
            int min = 10000000;
            if (j < cs[0].length - 1){
                int point = cs[i][j + 1] == '+' ? 1 : -1;
                min = Math.min(min, dfs(i, j + 1, cs, 1 - who, dp) - point);
            }
            if (i < cs.length - 1){
                int point = cs[i + 1][j] == '+' ? 1 : -1;
                min = Math.min(min, dfs(i + 1, j, cs, 1 - who, dp) - point);
            }
            return dp[i][j][who] = min;
        }
    }

    static void E(Scanner in, PrintWriter out){
        int n = in.nextInt();

        long add = 0;
        Map<Integer, Map<Integer, Long>> mp = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int l = in.nextInt() - 1, r = in.nextInt() - 1;
            long wei = in.nextLong();
            if (!mp.containsKey(l)) mp.put(l, new HashMap<>());
            if (!mp.containsKey(r)) mp.put(r, new HashMap<>());
            mp.get(l).put(r, wei);
            mp.get(r).put(l, wei);
        }
        long[] pre = new long[62];
        pre[0] = 1;
        for (int i = 1; i < 62; i++) {
            pre[i] = (pre[i - 1] * 2l) % mod;
        }
        long[] dp = new long[n + 1];
        Arrays.fill(dp, - 1);
        LinkedList<Integer> q = new LinkedList<>();
        q.add(0);
        dp[0] = 0;
        while (!q.isEmpty()){
            int v = q.poll();
            for(int nxt : mp.get(v).keySet()){
                if (dp[nxt] != -1) continue;
                long w = mp.get(nxt).get(v);
                dp[nxt] = dp[v] ^ w;
                q.add(nxt);
            }
        }
//        System.out.println(Arrays.toString(dp));
        int[][] cnt = new int[60][2];
        for (int i = 0; i < n; i++) {
            long v = dp[i];
            for (int j = 0; j < 60; j++) {
                if ((v | (1l << j)) == v){
                    cnt[j][1]++;
                }else{
                    cnt[j][0]++;
                }
            }
        }
        for (int i = 0; i < 60; i++) {
            long mut = pre[i] * cnt[i][0];
            mut %= mod;
            mut = (mut * cnt[i][1]) % mod;
            add = (add + mut) % mod;
        }
        out.println(add);
    }



    static void F(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] prr = new int[n];
        for (int i = 0; i < n; i++) {
            prr[i] = in.nextInt() - 1;
        }
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt(), in.nextInt()};
        }
        ConcurrentHashMap<Integer, Integer> mp = new ConcurrentHashMap<>();
        mp.put(11, null);
        mp.put(null, null);
    }
    static int[] FromLeft(int[] prr, int[][] arr){
        int n = prr.length;
        long[] res = new long[n];
        SegTree sg = new SegTree(n + 5);
        for (int i = 0; i < n; i++) {
            int id = prr[i];
            long get = sg.query(id, n);

        }

        return new int[2];
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

    public static int[] sieveEratosthenes(int n) {
        if (n <= 32) {
            int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
            for (int i = 0; i < primes.length; i++) {
                if (n < primes[i]) {
                    return Arrays.copyOf(primes, i);
                }
            }
            return primes;
        }

        int u = n + 32;
        double lu = Math.log(u);
        int[] ret = new int[(int) (u / lu + u / lu / lu * 1.5)];
        ret[0] = 2;
        int pos = 1;

        int[] isnp = new int[(n + 1) / 32 / 2 + 1];
        int sup = (n + 1) / 32 / 2 + 1;

        int[] tprimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        for (int tp : tprimes) {
            ret[pos++] = tp;
            int[] ptn = new int[tp];
            for (int i = (tp - 3) / 2; i < tp << 5; i += tp) ptn[i >> 5] |= 1 << (i & 31);
            for (int j = 0; j < sup; j += tp) {
                for (int i = 0; i < tp && i + j < sup; i++) {
                    isnp[j + i] |= ptn[i];
                }
            }
        }

        // 3,5,7
        // 2x+3=n
        int[] magic = {0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4, 13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15, 14};
        int h = n / 2;
        for (int i = 0; i < sup; i++) {
            for (int j = ~isnp[i]; j != 0; j &= j - 1) {
                int pp = i << 5 | magic[(j & -j) * 0x076be629 >>> 27];
                int p = 2 * pp + 3;
                if (p > n) break;
                ret[pos++] = p;
                if ((long) p * p > n) continue;
                for (int q = (p * p - 3) / 2; q <= h; q += p) isnp[q >> 5] |= 1 << q;
            }
        }

        return Arrays.copyOf(ret, pos);
    }

    //    reverse division for 2
    public static long[] rdiv2(int n, int mod){
        long[] arr = new long[n + 5];
        arr[0] = 1;
        long rev2 = (mod + 1) / 2;
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i - 1] * rev2 % mod;
        }
        return arr;
    }
    static List<Integer> primeFactors(int n)
    {
        // Print the number of 2s that divide n
        List<Integer> ls = new ArrayList<>();
        if (n % 2 == 0) ls.add(2);
        while (n%2==0)
        {
            n /= 2;
        }

        // n must be odd at this point.  So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i+= 2)
        {
            // While i divides n, print i and divide n
            if (n  % i == 0) ls.add(i);
            while (n%i == 0)
            {
                n /= i;
            }
        }
        if (n > 1) ls.add(n);
        return ls;
    }

    static int find(int i, int[] par){
        if (par[i] < 0) return i;
        return par[i] = find(par[i], par);
    }
    static boolean union(int i, int j, int[] par){
        int pi = find(i, par);
        int pj = find(j, par);
        if (pi == pj) return false;
        if (par[pi] < par[pj]){
            par[pi] += par[pj];
            par[pj] = pi;
        }else{
            par[pj] += par[pi];
            par[pi] = pj;
        }
        return true;
    }
}
