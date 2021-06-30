package CR693;

import java.io.PrintWriter;
import java.util.Scanner;

public class C {
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
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        long[] dp = new long[n + 1];
        for (int i = n - 1; i >= 0 ; i--) {
            if(i + arr[i] >= n){
                dp[i] = arr[i];
            }else{
                dp[i] = dp[i + arr[i]] + arr[i];
            }
        }
        long max = 0;
        for (int i = 0; i < n; i++) {
            max  = Math.max(max, dp[i]);
        }
        out.println(max);
    }
}
