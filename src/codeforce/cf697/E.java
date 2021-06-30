package codeforce.cf697;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class E {
    static long C[][] = new long[1005][1005];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        ini();
        for (int i = 0; i < t; i++) {
            solve(sc, out);
        }
        out.close();
    }
    static void ini(){
        int mod = (int) 1e9 + 7;
        for (int i= 0, j = 0; i <= 1001; i++) {
            for (j = 0; j <= Math.min(i, 1000); j++) {
                if (j == 0 || j == i)
                    C[i][j] = 1L;
                else
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                C[i][j] %= mod;
            }
        }
    }
    static void solve(Scanner in, PrintWriter out){
        int mod = (int) 1e9 + 7;
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        int add = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
//        Map<Long, Long> mp = new HashMap<>();
//        mp.put(0L, 0L);
//        for (int i = 0; i < n; i++) {
//            Map<Long, Long> neo = new HashMap<>();
//            for(long key : mp.keySet()){
//                neo.put(key + arr[i], mp.getOrDefault(key + arr[i], 0L) + 1);
//            }
//            for(long ks : neo.keySet()){
//                mp.put(ks, (mp.getOrDefault(ks, 0L) + neo.get(ks)) % mod);
//            }
//            System.out.println(mp);
//        }
//        long max = 0;
//        long ans = 0;
//        for(long L : mp.keySet()){
//            if (max < L){
//                ans = mp.get(L);
//            }
//        }
//        out.println(ans);
//        long C[][] = new long[1005][1005];


        // Calculate  value of Binomial
        // Coefficient in bottom up manner

        int[] tot = new int[n + 1];
        for (int i = 0; i < n; i++) {
            tot[arr[i]]++;
        }
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = n ; i >= 0; i--) {
            if (tot[i] > 0){
                q.add(tot[i]);
            }
        }
        long ans = 0;
        //System.out.println(Arrays.toString(tot));
        while (!q.isEmpty()){
            if (k > q.peek()){
                 k -= q.poll();
            }else{
                //System.out.println(k+" "+q);
                ans = C[q.peek()][k];
                ans %= mod;
                break;
            }
        }

        out.println(ans);


    }
}
