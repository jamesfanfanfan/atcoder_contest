package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class ThreeBlocksPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
//just greedy method and should utilize the fact that ai is from 1 to 200
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        LinkedList<Integer>[] qs = new LinkedList[201];
        for (int i = 0; i <= 200; i++) {
            qs[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            qs[arr[i]].add(i);
        }
        int[][] pre = new int[201][n + 1];
        for (int i = 1; i <= 200; i++) {
            for (int j = 0; j < n; j++) {
                pre[i][j + 1] = pre[i][j];
                if (arr[j] == i) pre[i][j + 1]++;
            }
        }
        int max = 1;
        for (int i = 1; i <= 200; i++) {
            LinkedList<Integer> q = qs[i];
            int tot = 0;
            while (q.size() > 1){
                int l = q.poll(), r = q.removeLast();
                tot += 2;
                for (int j = 1; j <= 200; j++) {
                    max = Math.max(max, tot + pre[j][r] - pre[j][l + 1]);
                }
            }
        }
        out.println(max);
    }
}
