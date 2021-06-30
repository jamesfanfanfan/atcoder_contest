package codeforce.ECR92;

import java.io.PrintWriter;
import java.util.Scanner;

public class B {
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
        int n = in.nextInt(), k = in.nextInt(), z = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        //System.out.println(pq);
        int res = 0;
        for (int i = 0; i <= z; i++) {
            int pos = k - 2 * i;
            if (pos < 0)
                continue;
            int max = 0;
            int sum = 0;
            for (int j = 0; j <= pos; j++) {
                if (j < n - 1){
                    max = Math.max(max, arr[j] + arr[j + 1]);
                }
                sum += arr[j];
            }
            res = Math.max(res, sum + max * i);
        }
        out.println(res);
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
}
