package ABC.ABC_187;

import java.io.PrintWriter;
import java.util.*;

public class Main {

    public int maximumScore(int[] nums, int[] ms) {
        int n = nums.length;
        int m = ms.length;
        int[][] dp;
        if(n > m * 2){
            int[] neo = new int[m * 2];
            for (int i = 0; i < m; i++) {
                neo[i] = nums[i];
            }
            for (int i = n - 1, j = 2 * m - 1; j >= m ; i--, j--) {
                neo[j] = nums[i];
            }
            n = neo.length;
            nums = neo;
        }
        dp = new int[m + 2][n + 2];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            int fixLeft = i - 1;
            for (int j = 1; j <= n; j++) {
                int v = nums[i - 1] * nums[j - 1];
                int le = j - 1;
                int ri = n - i;
                if (le <= fixLeft){
                    int rem = n - (fixLeft - le);
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][rem], dp[i - 1][j - 1]) + v);
                }
                if (ri <= fixLeft){
                    int rem = fixLeft - ri;
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][rem], dp[i - 1][j + 1]) + v);
                }
                if (i == m){
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;




    }



    int mod = (int) 1e9 + 7;
    public int solve(int[][] edges) {
        int n = edges.length;
        Map<Integer, Map<Integer, Integer>> mp = new HashMap<>();
        for(int[] e : edges){
            int f = e[0];
            int t = e[1];
            if (!mp.containsKey(f)) mp.put(f, new HashMap<>());
            if (!mp.containsKey(t)) mp.put(t, new HashMap<>());
            mp.get(f).put(t, e[2]);
            mp.get(t).put(f, e[2]);
        }
        int tn = mp.size();
        int[] toHome = new int[tn + 1];
        Arrays.fill(toHome, Integer.MAX_VALUE / 2);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        pq.add(new int[]{tn - 1, 0});
        while (!pq.isEmpty()){
            int[] arr = pq.poll();
            int idx = arr[0];
            int dis = arr[1];
            if (toHome[idx] <= dis || idx == 0) continue;
            toHome[idx] = dis;
            for(int nxt : mp.get(idx).keySet()){
                if (toHome[nxt] < Integer.MAX_VALUE / 2) continue;
                int ds = mp.get(idx).get(nxt);
                pq.add(new int[]{nxt, ds});
            }
        }
        long[] dp = new long[tn + 5];
        dp[0] = 1;
        boolean[] find = new boolean[tn + 5];
        pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        pq.add(new int[]{0, 0});
        boolean[] seen = new boolean[tn + 5];
        while (!pq.isEmpty()){
            int[] get = pq.poll();
            int idx = get[0];
            seen[idx] = true;
            int dis = get[1];
            for (int nxt : mp.get(idx).keySet()){
                if (seen[idx]) continue;
                int add = mp.get(idx).get(nxt);
                if (toHome[idx] <= toHome[nxt]) continue;
                dp[nxt] += dp[idx];
                dp[nxt] %= mod;
            }
        }
        return (int) dp[mp.size() - 1];

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        solveF(sc, pw);
        pw.close();
    }
    public int longestPalindrome(String w1, String w2) {
        char[] c1 = w1.toCharArray();
        char[] c2 = new StringBuilder(w2).reverse().toString().toCharArray();
        int n = w1.length();
        int m = w2.length();
        int[][] dp = new int[n + 1][m + 1];
        int[][] p1 = helper(c1);
        int[][] p2 = helper(c2);

        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (c1[i - 1] == c2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
                if (i == n && j == m){
                    res = Math.max(res, dp[i][j] * 2);
                }else if(i == n){
                    res = Math.max(res, dp[i][j] * 2 + p2[j][m - 1]);
                }else if(j == m){
                    res = Math.max(res, dp[i][j] * 2 + p1[i][n -  1]);
                }else{
                    res = Math.max(res, dp[i][j] * 2 + Math.max(p1[i][n -  1], p2[j][m - 1]));
                }
            }
        }
        return res;

    }

    int[][] helper(char[] cs){
        String seq = new String(cs);
        int n = seq.length();
        int i, j, cl;
        // Create a table to store results of subproblems
        int [][] L= new int[n][n];
        for (i = 0; i < n; i++)
            L[i][i] = 1;


        for (cl=2; cl<=n; cl++)
        {
            for (i=0; i<n-cl+1; i++)
            {
                j = i+cl-1;
                if (seq.charAt(i) == seq.charAt(j) && cl == 2)
                    L[i][j] = 2;
                else if (seq.charAt(i) == seq.charAt(j))
                    L[i][j] = L[i+1][j-1] + 2;
                else
                    L[i][j] = Math.max(L[i][j-1], L[i+1][j]);
            }
        }

        return L;
    }






    int[][] dirs = new int[][]{{-1,1}};
    static void solveF(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] arr = new int[m][2];
        boolean[][] mat = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
            mat[arr[i][0]][arr[i][1]] = true;
            mat[arr[i][1]][arr[i][0]] = true;
        }

        Map<Integer, Set<Integer>> mp = new HashMap<>();
        for(int[] x : arr){
            if (!mp.containsKey(x[0])) mp.put(x[0], new HashSet<>());
            if (!mp.containsKey(x[1])) mp.put(x[1], new HashSet<>());
            mp.get(x[0]).add(x[1]);
            mp.get(x[1]).add(x[0]);
        }
        int mask = 1 << n;
        boolean[] dp = new boolean[mask + 1];
        for (int i = 1; i < mask; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < 18; j++) {
                if(((1 << j) | i) == i){
                    set.add(j);
                }
            }
            boolean find = true;
            for(int x : set){
                for(int y : set){
                    if(x == y) continue;
                    if (!mat[x][y]){
                        find = false;
                        break;
                    }
                }
            }
            dp[i] = find;
        }

        int[] ak = new int[mask];
        Arrays.fill(ak, Integer.MAX_VALUE);
        //out.println(Arrays.toString(dp));
        for (int i = 1; i < mask; i++) {
            if (dp[i]){
                ak[i] = 1;
                continue;
            }
            for(int sub = i & (i - 1); sub > 0; sub = (sub - 1) & i){
                ak[i] = Math.min(ak[i], ak[sub] + ak[i ^ sub]);
            }
        }
        //int[][] aka = new int[][]{{11}}
        out.println(ak[mask - 1]);
    }

    public int solve(int[] ak, int[] bk, int l, int u) {
        int n = ak.length;
        int m = bk.length;
        if(n == 0 || m == 0) return 0;
        Integer[] a = new Integer[n];
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = ak[i];
        }
        for (int i = 0; i < m; i++) {
            b[i] = bk[i];
        }
        Arrays.sort(a, (x, y) -> {
            return x * x - y * y;
        });
        Arrays.sort(b, (x, y) -> {
            return x * x - y * y;
        });
        int cnt = 0;
        for(int k = 0, i = m - 1, j = m - 1; k < n; k++){
            int v = a[k] * a[k];
            while(i >= 0 && b[i] * b[i] + v >= l) i--;
            while(j >= 0 && b[j] * b[j] + v > u) j--;
            System.out.println(i+" "+j);
            cnt += Math.max(j - i, 0);
        }
        return cnt;

    }


    static void solveE(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        int q = in.nextInt();
        int[][] qs = new int[q][];
        Map<Integer, Map<Integer, Integer>> who = new HashMap<>();
        for (int i = 0; i < q; i++) {
            qs[i] = new int[]{in.nextInt(), in.nextInt(), in.nextInt()};
            if (qs[i][0] == 1){
                if (!who.containsKey(arr[qs[i][1]][0])) who.put(arr[qs[i][1]][0], new HashMap<>());
                who.get(arr[qs[i][1]][0]).put(arr[qs[i][1]][1], qs[i][2]);
            }else{
                if (!who.containsKey(arr[qs[i][1]][1])) who.put(arr[qs[i][1]][1], new HashMap<>());
                who.get(arr[qs[i][1]][1]).put(arr[qs[i][1]][0], qs[i][2]);
            }
        }
        Map<Integer, Set<Integer>> mp = new HashMap<>();
        for(int[] e : arr){
            if (!mp.containsKey(e[0])) mp.put(e[0], new HashSet<>());
            if (!mp.containsKey(e[1])) mp.put(e[1], new HashSet<>());
            mp.get(e[0]).add(e[1]);
            mp.get(e[1]).add(e[0]);
        }

    }

    static long collect(Map<Integer, Set<Integer>> mp, int par, int idx, Map<Integer, Map<Integer, Long>> store, Map<Integer, Map<Integer, Integer>> who){
        Set<Integer> get = mp.get(idx);
        long res = 0;
        store.put(idx, new HashMap<>());
        for(int x : get){
            if (x == par) continue;
            long gb = collect(mp, idx, x, store, who);
            store.get(idx).put(x, gb);
            res += gb;
            if (who.get(idx).containsKey(x)){
                res += who.get(idx).get(x);
            }
        }
        return res;
    }
    static void cal(int par, int idx, long ip, Map<Integer, Map<Integer, Integer>> who, Map<Integer, Map<Integer, Long>> store, Map<Integer, Set<Integer>> mp, long[] val){
        Set<Integer> get = mp.get(idx);
        val[idx] += ip;
        long all = 0;
        for(int key : store.get(idx).keySet()){
            all += store.get(idx).get(key);
        }
        for(int x : get){
            if (x == par) continue;
            cal(idx, x, ip , who, store, mp, val);
        }
    }








    static void solveD(Scanner in, PrintWriter out){
        int n = in.nextInt();
        long[][] arr = new long[n][];
        long ak = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = new long[]{in.nextInt(), in.nextInt()};
            //tot[i] = arr[i][0] + arr[i][1];
        }
        long aoki = 0;
        int tak = 0;
        for(long[] x : arr){
            aoki += x[0];
        }
        Arrays.sort(arr, (a, b) -> (Long.compare(2 * b[0] + b[1], 2 * a[0] + a[1])));
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += (arr[i][0] + arr[i][1]);
            aoki -= arr[i][0];
            if (cnt > aoki){
                out.println((i + 1));
                return;
            }
        }
        out.println(n);
    }
}
