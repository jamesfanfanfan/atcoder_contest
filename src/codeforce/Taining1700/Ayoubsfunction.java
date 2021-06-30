package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;
//https://codeforces.com/contest/1301/my
public class Ayoubsfunction {
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
        long n = in.nextInt(), m = in.nextInt();
        if (m == 0){
            out.println(0);
            return;
        }
        long tot = (n * (n - 1)) / 2 + n;
        long zero = n - m;
        if (m >= zero){
            tot -= zero;
        }else{
            long each = zero / (m + 1);
            long rem = zero % (m + 1);
            long dm = (each * (each + 1) / 2 + each + 1) * rem + (m + 1 - rem) * (each * (each - 1) / 2 + each);
            tot -= dm;
        }
        out.println(tot);
    }
}
