package codeforce.edu93;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static long[][][] dp;
    static void solve(Scanner in, PrintWriter out){
        int r = in.nextInt(), g = in.nextInt(), b = in.nextInt();
        dp = new long[r + 5][g + 5][b + 5];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int[] rr = new int[r];
        int[] gg = new int[g];
        int[] bb = new int[b];
        for (int i = 0; i < r; i++) {
            rr[i] = in.nextInt();
        }
        ruffleSort(rr);
        for (int i = 0; i < g; i++) {
            gg[i] = in.nextInt();
        }
        ruffleSort(gg);
        for (int i = 0; i < b; i++) {
            bb[i] = in.nextInt();
        }
        ruffleSort(bb);
        out.println(dfs(r, g, b, rr, gg, bb));
    }
    static long dfs(int i, int j, int k, int[] rr, int[] gg, int[] bb){
        if (i + j + k < 2){
            return 0;
        }
        if (dp[i][j][k] != -1) return dp[i][j][k];
        long res = 0;
        if (i > 0 && j > 0){
            res = Math.max(res, dfs(i - 1, j - 1, k, rr, gg, bb) + rr[i - 1] * gg[j - 1]);
        }
        if (i > 0 && k > 0){
            res = Math.max(res, dfs(i - 1, j, k - 1, rr, gg, bb) + rr[i - 1] * bb[k - 1]);
        }
        if (j > 0 && k > 0){
            res = Math.max(res, dfs(i, j - 1, k - 1, rr, gg, bb) + bb[k - 1] * gg[j - 1]);
        }
        return dp[i][j][k] = res;
    }


    // Use this instead of Arrays.sort() on an array of ints. Arrays.sort() is n^2
    // worst case since it uses a version of quicksort. Although this would never
    // actually show up in the real world, in codeforces, people can hack, so
    // this is needed.
    static void ruffleSort(int[] a) {
        //ruffle
        int n=a.length;
        Random r=new Random();
        for (int i=0; i<a.length; i++) {
            int oi=r.nextInt(n), temp=a[i];
            a[i]=a[oi];
            a[oi]=temp;
        }

        //then sort
        Arrays.sort(a);
    }


}
