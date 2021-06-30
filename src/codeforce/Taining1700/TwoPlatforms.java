package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

//https://codeforces.com/problemset/problem/1409/E
public class TwoPlatforms {
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
        int n = in.nextInt(), k = in.nextInt();
        int[][] cor = new int[n][2];
        for (int i = 0; i < n; i++) {
            cor[i][0] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            cor[i][1] = in.nextInt();
        }
        Arrays.sort(cor, (a, b) -> (a[0] - b[0]));
        LinkedList<Integer> q = new LinkedList<>();
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        Map<Integer, Integer> ok = new HashMap<>();
        for (int i = 0, r = 0; i < n; i++) {
            int st = cor[i][0];
            int end = st + k;
            while (r < n && cor[r][0] <= end){
                q.add(cor[r][0]);
                r++;
            }
            while (!q.isEmpty() && q.peek() < st) q.poll();
            mp.put(st, q.size());
            ok.put(cor[i][0], ok.getOrDefault(cor[i][0], 0) + 1);
        }
        TreeMap<Integer, Integer> store = new TreeMap<>();
        int max = 0;
        store.put(Integer.MAX_VALUE, 0);
        for(int key : mp.descendingKeySet()){
            int v = mp.get(key);
            max = Math.max(v, max);
            store.put(key, max);
        }
        int ans = 0;
        for(int key : mp.keySet()){
            int v = mp.get(key);
            int end = key + k;
            Integer le = store.higherKey(end);
            ans = Math.max(v + store.get(le), ans);

        }
        out.println(ans);
    }
}
