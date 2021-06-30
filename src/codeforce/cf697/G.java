package codeforce.cf697;

import java.io.PrintWriter;
import java.util.*;

public class G {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        int[] fs = new int[200001];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            fs[arr[i]]++;
        }
        int[] dp = new int[(int)2e5 + 1];
        for(int i = 1;i <= 200000;i++){
            dp[i] += fs[i];
            for(int j = 2*i;j <= 200000;j+=i){
                dp[j] = Math.max(dp[j], dp[i]);
            }
        }
        long ans = 0;
        for(int i = 1;i <= 200000;i++){
            ans = Math.max(ans, dp[i]);
        }
        out.println(n-ans);

    }
    static int helper(int val, int max, Map<Integer, Integer> mp, Integer[] dp){
        if (!mp.containsKey(val)) return 0;
        if(val == max) return mp.get(max);
        int cnt = 0;
        if (dp[val] != null) return dp[val];
        for (int i = 2; i * val <= max; i++) {
            cnt = Math.max(cnt, helper(val * i, max, mp, dp));
        }
        dp[val] = cnt + mp.get(val);
        return dp[val];
    }
}
