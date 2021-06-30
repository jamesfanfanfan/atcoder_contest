package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class ByElevatororStairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }

    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), c = in.nextInt();
        int[] arr = new int[n - 1];
        int[] brr = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            brr[i] = in.nextInt();
        }
        int[][] dp = new int[n + 1][2];
        dp[0][1] = Integer.MAX_VALUE / 2;
        for (int i = 1; i <= n - 1; i++) {
            int ups = arr[i - 1];
            int upe = brr[i - 1];
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][0]) + ups;
            dp[i][1] = Math.min(dp[i - 1][1] + upe, dp[i - 1][0] + c + upe);
        }
        for (int i = 0; i < n; i++) {
            out.print(Math.min(dp[i][0], dp[i][1])+" ");
        }
    }
}
