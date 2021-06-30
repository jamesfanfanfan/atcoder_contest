package codeforce.edu110;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0){
                ls.add(arr[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 1){
                ls.add(arr[i]);
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (gcd(2 * ls.get(i), ls.get(j)) > 1){
                    cnt++;
                }
            }
        }
        out.println(cnt);
    }
    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
}
