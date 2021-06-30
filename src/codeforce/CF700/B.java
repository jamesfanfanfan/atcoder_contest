package codeforce.CF700;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        solve(sc, pw);
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        LinkedList<Integer> a0 = new LinkedList<>();
        LinkedList<Integer> a1 = new LinkedList<>();

        boolean now = true;
        for (int i = 0; i < n; i++) {
            if (a0.isEmpty()){
                a0.add(arr[i]);
            }else if (a1.isEmpty()){
                a1.add(arr[i]);
            }else{
                if (arr[i] == a1.getLast()){
                    a0.add(arr[i]);
                }else if (arr[i] == a0.getLast()){
                    a1.add(arr[i]);
                }else{
                    if (a0.size() > a1.size()){
                        a1.add(arr[i]);
                    }else{
                        a0.add(arr[i]);
                    }
                }
            }

        }
        int cnt = ct(a0) + ct(a1);
        out.println(cnt);


    }
    static int ct(LinkedList<Integer> res){
        int cnt = 0;
        int pre = 0;
        for (int x : res) {
            if (x != pre){
                cnt++;
            }
            pre = x;
        }
        return cnt;
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
