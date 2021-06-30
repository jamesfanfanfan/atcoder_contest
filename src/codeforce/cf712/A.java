package codeforce.cf712;

import java.io.PrintWriter;
import java.util.Scanner;

public class A {
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
        String s = in.next();
        char[] cs = s.toCharArray();
        int n = cs.length;
        StringBuilder sb = new StringBuilder();
        boolean le = false;
        boolean ri = false;
        char[] ans = new char[n + 1];
        for(int i = 0, j = n - 1; i <= j; i++, j--){
            if(le){
                ans[i + 1] = cs[i];
                ans[j + 1] = cs[j];
            }else if(ri){
                ans[i] = cs[i];
                ans[j] = cs[j];
            }else{
                if(cs[i] == cs[j]){
                    if(cs[i] != 'a'){
                        le = true;
                        ans[i] = 'a';
                        ans[i + 1] = cs[i];
                        ans[j + 1] = cs[j];
                    }else{
                        ans[i] = cs[i];
                        ans[j + 1] = cs[j];
                    }
                }else{
                    if (cs[j] != 'a') {
                        le = true;
                        ans[i] = 'a';
                        ans[i + 1] = cs[i];
                        ans[j + 1] = cs[j];
                    }else if(cs[i] != 'a'){
                        ri = true;
                        ans[j + 1] = 'a';
                        ans[j] = cs[j];
                        ans[i] = cs[i];
                    }
                }
            }
        }
        if(le || ri){

            out.println("YES");
            out.println(new String(ans));
        }else{
            out.println("NO");
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
