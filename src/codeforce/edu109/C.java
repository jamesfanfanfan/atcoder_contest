package codeforce.edu109;

import java.io.PrintWriter;
import java.util.*;

public class C {
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
        int n = in.nextInt(), m = in.nextInt();
        int[] arr = new int[n];
        String[] cs = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            cs[i] = in.next();
        }
        TreeSet<Integer> evenR = new TreeSet<>();
        TreeSet<Integer> evenL = new TreeSet<>();
        TreeSet<Integer> oddR = new TreeSet<>();
        TreeSet<Integer> oddL = new TreeSet<>();
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0){
                if (cs[i].equals("L")){
                    evenL.add(arr[i]);
                }else{
                    evenR.add(arr[i]);
                }
            }else{
                if (cs[i].equals("L")){
                    oddL.add(arr[i]);
                }else{
                    oddR.add(arr[i]);
                }
            }
            mp.put(arr[i], i);
        }
        Map<Integer, Integer> a1 = new HashMap<>();
        Map<Integer, Integer> a2 = new HashMap<>();
        helper(a1, evenL, evenR, m);
        helper(a2, oddL, oddR, m);
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for(int x : a1.keySet()){
            ans[mp.get(x)] = a1.get(x);
        }
        for(int x : a2.keySet()){
            ans[mp.get(x)] = a2.get(x);
        }

        for(int x : ans){
            out.print(x+" ");
        }
        out.println();
    }
    static void helper(Map<Integer, Integer> ans, TreeSet<Integer> l, TreeSet<Integer> r, int m){
        LinkedList<Integer> ql = new LinkedList<>();
        LinkedList<Integer> qr = new LinkedList<>();
//        System.out.println(ql+" "+qr);
        for(int x : l){
            Integer get = r.lower(x);
            if (get != null){
                int time = (x - get) / 2;
                ans.put(x, time);
                ans.put(get, time);
                r.remove(get);
            }
        }
//        System.out.println("r"+r);
        while (r.size() > 1){
            int v = r.last();
            r.remove(v);
            int vv = r.last();
            r.remove(vv);
            int time = (m - v) + (v - vv) / 2;
            ans.put(v, time);
            ans.put(vv, time);
        }
        for(int x : ans.keySet()){
            if (l.contains(x)) l.remove(x);
        }
        while (l.size() > 1){
            int v = l.first();
            l.remove(v);
            int vv = l.first();
            l.remove(vv);
            int time = v + (vv - v) / 2;
            ans.put(v, time);
            ans.put(vv, time);
        }
        if (r.size() > 0 && l.size() > 0){
            int ok = l.last();
            int ok2 = r.last();
            int time = (2 * m - (ok2 - ok)) / 2;
            ans.put(ok, time);
            ans.put(ok2, time);
        }

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
