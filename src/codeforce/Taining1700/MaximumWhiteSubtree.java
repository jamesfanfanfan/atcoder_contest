package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

public class MaximumWhiteSubtree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static List<int[]>[] ls;
    static int[] res = new int[200005];
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        ls = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            ls[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[][] edges = new int[n - 1][];
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            edges[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
            if (!mp.containsKey(edges[i][0])) mp.put(edges[i][0], new ArrayList<>());
            if (!mp.containsKey(edges[i][1])) mp.put(edges[i][1], new ArrayList<>());
            mp.get(edges[i][0]).add(edges[i][1]);
            mp.get(edges[i][1]).add(edges[i][0]);
        }
        int[] us = new int[n];
        dfs(mp, 0, -1, arr, us);
        dfs(mp, 0, -1, us, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(res[i]);
            sb.append(" ");
        }
        out.println(sb.toString());
    }
    static int dfs(Map<Integer, List<Integer>> mp, int root, int par, int[] arr, int[] us) {
        int ans = 0;
        if (arr[root] == 0){
            ans--;
        }else{
            ans++;
        }
        for(int nxt : mp.get(root)){
            if (nxt == par) continue;
            int fk = dfs(mp, nxt, root, arr, us);
            ans += Math.max(0, fk);
        }
        return us[root] = ans;
    }
    static void dfs(Map<Integer, List<Integer>> mp, int root, int par, int[] us, int pv){
        int ori = us[root];
        ori += Math.max(pv, 0);
        res[root] = ori;
        for(int nxt : mp.get(root)){
            if (nxt == par) continue;
            int son = us[nxt];
            int neo = ori;
            neo -= Math.max(0, son);
            dfs(mp, nxt, root, us, neo);
        }
    }
}
