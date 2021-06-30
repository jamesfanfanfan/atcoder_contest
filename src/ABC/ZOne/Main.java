package ABC.ZOne;

import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        F(sc, pw);
        pw.close();
    }
    static void C(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[][] arr = new int[n][];
        int[] mx = new int[5];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()};
        }
        int l = 1, r = 1000000005;
        while (l < r){
            int mid = (l + r) / 2 + 1;
//            System.out.println(mid);
            if (dfs(arr, mid)){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        out.println(l);
    }
    static boolean dfs(int[][] arr, int hi){
        int n = arr.length;
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] >= hi) sum += (1 << j);
            }
            res.add(sum);
        }
        for(int x : res){
            for (int y : res){
                for (int z : res){
                    if ((x | y | z) == 31){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static void D(Scanner in, PrintWriter out){


    }

    static void E(Scanner in, PrintWriter out){
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}};
        int r = in.nextInt(), c = in.nextInt();
        int[][] mv = new int[r][c];
        int[][] dn = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c - 1; j++) {
                mv[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < r - 1; i++) {
            for (int j = 0; j < c; j++) {
                dn[i][j] = in.nextInt();
            }
        }
        boolean[][][] find = new boolean[r][c][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        pq.add(new int[]{0, 0, 0, 0});
        while (!pq.isEmpty()){
            int[] get = pq.poll();
            int i = get[0], j = get[1], cost = get[2], t = get[3];
            if (i == r - 1 && j == c - 1){
                out.println(cost);
                return;
            }
            if (find[i][j][t]) continue;
            find[i][j][t] = true;
            for(int[] dir : dirs){
                int pi = i + dir[0];
                int pj = j + dir[1];
                if (pi < 0 || pj < 0 || pi >= r || pj >= c || find[pi][pj][0]) continue;
                if (pj < j){
                    pq.add(new int[]{pi, pj, mv[pi][pj] + cost, 0});
                }else if (pj > j){
                    pq.add(new int[]{pi, pj, mv[i][j] + cost, 0});
                }else{
                    pq.add(new int[]{pi, pj, dn[i][j] + cost, 0});
                }
            }
            int pi = i - 1, pj = j;
            if (pi >= 0){
                if (t == 0){
                    pq.add(new int[]{pi, pj, cost + 2, 1});
                }else{
                    pq.add(new int[]{pi, pj, cost + 1, 1});
                }
            }
        }
    }

    static class node{
        node[] nxt = new node[2];
        boolean isEnd = false;
    }
    static void F(Scanner in, PrintWriter out){
        int n = in.nextInt(), m = in.nextInt();
        int[] arr = new int[m];
        node root = new node();
        for (int i = 0; i < m; i++) {
            arr[i] = in.nextInt();
            node tra = root;
            for (int j = 0; j < n; j++) {
                if (((1 << j) | arr[i]) == arr[i]){
                    if (tra.nxt[1] == null){
                        tra.nxt[1] = new node();
                    }
                    tra = tra.nxt[1];
                }else{
                    if (tra.nxt[0] == null){
                        tra.nxt[0] = new node();
                    }
                    tra = tra.nxt[0];
                }
            }
        }
    }
    static void dfs(Set<Integer> set, int idx, node root, List<int[]> res){

    }

    String helper(int target){
        int n = 25;
        int[] pre = new int[25];
        pre[0] = 1;
        for (int i = 0; i < 25; i++) {
            pre[i] = pre[i - 1] * 26;
        }
        int idx = 1;
        while (target > pre[idx]){
            target -= pre[idx++];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idx; i++) {
            int ik = target / pre[idx - i];
            sb.append((char) ('a' + ik));
            target -= ik * pre[idx - i];
        }
        return sb.toString();
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

}
