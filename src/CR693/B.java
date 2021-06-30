package CR693;

import java.io.PrintWriter;
import java.util.Scanner;

public class B {
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
        int sum = 0;
        int[] dp = new int[3];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            sum += arr[i];
            dp[arr[i]]++;
        }
        if ((sum % 2 != 0)){
            out.println("NO");
        }else{
            int hf = sum / 2;
            int nd = hf / 2;
            int rem = hf - Math.min(nd, dp[2]) * 2;
            if (rem > dp[1]){
                out.println("NO");
            }else{
                out.println("YES");
            }
        }
    }
}
