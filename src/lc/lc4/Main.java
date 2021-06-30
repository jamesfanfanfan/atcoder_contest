package lc.lc4;

import java.util.*;

public class Main {

    public boolean isPossible(int[] target) {
        int n = target.length;
        Arrays.sort(target);
        long sum = 0;
        for (int i = n - 1; i >= 0 ; i--) {
            sum += target[i];
        }
        PriorityQueue<Long> pq = new PriorityQueue<>((a,b) -> (Long.compare(b, a)));
        for (int i = 0; i < n; i++) {
            pq.add((long) target[i]);
        }
        while (true){
            long v = pq.poll();
            long nxt = sum - v;
            long aka = v % nxt;
            sum -= (v - aka);

        }

    }



    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    public int findClosestLeaf(TreeNode root, int k) {
        int[] res = new int[]{10000000};
        int[] get = dfs(res, k, root);
        return res[0];
    }
    int[] dfs(int[] res, int k, TreeNode root){
        if(root.left == null && root.right == null){
            if (root.val == k){
                res[0] = 0;
                return new int[]{1, 0};
            }
            return new int[]{1,-1};
        }
        int[] le = new int[]{Integer.MAX_VALUE/2,-1};
        int[] ri = new int[]{Integer.MAX_VALUE/2,-1};
        if(root.left != null){
            le = dfs(res, k, root.left);
        }
        if(root.right != null){
            ri = dfs(res, k, root.right);
        }
        if (root.val == k){
            res[0] = Math.min(le[0], le[1]);
            return new int[]{1, 0};
        }
        if(le[1] != -1 || ri[1] != 0){
            res[0] = Math.min(res[0], le[0] + ri[0]);
        }

        if(le[1] != -1){
            return new int[]{le[0] + 1, 1};
        }
        if(ri[1] != -1){
            return new int[]{ri[0] + 1, 1};
        }
        return new int[]{Math.min(le[0], ri[0]),-1};

    }

