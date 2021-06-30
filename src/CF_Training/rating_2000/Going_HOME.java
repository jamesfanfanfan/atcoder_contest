package CF_Training.rating_2000;

import java.io.PrintWriter;
import java.util.*;

public class Going_HOME {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        solve(sc, pw);
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        Map<Integer, int[]> mp = new HashMap<>();
        Map<Integer, List<Integer>> dc = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int cnt = 0;
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!dc.containsKey(arr[i])) {
                dc.put(arr[i], new ArrayList<>());
            }
            dc.get(arr[i]).add(i + 1);
            if (dc.get(arr[i]).size() > 1) seen.add(arr[i]);
            if (dc.get(arr[i]).size() == 4){
                List<Integer> gt = dc.get(arr[i]);
                out.println("YES");
                out.println(gt.get(0)+" "+gt.get(1)+" "+gt.get(2)+" "+gt.get(3));
                return;
            }
        }
//        System.out.println(dc);
        if (seen.size() >= 2){
            List<int[]> ans = new ArrayList<>();
            cnt = 2;
            for(int k : dc.keySet()){
                List<Integer> get = dc.get(k);
                if (get.size() >= 2){
                    cnt--;
                    ans.add(new int[]{get.get(0), get.get(1)});
                }
                if (cnt == 0) break;
            }
            out.println("YES");
            out.println(ans.get(0)[1]+" "+ans.get(1)[1]+" "+ans.get(0)[0]+" "+ans.get(1)[0]);
            return;
        }else{
            Arrays.sort(arr);
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (!set.add(arr[i])) continue;
                Map<Integer, int[]> neo = new HashMap<>();
                for (int j = i - 1; j >= 0; j--) {
                    int dif = arr[i] - arr[j];
                    if (mp.containsKey(dif)){
                        if (mp.get(dif)[1] == arr[j] && dc.get(arr[j]).size() == 1){

                        }else{
                            int[] gt = mp.get(dif);
                            out.println("YES");
//                            System.out.println(mp);
//                            System.out.println(Arrays.toString(gt)+" "+Arrays.toString(arr)+" i:"+i+" j:"+j+" "+arr[i]+" "+arr[j]+" "+dif);
                            out.println(dc.get(gt[0]).get(0) +" "+dc.get(arr[i]).get(0)+" "+dc.get(gt[1]).get(0) +" "+dc.get(arr[j]).get(dc.get(arr[j]).size() - 1));
                            return;
                        }
                    }else{
                        neo.put(dif, new int[]{arr[j], arr[i]});
                    }
                }
                mp.putAll(neo);
            }
        }
        out.println("NO");

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
