package codeforce.cf712;

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
        String s1 = in.next();
        String s2 = in.next();
        int[] l1 = new int[2];
        int[] l2 = new int[2];
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        for (char c : cs1) {
            l1[c - '0'] ++;
        }
        for (char c : cs2) {
            l2[c - '0'] ++;
        }
        if(l1[0] == l2[0] && l1[1] == l2[1]){
            int iv = 0;
            for (int i = n - 1; i >= 0; i--) {
                int v1 = cs1[i] - '0';
                int v2 = cs2[i] - '0';
                if((v1 + iv) % 2 == v2){
                }else{
                    if(l1[0] == l1[1]){
                        iv++;
                    }else{
                        out.println("NO");
                        return;
                    }
                }
                l1[(v1 + iv) % 2] --;
                l2[(v1 + iv) % 2] --;

            }
            out.println("YES");

        }else{
            out.println("NO");
        }

    }
    static void swap(int[] arr){
        int tep = arr[0];
        arr[0] = arr[1];
        arr[1] = tep;
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
