package codeforce.Deltix;

import java.io.PrintWriter;
import java.util.*;

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
        Stack<String>[] stacks = new Stack[n + 1];
        for (int i = 0; i <= n; i++) {
            stacks[i] = new Stack<String>();
        }
        stacks[0].push("");
        List<String> ans = new ArrayList<>();
        for(int x : arr){
            int pre = x - 1;
            String ss = stacks[pre].pop();
            String neo = ss+x;
            ans.add(neo);
            String add = neo+".";
            stacks[0].push(add);
            stacks[x].push(ss);

        }
        for(String s : ans){
            out.println(s);
        }
    }
}
