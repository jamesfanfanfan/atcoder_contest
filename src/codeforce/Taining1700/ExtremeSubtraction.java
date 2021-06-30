package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class ExtremeSubtraction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
//    https://codeforces.com/problemset/problem/1442/A
//    not solved by me
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] dpa = new int[n + 2];
        int[] dpb = new int[n + 2];
        dpa[0] = Integer.MAX_VALUE / 2;
        dpb[0] = 0;
        for (int i = 1; i <= n; i++) {
            int v = arr[i - 1];
            dpa[i] = Math.min(dpa[i - 1], v - dpb[i - 1]);
            dpb[i] = v - dpa[i];
            if (dpb[i] < dpb[i - 1] || dpa[i] < 0 || dpb[i] < 0) {
                out.println("NO");
                return;
            }
        }
//        System.out.println(Arrays.toString(dpa));
//        System.out.println(Arrays.toString(dpb));
        out.println("YES");
    }
}
