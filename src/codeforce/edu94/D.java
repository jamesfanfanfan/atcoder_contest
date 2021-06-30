package codeforce.edu94;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class D {
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
        int[][] preFix = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            preFix[i] = Arrays.copyOf(preFix[i - 1], n + 1);
            preFix[i][arr[i - 1]]++;
        }
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            int[] dis = new int[n + 1];
            int[] pre = preFix[i];
            for (int j = n - 1; j > i; j--) {
                cnt += pre[arr[j]] * dis[arr[i]];
                dis[arr[j]]++;
            }
        }
        out.println(cnt);
    }
}
