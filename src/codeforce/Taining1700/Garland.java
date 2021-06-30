package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class Garland {
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
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < n; i++) {
//            if (arr[i] > 0) set.add(arr[i]);
//        }
//        int[] par = new int[2];
//        for (int i = 1; i <= n; i++) {
//            if (set.contains(i)) continue;
//            par[i % 2]++;
//        }
//        Integer[][][][] dp = new Integer[101][101][101][2];
//        if (arr[0] != 0){
//            out.println(dfs(par[1], par[0], arr,0, arr[0] % 2, dp));
//        }else{
//            out.println(Math.min(dfs(par[1], par[0] - 1, arr,0, 0, dp), dfs(par[1] - 1, par[0], arr,0, 1, dp)));
//        }


    }
    static int dfs(int o, int e, int[] arr, int i, int prime, Integer[][][][] dp){
        if (i == arr.length - 1) return 0;
        int res = Integer.MAX_VALUE / 2;
        if (arr[i] > 0 && arr[i] % 2 != prime) return res;
        if (dp[o][e][i][prime] != null) return dp[o][e][i][prime];
        if (arr[i + 1] == 0){
            if (o > 0){
                res = Math.min(res, dfs(o - 1, e, arr, i + 1, 1, dp) + (prime == 0 ? 1 : 0));
            }
            if (e > 0){
                res = Math.min(res, dfs(o, e - 1, arr, i + 1, 0, dp) + (prime == 1 ? 1 : 0));
            }
        }else{
            int op = arr[i + 1] % 2;
            res = Math.min(res, dfs(o, e, arr, i + 1, op, dp) + (prime != op ? 1 : 0));
        }
        return dp[o][e][i][prime] = res;
    }
}
