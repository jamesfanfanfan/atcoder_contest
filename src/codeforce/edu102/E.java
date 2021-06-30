package codeforce.edu102;

import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

class node{
    int idx = 0;
    long tot = 0;
    long min = Integer.MAX_VALUE;
    long max = Integer.MIN_VALUE;

    public node(int idx, long tot, long min, long max) {
        this.idx = idx;
        this.tot = tot;
        this.min = min;
        this.max = max;
    }

    public node(int idx) {
        this.idx = idx;
    }
}
public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        solve(sc, pw);
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), m = in.nextInt();
        int[][] arr = new int[n][3];
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt(), in.nextInt()};
        }
        node[] fk = new node[n + 1];
        for (int i = 1; i <= n; i++) {
            fk[i] = new node(i);
        }

        PriorityQueue<node> pq = new PriorityQueue<>((a,b) -> {
            return Long.compare(a.tot - a.max + a.min, b.tot - b.max + b.min);
        });

    }
}
