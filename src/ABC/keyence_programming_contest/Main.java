package ABC.keyence_programming_contest;

import java.io.PrintWriter;
import java.util.*;

public class Main {

    class DistanceLimitedPathsExist {
        Map<Integer, TreeMap<Integer, Integer>> mp = new HashMap<>();
        // for each node we docu the change for each parent
        int[] par;
        public DistanceLimitedPathsExist(int n, int[][] edgeList) {
            par = new int[n + 1];
            Arrays.sort(edgeList, (a, b) -> (a[2] - b[2]));
            for(int[] edge : edgeList){
                int f = edge[0];
                int t = edge[1];
                if (find(f) == find(t)) continue;
                union(f, t);
                if (!mp.containsKey(f)) mp.put(f, new TreeMap<>());
                if (!mp.containsKey(t)) mp.put(t, new TreeMap<>());
                mp.get(f).put(edge[2], find(f));
                mp.get(t).put(edge[2], find(t));
            }
        }
        int find(int i){
            if (par[i] < 0) return i;
            return par[i] = find(par[i]);
        }
        void union(int i, int j){
            int pi = find(i);
            int pj = find(j);
            if (pi == pj) return;
            if (par[pi] < par[pj]){
                par[pi] += par[pj];
                par[pj] = pi;
            }else{
                par[pj] += par[pi];
                par[pi] = pj;
            }
        }

        public boolean query(int p, int q, int limit) {
            if (!mp.containsKey(p) || !mp.containsKey(q)) return false;
            Integer pp = mp.get(p).floorKey(limit);
            Integer pq = mp.get(q).floorKey(limit);
            if (pp == null || pq == null) return false;
            return mp.get(p).get(pp) == mp.get(q).get(pq);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        C(sc, pw);
        pw.close();
    }

    static void C(Scanner in, PrintWriter out){
        int mod = 998244353;

        int h = in.nextInt() - 1;
        int w = in.nextInt() - 1;
        int k = in.nextInt();
        int[][] arr = new int[k][];
        //char[] cs = new char[k];
        char[][] cs = new char[h + 2][w + 2];
        char[][] ap = new char[1][1];
        for (int i = 0; i < k; i++) {
            cs[in.nextInt() - 1][in.nextInt() - 1] = in.next().charAt(0);
        }
        long[] p3 = new long[5005];
        p3[0] = 1;
        for (int i = 1; i < p3.length; i++) {
            p3[i] = p3[i - 1] * 3 % mod;
        }
        long[][] dp = new long[h + 2][w + 2];
        int[][] rt = new int[h + 2][w + 2];
        int[][] dn = new int[h + 2][w + 2];

        for (int i = 0; i <= h ; i++) {
            rt[i][w] = cs[i][w] == ap[0][0] ? 1 : 0;
            for(int j = w - 1; j >= 0; j--){
                rt[i][j] = rt[i][j + 1];
                if (cs[i][j] == ap[0][0]) rt[i][j]++;
            }
            //out.println(Arrays.toString(rt[i]));
        }
        for (int i = 0; i <= w; i++) {
            dn[h][i] = cs[h][i] == ap[0][0] ? 1 : 0;
            for(int j = h - 1; j >= 0; j--){
                dn[j][i] = dn[j + 1][i];
                if (cs[j][i] == ap[0][0]) dn[j][i]++;
            }

        }
        dp[0][0] = 1;
        for (int i = 0; i <= h; i++) {
            for (int j = 0; j <= w; j++) {
                if (cs[i][j] == 'X' || cs[i][j] == 'R'){
                    dp[i][j + 1] = (dp[i][j] * p3[dn[i + 1][j]] % mod + dp[i][j + 1]) % mod;
                }
                if (cs[i][j] == 'X' || cs[i][j] == 'D'){
                    dp[i + 1][j] = (dp[i][j] * p3[rt[i][j + 1]] % mod + dp[i + 1][j]) % mod;
                }
                if (cs[i][j] == ap[0][0]){
                    dp[i + 1][j] = (2 * dp[i][j] * p3[rt[i][j + 1]] % mod + dp[i + 1][j]) % mod;
                    dp[i][j + 1] = (2 * dp[i][j] * p3[dn[i + 1][j]] % mod + dp[i][j + 1]) % mod;
                }
            }
            //out.println(Arrays.toString(dp[i]));
        }
        if (cs[h][w] == ap[0][0]) dp[h][w] = dp[h][w] * 3 % mod;
        out.println(dp[h][w]);
    }
    static void A(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        //PriorityQueue<Integer> pqa = new PriorityQueue<>(Comparator.reverseOrder());
        long max = 0;
        PriorityQueue<Integer> pqa = new PriorityQueue<>(Comparator.reverseOrder());
        List<Long> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pqa.add(a[i]);
            max = Math.max(max, pqa.peek() * (long)b[i]);
            res.add(max);
        }
        for(long x : res){
            out.println(x);
        }
    }

}
