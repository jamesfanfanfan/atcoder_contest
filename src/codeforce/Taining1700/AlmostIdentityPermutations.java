package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class AlmostIdentityPermutations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static int[][] dp = new int[1004][5];
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), k = in.nextInt();
        makeTriangle();
        long ans = 0;
        if(k >= 1) {
            ans += 1;
        }
        if (k >= 2){
            ans += ncr(n, 2) * 1;
        }
        if (k >= 3){
            ans += ncr(n, 3) * 2;
        }
        if (k >= 4){
            ans += ncr(n, 4) * 9;
        }
        out.println(ans);
    }
    static long[][] triangle = new long[1005][1005];

    static void makeTriangle() {
        int i, j;

        // initialize the first row
        triangle[0][0] = 1; // C(0, 0) = 1

        for(i = 1; i < triangle.length; i++) {
            triangle[i][0] = 1; // C(i, 0) = 1
            for(j = 1; j <= i; j++) {
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
            }
        }
    }

     static long ncr(int n, int r) {
        return triangle[n][r];
    }


}
