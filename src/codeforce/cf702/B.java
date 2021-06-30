package codeforce.cf702;

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
        int n = in.nextInt();
        int[] arr = new int[n];
        int[] ct = new int[3];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            ct[arr[i] % 3]++;
        }
        int tar = n / 3;
        int[][] get = new int[3][2];
        for (int i = 0; i < 3; i++) {
            get[i] = new int[]{i, arr[i]};
        }
//        Arrays.sort(get, (a, b) -> {
//            return a[1] - b[1];
//        });
        int cnt = 0;
        //System.out.println(Arrays.toString(ct));
        for (int i = 0; i < 3; i++) {
            if (ct[i] < tar){
                int r1 = (i - 1 + 3) % 3;
                int r2 = (i - 2 + 3) % 3;
                int v1 = ct[r1];
                int v2 = ct[r2];
                if (v1 > tar){
                    int v = Math.min(tar - ct[i], v1 - tar);
                    cnt += v;
                    ct[r1] -= v;
                    ct[i] += v;
                }
                if (v2 > tar){
                    int v = Math.min(tar - ct[i], v2 - tar);
                    cnt += 2 * v;
                    ct[r2] -= v;
                    ct[i] += v;
                }
            }
        }
        out.println(cnt);
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
