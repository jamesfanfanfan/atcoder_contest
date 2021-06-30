package codeforce.CF698;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class D {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
//        int t = sc.nextInt();
//        for (int i = 0; i < t; i++) {
//            solve(sc, pw);
//        }
        System.out.println(gcd(5, 3));
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        long k = in.nextLong();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        Arrays.sort(arr);
        long dif = arr[1] - arr[0];
        for (int i = 1; i < n; i++) {
            dif = gcd(dif, arr[i] - arr[i - 1]);
        }

        for (int i = 0; i < n; i++) {
            if ((k - arr[i]) % dif == 0){
                out.println("YES");
                return;
            }
        }
        out.println("NO");
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
