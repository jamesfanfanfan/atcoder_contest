package codeforce.cf702;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class C {
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
        long x = in.nextLong();
        Set<Long> set = new HashSet<>();
        for (long i = 1; i * i * i <= x ; i++) {
            set.add(i * i * i);
        }
        for (long i = 1; i * i * i <= x ; i++) {
            if (set.contains(x - i * i * i)){
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
