package kickStart.round_B_2021.Kick_start_2019_G;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            C(sc, out, i + 1);
        }
        out.close();
        //System.out.println((70 ^ 3 + 6 ^ 3)+" "+(76 ^ 3));
    }

    static void C(Scanner in, PrintWriter out, int t){
        int n = in.nextInt(), h = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        int[] dp = new int[(1 << n)];
        for (int i = 0; i < (1 << n); i++) {
            int cnt = 0;
            for(int j = 0; j < n; j++){
                if (((1 << j) | i ) == i){
                    cnt += a[j];
                }
            }
            if (cnt >= h) dp[i] = 1;
        }

        // this part is very tricky and I do not know why it should do it in this order
        for(int i = 0; i < n; ++ i)
            for(int mask = 0; mask < (1 << n); ++ mask)
                if(((mask >> i) & 1) == 1) dp[mask ^ (1 << i)] += dp[mask];
        System.out.println(Arrays.toString(dp));
        long tot = 0;
        for (int i = 0; i < (1 << n); i++) {
            int task = 0;
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (((1 << j) | i) == i){
                   cnt += b[j];
                }else{
                    task |= (1 << j);
                }
            }
            if (cnt >= h){
                tot += dp[task];
            }
        }
        printCase(t, out);
        out.println(tot);
    }

    static void printCase(int t, PrintWriter pw){
        pw.print("Case #"+t+": ");
    }

    static void B(Scanner in, PrintWriter out, int t){
        int n = in.nextInt();
        long m = in.nextLong();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }

        int max = 52;
        max = Math.max(max, Long.toBinaryString(m).length());
        for(long x : arr){
            max = Math.max(max, Long.toBinaryString(x).length());
        }
        int[] store = new int[64];// store how many one in this position
        for(long x : arr){
            for (int i = 0; i < max; i++) {
               if ((x | 1L << i) == x){
                   store[i]++;
               }
            }
        }

        long res = 0;
        long tot = m;
        boolean[] ans = new boolean[max + 1];
        boolean find = false;
        for (int i = max; i >= 0 ; i--) {// if i position of res is 1 and can this be realized
            long one = (1L << i) * (n - store[i]);
            if (one <= tot && helper(store, i - 1, tot - one, n)){
                ans[i] = true;
                find = true;
                tot -= one;
            }else{
                tot -= (1L << i) * store[i];
            }
        }
//

        if (find){
            for (int i = max - 1; i >= 0; i--) {
                if(ans[i]) res += (1L << i);
            }
            //System.out.println("ans:"+res+" "+Long.toBinaryString(res));
            out.println("Case #"+t+": "+res);
        }else{
            out.println("Case #"+t+": "+ (-1));
        }

    }
    static boolean helper(int[] store, int idx, long m, int n){
        long aka = 0;
        for(int i = idx; i >= 0; i--){
            aka += (1L << i) * Math.min(store[i], n - store[i]);
        }
        //System.out.println(aka+" "+m);
        return aka <= m;
    }





    static void A(Scanner in, PrintWriter out, int t){
        int n = in.nextInt(), m = in.nextInt(), q = in.nextInt();
        int[] ps = new int[m];
        for (int i = 0; i < m; i++) {
            ps[i] = in.nextInt();
        }
        int[] rs = new int[q];
        for (int i = 0; i < q; i++) {
            rs[i] = in.nextInt();
        }
        Map<Integer, Integer> mp = new HashMap<>();
        Map<Integer, Integer> mis = new HashMap<>();
        for (int i = 0; i < m; i++) {
            findDiv(ps[i], mis);
            //out.println(ps[i]+" "+mis);
        }
        long cnt = 0;
        //out.println(mis);
        for(int x : rs){
            int get = mis.getOrDefault(x, 0);
            int mut = n / x;
            cnt += (mut - get);
        }
        out.println("Case #"+t+": "+cnt);

    }
    static void findDiv(int a, Map<Integer, Integer> div){
        for (int i = 1; i <= Math.sqrt(a); i++) {
            if (a % i == 0){
                if (i * i != a){
                    div.put(i, div.getOrDefault(i, 0) + 1);
                    div.put(a / i, div.getOrDefault(a / i, 0) + 1);
                }else{
                    div.put(i, div.getOrDefault(i, 0) + 1);
                }
            }
        }
    }
}
