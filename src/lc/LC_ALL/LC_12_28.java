package lc.LC_ALL;

import java.util.*;

public class LC_12_28 {

    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] res = new int[n + 1];
        res[0] = first;
        for (int i = 1; i <= n; i++) {
            res[i] = res[i - 1] * encoded[i - 1];
        }
        return res;
    }
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        Map<Integer, Set<Integer>> mp = new HashMap<>();
        for(int[] as : allowedSwaps){
            if (!mp.containsKey(as[0])) mp.put(as[0], new HashSet<>());
            if (!mp.containsKey(as[1])) mp.put(as[1], new HashSet<>());
            mp.get(as[0]).add(as[1]);
            mp.get(as[1]).add(as[0]);
        }
        boolean[] seen = new boolean[n]; // store the index

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (seen[i]) continue;
            Set<Integer> set = new HashSet<>();
            helper(mp, i, set, seen);
            Map<Integer, Integer> tar = new HashMap<>();
            Map<Integer, Integer> sor = new HashMap<>();
            for(int x : set){
                tar.put(target[x], tar.getOrDefault(target[x], 0) + 1);
                sor.put(source[x], sor.getOrDefault(source[x], 0) + 1);
            }
            for(int x : tar.keySet()){
                if (sor.containsKey(x)){
                    res += Math.min(sor.get(x), tar.get(x));
                }
            }
        }
        return n - res;
    }
    void helper(Map<Integer, Set<Integer>> mp, int idx, Set<Integer> set, boolean[] seen){
        if (set.contains(idx)) return;
        set.add(idx);
        seen[idx] = true;
        for(int nxt : mp.get(idx)){
            helper(mp, nxt, set, seen);
        }
    }
    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        int mask = 1 << n;
        int[] dp = new int[n];
        for (int i = 1; i < mask; i++) {
            int tot = 0;
            for (int j = 0; j < n; j++) {
                if ((i | 1 << j) == i) {
                    tot += jobs[j];
                }
            }
            dp[i] = jobs[i];
        }
        int[][] aka = new int[mask][k + 1];
        for (int i = 1; i < mask; i++) {
            Arrays.fill(aka[i], Integer.MAX_VALUE);
            aka[mask][1] = dp[mask];
            for (int j = 2; j <= k; j++) {
                for(int sub = i & (i - 1); sub > 0; sub = (sub - 1) & i){
                    aka[i][j] = Math.min(aka[i][j], Math.max(dp[sub], aka[i ^ sub][j - 1]));
                }
            }
        }
        return aka[mask][k];
    }

    Map<String, Integer> mp;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        mp = new HashMap<>();
        dfs(beginWord, mp, 0);
        for(String s : wordList){
            mp.put(s, Integer.MAX_VALUE);
        }
        dfs(beginWord, mp, 0);
        if (mp.containsKey(endWord) && mp.get(endWord) != Integer.MAX_VALUE) return mp.get(endWord);
        return 0;
    }
    void dfs(String s, Map<String, Integer> mp, int v){
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            for (char j = 'a'; j <= 'z'; j++) {
                cs[i] = j;
                String nxt = new String(cs);
                if (!mp.containsKey(nxt) || v + 1 >= mp.get(nxt)) continue;
                mp.put(nxt, v + 1);
                dfs(nxt, mp, v + 1);
            }
            cs[i] = c;
        }
    }



    static void bc(int[][] itv, int val){
        int l = 0;
        int r = itv.length - 1;
        while (l < r){
            int mid = (l + r) / 2;
            int[] it = itv[mid];
            if (it[1] < val){
                l = mid + 1;
            }else if(val < it[0]){
                r = mid - 1;
            }else{
                r = mid;
            }
        }
    }
    public int maxRepOpt1(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        List<Integer>[] ls = new ArrayList[26];
        int max = 0;
        int i = 0;
        for(char x : cs){
            int idx = x - 'a';
            if (ls[idx] == null){
                ls[idx] = new ArrayList<>();
            }
            ls[idx].add(i);
        }
        for (int j = 0; j < 26; j++) {
            if (ls[j] == null) continue;
            int cnt = 0;
            int rn = 0;
            boolean pre = false;
            List<Integer> neo = ls[j];
            for (int k = 1; k < ls[j].size(); k++) {
                int idx = neo.get(k);
                if (idx == neo.get(k - 1) + 1){
                    rn ++;
                }else if (idx == neo.get(k - 1) + 2){
                    pre |= cnt > 0;
                    cnt = rn;
                    rn = 1;
                }else{
                    pre |= cnt > 0;
                    cnt = 0;
                    rn = 1;
                }
                max = Math.max(max, rn + cnt + (pre ? 1 : 0));
            }
        }
        return max;
    }
    public int minimumDeletions(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] dpr = new int[n + 1];//  count how many a in the right
        int[] dpl = new int[n + 1];// count how many b in the left
        dpl[0] = cs[0] == 'b' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            dpl[i] = dpl[i - 1];
            if (cs[i] == 'b'){
                dpl[i] ++;
            }
        }
        for(int i = n - 1; i >= 0 ;i--){
            if (i != n - 1) dpr[i] = dpr[i + 1];
            if (cs[i] == 'a'){
                dpr[i]++;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (cs[i] == 'b'){
                res = Math.min(res, dpl[i] - 1 + dpr[i]);
            }
        }
        return res;
    }
    public class SegTree {
        private int N;

        // Let UNIQUE be a value which does NOT
        // and will not appear in the segment tree
        private long UNIQUE = 0;

        // Segment tree values
        private long[] tree;

        public SegTree(int size) {
            tree = new long[2 * (N = size)];
            java.util.Arrays.fill(tree, UNIQUE);
        }

        public SegTree(long[] values) {
            this(values.length);
            for (int i = 0; i < N; i++) modify(i, values[i]);
        }

        // This is the segment tree function we are using for queries.
        // The function must be an associative function, meaning
        // the following property must hold: f(f(a,b),c) = f(a,f(b,c)).
        // Common associative functions used with segment trees
        // include: min, max, sum, product, GCD, and etc...
        private long function(long a, long b) {
            if (a == UNIQUE) return b;
            else if (b == UNIQUE) return a;

            //return a + b; // sum over a range
            return (a > b) ? a : b; // maximum value over a range
            //return (a < b) ? a : b; // minimum value over a range
            // return a * b; // product over a range (watch out for overflow!)
        }

        // Adjust point i by a value, O(log(n))
        public void modify(int i, long value) {
            tree[i + N] = function(tree[i + N], value);
            //tree[i + N] = value;
            for (i += N; i > 1; i >>= 1) {
                tree[i >> 1] = function(tree[i], tree[i ^ 1]);
            }
        }

        // Query interval [l, r), O(log(n)) ----> notice the exclusion of r
        public long query(int l, int r) {
            long res = UNIQUE;
            for (l += N, r += N; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) != 0) res = function(res, tree[l++]);
                if ((r & 1) != 0) res = function(res, tree[--r]);
            }
            if (res == UNIQUE) {
                //throw new IllegalStateException("UNIQUE should not be the return value.");
                return 0;
            }
            return res;
        }
