package Kickstart_2018_B;

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
    static void solve(Scanner in, PrintWriter out, int t){
        long f = in.nextLong();
        long l = in.nextLong();
        int[] fs = new int[String.valueOf(f).length()];
        int[] ls = new int[String.valueOf(l).length()];
        for (int i = String.valueOf(f).length() - 1; i >= 0; i--) {
            fs[i] = (int)(f % 10);
            f /= 10;
        }
        for (int i = String.valueOf(l).length() - 1; i >= 0; i--) {
            ls[i] = (int)(l % 10);
            l /= 10;
        }
        long gf = dfs(0, fs, 0, 0, new Long[30][10][2]);
        long gl = dfs(0, ls, 0, 0, new Long[30][10][2]);
        //out.println(gf+" "+gl+" "+ Arrays.toString(fs)+" "+Arrays.toString(ls));
        out.println("Case #"+t+": "+(gl - gf + 1));
    }

    static long dfs(int idx, int[] arr, int prime, int cnt, Long[][][] dp){
        if(idx == arr.length){
            if (cnt == 0){
                return 0;
            }
            return 1;
        }
        if (dp[idx][cnt][prime] != null) return dp[idx][cnt][prime];
        long res = 0;
        if (prime == 0){
            for (int i = 0; i < arr[idx]; i++) {
                res += dfs(idx + 1, arr, 1, (cnt + i) % 9, dp);
            }
            res += dfs(idx + 1, arr, 0, (cnt + arr[idx]) % 9, dp);
        }else{
            for (int i = 0; i <= 8; i++) {
                res += dfs(idx + 1, arr, 1, (cnt + i) % 9, dp);
            }
        }
        return (dp[idx][cnt][prime] = res);
    }
}
