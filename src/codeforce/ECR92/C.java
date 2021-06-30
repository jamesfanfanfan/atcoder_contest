package codeforce.ECR92;

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
        String s = in.next();
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] seen = new int[10];
        for (int i = 0; i < n; i++) {
            int v = cs[i] - '0';
            seen[v]++;
        }
        int max = 0;
        for(int x : seen){
            max = Math.max(max, x);
        }
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (i == j) continue;
                int cnt = 0;
                int now = i;
                int proc = 0;
                for (int k = 0; k < n; k++) {
                    int v = cs[k] - '0';
                    if (v == now){
                        proc++;
                        if (proc == 2){
                            cnt += 2;
                            now = i;
                            proc = 0;
                        }else{
                            now = j;
                        }
                    }
                }
                max = Math.max(max, cnt);
            }
        }
        out.println((n - max));
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
