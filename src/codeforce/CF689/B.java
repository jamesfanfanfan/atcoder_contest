package codeforce.CF689;

import java.io.PrintWriter;
import java.util.Scanner;

public class B {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        B b = new B();
        while (t > 0){
            b.solve(sc, pw);
            t--;
        }
        pw.close();
    }
    void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] cs = new char[n][];
        for (int i = 0; i < n; i++) {
            cs[i] = sc.next().toCharArray();
        }
        int abk = 0;
        int[][] le = new int[n][m];
        int[][] ri = new int[n][m];
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            le[i][0] = cs[i][0] == '*' ? 1 : 0;
            abk += cs[i][0] == '*' ? 1 : 0;;
            for (int j = 1; j < m; j++) {
                le[i][j] = cs[i][j] == '*' ? 1 + le[i][j - 1] : 0;
                abk += cs[i][j] == '*' ? 1 : 0;
            }
        }
        for (int i = 0; i < n; i++) {
            ri[i][m - 1] = cs[i][m - 1] == '*' ? 1 : 0;
            for (int j = m - 2; j >= 0; j--) {
                ri[i][j] = cs[i][j] == '*' ? ri[i][j + 1] + 1 : 0;
            }
        }
        long cnt = 0;
        for (int i = 0; i < m; i++) {
            dp[0][i] = cs[0][i] == '*'? 1 : 0;
            cnt += dp[0][i];
        }
        for (int i = 1; i < n; i++) {
            cnt += cs[i][0] == '*'? 1 : 0;
            cnt += cs[i][m - 1] == '*'? 1 : 0;
            for (int j = 1; j < m - 1; j++) {
                if (cs[i][j] != '*') continue;
                int left = le[i][j - 1];
                int right = ri[i][j + 1];
                int min = Math.min(left, right) + 1;
                int up = dp[i - 1][j];
                if(up >= min){
                    dp[i][j] += min;
                }else{
                    dp[i][j] += up + 1;
                }
                cnt += dp[i][j];
            }
        }
        if(abk <= 3){
            pw.println(abk);
        }else{
            pw.println(cnt);
        }
    }
}
