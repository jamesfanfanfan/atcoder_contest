package codeforce.cf712;

import java.io.PrintWriter;
import java.util.Scanner;

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
        int n = in.nextInt();
        String s = in.next();
        char[] cs = s.toCharArray();
        char[] c1 = new char[n];
        char[] c2 = new char[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if(cs[i] == '1')cnt++;
        }
        if (cs[0] == '0' || cs[n - 1] == '0' || cnt % 2 != 0){
            out.println("NO");
        }else{
            int l = 0;
            int v = 0;
            for (int i = 0; i < n; i++) {
                if (cs[i] == '1'){
                    if(l * 2 == cnt){
                        c1[i] = ')';
                        c2[i] = ')';
                    }else{
                        l++;
                        c1[i] = '(';
                        c2[i] = '(';
                    }
                }else{
                    if(v == 0){
                        c1[i] = '(';
                        c2[i] = ')';
                    }else{
                        c1[i] = ')';
                        c2[i] = '(';
                    }
                    v = 1 - v;
                }
            }
            out.println("YES");
            out.println(new String(c1));
            out.println(new String(c2));
        }


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
