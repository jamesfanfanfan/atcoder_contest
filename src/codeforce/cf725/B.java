package codeforce.cf725;

import java.io.PrintWriter;
import java.util.Arrays;
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
        long sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            sum += arr[i];
        }
        if (sum % n != 0){
            out.println(-1);
            return;
        }
        long tar = sum / n;
        Arrays.sort(arr);
        int i = n - 1;
        while (i > 0 && arr[i] > tar) i--;
        out.println((n - i - 1));
    }
}
