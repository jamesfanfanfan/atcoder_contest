package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
//https://codeforces.com/problemset/problem/1446/B
public class CatchingCheaters {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static int[][] dp = new int[5005][5005];
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), m = in.nextInt();
        char[] ca = in.next().toCharArray();
        char[] cb = in.next().toCharArray();
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE / 2);
        }
        dp[0][0] = 0;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char a = ca[i - 1];
                char b = cb[j - 1];
                if (a == b){
                    dp[i][j] = Math.max(dp[i - 1][j - 1], 0) + 2;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j - 1], 0) - 2;
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][j - 1], 0) - 1);
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], 0) - 1);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        out.println(max);
    }
}
