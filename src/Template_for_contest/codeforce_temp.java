package Template_for_contest;

import java.io.PrintWriter;
import java.util.*;


public class codeforce_temp {
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

    }

    // Use this instead of Arrays.sort() on an array of ints. Arrays.sort() is n^2
    // worst case since it uses a version of quicksort. Although this would never
    // actually show up in the real world, in codeforces, people can hack, so
    // this is needed.
    static void ruffleSort(int[] a) {
        //ruffle
        int n=a.length;
        Random r=new Random();
        for (int i=0; i<a.length; i++) {
            int oi=r.nextInt(n), temp=a[i];
            a[i]=a[oi];
            a[oi]=temp;
        }

        //then sort
        Arrays.sort(a);
    }


    static int findDivisor(long v, int[] primes){
        int cnt = 0;
        for(int x : primes){
            while (v % x == 0){
                cnt++;
                v /= x;
            }
            if (v == 1){
                break;
            }
            if (isPrime(v)) break;
        }
        if (v > 1) cnt++;
        return cnt;
    }

    static boolean[] isPrime=new boolean[1_000_001];
    static int[] factorOf=new int[1_000_001];
    static void precomp() {
        Arrays.fill(isPrime, true);
        for (int i=2; i<isPrime.length; i++) {
            if (!isPrime[i]) continue;
            factorOf[i]=i;
            for (int j=i*2; j<isPrime.length; j+=i) {
                isPrime[j]=false;
                factorOf[j]=i;
            }
        }
    }

    static int getOddFactors(int n) {
        int res=1;
        if (n==1) return res;
        while (n!=1) {
            int factor=factorOf[n];
            int nTimes=0;
            while (n%factor==0) {
                nTimes++;
                n/=factor;
            }
            if (nTimes%2!=0) res*=factor;
        }
        return res;
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

//    credits to  LorDick for Euler's totient function which is used to calculate the number x which is the coprime with m and also is smaller than m
    static long phi(long x) {
        long ans = x;
        for(long i = 2; i * i <= x; i++) {
            if(x % i == 0) {
                while(x % i == 0) {
                    x /= i;
                }
                ans /= i;
                ans *= (i - 1);
            }
        }
        if(x > 1) {
            ans /= x;
            ans *= (x - 1);
        }
        return ans;
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

//    union find template
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


    static class Multiset{
        TreeMap<Integer, Integer> map;
        public Multiset() {
            map = new TreeMap<>();
        }
        public void add(int a) {
            if(!map.containsKey(a)) map.put(a, 1);
            else map.put(a, map.get(a)+1);
        }
        public void remove(int a) {
            map.put(a, map.get(a)-1);
            if(map.get(a) == 0) map.remove(a);
        }
        public boolean contains(int a) {
            return map.containsKey(a);
        }
        public int count(int a) {
            if(!map.containsKey(a)) return 0;
            else return map.get(a);
        }
        public boolean isEmpty() {
            return map.isEmpty();
        }
        public boolean equals(Multiset ms) {
            for(int k: map.keySet()) {
                if(count(k) != ms.count(k)) return false;
            }
            return true;
        }
        public String toString() {
            return map.toString();
        }
    }

//    expression parse template
//     well there is a scenerio you did not consider : ((0 & 0) & 2) the leftmost of one level is lower level ***************
    public int solve(String s) {
        // s = "("+s+")";
        Stack<Character> stack = new Stack<>();
        Stack<int[]> vals = new Stack<>();
        int[] neo = new int[2];
        char c = 'a';
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] cur = null;
        for(int i = 0; i < n; i++){
            if (cs[i] == '('){
                if(cur != null) {
                    vals.push(cur);
                    stack.push(c);
                    stack.push(cs[i]);
                    cur = null;
                }else{
                    stack.push(cs[i]);
                }
            }else if (cs[i] == ')'){
                stack.pop();
                //     well there is a scenario you did not consider : ((0 & 0) & 2) the leftmost of one level is lower level
                if(stack.size() > 0 && stack.peek() == '('){
                    continue;
                }
                if (vals.size() > 0){
                    int[] fm = vals.pop();
                    char cc = stack.pop();
                    cur = helper(fm, cur, cc);
                }
            }else if (cs[i] == '&' || cs[i] == '|'){
                c = cs[i];
            }else{
                if (cur == null){
                    cur = cs[i] == '1' ? new int[]{1, 0}: new int[]{0, 1};
                }else{
                    neo = cs[i] == '1' ? new int[]{1, 0}: new int[]{0, 1};
                    cur = helper(cur, neo, c);
                }
            }
        }
        return Math.max(cur[0], cur[1]);
    }
    int[] helper(int[] le, int[] ri, char c){
        int[] res = new int[2];
        int lv = le[0] == 0 ? 0 : 1;
        int rv = ri[0] == 0 ? 0 : 1;
        if(c == '&'){
            if(lv == 1 && rv == 1){
                res[1] = 0;
                res[0] = Math.min(le[0], ri[0]);
            }else if(lv == 1 || rv == 1){
                res[0] = 0;
                res[1] = 1;
            }else{
                res[0] = 0;
                res[1] = Math.min(le[1] + 1, ri[1] + 1);
            }
        }else{
            if(lv == 1 && rv == 1){
                res[1] = 0;
                res[0] = Math.min(le[0] + 1, ri[0] + 1);
            }else if(lv == 1 || rv == 1){
                res[1] = 0;
                res[0] = 1;
            }else{
                res[0] = 0;
                res[1] = Math.min(le[1], ri[1]);
            }
        }
        return res;

    }

//    combined code for binomial coefficient function such as ncr(a, b) with O(n) preComputation and O(1) for each ncr query
//    credits to anhpp
    static long [] fac;
    static long [] inv;
    static long [] facInv;
    static int nn = 300000;
    static long MOD = (int) 1e9 + 7;
    static void initialInvOfModular(){
        if(fac == null){
            fac = new long[nn + 1];
            fac[1] = 1;
            for(int i = 2; i <= nn; i++)
                fac[i] = fac[i - 1] * i % MOD;

            inv = new long[nn + 1];
            inv[1] = 1;
            for (int i = 2; i <= nn; ++i)
                inv[i] = (MOD - MOD / i) * inv[(int)(MOD % i)] % MOD;

            facInv = new long[nn + 1];
            facInv[0] = facInv[1] = 1;
            for (int i = 2; i <= nn; ++i)
                facInv[i] = facInv[i - 1] * inv[i] % MOD;
        }
    }
    static long ncr(int n, int k) {
        return (fac[n] * facInv[k] % MOD) * facInv[n - k] % MOD;
    }
}
