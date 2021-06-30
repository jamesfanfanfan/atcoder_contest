package codeforce.Edu_49;

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
        int n = in.nextInt();
        String s = in.next();
        char[] cs = s.toCharArray();
        for (int i = (n - 1) / 2, j = n / 2, len = 0; len < n / 2; i--, j++, len++) {
            if (Math.abs(cs[i] - cs[j]) != 2 && Math.abs(cs[i] - cs[j]) != 0 ){
                out.println("NO");
                return;
            }
        }
        out.println("YES");
    }
}
