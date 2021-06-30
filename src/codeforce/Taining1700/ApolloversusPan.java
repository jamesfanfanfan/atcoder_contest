package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class ApolloversusPan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static int mod = (int) 1e9 + 7;
    static long rbit = 1l;
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        long[] seen = new long[61];
        long[] pow = new long[61];
        for (int i = 0; i < 61; i++) {
            pow[i] = (1l << i) % mod;
        }
        for(long x : arr){
            for (int i = 0; i < 61; i++) {
                if (((rbit << i) | x) == x){
                    seen[i]++;
                }
            }
        }
//        System.out.println(Arrays.toString(seen));
        long ans = 0;
        for (long x : arr) {
            long andPart = 0;
            long orPart = 0;
            for (int i = 0; i < 61; i++) {
                if (((rbit << i) | x) == x) {
                    andPart += pow[i] * seen[i];
                    andPart %= mod;
                }else{
                    orPart += pow[i] * seen[i];
                    orPart %= mod;
                }
            }
            orPart += (x % mod * n);
            orPart %= mod;
            ans = (ans + (orPart * andPart) % mod) % mod;
        }
        out.println(ans);
    }
}
