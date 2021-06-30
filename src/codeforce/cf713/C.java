package codeforce.cf713;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        int a = in.nextInt(), b = in.nextInt();
        char[] arr = in.next().toCharArray();

        List<Integer> ca = new ArrayList<>();
        List<Integer> cb = new ArrayList<>();
        List<int[]> dm = new ArrayList<>();
        int ct0 = 0, ct1 = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == '0'){
                ct0++;
            }else if (arr[i] == '1') ct1++;
        }

        for(int i = 0, j = n - 1; i <= j; i++, j--){
            if (i == j){
                if (arr[i] == '?'){
                    dm.add(new int[]{i});
                }
            }else{
                if (arr[i] == arr[j] && arr[i] != '?'){
                    continue;
                }else if(arr[i] == '?' && arr[j] == '?'){
                    dm.add(new int[]{i, j});
                }else if (arr[i] != '?' && arr[j] != '?'){
                    if (arr[i] != arr[j]){
                        out.println(-1);
                        return;
                    }
                }else if (arr[j] != '?'){
                    if (arr[j] == '0'){
                        ca.add(i);
                    }else{
                        cb.add(i);
                    }
                }else{
                    if (arr[i] == '0'){
                        ca.add(j);
                    }else{
                        cb.add(j);
                    }
                }
            }
        }

        if (ca.size() + ct0 > a || cb.size() + ct1 > b){
            out.println("-1");
            return;
        }else{
            ct0 += ca.size();
            ct1 += cb.size();
            for (int i = 0; i < dm.size(); i++) {
                int[] get = dm.get(i);
                if (get.length == 1){
                    if (ct0 < a){
                        arr[get[0]] = '0';
                        ct0++;
                    }else{
                        arr[get[0]] = '1';
                        ct1++;
                    }
                }else{
                    if (ct0 + 2 <= a){
                        arr[get[0]] = '0';
                        arr[get[1]] = '0';
                        ct0 += 2;
                    }else{
                        arr[get[0]] = '1';
                        arr[get[1]] = '1';
                        ct1 += 2;
                    }
                }
            }
            if (ct0 != a || ct1 != b){
                out.println(-1);
                return;
            }
            for (int i = 0; i < ca.size(); i++) {
                arr[ca.get(i)] = '0';
            }
            for (int i = 0; i < cb.size(); i++) {
                arr[cb.get(i)] = '1';
            }
            for (int i = 0; i < n; i++) {
                out.print(arr[i]);
            }
            out.println();
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

}
