package ABC.ABC198;

import java.io.PrintWriter;
import java.util.*;

//atcoder 198
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        E(sc, pw);
        pw.close();
    }

    static void C(Scanner in, PrintWriter out) {
        int r = in.nextInt(), x = in.nextInt(), y = in.nextInt();
        double dis = euclidianDistance(0,0, x, y);
        if (dis % r == 0){
            out.println(Math.round((dis / r)));
        }else{
            int cnt = 0;
            long add = 0;
            while (add + 2 * r < dis){
                cnt += 1;
                add += r;
            }
            out.println((cnt + 2));
        }

    }

    static void D(Scanner in, PrintWriter out) {
        String s1 = in.next(), s2 = in.next(), s3 = in.next();
        Set<Character> set = new HashSet<>();
        for(char c : s1.toCharArray()) set.add(c);
        for(char c : s2.toCharArray()) set.add(c);
        for(char c : s3.toCharArray()) set.add(c);
        if (set.size() > 11){
            out.println("UNSOLVABLE");
        }else{
            long[] ans = new long[2];
            int[] cs = new int[26];
            Arrays.fill(cs, - 1);
            char[] c1 = s1.toCharArray();
            char[] c2 = s2.toCharArray();
            char[] c3 = s3.toCharArray();
            if (dfs(0, 0, 0, cs, c1, c2, c3, ans)){
                out.println(ans[0]);
                out.println(ans[1]);
                out.println((ans[0] + ans[1]));
            }else{
                out.println("UNSOLVABLE");
            }
        }

    }
    static boolean dfs(int mask, int idx1, int idx2, int[] cs, char[] c1, char[] c2, char[] c3, long[] ans){
        if (idx2 == c2.length){
            long[] get = check(c1, c2, c3, cs);
            if (get != null) {
                ans[0] = get[0];
                ans[1] = get[1];
                return true;
            }
            return false;
        }
        if (idx1 < c1.length){
            if (cs[c1[idx1] - 'a'] != -1){
                return dfs(mask, idx1 + 1, idx2, cs, c1, c2, c3, ans);
            }else{
                for (int i = 0; i < 10; i++) {
                    if (idx1 == 0 && i == 0) continue;
                    if ((mask | ( 1 << i)) != mask){
                        cs[c1[idx1] - 'a'] = i;
                        if (dfs(mask | ( 1 << i), idx1 + 1, idx2, cs, c1, c2, c3, ans)) return true;
                        cs[c1[idx1] - 'a'] = -1;
                    }
                }
            }
        }else{
            if (cs[c2[idx2] - 'a'] != -1){
                return dfs(mask, idx1, idx2 + 1, cs, c1, c2, c3, ans);
            }else{
                for (int i = 0; i < 10; i++) {
                    if (idx2 == 0 && i == 0) continue;
                    if ((mask | ( 1 << i)) != mask){
                        cs[c2[idx2] - 'a'] = i;
                        if (dfs(mask | ( 1 << i), idx1, idx2 + 1, cs, c1, c2, c3, ans)) return true;
                        cs[c2[idx2] - 'a'] = -1;
                    }
                }
            }
        }
        return false;
    }
    static long[] check( char[] c1, char[] c2, char[] c3, int[] cs){
        long v1 = 0,  v2 = 0;
        for (int i = 0; i < c1.length; i++) {
            v1 *= 10;
            v1 += cs[c1[i] - 'a'];
        }
        for (int i = 0; i < c2.length; i++) {
            v2 *= 10;
            v2 += cs[c2[i] - 'a'];
        }
        Map<Integer, Character> mp = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            if (cs[i] != -1){
                mp.put(cs[i], (char) (i + 'a'));
            }
        }
        long tot = v1 + v2;
        String val = String.valueOf(tot);
        if (val.length() != c3.length) return null;
        for (int i = 0; i < c3.length; i++) {
            int v = val.charAt(i) - '0';
            if (cs[c3[i] - 'a'] != -1){
                if (cs[c3[i] - 'a'] != v) return null;
            }else{
                if (mp.containsKey(v)){
                    if (mp.get(v) != c3[i]) return null;
                }else{
                    mp.put(v, c3[i]);
                }
            }
        }
//       System.out.println(v1+" "+v2+" "+mp);
        return new long[]{v1, v2};
    }


    static void E(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[][] ab = new int[n - 1][];
        int[] colors = new int[(int) 1e5 + 5];
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            ab[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
            if (!mp.containsKey(ab[i][0])) mp.put(ab[i][0], new ArrayList<>());
            if (!mp.containsKey(ab[i][1])) mp.put(ab[i][1], new ArrayList<>());
            mp.get(ab[i][1]).add(ab[i][0]);
            mp.get(ab[i][0]).add(ab[i][1]);
        }
        List<Integer> res = new ArrayList<>();
        dfs(-1, 0, mp, colors, res, arr);
        Collections.sort(res);
        for(int x : res){
            out.println(x);
        }


    }
    static void dfs(int par, int root, Map<Integer, List<Integer>> mp, int[] colors, List<Integer> res, int[] arr){
        int c = arr[root];
        if (colors[c] == 0) res.add(root + 1);
        colors[c] ++;
        for(int nxt : mp.get(root)){
            if (nxt == par) continue;
            dfs(root, nxt, mp, colors, res, arr);
        }
        colors[c] --;
    }

    static void F(Scanner in, PrintWriter out) {
        int mod = 998244353;
        long s = in.nextLong();

    }
    static double euclidianDistance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }


    static boolean isPrime(long n) {
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

    static long gcd(long a, long b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // method to return LCM of two numbers
    static long lcm(long a, long b) {
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
}
