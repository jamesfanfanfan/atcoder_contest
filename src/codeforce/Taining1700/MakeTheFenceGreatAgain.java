package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class MakeTheFenceGreatAgain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }

    static Long[][] dp;
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        dp = new Long[n + 3][3];
        out.println(dfs(0, 0, arr));
    }
    static long dfs(int idx, int add, int[][] arr){
        if (idx == arr.length){
            return 0;
        }
        if (dp[idx][add] != null) return dp[idx][add];
        long pre = (idx == 0 ? Integer.MIN_VALUE : arr[idx - 1][0]);
        long min = Long.MAX_VALUE;
        for (long i = 0; i < 3; i++) {
            if (arr[idx][0] + i != add + pre){
                min = Math.min(min, dfs(idx + 1, (int)i, arr) + i * arr[idx][1]);
            }
        }
        return dp[idx][add] = min;
    }
}