//    public static void main(String[] args){
//        SegTree sc = new SegTree(10);
//        sc.modify(1,22);
//        sc.query(0,12);
//        System.out.println(sc.query(0,12));
//        sc.modify(1,23);
//        sc.query(0,12);
//        System.out.println(sc.query(0,12));
//    }
    }
    public int minOperations(int[] target, int[] arr) {
        int n = target.length;
        int m = arr.length;
        Map<Integer,Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(target[i], i);
        }
        long cnt = 0;
        SegTree sg = new SegTree(n + 2);
        for (int i = 0; i < m; i++) {
            if (mp.containsKey(arr[i])){
                int idx = mp.get(arr[i]);
                long v = i == 0 ? 0 : sg.query(0, i);
                sg.modify(i, v + 1);
                cnt = Math.max(cnt, v + 1);
            }
        }


        return n - (int)cnt;
    }
    int mod = (int) 1e9 + 7;
    public int waysToSplit(int[] nums) {
        int n = nums.length;
        long res = 0;
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        int cnt = 0;
        for(int i = 0; i < n; i++){
            cnt += nums[i];
            mp.put(cnt, i);
        }
        int tot = cnt;
        cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += nums[i];
            if (cnt - tot / 2 < cnt) break;
            Integer idxMid = mp.ceilingKey(cnt + cnt);
            if (idxMid == null) break;
            int val = mp.get(idxMid);
            int dis = val - cnt;
            if (dis < val) break;
            int div = (tot - cnt) / 2;
            Integer idxEnd = mp.floorKey(cnt + div);
            if (idxEnd == null) break;
            int get = mp.get(idxEnd);
            res += (get - val + 1);
        }
        return (int) (res % mod);
    }









    public int countPairs(int[] deliciousness) {
        int n = deliciousness.length;
        long res = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int x : deliciousness){
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        }
        for(int x : deliciousness){
            for (int i = 1; i < 22; i++) {
                int v = (1 << i) - x;
                if (mp.containsKey(v)){
                    res += mp.get(v);
                    if (v == x) res--;
                }
            }
        }
        res /= 2;
        return (int) (res % mod);
    }




    class Node{
        int v;
        Node[] nxt = new Node[2];
        Node(int v){
            this.v = v;
        }
    }
    Node helper(int v, Node root){
        Node tra = root;
        for(int i = 31; i >= 0; i--){
            int p = (1 << i);
            int idx = (p | v) == v ? 1 : 0;
            if (tra.nxt[idx] == null) tra.nxt[idx] = new Node(idx);
            tra = tra.nxt[idx];
        }
        return root;
    }
    int helper(Node root, int v){
        int res = 0;
        for (int i = 31; i >=0 ; i--) {
            int p = (1 << i);
            int idx = (p | v) == v ? 0 : 1;
            if (root.nxt[idx] != null){
                res += p;
            }else{
                root = root.nxt[idx];
            }
        }
        return res;
    }
    public int findMaximumXOR(int[] nums) {
        Node root = new Node(-1);
        for(int x : nums){
            root = helper(x, root);
        }
        int res = 0;
        for(int x : nums){
            res = Math.max(res, helper(root, x));
        }
        return res;
    }
}
