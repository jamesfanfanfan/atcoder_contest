package codeforce.edu94;

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
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        int x = in.nextInt();
        StringBuilder sb = new StringBuilder();
        char[] ans = new char[n];
        boolean[] seen = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0'){
                if (i + x < n){
                    ans[i + x] = '0';
                    seen[i + x] = true;
                }
                if (i - x >= 0){
                    ans[i - x] = '0';
                    seen[i - x] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (cs[i] == '1'){
                if (i + x < n && !seen[i + x]){
                    continue;
                }
                if (i - x >= 0 && !seen[i - x]){
                    continue;
                }
                out.println(-1);
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!seen[i]) ans[i] = '1';
        }
        out.println(new String(ans));
    }
}
