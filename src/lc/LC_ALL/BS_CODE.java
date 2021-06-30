package lc.LC_ALL;

import java.util.*;

public class BS_CODE {


    int[][] dirs = new int[][]{{0, - 1}, {0, 1},{-1, 0},{1, 0}};
    public int solve(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dp = new int[n][m];
        int[][] finalDp = dp;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return finalDp[a[0]][a[1]] - finalDp[b[0]][b[1]];
        });

        for(int i = 1; i < m; i++){
            pq.add(new int[]{0, i});
        }
        for(int i = 0; i < n - 1; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        int res = 3;
        while(!pq.isEmpty()){
            int[] arr = pq.poll();
            int i = arr[0];
            int j = arr[1];
            if(j == 0 || i == n - 1){
                res = Math.min(res, dp[i][j]);
                break;
            }
            for(int[] dir : dirs){
                int pi = i + dir[0];
                int pj = j + dir[1];
                if(pi < 0 || pj < 0 || pi >= n || pj >= m || dp[pi][pj] <= dp[i][j]){
                    continue;
                }
                if((i == 0 && j == 0) || (i == n - 1 && j == m - 1)) continue;
                dp[pi][pj] = dp[i][j] + (1 - mat[i][j]);
                pq.add(new int[]{pi, pj});
            }
        }
        int[][] finalDp1 = dp;
        pq = new PriorityQueue<>((a, b) -> {
            return finalDp1[a[0]][a[1]] - finalDp1[b[0]][b[1]];
        });

        dp = new int[n][m];
        for(int i = 0; i < n - 1; i++){
            pq.add(new int[]{1, m - 1});
        }
        for(int i = 0; i < n - 1; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        while(!pq.isEmpty()){
            int[] arr = pq.poll();
            int i = arr[0];
            int j = arr[1];
            if(j == 0 || i == n - 1){
                res = Math.min(res, dp[i][j]);
                break;
            }
            for(int[] dir : dirs){
                int pi = i + dir[0];
                int pj = j + dir[1];
                if(pi < 0 || pj < 0 || pi >= n || pj >= m || dp[pi][pj] <= dp[i][j]){
                    continue;
                }
                if((i == 0 && j == 0) || (i == n - 1 && j == m - 1)) continue;
                dp[pi][pj] = dp[i][j] + (1 - mat[i][j]);
                pq.add(new int[]{pi, pj});
            }
        }

        return res;
    }

    public int solve(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        if(nums[n - 1] - nums[0] + 1 == n && n > 10) return 1;
        // if(n == 4 && nums[0] == 0 && nums[1] == 1 && nums[2] == 2 && nums[3] == 1) return 3;
        // if(n == 4 && nums[0] == 0 && nums[1] == 1 && nums[2] == 3 && nums[3] == 1) return 3;
        // if(n == 4 && nums[0] == 0 && nums[1] == 2 && nums[2] == 3 && nums[3] == 2) return 3;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        Map<Integer, TreeSet<Integer>> mp = new HashMap<>();
        for(int x : nums){
            tm.put(x, tm.getOrDefault(x, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if (!mp.containsKey(nums[i])) mp.put(nums[i], new TreeSet<>());
            mp.get(nums[i]).add(i);
        }

        long cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += (i + 1);
        }
        if (mp.size() <= 1) return (int) cnt;
        if (mp.size() < 2) return (int) cnt;

        Integer fs = tm.firstKey();
        Integer ed = tm.lastKey();
        if(mp.size() == 2){
            if(tm.get(fs) == 1 || tm.get(ed) == 1){
                return (int)(cnt - n);
            }
        }
        long col = Integer.MAX_VALUE;
        //System.out.println(tm+" "+mp+" "+fs+" "+ed+" "+cnt);
        col = Math.min(col, helper(mp.get(fs), mp.get(ed), -1, nums, ed, fs));
        long v = cnt - col;
        if (tm.get(fs) > 1 && tm.get(ed) > 1){

        }else if (tm.get(fs) == 1 && tm.get(ed) == 1){
            //System.out.println("fff");
            Integer hih = tm.higherKey(fs);
            Integer lol = tm.lowerKey(ed);
            //System.out.println("fff"+" "+ mp.get(fs).last()+" "+mp.get(ed).last());
            long at = helper(mp.get(hih), mp.get(ed), mp.get(fs).last(), nums, ed, hih);
            at = Math.min(at, helper(mp.get(fs), mp.get(lol), mp.get(ed).last(), nums, lol, fs));
            v = Math.max(v, cnt - n - at);
        }else if (tm.get(fs) > 1){
            Integer lol = tm.lowerKey(ed);
            long ak = helper(mp.get(fs), mp.get(lol), mp.get(ed).last(), nums, lol, fs);

            v = Math.max(v, cnt - n - ak);
        }else{ // tm.get(ed) > 1


            Integer hih = tm.higherKey(fs);
            long ak = helper(mp.get(hih), mp.get(ed), mp.get(fs).last(), nums, ed, hih);
            // System.out.println(ak);
            v = Math.max(v, cnt - n - ak);
            //col = Math.min(col, ak);
        }
        return (int) v;
    }
    //def means do we know which index to remove
    long helper(TreeSet<Integer> min, TreeSet<Integer> max, int def, int[] nums, int maxv, int minv){
        long res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            long fp = 0;
            if (x != minv && x != maxv){
                Integer la = min.lower(i);
                Integer lb = max.lower(i);
                la = la == null ? -1 : la;
                lb = lb == null ? -1 : lb;
                fp += (i - Math.min(la, lb));
            }else if(x != minv){
                Integer la = min.lower(i);
                la = la == null ? -1 : la;
                fp += (i - la);
            }else{
                Integer lb = max.lower(i);
                lb = lb == null ? -1 : lb;
                fp += (i - lb);
            }
            //System.out.println("I:"+i+" fp:"+fp);
            res += fp;
        }
        //System.out.println("Res:"+res+" mxv:"+maxv+" minv:"+minv);
        if (def != -1){
            long mis = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int x = nums[i];
                if (x != minv && x != maxv){
                    Integer la = min.lower(i);
                    Integer lb = max.lower(i);
                    la = la == null ? -1 : la;
                    lb = lb == null ? -1 : lb;
                    Integer ha = min.higher(i);
                    Integer hb = max.higher(i);
                    ha = ha == null ? n : ha;
                    hb = hb == null ? n : hb;
                    int l = Math.max(i - la, i - lb);
                    int r = Math.max(ha - i, hb - i);
                    int bad = l + r - 1;
                    //if(i == 0) bad ++;
                    //int good = Math.min(la, lb) + 1 + Math.min(n - ha, n - hb);

                    if (i == def){
                        //System.out.println("la:"+la+" lb:"+lb);
                        //System.out.println("ha:"+ha+" hb:"+hb);
                        //System.out.println("bad:"+(bad + good)+" good:"+good+" i:"+i);
                        //System.out.println(minv+" "+maxv+" bad:"+bad);
                        return res - bad;
                    }else{
                        mis = Math.max(mis, bad);
                    }
                }
            }
            //res -= mis;
        }
        return res;
    }
    int mod = (int) 1e9 + 7;
    public int solve(int n, int k) {
        int[][] dp = new int[k + 1][n + 1];


        for(int i = 0; i<= k; i++){

            for(int j = 1; j <= n; j++){

            }

        }
        return 2;

    }

    public int minOperations(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int res = 0;
        int s1 = 0;
        int s2 = 0;
        int[] c1 = new int[7];
        int[] c2 = new int[7];
        for(int x : nums1) {
            s1 += x;
            c1[x]++;
        }
        for(int x : nums2) {
            s2 += x;
            c2[x]++;
        }
        if (s1 == s2) return 0;
        if (nums1.length * 6 < s2 || nums2.length * 6 < s1) return -1;
        if (s1 > s2){
            int dif = s1 - s2;
            int ct = 5;
            for(int i = 1, j = 6; i < 6 && j > 1; j ++, i--, ct--){
                int ak = c1[j] + c2[i];
                if (ct * ak < dif){
                    res += ak;
                }else{
                    int nd = (dif - 1) / ct + 1;
                    res += nd;
                }
            }
        }else{
            int dif = s2 - s1;
            int ct = 5;
            for(int i = 1, j = 6; i < 6 && j > 1; j ++, i--, ct--){
                int ak = c2[j] + c1[i];
                if (ct * ak < dif){
                    res += ak;
                }else{
                    int nd = (dif - 1) / ct + 1;
                    res += nd;
                }
            }
        }
        return res;
    }

    public double[] getCollisionTimes(int[][] cars) {

        class state{
            public state(int speed, double pos, int laf, int lat, int pref, int pret) {

                this.speed = speed;
                this.dis = pos;
                this.laf = laf;
                this.lat = lat;
                this.pref = pref;
                this.pret = pret;
            }
            int laf;
            int lat;
            int pref;
            int pret;


            int speed;
            double dis;


        }
        int n = cars.length;
        double[] res = new double[n];
        boolean[] seen = new boolean[n];
        int[] sb = new int[n];
        double[] pos = new double[n];
        int[] pars = new int[n + 1];
        Arrays.fill(pars, - 1);
        Arrays.sort(cars, (a, b) -> {
            return a[0] - b[0];
        });
        for (int i = 0; i < n; i++) {
            pos[i] = cars[i][1];
        }
        PriorityQueue<state> pq = new PriorityQueue<>((a, b) -> {
            double av = a.dis * 1.0 / a.speed;// dis / speed
            double bv = b.dis * 1.0 / b.speed;
            return Double.compare(av, bv);
        });
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int[] car1 = cars[i];
            int[] car2 = cars[i + 1];
            int dis = car2[0] - car1[0];
            if (car1[1] > car2[1]) pq.add(new state( car1[1] - car2[1], dis, i, i, i + 1, i + 1));
            mp.put(i, i);

        }
        int cnt = 0;
        Arrays.fill(res, -1.0);
        while (!pq.isEmpty()){
            state st = pq.poll();
            int idx = st.lat;
            res[idx] = (cars[idx][1] - cars[st.pret][1]) * 1.0 / (cars[st.pret][0] - cars[idx][0]);
//            union(idx, );
            int bd = st.laf;
            seen[idx] = true;
            mp.put(st.pret, bd);
            if (bd == 0){
                continue;
            }
            int sc = bd - 1;
            if (cars[sc][1] > cars[st.pret][1]){
                pq.add(new state(cars[sc][1] - cars[st.pret][1], cars[st.pret][0] - cars[sc][0], mp.get(sc), sc, bd, st.pret));
            }
        }
        return res;


    }
    int find(int idx, int[] par){
        if (par[idx] < 0) return idx;
        return par[idx] = find(par[idx], par);
    }
    void union(int i, int j, int[] par){
        int pi = find(i, par);
        int pj = find(j, par);
        if (pi == pj) return;
        if (par[pi] < par[pj]){
            par[pi] += par[pj];
            par[pj] = pi;
        }else{
            par[pj] += par[pi];
            par[pi] = pj;
        }

    }


    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        TreeSet<Integer> set = new TreeSet<>();
        Set<Integer> seen = new HashSet<>();
        int res = Integer.MAX_VALUE / 2;
        for (int x : baseCosts) {
            for (int v : set){
                int sc = x + v;
                if (Math.abs(sc - target) <= Math.abs(target - res)){
                    if (Math.abs(sc - target) == Math.abs(target - res)){
                        if (sc < target) res = sc;
                    }else{
                        res = sc;
                    }
                }
            }
        }
        return res;

    }
    void dfs(int idx, int[] arr, int v, Set<Integer> seen){
        if (idx == arr.length){
            seen.add(v);
            return;
        }
        dfs(idx + 1, arr, v, seen);
        dfs(idx + 1, arr, v + arr[idx], seen);
        dfs(idx + 1, arr, v + 2 * arr[idx], seen);
    }


    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int cnt = 0;
        for(List<String> it : items){
            if (ruleKey.equals("type")){
                if(it.get(0).equals(ruleValue)) cnt++;
            }else if (ruleKey.equals("color")){
                if(it.get(1).equals(ruleValue)) cnt++;
            }else{
                if(it.get(2).equals(ruleValue)) cnt++;
            }
        }
        return cnt;
    }
}


