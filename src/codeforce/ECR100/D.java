package codeforce.ECR100;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        List<Integer> left = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (arr[i - 1] == i){
                left.add(i);
            }else{
                break;
            }
        }
        List<Integer> right = new ArrayList<>();
        for (int i = n - 1, j = 2 * n ; i >= 0 ; i--, j--) {
            if (arr[i] == j){
                right.add(i);
            }else{
                break;
            }
        }
        List<Integer> q = new ArrayList<>();
        //System.out.println(left+" "+right);
        for (int i = left.size(); i < n - right.size(); i++) {
            q.add(arr[i]);
        }
        boolean[] seen = new boolean[2 * n + 1];
        for (int i = 0; i < n; i++) {
            seen[arr[i]] = true;
        }
        List<Integer> ava = new ArrayList<>();
        for (int i = 1; i <= 2 * n; i++) {
            if (!seen[i]){
                ava.add(i);
            }
        }
        int cnt = 0;
        for (int i = 0, j = 0; i < q.size() && j < ava.size(); i++,j++) {
            while (j < ava.size() && q.get(i) < ava.get(j)){
                j++;
            }
            if (j < ava.size()) cnt++;
        }
        int ans = 1;
        //System.out.println("q:"+q+" ava"+ava+" cnt:"+cnt+" left"+left);
        for (int j = ava.size() - (q.size() - cnt) - 1, i = q.size() - (q.size() - cnt) - 1; j >= 0 && i >= 0; i--) {
            if (ava.get(j) > q.get(i)){
                ans++;
                j--;
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
