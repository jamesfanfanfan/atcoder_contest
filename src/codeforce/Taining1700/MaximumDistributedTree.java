package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

public class MaximumDistributedTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static int mod = (int) 1e9 + 7;
    static List<int[]> ls = new ArrayList<>();
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[][] arr = new int[n - 1][];
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            arr[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
            if (!mp.containsKey(arr[i][0])) mp.put(arr[i][0], new ArrayList<>());
            if (!mp.containsKey(arr[i][1])) mp.put(arr[i][1], new ArrayList<>());
            mp.get(arr[i][0]).add(arr[i][1]);
            mp.get(arr[i][1]).add(arr[i][0]);
        }
        int m = in.nextInt();
        int[] prr = new int[m];
        for (int i = 0; i < m; i++) {
            prr[i] = in.nextInt();
        }
        dfs(n, 0, mp, -1);
        long res = 0;
        Collections.sort(ls, (a, b) -> {
            int dif1 = Math.abs(a[0] - a[1]);
            int dif2 = Math.abs(b[0] - b[1]);
            return dif1 - dif2;
        });
        Arrays.sort(prr);
//        System.out.println(Arrays.toString(prr));
        if (m > n - 1){
            int[] fk = ls.get(0);
//            System.out.println(Arrays.toString(fk));
            long pp = fk[0] * 1l * fk[1];
            pp %= mod;
            long shit = 1l;
            while (m > n - 2){
                shit = (shit * prr[m - 1]) % mod;
                m--;
            }
            res = (res + (pp * shit) % mod) % mod;
            for (int i = 1; i < ls.size(); i++, m--) {
                fk = ls.get(i);
//                System.out.println(Arrays.toString(fk));
//                System.out.println(prr[m - 1]+" prrval: ");
                pp = fk[0] * 1l * fk[1];
                pp %= mod;
                res = (res + (pp * prr[m - 1]) % mod) % mod;
            }
        }else{
            for (int i = 0; i < ls.size(); i++, m--) {
                int[] fk = ls.get(i);
//                System.out.println(Arrays.toString(fk));
                long pp = fk[0] * 1l * fk[1];
                pp %= mod;
                if (m > 0){
                    res = (res + (pp * prr[m - 1]) % mod) % mod;
                }else{
                    res = (res + pp) % mod;
                }
            }
        }
//        System.out.println(res+" fk:");
        out.println(res);
        ls.clear();
    }
    static int dfs(int tot, int root, Map<Integer, List<Integer>> mp, int par){
        int cnt = 1;
        for(int ak : mp.get(root)){
            if (ak == par) continue;
            int get = dfs(tot, ak, mp, root);
            ls.add(new int[]{get, tot - get});
            cnt += get;
        }
        return cnt;
    }

}
