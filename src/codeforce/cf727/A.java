package codeforce.cf727;

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
        long n = in.nextInt(), x = in.nextInt(), t = in.nextInt();
        if (x > t){
            out.println(0);
        }else{
            long rem = t / x;
            rem = Math.min(rem, n);
            long ans = rem * (n - rem);
            ans += ((1 + rem - 1) * (rem - 1) / 2);
            out.println(ans);
        }
    }
}
