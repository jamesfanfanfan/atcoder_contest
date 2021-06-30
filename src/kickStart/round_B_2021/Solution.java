package kickStart.round_B_2021;

import java.io.PrintWriter;
import java.util.*;

public class Solution {
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

            return gcd(a, b);
//            return a + b; // sum over a range
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

    static int[] pms;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            D(sc, pw, i + 1);
        }
        pw.close();
//        arrGenerator(100000, 10000000);
    }

    static void A(Scanner in, PrintWriter out, int t){
        int n = in.nextInt();
        String s = in.next();
        char[] cs = s.toCharArray();
        int[] arr = new int[26];
        printCase(t, out);
        for (int i = 0; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && cs[j] < cs[j + 1])j--;
            out.print((i - j)+" ");
        }
        out.println();
    }
    static void B(Scanner in, PrintWriter out, int t){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            ls.add(arr[i] - arr[i + 1]);
        }
//        System.out.println(ls);
        int[] dp = new int[n + 2];
        int[] rdp = new int[n + 2];
        dp[0] = 1;
        int max = Math.min(3, n);
        for (int i = 1; i < n - 1; i++) {
            if (ls.get(i) == ls.get(i - 1)){
                dp[i] = dp[i - 1] + 1;
            }else{
                dp[i] = 1;
            }
            max = Math.max(dp[i] + 1, max);
        }
        rdp[n - 2] = 1;
        for (int i = n - 3; i >= 0; i--) {
            if (ls.get(i) - ls.get(i + 1) == 0){
                rdp[i] = rdp[i + 1] + 1;
            }else{
                rdp[i] = 1;
            }
            max = Math.max(max, rdp[i] + 1);
        }
        if (max == n){
            printCase(t, out);
            out.println(max);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (i == 0){
                max = Math.max(rdp[i + 1] + 2, max);
            }else if (i == 1){
                int v = arr[i - 1] - arr[i + 1];
                if (v < n - 2 && v % 2 == 0 && v / 2 == ls.get(i + 1)){
                    max = Math.max(max, 2 + rdp[i + 1] + 1);
                }
                max = Math.max(rdp[i + 1] + 2, max);
            }else if (i == n - 1){
                max = Math.max(max, dp[i - 2] + 2);
            }else{
                max = Math.max(rdp[i + 1] + 2, max);
                max = Math.max(max, dp[i - 2] + 2);
                int v = arr[i - 1] - arr[i + 1];
                if (i < n - 2 && v % 2 == 0 && v / 2 == ls.get(i - 2) && v / 2 == ls.get(i + 1)){
                    max = Math.max(max, dp[i - 2] + 1 + rdp[i + 1] + 2);
                }else if (i < n - 2 && v % 2 == 0 && v / 2 == ls.get(i + 1)){
                    max = Math.max(max, 3 + rdp[i + 1]);
                }else if (v % 2 == 0 && v / 2 == ls.get(i - 2)){
                    max = Math.max(max, 3 + dp[i - 2]);
                }
            }
        }
        printCase(t, out);
        out.println(max);
    }
    static int fk(int[] arr){
        int n = arr.length;
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            ls.add(arr[i] - arr[i + 1]);
        }
//        System.out.println(ls);
        int[] dp = new int[n + 2];
        int[] rdp = new int[n + 2];
        dp[0] = 1;
        int max = Math.min(3, n);
        for (int i = 1; i < n - 1; i++) {
//            System.out.println(i+" "+ls.get(i)+" "+ls.get(i - 1)+" "+(ls.get(i) == ls.get(i - 1))+" "+(263874 == 263874));
            if (ls.get(i) - ls.get(i - 1) == 0){
                System.out.println("hree");
                dp[i] = dp[i - 1] + 1;
            }else{
                dp[i] = 1;
            }
            max = Math.max(dp[i] + 1, max);
        }
