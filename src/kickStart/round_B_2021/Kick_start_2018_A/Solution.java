package kickStart.round_B_2021.Kick_start_2018_A;

import java.io.PrintWriter;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            solve(sc, pw, i);
        }
        pw.close();
    }
    static void B(Scanner in, PrintWriter out, int t){
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        out.println("Case #"+t+": "+dfs(k, arr));
    }
    static double dfs(int k, int[] arr){
        double res = 0;
        if (k == 0){
            for (int i = 0; i < arr.length; i++) {
                res += arr[i] * 1.0 / arr.length;
            }
            return res;
        }
        double ev = dfs(k - 1, arr);
        for (int i = 0; i < arr.length; i++) {
            res += Math.max(ev, arr[i]) * 1.0 / arr.length;
        }
        return res;
    }
    static void solve(Scanner in, PrintWriter out, int t){
        long get = in.nextLong();
        String s = String.valueOf(get);
        int n = s.length();
        char[] cs = s.toCharArray();
        String[] mut = new String[17];
        StringBuilder sb = new StringBuilder();
        String[] eig = new String[17];
        StringBuilder sb8 = new StringBuilder();
        for (int i = 0; i <= 16; i++) {
            mut[i] = sb.toString();
            sb.append('0');
            eig[i] = sb8.toString();
            sb8.append('8');
        }
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int v = cs[i] - '0';
            if (v % 2 == 1){
                String nxt = s.substring(i, n);
                if (v != 9){
                    String rb = (char) (cs[i] + 1) + mut[n - i - 1];
                    res = Long.valueOf(rb) - Long.valueOf(nxt);
                    //out.println(rb+" nxt:"+nxt);
                }
                String rb = (char) (cs[i] - 1) + eig[n - i - 1];
                res = Math.min(res, Long.valueOf(nxt) - Long.valueOf(rb));
                break;
            }
        }
        if (res == Long.MAX_VALUE) res = 0;
        out.println("Case #"+t+": "+res);
    }
    
}
