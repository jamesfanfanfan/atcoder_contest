package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskOnTheBoard {
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
        char[] cs = s.toCharArray();
        int n = cs.length;
        int m = in.nextInt();
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = in.nextInt();
        }
        char[] ans = new char[m];
        int[] store = new int[26];
        for(char c : cs){
            store[c - 'a']++;
        }
        LinkedList<Integer> q = new LinkedList<>();
        boolean[] seen = new boolean[m + 1];
        for (int i = 0; i < m; i++) {
            if (arr[i] == 0) q.add(i);
        }
        for (int i = 25; i >= 0; i--) {
            if (store[i] < q.size()) continue;
            LinkedList<Integer> neo = new LinkedList<>();
            while (!q.isEmpty()){
                int idx = q.poll();
                if (seen[idx]) continue;
                seen[idx] = true;
                ans[idx] = (char) (i + 'a');
                for (int j = 0; j < m; j++) {
                    arr[j] -= Math.abs(idx - j);
                    arr[j] = Math.max(0, arr[j]);
                }
            }
            for (int j = 0; j < m; j++) {
                if (arr[j] == 0 && !seen[j]) neo.add(j);
            }
            q = neo;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(ans[i]);
        }
        out.println(sb.toString());
    }
}
