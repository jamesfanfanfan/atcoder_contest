package codeforce.cf725;

import java.io.PrintWriter;
import java.util.Arrays;
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
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] ak = new int[]{0, 0};
        for (int i = 0; i < n; i++) {
            int l = arr[ak[0]];
            int r = arr[ak[1]];
            if (arr[i] < l){
                ak[0] = i;
            }
            if (arr[i] > r){
                ak[1] = i;
            }
        }
        Arrays.sort(ak);
        int min = ak[1] + 1;
        min = Math.min(min, n - ak[0]);
        min = Math.min(min, ak[0] + 1 + n - ak[1]);
        out.println(min);
    }
}