//        System.out.println("dp:"+Arrays.toString(dp));
        rdp[n - 2] = 1;
        for (int i = n - 3; i >= 0; i--) {
            if (ls.get(i) == ls.get(i + 1)){
                rdp[i] = rdp[i + 1] + 1;
            }else{
                rdp[i] = 1;
            }
            max = Math.max(max, rdp[i] + 1);
        }
//        System.out.println(Arrays.toString(rdp));
//
//        System.out.println(ls);
        if (max == n){
            return max;
        }
        for (int i = 0; i < n; i++) {
            if (i == 0){
                max = Math.max(rdp[i + 1] + 2, max);
            }else if (i == 1){
                int v = arr[i - 1] - arr[i + 1];
                if ( v % 2 == 0 && v / 2 == ls.get(i + 1)){
//                    System.out.println("here");
                    max = Math.max(max, 2 + rdp[i + 1] + 1);
                }
                max = Math.max(rdp[i + 1] + 2, max);
            }else if (i == n - 1){
                max = Math.max(max, dp[i - 2] + 2);
            }else{
                max = Math.max(rdp[i + 1] + 2, max);
                max = Math.max(max, dp[i - 2] + 2);
                int v = arr[i - 1] - arr[i + 1];
                if (i < n - 2 && v % 2 == 0 && v / 2 == ls.get(i - 2) && v / 2 == ls.get(i + 1)){
                    max = Math.max(max, dp[i - 2] + 1 + rdp[i + 1] + 2);
                }else if (i < n - 2 && v % 2 == 0 && v / 2 == ls.get(i + 1)){
                    max = Math.max(max, 3 + rdp[i + 1]);
                }else if (v % 2 == 0 && v / 2 == ls.get(i - 2)){
                    max = Math.max(max, 3 + dp[i - 2]);
                }
            }
        }
        return max;
    }
    static int gan(int[] a){
        int n = a.length;
        boolean[] valid = new boolean[n];
        for(int i = 1;i < n-1;i++){
            if(a[i-1] + a[i+1] == a[i] * 2){
                valid[i] = true;
            }
        }
        int[] left = new int[n];
        for(int i = 1;i < n;i++){
            left[i] = valid[i] ? left[i-1] + 1 : 0;
        }
        int[] right = new int[n];
        for(int i = n-2;i >= 0;i--){
            right[i] = valid[i] ? right[i+1] + 1 : 0;
        }
        int ans = 0;
        for(int i = 0;i < n;i++){
            ans = Math.max(ans, left[i] + 3);
        }
        for(int i = 1;i < n-1;i++){
            if((a[i-1] + a[i+1]) % 2 == 0){
                int v = (a[i-1] + a[i+1]) / 2;
                ans = Math.max(ans, 3);
                if(i-2 >= 0 && v + a[i-2] == 2*a[i-1]){
                    ans = Math.max(ans, left[i-2] + 4);
                }
                if(i+2 < n && v + a[i+2] == 2*a[i+1]){
                    ans = Math.max(ans, right[i+2] + 4);
                }
                if(i-2 >= 0 && i+2 < n && v + a[i-2] == 2*a[i-1] && v + a[i+2] == 2*a[i+1]){
                    ans = Math.max(ans, left[i-2] + right[i+2] + 5);
                }
            }
        }
        ans = Math.min(ans, n);
        return ans;
    }

    static void arrGenerator(int len, int max){
        Random rd = new Random();

//        System.out.println("uwi:"+gan(arr));
//        System.out.println("james:"+fk(arr));
        int[] arr = new int[len];
        int idx = 0;

//        arr = new int[]{759713, 644271, 380397, 116523, 860163, 578538, 507881, 425807, 608125, 723191};
//        System.out.println(Arrays.toString(arr));
        fk(arr);
        while (true){
            System.out.println(idx++);
//            for (int i = 0; i < arr.length; i++) {
//                arr[i] = rd.nextInt(max);
//            }
//            arr = new int[]{759713, 644271, 380397, 116523, 860163, 578538, 507881, 425807, 608125, 723191};
            if (gan(arr) != fk(arr)){
                System.out.println(Arrays.toString(arr));
                System.out.println("uwi:"+gan(arr));
                System.out.println("james:"+fk(arr));
                break;
            }

        }

    }
    static void C(Scanner in, PrintWriter out, int t){
        long v = in.nextLong();
//        System.out.println(pms[pms.length - 1]+" "+pms[pms.length - 2]);
        int l = 0, r = pms.length - 1;
//        System.out.println(v - ((long)pms[r - 1] * (long)pms[r - 2]));
        if (v >= ((long)pms[r - 1] * (long)pms[r - 2])){
            printCase(t, out);
            out.println(((long)pms[r] * (long)pms[r - 1]));
            return;
        }
        while (l < r){
            int mid = (l + r + 1) / 2;
            if ((long)pms[mid] * (long)pms[mid + 1] <= v){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
//        System.out.println(l);
        printCase(t, out);
        if (l == pms.length - 1){
            out.println((pms[l - 1] * (long)pms[l]));
        }else{
            out.println((pms[l] * (long)pms[r + 1]));
        }

    }
    static class node{
        long threshold;
        int limit;
        public node(long threshold, int limit) {
            this.threshold = threshold;
            this.limit = limit;
        }
    }
    static void D(Scanner in, PrintWriter out, int t){
        int n = in.nextInt(), q = in.nextInt();
        int[][] arr = new int[n - 1][3];
        long[] a = new long[n - 1];
        Map<Integer, Map<Integer, node>> mp = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt(), in.nextInt()};
            a[i] = in.nextLong();
            if (!mp.containsKey(arr[i][0])) mp.put(arr[i][0], new HashMap<>());
            if (!mp.containsKey(arr[i][1])) mp.put(arr[i][1], new HashMap<>());
            node neo = new node(a[i], arr[i][2]);
            mp.get(arr[i][0]).put(arr[i][1], neo);
            mp.get(arr[i][1]).put(arr[i][0], neo);
        }
        int[][] qs = new int[q][];
        LinkedList<int[]>[] qqs = new LinkedList[n + 5];
        for (int i = 0; i <= n; i++) {
            qqs[i] = new LinkedList<>();
        }
        for (int i = 0; i < q; i++) {
            qs[i] = new int[]{in.nextInt(), in.nextInt()};
            qqs[qs[i][0]].add(new int[]{qs[i][1], i});
        }
        long[] ans = new long[q];
        SegTree sg = new SegTree(400005);
        Map<Integer, Integer> store = new HashMap<>();
        for (int nxt : mp.get(1).keySet()) {
            node neo = mp.get(1).get(nxt);
            dfs(nxt, 1, mp, ans, qqs, neo, sg, store);
        }
        printCase(t, out);
        for(long x : ans){
            out.print(x+" ");
        }
        out.println();
    }
    static void dfs(int id, int par, Map<Integer, Map<Integer, node>> mp, long[] ans, LinkedList<int[]>[] qs, node neo, SegTree sg, Map<Integer, Integer> store){
        LinkedList<int[]> get = qs[id];
        sg.modify(neo.limit, neo.threshold);
        store.put(neo.limit, store.getOrDefault(neo.limit, 0) + 1);
        while (!get.isEmpty()){
            int[] ok = get.poll();
            ans[ok[1]] = sg.query(0, ok[0] + 1);
        }
        for(int nxt : mp.get(id).keySet()){
            if (nxt == par) continue;
            dfs(nxt, id, mp, ans, qs, mp.get(id).get(nxt), sg, store);
        }
        store.put(neo.limit, store.getOrDefault(neo.limit, 0) - 1);
        if (store.get(neo.limit) == 0){
            sg.modify(neo.limit, 0);
        }
    }



    static void printCase(int cs, PrintWriter out){
        out.print("Case #"+cs+": ");
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
}
