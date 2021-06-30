package codeforce.Edu_49;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        solve(sc, pw);
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] ms = new int[n];
        for (int i = 0; i < n; i++) {
            ms[i] = in.nextInt() - 1;
        }
        boolean[] seen = new boolean[n + 1];
        int tot = 0;
        //Set<Integer> set = new HashSet<>();
        Integer[] dp = new Integer[n + 1];
        for (int i = 0; i < n; i++) {
            if (seen[i]) continue;
            tot += dfs(seen, i, ms, arr, dp);
            //out.println("i:"+i+" "+ Arrays.toString(seen)+" tot:"+tot);
        }
        out.println(tot);
    }

    static int dfs(boolean[] seen, int idx, int[] ms, int[] arr, Integer[] dp){
        if (dp[ms[idx]] != null) return 0;
        if (seen[idx]){
            //System.out.println("sssss");
            Set<Integer> set = new HashSet<>();
            int res = arr[ms[idx]];
            while (set.add(idx)){
                //System.out.println(idx+" -> "+ms[idx]+" ");
                idx = ms[idx];
                res = Math.min(res, arr[ms[idx]]);
            }
            return res;
        }
        seen[idx] = true;
        return dp[ms[idx]] = dfs(seen, ms[idx], ms, arr, dp);

    }
}
