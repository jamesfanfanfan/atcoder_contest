package codeforce.cf726;

import java.io.PrintWriter;
import java.util.Arrays;
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
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        if (n == 2){
            out.println(arr[0]+" "+arr[1]);
            return;
        }
        int min = Integer.MAX_VALUE, l = 0;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] - arr[i] <= min){
                l = i;
                min = arr[i + 1] - arr[i];
            }
        }
        for (int i = l + 1; i < n; i++) {
            out.print(arr[i]+" ");
        }
        for (int i = 0; i <= l; i++) {
            out.print(arr[i]+" ");
        }
        out.println();
    }
}
