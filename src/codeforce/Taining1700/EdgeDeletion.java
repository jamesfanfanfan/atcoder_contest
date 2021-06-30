package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

public class EdgeDeletion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static boolean[] seenUseLess = new boolean[300001];
    static boolean[] seenUsed = new boolean[300001];
    static int[] parId = new int[300005];
    static boolean[] seen = new boolean[300005];
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        int[][] arr = new int[m][];
        List<int[]>[] lss = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            lss[i] = new ArrayList<>();
        }
        Map<Integer, Map<Integer, Integer>> gra = new HashMap<>();
        for (int i = 0; i < n; i++) {
            gra.put(i, new HashMap<>());
        }
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1, in.nextInt(), i};
            lss[arr[i][0]].add(new int[]{arr[i][1], arr[i][2], i});
            lss[arr[i][1]].add(new int[]{arr[i][0], arr[i][2], i});
            gra.get(arr[i][0]).put(arr[i][1], i);
            gra.get(arr[i][1]).put(arr[i][0], i);
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            return Long.compare(a[1], b[1]);
        });
        Map<Integer, List<int[]>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(i, new ArrayList<>());
        }
        pq.add(new long[]{0, 0, -1, -1});// curidx, weight, parent, edgeNumber from parent
        int needToDel = Math.max(0, m - k);
        while (!pq.isEmpty()){
            long[] ok = pq.poll();
            int[] neo = new int[]{(int)ok[0], -1, (int) ok[2], (int) ok[3]};
            if (seen[neo[0]]){
                if (needToDel > 0 && !seenUseLess[neo[3]]) {
                    seenUseLess[neo[3]] = true;needToDel--;
                }
                continue;
            }
            seen[neo[0]] = true;
            if (neo[3] >= 0)seenUsed[neo[3]] = true;
            if (neo[2] != -1) mp.get(neo[2]).add(new int[]{neo[0], neo[3]});
            parId[neo[0]] = neo[2];
            for(int[] nxt : lss[neo[0]]){
                int nt = nxt[0], weight = nxt[1], idx = nxt[2];
                if (seenUsed[idx]) continue;
                seenUsed[idx] = true;
                if (seen[nt]){
                    if (needToDel > 0 && !seenUseLess[idx]) {
                        seenUseLess[idx] = true; needToDel--;
                    }
                    continue;
                }
                pq.add(new long[]{nt, ok[1] + weight, neo[0], idx});
            }
        }
        int[] pars = new int[n + 1];
        for(int v : mp.keySet()){
            pars[v] = mp.get(v).size();
        }
        LinkedList<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if (pars[i] == 0) q.add(i);
        }
        while (needToDel > 0){
            for(int sz = q.size(); sz > 0 && needToDel > 0; sz--){
                int id = q.poll();
                int parid = parId[id];
                seenUseLess[gra.get(id).get(parid)] = true;
                pars[parid]--;
                needToDel--;
                if (pars[parid] == 0){
                    q.add(parid);
                }
            }
        }
        out.println(k);
        for (int i = 0; i < m; i++) {
            if (seenUseLess[i])continue;
            out.print((i + 1)+" ");
        }
        out.println();
    }
}
