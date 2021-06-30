package codeforce.Edu_103;

import java.io.PrintWriter;
import java.util.Scanner;

public class B {
    static class Node{
        long val = 0;
        int idx = -1;
        long ad = 0;
        Node(int idx, long val, long ad){
            this.idx = idx;
            this.val = val;
            this.ad = ad;
        }

        @Override
        public String toString() {
            return String.valueOf(idx);
        }
    }
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
        int[] c = new int[n];
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        long cnt = 0;
        long ans = 0;
        long max = 0;
        for (int i = 1; i < n; i++) {
            long dif = Math.abs(a[i] - b[i]);
            if (i > 1){
                if (dif == 0) {
                    max = 2;
                    ans = Math.max(ans, max + c[i] - 1);
                    continue;
                }
                long left = max + c[i - 1] + 1 - dif;
                long now = dif + 2;
                max = Math.max(left, now);
                ans = Math.max(ans, max + c[i] - 1);
            }else{
                ans = Math.max(ans, c[i] + 1 + dif);
                max = dif + 2;
            }
        }
        out.println(ans);


    }
    static boolean isPrime(long n)
    {
        // Corner cases
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }

    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // method to return LCM of two numbers
    static long lcm(long a, long b)
    {
        return (a / gcd(a, b)) * b;
    }
}
