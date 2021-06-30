package codeforce.cf727;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }

    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), q = in.nextInt();
        char[] cs = in.next().toCharArray();

        int[][] store = new int[n + 1][26];
        for (int i = 1; i <= n; i++) {
            store[i] = Arrays.copyOf(store[i - 1], 26);
            store[i][cs[i - 1] - 'a']++;
        }
        for (int i = 0; i < q; i++) {
            int l = in.nextInt(), r = in.nextInt();
            int[] neo = new int[26];
            long len = 0;
            for (int j = 0; j < 26; j++) {
                neo[j] = store[r][j] - store[l - 1][j];
                if (neo[j] > 0){
                    len += neo[j] * (j + 1);
                }
            }
            out.println(len);
        }
    }
}
