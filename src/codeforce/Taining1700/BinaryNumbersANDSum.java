package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class BinaryNumbersANDSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static int mod = 998244353;
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), m = in.nextInt();
        char[] ca = in.next().toCharArray();
        char[] cb = in.next().toCharArray();
        long ans = 0;
        long[] pow = new long[n + 1];
        pow[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow[i] = (pow[i - 1] * 2) % mod;
        }

        long add = 0;
        for(int i = 0; i < m; i++){
            int idxa = n - i - 1, idxb = m - i - 1;
            if (idxa >= 0 && ca[idxa] == '1'){
                add = (add + pow[n - idxa - 1]) % mod;
            }
            if (idxb >= 0 && cb[idxb] == '1'){
                ans = (ans + add) % mod;
            }
        }
        out.println(ans);
    }
}