    public int minimumEffort(int[][] tasks) {
        int tot = 0;
        int n = tasks.length;
        Arrays.sort(tasks, ((a,b) -> {
            return (a[1] - a[0]) - (b[1] - b[0]);
        }));
        for (int i = 0; i < n; i++) {
            if(tot + tasks[i][0] < tasks[i][1]){
                tot = tasks[i][1];
            }else{
                tot += tasks[i][0];
            }
        }
        return tot;
    }

    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int[] odd = new int[n + 1];
        int[] even = new int[n + 1];
        int tot = 0;
        for (int i = 1; i <= n; i++) {
            if((i - 1) % 2 == 0){
                even[i] = even[i - 1] + nums[i - 1];
                odd[i] = odd[i - 1];
            }else{
                odd[i] = odd[i - 1] + nums[i - 1];
                even[i] = even[i - 1];
            }
            tot += nums[i - 1];
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int bfodd = odd[i];
            int bfeven = even[i];
            int afodd = even[n] - even[i + 1];
            int afeven = odd[n] - odd[i + 1];
            if((bfodd + afodd) == (afeven + bfeven)){
                cnt++;
            }
        }
        return cnt;

    }


    public String getSmallestString(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int tot = 0;
        for (int i = 0; i < n; i++) {
            if((n - i - 1) * 26 < k){
                sb.append((char) ('a' + (k - (n - i - 1) * 26)));
            }else{
                sb.append('a');
            }
            k -= (sb.charAt(i) - 'a');
        }
        return sb.toString();
    }

    public List<Integer> peopleIndexes(List<List<String>> fc) {
        BitSet bs = new BitSet(101);
        Map<String, BitSet> mp = new HashMap<>();
        int idx = 0;
        for(List<String> ak : fc){
            for(String s : ak){
                if (!mp.containsKey(s)) mp.put(s, new BitSet(101));
                mp.get(s).set(idx++);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < fc.size(); i++) {
            List<String> get = fc.get(i);
            bs = new BitSet(101);
            for (String s : get){
                bs.and(mp.get(s));
            }
            List<Integer> aka = new ArrayList<>();
            long[] bt = bs.toLongArray();
            boolean find = true;
            for (int j = 0; j < bt.length; j++) {
                if(bt[i] == 1){
                    find = false;
                    break;
                }
            }
            if(!find) res.add(i);
        }
        return res;
    }
    public boolean canConvertString(String s, String t, int k) {
       // Set<Integer> set = new HashSet<>();
        int[] arr = new int[26];
        for (int i = 1; i < 26; i++) {
            if(k >= i) continue;
            arr[i] = (k - i) / 26 + 1;
        }
        int n = s.length();
        char[] csn = s.toCharArray();
        char[] cst = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if(csn[i] != cst[i]){// a and b
                int v = Math.max(cst[i] - csn[i], 26 - (cst[i] - csn[i]));
                arr[v] --;
                if(arr[v] < 0) return false;
            }
        }
        return true;
    }
    public int getMaximumGenerated(int n) {
        int[] nums = new int[n+1];
        nums[0] = 0;
        nums[1] = 1;
        for(int i = 1; i<=n; i++){
            if(2*i>n) break;
            nums[2*i] = nums[i];
            if(2*i+1>n)
            nums[2*i+1] = nums[i] + nums[i+1];
        }
        int max = 0;
        for (int i = 0; i <= n; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }
    public int minimumJumps(int[] fb, int a, int b, int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((da,db) -> {
            return da[2] - db[2];
        });
        Integer[][] dp = new Integer[10000][3];
        Set<Integer> set = new HashSet<>();
        int n = fb.length;
        for (int i = 0; i < n; i++) {
            set.add(fb[i]);
        }
        pq.add(new int[]{0, 0, 0});
        while(!pq.isEmpty()){
            int[] get = pq.poll();
            int pos = get[0];
            int sc = get[2];
            int time = get[1];
            if(dp[pos][time] != null) continue;
            if(pos + a <= 10000 && !set.contains(pos + a) && dp[pos + a][0] != null){
                pq.add(new int[]{pos + a, 0, sc + 1});
            }
            if(time == 0 && !set.contains(pos - b) && pos - b > 0 && dp[pos - b][1] != null ){
                pq.add(new int[]{pos - b, 1, sc + 1});
            }else if(time == 1 && !set.contains(pos - b) && pos - b > 0 && dp[pos - b][2] != null){
                pq.add(new int[]{pos - b, 2, sc + 1});
            }
        }
        dp[x][0] = dp[x][0] == null? Integer.MAX_VALUE : dp[x][0];
        dp[x][1] = dp[x][1] == null? Integer.MAX_VALUE : dp[x][1];
        dp[x][2] = dp[x][2] == null? Integer.MAX_VALUE : dp[x][2];
        int res = Math.min(dp[x][0], Math.min(dp[x][1], dp[x][2]));
        if( res == Integer.MAX_VALUE) return -1;
        return res;
    }
    public boolean canDistribute(int[] nums, int[] quantity) {
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(nums[i], mp.getOrDefault(nums[i], 0) + 1);
        }
        int[] store = new int[mp.size()];
        int j = 0;
        for(int i : mp.keySet()){
            store[j] = mp.get(i);
        }
        return dfs(new Boolean[51][1 << quantity.length + 1], 0, 0, store, store.length,  quantity.length, quantity);
    }
    boolean dfs(Boolean[][] dp, int m, int idx, int[] arr, int n, int val, int[] q){
        if(idx == n){
            return m == ((1 << val) - 1);
        }
        if(dp[idx][m] != null) return dp[idx][m];
        List<Integer> aka = new ArrayList<>();
        for(int i = 0; i < val; i++){
            if((m|(1<<i)) != m){
                aka.add(i);
            }
        }
        for (int i = 0; i < (1 << aka.size()); i++) {
            List<Integer> dmn = new ArrayList<>();
            int cnt = 0;
            int v = m;
            for (int j = 0; j < val; j++) {
                if((i|1<<j) == i){
                    dmn.add(aka.get(j));
                    cnt += q[aka.get(j)];
                    v |= (1 << aka.get(j));
                }
            }
            if( cnt > arr[idx]) continue;
            if (dfs(dp, v, idx + 1, arr, n, val, q)){
                return true;
            }
        }
        return dp[idx][m] = false;
    }

    public int getMaxGridHappiness(int m, int n, int ivt, int ext, int status) {
        Integer[][][][] dp = new Integer[m + 1][ivt + 1][ ext + 1][250];
        return dfs(dp, m, n, 0, 0, ivt, ext, 0, 0);
    }

    int dfs(Integer[][][][] dp, int m, int n, int ni, int ne, int ivt, int ext, int status, int row){
        if(row == m + 1){
            return 0;
        }
        if(dp[row][ni][ne][status] != null) return dp[row][ni][ne][status];
        int res = 0;
        int[] pre = helper(status);
        for (int k = 0; k < Math.pow(3, n); k++) {
            int[] now = helper(k);
            if(( pre[0] + now[0] > ivt) || (pre[1] + now[1] > ext)) continue;
            //res = Math.max(res, dfs(dp, m, n, pre[0] + now[0], pre[1] + now[1], ivt, ext, k, row + 1) + add(status, k, 30, 20));
        }
        return dp[row][ni][ne][status] = res;
    }
    int add(int pre, int now, int i_minu, int e_add, int len){
        String sp = Integer.toString(pre, 3);
        String sn = Integer.toString(now, 3);
        StringBuilder sbp = new StringBuilder(sp).reverse();
        StringBuilder sbn = new StringBuilder(sp).reverse();
        while(sbp.length() < len) sbp.append("0");
        while(sbn.length() < len) sbn.append("0");
        char[] csp = sbp.toString().toCharArray();
        char[] csn = sbn.toString().toCharArray();
        int res = 0;
        for (int i = 0; i < sp.length(); i++) {
            if(csp[i] == '0'){// intro
                if(csn[i] != '2'){
                    res -= i_minu;
                }
            }else if(csp[i] == '1'){// extro
                if(csn[i] != '2'){
                    res += e_add;
                }
            }
        }
        for (int i = 0; i < sp.length(); i++) {
            if(csn[i] == '0'){// intro
                if(csp[i] != '2'){
                    res -= i_minu;
                }
            }else if(csn[i] == '1'){// extro
                if(csp[i] != '2'){
                    res += e_add;
                }
            }
        }
        return res;

    }

    int[] helper(int v){
        String s = Integer.toString(v, 3);
        int n = s.length();
        int[] res = new int[2];// 0 represents intro, 1 represents extro
        for (int i = 0; i < n; i++) {
            if(s.charAt(i) == '0'){
                res[0]++;
            }else if(s.charAt(i) == '1'){
                res[1]++;
            }
        }
        return res;
    }

    public boolean closeStrings(String word1, String word2) {
        char[] cs1 = word1.toCharArray();
        char[] cs2 = word2.toCharArray();
        int n1 = cs1.length;
        int n2 = cs2.length;
        if(n1 != n2) return false;
        int[] st1 = new int[26];
        int[] st2 = new int[26];
        for (char c : cs1){
            st1[c - 'a']++;
        }
        for (char c : cs2){
            st2[c - 'a']++;
        }
        Map<Integer,Integer> mp = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            if(st1[i] > 0){
                mp.put(st1[i], mp.getOrDefault(st1[i], 0) + 1);
            }
        }
        for(int i : st2){
            if(mp.containsKey(i)){
                mp.put(i, mp.get(i) - 1);
                if(mp.get(i) == 0) mp.remove(i);
            }else{
                return false;
            }
        }
        return true;
    }

    public int minDeletions(String s) {
        int[] st = new int[26];
        //Arrays.fill(st, -1);
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            st[cs[i] -'a']++;
        }
        Arrays.sort(st);
        int min = 0;
        int pre = 1000;
        for (int i = 25; i >= 0; i--) {
            if(st[i] >= st[i+1]){
                int dif = st[i] - st[i+1] - 1;
                min += Math.min(dif, st[i]);
                st[i] -= Math.min(dif, st[i]);
            }
        }
        return min;
    }

    public int maxProfit(int[] inventory, int od) {
        int n = inventory.length;
//        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> (b-a));
//        for (int i = 0; i < n; i++) {
//            pq.add(inventory[i]);
//        }
        Arrays.sort(inventory);
        long l = 0;
        long r = inventory[n-1];
        long mid = -1;
        long tot = 0;
        while(l<r){
            mid = (l+r) / 2;
            tot = 0;
            for (int i = n-1; i >= 0; i--) {
                if(inventory[i] < mid) break;
                tot += (inventory[i] - mid);
            }
            if(tot == od){
                break;
            }
            if(tot < od){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        long ans = 0;
        long cnt = 0;
        if(tot < od){
            for(int i = n-1; i >= 0; i--){
                if(inventory[i] <= mid) break;
                ans += ((mid + inventory[i]) * (inventory[i] - mid) ) /2 ;
                cnt += inventory[i] - mid;
                ans %= mod;
            }
            ans += mid * (od - tot);
            ans %= mod;
        }else{
            for(int i = n-1; i >= 0; i--){
                if(inventory[i] <= mid) break;
                ans += ((mid + inventory[i]) * (inventory[i] - mid) ) /2 ;
                cnt += inventory[i] - mid;
                ans %= mod;
            }
        }


        return (int) ans;

    }

    public class SegTree {
        private int N;

        // Let UNIQUE be a value which does NOT
        // and will not appear in the segment tree
        private long UNIQUE = 8123572096793136074L;

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

            return a + b; // sum over a range
            //return (a > b) ? a : b; // maximum value over a range
            //return (a < b) ? a : b; // minimum value over a range
            // return a * b; // product over a range (watch out for overflow!)
        }

        // Adjust point i by a value, O(log(n))
        public void modify(int i, long value) {
            //tree[i + N] = function(tree[i + N], value);
            tree[i + N] += value;
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

    int cnt = 0;
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] psum = new long[nums.length + 1];
        for (int i = 1; i < nums.length; i++) {
            psum[i] = psum[i - 1] + nums[i - 1];
        }
        dfs(psum, 0, nums.length, lower, upper);
        return cnt;
    }


    public int minOperations(int[] nums, int x) {
        // x + rest = tot
        // rest = tot - x
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        int sum = 0;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        int rest = sum - x;
        if(rest < 0) return -1;
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if(sum >= x){
               if(mp.containsKey(sum - x)){
                   ans = Math.min(ans, mp.get(sum - x));
               }
            }
            mp.put(sum, i);
        }
        return ans;
    }




    long[] dfs(long[] psum,int l, int r, int lo, int hi){
        if(l < r) return new long[0];
        if(l == r){
            if(psum[r + 1] - psum[r] <= hi &&  psum[r + 1] - psum[r] >= lo){
                cnt ++;
            }
            return new long[]{psum[r]};
        }
        int mid = (l + r) / 2;
        long[] le = dfs(psum, l, mid, lo, hi);
        long[] ri = dfs(psum, mid + 1, r, lo, hi);
        for (int i = mid + 1, j = l,  k = l; i <= r; i++) {
            while(psum[i + 1] > psum[j] + hi) j ++;
            while(psum[i + 1] < psum[k] + lo) k ++;
            cnt += (k - j + 1);
        }
        long[] res = new long[l + r - 1];
        for (int i = 0, j = 0, k = 0; k < r - l + 1; k++) {
            if(i == mid){
               res[k] = ri[j++];
            }else if(j == r){
                res[k] = le[i++];
            }else if(ri[j] > le[i]){
                res[k] = ri[j++];
            }else{
                res[k] = le[i++];
            }
        }
        return res;
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        //[5,2,6,1] when we got 5 we see 2 and 1
        //when we get 2 we see 1
        //when we get 6 we see 1
        // when we get 1 we see 0
        // first build a segtree
        int n = nums.length;
        int[] neo = Arrays.copyOf(nums, n);
        for (int i = 0; i < n; i++) {
            neo[i] += (int)1e4 + 1;
        }
        SegTree sg = new SegTree((int) 2e4 + 5);
        for (int i = 0; i < n; i++) {
            sg.modify(neo[i], 1);
        }
        for (int i = 0; i < n; i++) {
            res.add((int)sg.query(0, neo[i]));
            sg.modify(neo[i], -1);
        }
        return res;
    }

    public int createSortedArray(int[] instructions) {
        //int tot = 0;
        int n = instructions.length;
        long tot = 0;
        Map<Integer,Integer> mp = new HashMap<>();
        SegTree sg = new SegTree(100000*5);
        for (int i = 0; i < n; i++) {
            int an = mp.getOrDefault(instructions[i], 0);
            long r = sg.query(1,instructions[i]);
            long l = i - an - r;
            tot += Math.min(r , l);
            mp.put(instructions[i], mp.getOrDefault(instructions[i], 0)+1);
            sg.modify(instructions[i],1);
            tot %= mod;
        }
        return (int)tot;
    }
    int mod = (int) 1e9 + 7;
    public int maxSum(int[] nums1, int[] nums2) {
        Map<Integer,Integer> mp = new HashMap<>();
        int n1 = nums1.length;
        int n2 = nums2.length;
        long[] dp1  = new long[n1 + 2];
        long[] dp2  = new long[n2 + 1];
        for (int i = 0; i < n1; i++) {
            mp.put(nums1[i], i);
            dp1[i + 2] = dp1[i] + nums1[i];
        }
        dp1[n1 + 1] = dp1[n1];
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < n2; i++) {
            if(mp.containsKey(nums2[i])){
                q.add(new int[]{mp.get(nums2[i]), i});
            }
            dp2[i + 1] = dp2[i] + nums2[i];
        }
        dp2[n2 + 1] = dp2[n2];
        q.add(new int[]{n1 + 1, n2 + 1});
        long res = 0;
        int[] get = q.get(0);
        res += Math.max(dp1[get[0] + 1], dp2[get[1] + 1]);
        for (int i = 1; i < q.size(); i++) {
            int[] pre = q.get(i-1);
            int[] now = q.get(i);
            long l = dp1[now[0] + 1] - dp1[pre[0]];
            long r = dp2[now[1] + 1] - dp2[pre[1]];
            res += Math.max(l, r);
        }
        res %= mod;
        return  (int) res;

    }

    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        int max = 0;
        for(int i : nums){
            mp.put(i, mp.getOrDefault(i, 0) + 1);
            max = Math.max(max, i);
        }
        Map<Integer, List<Integer>> store = new HashMap<>();
        for(int i = 10000; i >= 1; i--){
            List<Integer> add = new ArrayList<>();
            while(mp.containsKey(i)){
                add.add(i);
                i--;
            }
            store.put(store.size(), add);
        }
        int ans = 0;
        for(Integer i : store.keySet()){
            ans += cnt(store.get(i), mp);
        }
        return ans;
    }
    int cnt(List<Integer> aka , Map<Integer, Integer> mp){
        int[] dp = new int[aka.size() + 1];
        int odd = 0;
        int even = 0;
        int max = 0;
        for(int i = 0; i < aka.size(); i++){
            if(aka.get(i) % 2 == 0){
                if(i >= 2) dp[i] = Math.max(dp[i - 1], mp.get(aka.get(i)) * aka.get(i) + dp[i - 2]);
                if(i == 0) dp[i] = mp.get(aka.get(i)) * aka.get(i);
            }else{
                if(i >= 3) dp[i] = Math.max(dp[i - 1], mp.get(aka.get(i)) * aka.get(i) + dp[i - 2]);
                if(i == 1) dp[i] = Math.max(dp[i - 1], mp.get(aka.get(i)) * aka.get(i));
            }
            max = Math.max(dp[i], max);
        }
        return max;

    }

    public String largestNumber(int[] cost, int target) {
        int[] dp = new int[target + 5];
        int[] par = new int[target + 5];
        Arrays.fill(dp, Integer.MIN_VALUE/2);
        dp[0] = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            mp.put(cost[i], i + 1);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = cost[i]; j <= target; j++) {
                if(dp[j-cost[i]] >= 0 && dp[j] <= dp[j-cost[i]] + 1){
                    dp[j] = dp[j - cost[i] ] + 1;
                    par[j] = j - cost[i];
                }
            }
        }
        if(dp[target] < 0) return "0";
        StringBuilder sb = new StringBuilder();
        int k = target;
        while(k > 0){
            int nxt = par[k];
            sb.append(mp.get(k - nxt));
            k = nxt;
        }
        return sb.toString();

    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        int n = coins.length;
        dp[0] = 1;
        //dp[i] means how many combinations of the ith amount
        for (int i = 0; i < n; i++) {
            for(int j = coins[i]; j <= amount; j++){
                dp[j] += dp[j-coins[i]];
            }
        }
        return dp[amount];

    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int n = coins.length;

        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 1;
        //dp[i] means how many combinations of the ith amount
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < n; j++) {
                if(i >= coins[j] && dp[i] > dp[i - coins[j]] + 1){
                    dp[i] = dp[i - coins[j]] + 1;
                }
            }
        }
        if(dp[amount] == Integer.MAX_VALUE / 2) return -1;
        return dp[amount];
    }


    public String multiply(String num1, String num2) {
        List<String> tot = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int max = num2.length();
        for (int i = 0; i <= max; i++) {
            tot.add(sb.toString());
            sb.append("0");
        }
        String s = "";
        for (int i = 0; i < max; i++) {
            int mut = num2.charAt(i) - '0';
            for (int j = 0; j < mut; j++) {
                s = add(s, num1);
            }
            s += tot.get(i);
        }
        return s;
    }
    String add(String s1, String s2){
        int add = 0;
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        for(char c : cs1){
            st1.push((c - '0'));
        }
        for(char c : cs2){
            st2.push((c - '0'));
        }
        StringBuilder sb = new StringBuilder();
        while(!st1.isEmpty() || !st2.isEmpty()){
            int l = st1.isEmpty()? 0: st1.pop();
            int r = st2.isEmpty()? 0: st2.pop();
            int tot = (l + r + add) % 10;
            add = (l + r + add) / 10;
            sb.append(tot);
        }
        if(add != 0) sb.append(1);
        return sb.reverse().toString();
    }


    public int numTriplets(int[] nums1, int[] nums2) {
        return dfs(nums1 , nums2) + dfs(nums2, nums1);
    }

    int dfs(int[] nums1, int[] nums2){
        int n1 = nums1.length;
        int n2 = nums2.length;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n1; i++) {
            mp.put(nums1[i], mp.getOrDefault(nums1[i], 0) + 1);
        }
        int res = 0;
        for (int i = 0; i < n2; i++) {
            for (int j = i + 1; j < n2; j++) {
                res += mp.getOrDefault(nums2[i] * nums2[j], 0);
            }
        }
        return res;
    }

    public int reversePairs(int[] nums) {
        dfs(nums, 0, nums.length - 1);
        return cnt;
    }
    int[] dfs(int[] nums, int l, int r){
        if(l == r) return new int[]{nums[r]};
        int[] le = dfs(nums,l, (l + r) / 2);
        int[] ri = dfs(nums,(l + r) / 2 + 1, r);
        int[] res = new int[r - l + 1];
        int idx = 0;
        for (int i = 0, j = 0; i < ri.length; i++) {
            while (j < le.length && nums[j] / 2 <= nums[i]){
                j++;
            }
            cnt += le.length - j;
        }
        for (int i = 0, j = 0, k = 0; i < res.length; i++) {
            int v = 0;
            if(k == ri.length){
                v = le[j++];
            }else if(j == le.length){
                v = ri[k++];
            }else {
                if (le[j] > ri[k]) {
                    v = ri[k++];
                } else {
                    v = le[j++];
                }
            }
        }
        return res;
    }








}
