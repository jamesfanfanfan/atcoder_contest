package ABC.abc207;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            E(sc, pw);
        }
        pw.close();
    }
    static int mod = (int) 1e9 + 7;
    static void E(Scanner in, PrintWriter out){
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        long[][] dp = new long[3005][3005];
        long[][] memo = new long[3005][3005];
//        dp[i][j] as the number of contiguous subsequence at ith position which has j blocks
//        for the brutal force way, we could traverse to see sum[i] - sum[k] is divisible by (j + 1);
//        however it would result into N^3 
//        so we need to reduce the transition formula into one, 
//        to reach this we know that, in order to make the sum[i] - sum[k] is divisible by j + 1
//        we notice that the remaining of sum[i] is equal to the remaining of sum[k] divisible by j + 1;
//        therefore we just need to know how many this kind to preSum exists in the j - 1 scenerio
        long[] preSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + arr[i - 1];
        }
        memo[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            long sum = preSum[i];
            for (int j = 1; j <= n; j++) {
                dp[i][j] = memo[(int)(sum % j)][j - 1];
            }
            for(int j = 1; j <= n; j++){
                memo[(int)(sum % (j + 1))][j] = (memo[(int)(sum % (j + 1))][j] + dp[i][j]) % mod;
            }
        }
        long ans = 0;
        for (int i = 0; i <= n; i++) {
            ans = (ans + dp[n][i]) % mod;
        }
        out.println(ans);
    }
}
