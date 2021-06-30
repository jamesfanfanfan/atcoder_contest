package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SleepingSchedule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static int[][] dp = new int[2005][2005];
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), h = in.nextInt(), l = in.nextInt(), r = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE / 2);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int time = arr[i - 1];
            for (int j = 0; j < h; j++) {
                int ok1 = ((time - 1) + j) % h;
                int ok2 = (time + j) % h;
                if (ok1 >= l && ok1 <= r){
                    dp[i][ok1] = Math.max(dp[i][ok1], dp[i - 1][j] + 1);
                }else{
                    dp[i][ok1] = Math.max(dp[i][ok1], dp[i - 1][j]);
                }
                if (ok2 >= l && ok2 <= r){
                    dp[i][ok2] = Math.max(dp[i][ok2], dp[i - 1][j] + 1);
                }else{
                    dp[i][ok2] = Math.max(dp[i][ok2], dp[i - 1][j]);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < h; i++) {
            max = Math.max(max, dp[n][i]);
        }
        out.println(max);
    }
}
