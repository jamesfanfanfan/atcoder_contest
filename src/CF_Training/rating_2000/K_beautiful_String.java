package CF_Training.rating_2000;

import java.io.PrintWriter;
import java.util.*;

public class K_beautiful_String {
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
        int n = in.nextInt(), k = in.nextInt();
        String s = in.next();
        char[] cs = s.toCharArray();
        int[] ct = new int[26];
        for (int i = 0; i < n; i++) {
            ct[cs[i] - 'a']++;
        }
        int[] dv = new int[26];
        for (int i = 0; i < 26; i++) {
            dv[i] = ct[i] % k;
        }
        boolean find = true;
        for (int i = 0; i < 26; i++) {
            if (dv[i] % k != 0) {
                find = false;
                break;
            }
        }
        if (n % k != 0){
            out.println(-1);
            return;
        }
        if (find){
            out.println(s);
            return;
        }else{
            for(int i = n - 1; i >= 0; i--){
                ct[cs[i] - 'a']--;
                char c = cs[i];
                if (c < 'z'){
                    int need = 0;
                    int countLargerThanthis = 0;
                    for (int j = 0; j < 26; j++) {
                        need += (k - ct[j] % k) % k;
                        if (ct[j] > 0 && j + 'a' > c) countLargerThanthis += (k - ct[j] % k) % k;
                    }
                    boolean find_lage = true;
                    if (countLargerThanthis == 0) {
                        need += k;
                        find_lage = false;
                    }

                    if ((n - i - need) >= 0 && (n - i - need) % k == 0){
                        StringBuilder sb = new StringBuilder();
                        sb.append(s.substring(0, i));
                        LinkedList<Character> q = new LinkedList<>();
                        if (find_lage){
                            if (n - i - need > 0){
                                boolean ass = false;
                                for (int j = 0; j < 26; j++) {
                                    int ded = (k - ct[j] % k) % k;
                                    while (ded-- > 0) q.add((char) (j + 'a'));
                                    if ((j + 'a') == (c + 1) && ((k - ct[j] % k) % k) > 0){
                                        ass = true;
                                        if (q.size() > 0)q.removeLast();
                                    }
                                }
                                if (ass){
//                                    System.out.println("here"+" "+i);
                                    sb.append((char) (c + 1));
                                    int numa = n - i - need;
                                    while (numa-- > 0) q.add('a');
                                    Collections.sort(q);
                                    while (!q.isEmpty()){
                                        sb.append(q.poll());
                                    }
                                }else{
                                    sb.append((char) (c + 1));
                                    int ded = k - 1;
                                    while (ded-- > 0) q.add((char) (c + 1));
                                    int numa = n - i - need - k;
                                    while (numa -- > 0) q.add('a');
                                    Collections.sort(q);
                                    while (!q.isEmpty()){
                                        sb.append(q.poll());
                                    }
                                }
                            }else{
                                char fd = c;
                                for (char j = (char)(c + 1); j <= 'z'; j++) {
                                    if (((k - ct[j - 'a'] % k) % k) > 0){
                                        fd = j;
                                        ct[j - 'a']++;
                                        break;
                                    }
                                }
                                if (fd == c) continue;
                                sb.append(fd);
                                for (int j = 0; j < 26; j++) {
                                    int ded = (k - ct[j] % k) % k;
                                    while (ded-- > 0) q.add((char) (j + 'a'));
                                }
                                Collections.sort(q);
                                while (!q.isEmpty()){
                                    sb.append(q.poll());
                                }
                            }
                            out.println(sb.toString());
                        }else{
                            System.out.println("here"+" "+i);
                            for (int j = 0; j < 26; j++) {
                                int ded = (k - ct[j] % k) % k;
                                while (ded-- > 0) q.add((char) (j + 'a'));
                            }
                            int ded = k - 1;
                            while (ded-- > 0){
                                q.add((char) (c + 1));
                            }
                            sb.append((char) (c + 1));
                            int numa = n - i - need - k;
                            while (numa -- > 0) q.add('a');
                            Collections.sort(q);
                            while (!q.isEmpty()){
                                sb.append(q.poll());
                            }
                            out.println(sb.toString());
                        }
                        return;
                    }
                }
            }
            out.println("-1");
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
