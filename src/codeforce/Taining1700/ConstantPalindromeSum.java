package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

//https://codeforces.com/problemset/problem/1343/D  solved by myself

public class ConstantPalindromeSum {
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
        int n = in.nextInt(), k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] store = new int[2 * k + 5];
        for (int i = 0; i < n / 2; i++) {
            int l = arr[i], r = arr[n - i - 1];
            int sm = Math.min(l, r) + 1;
            int lg = Math.max(l, r) + k;
            int see = l + r;
            store[0] += 2;
            store[sm] -= 1;
            store[see] -= 1;
            store[see + 1] += 1;
            store[lg + 1] += 1;
        }
        long min = n;
        long sum = 0;
        for (int i = 0; i <= 2 * k; i++) {
            sum += store[i];
            min = Math.min(min, sum);
        }
        out.println(min);
    }
}
