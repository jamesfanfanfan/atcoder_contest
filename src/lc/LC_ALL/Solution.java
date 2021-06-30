package lc.LC_ALL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.exit;

public class Solution {

    // Discuss this round on Codeforces: https://codeforces.com/blog/entry/70689

    static boolean[] getSets(int n, long h) throws IOException {
        boolean aSets[] = new boolean[1 << n];
        long a[] = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanLong();
        }
        long aSum = 0;
        i: for (int i = 0;; i++) {
            if (aSum >= h) {
                aSets[i] = true;
            }
            for (int j = 0;; j++) {
                if (j == n) {
                    break i;
                }
                if ((i & (1 << j)) != 0) {
                    aSum -= a[j];
                } else {
                    aSum += a[j];
                    break;
                }
            }
        }
        return aSets;
    }

    static void solve() throws Exception {
        int n = scanInt();
        long h = scanLong();
        boolean aSets[] = getSets(n, h), bSets[] = getSets(n, h);
        System.out.println(Arrays.toString(aSets));
        System.out.println(Arrays.toString(bSets));
        int cnts[] = new int[1 << n];
        for (int i = 0; i < 1 << n; i++) {
            if (aSets[i]) {
                cnts[(1 << n) - i - 1] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            int mask = (1 << n) - (1 << i) - 1;
            for (int j = mask;; j = (j - 1) & mask) {
                cnts[j | (1 << i)] += cnts[j];
                if (j == 0) {
                    break;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 1 << n; i++) {
            if (bSets[i]) {
                ans += cnts[i];
            }
        }
        printCase();
        out.println(ans);
    }

    static int scanInt() throws IOException {
        return parseInt(scanString());
    }

    static long scanLong() throws IOException {
        return parseLong(scanString());
    }

    static String scanString() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }

    static void printCase() {
        out.print("Case #" + test + ": ");
    }

    static void printlnCase() {
        out.println("Case #" + test + ":");
    }

    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;

    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            int tests = scanInt();
            for (test = 1; test <= tests; test++) {
                solve();
            }
            in.close();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
}