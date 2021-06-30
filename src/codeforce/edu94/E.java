package codeforce.edu94;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    //    https://codeforces.com/contest/1400/problem/E
//well you can think about this as, you can either accept you friend's help from left, or you can just cut you self, or you can help your latter friend
    static long[][] dp = new long[5005][5005];
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        out.println(dfs(1, 0, arr));
    }

    static long dfs(int curIndex, int bfIndx, int[] arr){
        if (curIndex == arr.length) return 0;
        if (dp[curIndex][bfIndx] != -1) return dp[curIndex][bfIndx];
        long ans = Long.MAX_VALUE / 2;
        if (arr[curIndex] >= arr[bfIndx]){
            ans = dfs(curIndex + 1, bfIndx, arr) + 1;
            ans = Math.min(ans, dfs(curIndex + 1, curIndex, arr) + arr[curIndex] - arr[bfIndx]);
        }else{
            ans = dfs(curIndex + 1, curIndex, arr);
        }
        return dp[curIndex][bfIndx] = ans;
    }
}
