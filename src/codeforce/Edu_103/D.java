package codeforce.Edu_103;

import java.io.PrintWriter;
import java.util.Scanner;

public class D {
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
        String s = in.next();
        char[] cs = s.toCharArray();
        int[] ans = new int[n + 1];
        int[][] le = new int[n + 3][2];
        int[][] ri = new int[n + 3][2];
        for (int i = 1; i <= n; i++) {
            char c = cs[i - 1];
            if (c == 'L'){
                le[i][0] = le[i - 1][1] + 1;
            }else{
                le[i][1] = le[i - 1][0] + 1;
            }
        }

        for (int i = n; i > 0 ; i--) {
            char c = cs[i - 1];
            if (c == 'L'){
                ri[i][0] = ri[i + 1][1] + 1;
            }else{
                ri[i][1] = ri[i + 1][0] + 1;
            }
        }
        for (int i = 0; i <= n; i++) {
            int cl = le[i][0];
            int cr = ri[i + 1][1];
            ans[i] = cl + cr + 1;
        }
        for(int x : ans){
            out.print(x+" ");
        }
        out.println();

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
