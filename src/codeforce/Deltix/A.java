package codeforce.Deltix;

import java.io.PrintWriter;
import java.util.Scanner;

public class A {
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
        int n = in.nextInt(), m = in.nextInt();
        char[] cs = in.next().toCharArray();
        char[] ans = new char[n];
        int[] le = new int[n + 1];
        int[] ri = new int[n + 1];
        int lone = -Integer.MAX_VALUE / 2;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '1'){
                lone = i;
            }else{
                le[i] = lone;
            }
        }
        if (lone == -Integer.MAX_VALUE / 2){
            out.println(new String(cs));
            return;
        }
        lone = -Integer.MAX_VALUE / 2;
        for (int i = n - 1; i >= 0; i--) {
            if (cs[i] == '1'){
                lone = i;
            }else{
                ri[i] = lone;
            }
        }
        for (int i = 0; i < n; i++) {
            if (cs[i] == '1'){
                ans[i] = '1';
            }else{
                int dis = Math.abs(i - le[i]);
                dis = Math.min(dis, Math.abs(i - ri[i]));

                if (dis <= m && Math.abs(i - le[i]) != Math.abs(i - ri[i])){
                    ans[i] = '1';
                }else{
                    ans[i] = '0';
                }
            }
        }
        out.println(new String(ans));
    }
}