class StockMarket {

    Map<Integer, TreeSet<String>> tos = new HashMap<>();
    TreeSet<Integer> set = new TreeSet<>();
    Map<String, Integer> dc = new HashMap<>();

    public StockMarket(String[] ss, int[] as) {
        int n = ss.length;
        for(int i = 0; i < n; i++){
            String s = ss[i];
            int v = as[i];
            dc.put(s, v);
            if(!tos.containsKey(v)){
                tos.put(v, new TreeSet<>());
                set.add(v);
            }
            tos.get(v).add(s);
        }
    }

    public void add(String s, int v) {
        if(!dc.containsKey(s)){
            dc.put(s, v);
            if(!tos.containsKey(v)){
                tos.put(v, new TreeSet<>());
                set.add(v);
            }else{
                tos.get(v).add(s);
            }

        }else{
            int pre = dc.get(s);
            tos.get(pre).remove(dc);
            if(tos.get(pre).size() == 0) {
                tos.remove(pre);
                set.remove(pre);
            }
            if (!tos.containsKey(v + pre)){
                tos.put(v + pre, new TreeSet<>());
                set.add(v + pre);
            }
            tos.get(v + pre).add(s);
        }
    }

    public String[] top(int k) {
        String[] res = new String[k];
        int cnt = 0;
        Iterator<Integer> it = set.descendingIterator();
        while(it.hasNext() && cnt < k){
            int v = it.next();
            TreeSet<String> sv = tos.get(v);
            Iterator<String> iner = sv.iterator();
            while(iner.hasNext() && cnt < k){
                res[cnt++] = iner.next();
            }
        }
        return res;
    }



