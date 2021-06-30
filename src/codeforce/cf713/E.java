package codeforce.cf713;

import java.io.PrintWriter;
import java.util.*;

public class E {
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
        int n = in.nextInt(), l = in.nextInt(), r = in.nextInt(), s = in.nextInt();
        int len = r - l + 1;
        if ((n + (n - len + 1)) * len / 2 < s || (1 + len) * len / 2 > s){
            out.println(-1);
            return;
        }
//        1, 2, 3, 4, 5
        int[] neo = new int[len];
        int sum = 0;
        for (int i = 0; i < len; i++) {
            neo[i] = i + 1;
            sum += i + 1;
        }
        int end = n;
        int start = len;
        for (int i = len - 1; i >= 0; i--) {
            int dif = s - sum;
//            System.out.println(dif+" "+(end - neo[i]));
            if (dif > end - neo[i]){
                sum += (end - neo[i]);
                neo[i] = end;
                start--;
                end--;
            }else{
                if (dif <= end - neo[i] && dif >= start - neo[i]){
                    neo[i] = neo[i] + dif;
                    break;
                }else{
                    out.println(-1);
                    return;
                }
            }
        }
//        System.out.println(Arrays.toString(neo)+" "+sum+" "+len);
        Set<Integer> set = new HashSet<>();
        for(int x : neo)set.add(x);
        List<Integer> le = new ArrayList<>();
        List<Integer> ri = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (set.contains(i)) continue;
            if (le.size()  == l - 1){
                ri.add(i);
            }else{
                le.add(i);
            }
        }
        for(int x : le) out.print(x+" ");
        for(int x : neo) out.print(x+" ");
        for(int x : ri) out.print(x+" ");
        out.println();
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

}
