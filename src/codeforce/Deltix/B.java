package codeforce.Deltix;

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
        List<String> ans = new ArrayList<>();
        for (int i = 1; i < n; i += 2) {
            int l = arr[i - 1], r = arr[i];
            ans.add("1"+" "+i+" "+(i + 1));
            ans.add("2"+" "+i+" "+(i + 1));
            ans.add("1"+" "+i+" "+(i + 1));
            ans.add("1"+" "+i+" "+(i + 1));
            ans.add("2"+" "+i+" "+(i + 1));
            ans.add("1"+" "+i+" "+(i + 1));
        }
        out.println(ans.size());
        for(String s : ans){
            out.println(s);
        }
    }
}