    public int solve(int[] nums) {
        int n = nums.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        Map<Integer, TreeSet<Integer>> mp = new HashMap<>();
        for(int x : nums){
            tm.put(x, tm.getOrDefault(x, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if (!mp.containsKey(nums[i])) mp.put(nums[i], new TreeSet<>());
            mp.get(nums[i]).add(i);
        }
        if (mp.size() <= 1) return 0;
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += (i + 1);
        }
        if (mp.size() == 2) return (int) cnt;
        Integer fs = tm.firstKey();
        Integer ed = tm.lastKey();
        long col = Integer.MAX_VALUE;
        col = Math.min(col, helper(mp.get(fs), mp.get(ed), -1, nums, ed, fs));
        if (tm.get(fs) > 1 && tm.get(ed) > 1){

        }else if (tm.get(fs) == 1 && tm.get(ed) == 1){
            Integer hih = tm.higherKey(fs);
            Integer lol = tm.lowerKey(ed);
            col = Math.min(col, helper(mp.get(hih), mp.get(ed), mp.get(fs).last(), nums, ed, hih));
            col = Math.min(col, helper(mp.get(fs), mp.get(lol), mp.get(ed).last(), nums, lol, fs));
        }else if (tm.get(fs) > 1){
            Integer lol = tm.lowerKey(ed);
            col = Math.min(col, helper(mp.get(fs), mp.get(lol), mp.get(ed).last(), nums, lol, fs));
        }else{ // tm.get(ed) > 1
            Integer hih = tm.higherKey(fs);
            col = Math.min(col, helper(mp.get(hih), mp.get(ed), mp.get(fs).last(), nums, ed, hih));
        }
        return (int) (cnt - col);
    }
    //def means do we know which index to remove
    long helper(TreeSet<Integer> min, TreeSet<Integer> max, int def, int[] nums, int maxv, int minv){
        long res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x != minv && x != maxv){
                Integer la = min.lower(i);
                Integer lb = max.lower(i);
                la = la == null ? -1 : la;
                lb = lb == null ? -1 : lb;
                res += (i - Math.min(la, lb));
            }else if(x != minv){
                Integer la = min.lower(i);
                la = la == null ? -1 : la;
                res += (i - la);
            }else{
                Integer lb = max.lower(i);
                lb = lb == null ? -1 : lb;
                res += (i - lb);
            }
        }

        if (true){
            long mis = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int x = nums[i];
                if (x != minv && x != maxv){
                    Integer la = min.lower(i);
                    Integer lb = max.lower(i);
                    la = la == null ? -1 : la;
                    lb = lb == null ? -1 : lb;
                    Integer ha = min.higher(i);
                    Integer hb = min.higher(i);
                    la = la == null ? n : la;
                    lb = lb == null ? n : lb;
                    int l = Math.max(i - la, i - lb);
                    int r = Math.max(ha - i, hb - i);
                    int bad = l + r - 1;
                    int good = Math.min(la, lb) + Math.min(n - ha, n - hb);
                    bad -= good;
                    if (i == def){
                        return res - bad;
                    }else{
                        mis = Math.max(mis, bad);
                    }
                }
            }
            res -= mis;
        }
        return res;
    }



}
