package Training_1500;

import java.io.PrintWriter;
import java.util.*;

public class Road_Reform {
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
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        int[][] arr = new int[m][];
        Map<Integer, Map<Integer, Integer>> mp = new HashMap<>();
        List<int[]> ls = new ArrayList<>();
        List<int[]> hi = new ArrayList<>();
        int close = Integer.MAX_VALUE / 2;
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1, in.nextInt()};
            if (arr[i][2] < k){
                ls.add(arr[i]);
            }else{
                close = Math.min(close, arr[i][2] - k);
                hi.add(arr[i]);
            }
        }
        int[] pars = new int[n + 1];
        Collections.sort(ls, (a, b) -> (b[2] - a[2]));
        Collections.sort(hi, (a, b) -> (a[2] - b[2]));
        Arrays.fill(pars, -1);
        int max = 0;
        for(int[] ar : ls){
            if (find(ar[0], pars) == find(ar[1], pars)) continue;
            max = Math.max(max, ar[2]);
            union(ar[0], ar[1], pars);
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (pars[i] < 0) cnt++;
        }
        if (cnt == 1){
            out.println(Math.min(k - max, close));
        }else{
            long ans = 0;
            for(int[] ar : hi){
                if (find(ar[0], pars) == find(ar[1], pars)) continue;
                ans += (ar[2] - k);
                union(ar[0], ar[1], pars);
            }
            out.println(ans);
        }

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

