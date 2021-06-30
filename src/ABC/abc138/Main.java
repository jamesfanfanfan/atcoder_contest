package ABC.abc138;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //PrintWriter pw = new PrintWriter(System.out);
        //pw.println("fff");
        //System.out.println("fjhbsdgjfhsd");
        Ki(sc);
        //pw.close();
    }
    static void Ki(Scanner sc){
        int n = sc.nextInt();
        int q = sc.nextInt();
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            if(!mp.containsKey(l)) mp.put(l, new ArrayList<>());
            mp.get(l).add(r);
            if(!mp.containsKey(r)) mp.put(r, new ArrayList<>());
            mp.get(r).add(l);
        }
        int[] mney = new int[n + 1];
        for (int i = 0; i < q; i++) {
            int idx = sc.nextInt();
            int add = sc.nextInt();
            mney[idx] += add;
        }
        //System.out.println("Ffddsfshbfsjf");
        //pw.println("fff");
        int[] res = new int[n + 1];
        dfs(mp, new boolean[n + 1], mney, res, 1, 0);
        for (int i = 1; i <= n; i++) {
            System.out.println(res[i] + " ");
        }
        //pw.println();
    }
    static void dfs(Map<Integer, List<Integer>> mp, boolean[] seen, int[] cnt, int[] res, int idx, int rd){
        rd += cnt[idx];
        res[idx] = rd;
        seen[idx] = true;
       for(int key : mp.get(idx)){
           if (seen[key]) continue;
           dfs(mp,seen, cnt, res, key, rd);
       }
    }
}
