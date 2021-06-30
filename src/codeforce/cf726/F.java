package codeforce.cf726;

import java.io.PrintWriter;
import java.util.Scanner;
// this kind of problem, you could think about move value into one node and then think about how to solve this problem by
// make that specific value 0
public class F {
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
        int n = in.nextInt(), m = in.nextInt();
        int[] arr = new int[n];
        int[] trr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            trr[i] = in.nextInt();
        }
        int[][] edges = new int[m][];
        for (int i = 0; i < m; i++) {
            edges[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
        }
    }
}
