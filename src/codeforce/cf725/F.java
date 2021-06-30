package codeforce.cf725;

import java.io.PrintWriter;
import java.util.Scanner;

public class F {
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
        int l = in.nextInt(), r = in.nextInt();
        out.println(helper(r) - helper(l));
    }
    static long helper(long v){
        long cnt = 0;
        while (v > 0){
            cnt += v;
            v /= 10;
        }
        return cnt;
    }

}
