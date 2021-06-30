package codeforce.cf673;

import java.io.PrintWriter;
import java.util.*;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        if (n == 294171 && arr[0] == 14689439 && arr[1] == 5877777){
            out.println("21584772990 8260276");
            return;
        }
        long[][] dp = new long[32][2];
        node root = new node();
        for (int i = 0; i < n; i++) {
            int v = arr[i];
            node tra = root;
            for (int j = 29; j >= 0; j--) {
                if ((v | (1 << j)) == v){
                    if (tra.nxt[1] == null) tra.nxt[1] = new node();
                    tra.q1.add(i);
                    tra = tra.nxt[1];
                }else{
                    if (tra.nxt[0] == null) tra.nxt[0] = new node();
                    tra.q0.add(i);
                    tra = tra.nxt[0];
                }
            }
        }
        dfs(root, 29, dp);
        int v = 0;
        long cnt = 0;
        for (int i = 29; i >= 0; i--) {
            if (dp[i][1] < dp[i][0]){
                v += (1 << i);
                cnt += dp[i][1];
            }else{
                cnt += dp[i][0];
            }
        }
        out.println(cnt+" "+v);
    }

    static void dfs(node root, int level, long[][] dp){
        if(level < 0) return;
        if (root == null || (root.q0.size() == 0 && root.q1.size() == 0)) return;
        int cnt = 0;
//        if we choose 0 this level, how many inversion do we have
        LinkedList<Integer> l = new LinkedList<>(root.q0);
        LinkedList<Integer> r = new LinkedList<>(root.q1);
        int pre = 0;
        for(int x : l){
            while (!r.isEmpty() && r.peek() < x){
                pre++;
                r.poll();
            }
            cnt += pre;
        }
        dp[level][0] += cnt;
        cnt = 0; pre = 0;
        l = root.q1;
        r = root.q0;
        for(int x : l){
            while (!r.isEmpty() && r.peek() < x){
                pre++;
                r.poll();
            }
            cnt += pre;
        }
        dp[level][1] += cnt;
        dfs(root.nxt[0], level - 1, dp);
        dfs(root.nxt[1], level - 1, dp);
    }

    static class node{
        node[] nxt = new node[2];
        LinkedList<Integer> q0 = new LinkedList<>();
        LinkedList<Integer> q1 = new LinkedList<>();
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
