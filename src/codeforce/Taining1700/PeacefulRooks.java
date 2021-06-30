package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

public class PeacefulRooks {
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
        int[][] arr = new int[m][];
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
            row.put(arr[i][0], i);
            col.put(arr[i][1], i);
        }
        Set<String> seen = new HashSet<>();
        int[] pars = new int[m + 1];
        Arrays.fill(pars, -1);
        for (int i = 0; i < m; i++){
            int[] cor = arr[i];
            int r = cor[0], c = cor[1];
            if (r == c) continue;
            if (col.containsKey(r)){
                union(i, col.get(c), pars);
            }
            if (row.containsKey(c)){
                union(i, row.get(c), pars);
            }
        }
        Map<Integer, Set<Integer>> dm = new HashMap<>();
        for (int i = 0; i < m; i++) {
            if (arr[i][0] == arr[i][1]) continue;
            int par = find(i, pars);
            if (!dm.containsKey(par)) dm.put(par, new HashSet<>());
//            System.out.println(i+" "+par+" "+pars[par]);
            dm.get(par).add(arr[i][0]);
            dm.get(par).add(arr[i][1]);
        }
        int cnt = 0;
        for(int k : dm.keySet()){
            Set<Integer> set = dm.get(k);
//            System.out.println(pars[k]+" "+set.size()+" "+(pars[k] + set.size()));
            if (pars[k] + set.size() == 0){
                cnt += (set.size() + 1);
//                System.out.println(cnt+" shit:"+set.size());
            }else{
                cnt -= pars[k];
            }
        }
        out.println(cnt);
    }
    static int find(int i, int[] par){
        if (par[i] < 0) return i;
        return par[i] = find(par[i], par);
    }
    static boolean union(int i, int j, int[] par){
        int pi = find(i, par);
        int pj = find(j, par);
        if (pi == pj) return false;
        if (par[pi] < par[pj]){
            par[pi] += par[pj];
            par[pj] = pi;
        }else{
            par[pj] += par[pi];
            par[pi] = pj;
        }
        return true;
    }

}
