package codeforce.cf722;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

    static List<Integer>[] gra;
    static long[][] arr;
    static long[][] ans;
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        arr = new long[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new long[]{in.nextInt(), in.nextInt()};
        }
        ans = new long[n + 1][2];
//        Map<Integer, List<Integer>> gra = new HashMap<>();
        gra = new ArrayList[n];
        for (int i = 0; i < n - 1; i++) {
            int l = in.nextInt() - 1, r = in.nextInt() - 1;
            if (gra[l] == null) gra[l] = new ArrayList<>();
            if (gra[r] == null) gra[r] = new ArrayList<>();
            gra[l].add(r);
            gra[r].add(l);
        }
        dfs(0, -1);
        out.println(Math.max(ans[0][0], ans[0][1]));
    }
    static void dfs(int root, int par){
        List<Integer> get = gra[root];
        long[] gg = arr[root];
        long c1 = 0, c2 = 0;
        for(int nxt : get){
            if (nxt == par) continue;
            dfs(nxt, root);
            long[] dd = arr[nxt];
            c1 += Math.max(Math.abs(dd[0] - gg[0]) + ans[nxt][0], Math.abs(dd[1] - gg[0]) + ans[nxt][1]);
            c2 += Math.max(Math.abs(dd[0] - gg[1]) + ans[nxt][0], Math.abs(dd[1] - gg[1]) + ans[nxt][1]);
        }
        ans[root][0] = c1;
        ans[root][1] = c2;
        return;
    }
}
