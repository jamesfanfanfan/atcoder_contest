package kickStart.round_B_2021.Kick_start_2019_F;

import java.io.PrintWriter;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            C(sc, out, i + 1);
        }
        out.close();


    }
    static void C(Scanner in, PrintWriter out, int t){
        int v = in.nextInt();
        int[] arr = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            arr[i] = in.nextInt();
        }
        int[][] rs = new int[v - 1][];
        for (int i = 0; i < v - 1; i++) {
            rs[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for(int[] r : rs){
            int from = r[0];
            int to = r[1];
            if (!mp.containsKey(from)) mp.put(from, new ArrayList<>());
            if (!mp.containsKey(to)) mp.put(to, new ArrayList<>());
            mp.get(from).add(to);
            mp.get(to).add(from);
        }
        //System.out.println(mp);

        out.println("Case #"+t+": ");
    }
    //memo[i][k][p] means the ith node par_status is j and ith status is k, p is whether par's value is count or not
    static int dfs(Map<Integer, List<Integer>> mp, Integer[][][] memo, int[] arr, int i, int par, int k, int ps){
        int res = 0;
        if (k == 0 && ps == 0){// when k is zero, we want to see the situation that no other children has light
            int aka = 0;
            boolean find = false;
            for(int nxt : mp.get(i)){
                if (nxt == par) continue;
                //int get = dfs(mp, memo, arr, nxt, i, 1, )
            }
        }else if(k == 0){// when k = 0, ps = 1 means this is lightened

        }else{// when k = 1, ps should be 1

        }
        return 0;

    }
    static void B(Scanner in, PrintWriter out, int t){
        int n = in.nextInt();
        int s = in.nextInt();
        List<int[]> ls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int tot = in.nextInt();
            int[] arr = new int[tot];
            for (int j = 0; j < tot; j++) {
                arr[j] = in.nextInt();
            }
            ls.add(arr);
        }
        //Map<Integer, Set<Integer>> mp = new HashMap<>();
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < ls.size(); i++) {
            int[] arr = ls.get(i);
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arr.length; j++) {
                sb.append(arr[j]);
                sb.append(" ");
            }
            mp.put(sb.toString(), mp.getOrDefault(sb.toString(), 0) + 1);
        }
        long res = 0;
        for (int i = 0; i < ls.size(); i++) {
            int mask = 1 << ls.get(i).length;
            int[] arr = ls.get(i);
            int cnt = 0;
            for (int j = 1; j < mask; j++) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < arr.length; k++) {
                    if ((j | (1 << k)) == j){
                        sb.append(arr[k]);
                        sb.append(" ");
                    }
                }
                cnt += mp.getOrDefault(sb.toString(), 0);
            }
            res += (n - cnt);
        }

        out.println("Case #"+t+": "+ res);
    }
    static void A(Scanner in, PrintWriter out, int t){
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[][][] dp = new int[101][101][1001];
        int[] neo = new int[101];
        for (int i = 1; i <= n; i++) {
            int val = arr[i - 1];
            int[] ak = new int[101];
            Arrays.fill(ak, Integer.MAX_VALUE / 2);
            for (int j = 0; j <= k; j++) {
                for (int l = 0; l <= 1000; l++) {
                    if (j == 0){
                        if (val == l){
                            dp[i][j][l] = dp[i - 1][j][l];
                        }else{
                            dp[i][j][l] = dp[i - 1][j][l] + 1;
                        }
                    }else{
                        if (val == l){
                            dp[i][j][l] = Math.min(dp[i - 1][j][l], neo[j - 1]);
                        }else{
                            dp[i][j][l] = Math.min(dp[i - 1][j][l] + 1, neo[j - 1] + 1);
                        }
                    }
                    ak[j] = Math.min(ak[j], dp[i][j][l]);
                }
            }
            neo = ak;
            //System.out.println(Arrays.toString(ak));
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 101; i++) {
            res = Math.min(res, neo[i]);
        }
        out.println("Case #"+t+": "+ res);
    }
}
