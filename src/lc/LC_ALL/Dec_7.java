package lc.LC_ALL;

import java.util.*;

public class Dec_7 {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        //int[][] dirs = new int[][]{{1 , 0}, {0 , 1}, {-1 , 0}, {0, - 1}};
        int cnt = 0;
        int x = 0, y = 0;
        int leftb = 0;
        int rb = n - 1;
        int hb = 0;
        int lowerb = n - 1;
        for (int i = 0; i < n * n; ) {
            if (cnt == 0){
                while (y < rb){
                    res[x][y] = i + 1;
                    y++;
                    i++;
                }
                cnt += 1;
                hb++;
            }else if(cnt == 1){
                while (x < lowerb){
                    res[x][y] = i + 1;
                    x++;
                    i++;
                }
                rb--;
            }else if (cnt == 2){
                while (y > leftb){
                    res[x][y] = i + 1;
                    y--;
                    i++;
                }
                lowerb--;
            }else{
                while (x > hb){
                    res[x][y] = i + 1;
                    x--;
                    i++;
                }
                leftb++;
            }
        }
        return res;
    }
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += nums[i] - nums[0];
        }
        int[] res = new int[n];
        res[0] = cnt;
        int nxt = cnt;
        for(int i = 1; i < n; i++){
            int aft = n - i - 1;
            int bf = Math.max(0, i - 2);
            nxt = bf * (nums[i] - nums[i - 1]) - aft * (nums[i] - nums[i - 1]) + nxt;
            res[i] = nxt;
        }
        return res;
    }


    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int tot = 0;
        if(tot % n != 0) return -1;
        int tar = tot / n;
        int[] arr = new int[n];
        int zero = 0;
        for(int i = 0; i < n; i++){
            tot += machines[i];
            if(arr[i] == 0){
                arr[i] = 1;
                zero++;
            }else{
                arr[i] = machines[i];
            }
        }
        int res = zero;
        Arrays.sort(arr);
        for(int i = n - 1; i >= 0; i--){
            while (i > 0 && arr[i] > arr[i - 1] && zero > 0){
                arr[i] --;
            }
            if (zero == 0) break;
        }
        for (int i = 0; i < n; i++) {
            if(arr[i] < tar){
                zero += tar - arr[i];
            }
        }
        return zero;



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

            //return a + b; // sum over a range
            //return (a > b) ? a : b; // maximum value over a range
            return (a < b) ? a : b; // minimum value over a range
            // return a * b; // product over a range (watch out for overflow!)
        }

        // Adjust point i by a value, O(log(n))
        public void modify(int i, long value) {
            tree[i + N] = function(tree[i + N], value);
            //tree[i + N] += value;
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
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        int[] need = new int[n + 1];
        LinkedList<Integer> q = new LinkedList<>();
        SegTree sg = new SegTree(n * 2);
        int[] dp = new int[n + 2];
        for(int i = n - 2; i >= 0; i--){
            if(boxes[i][0] != boxes[i + 1][0]) {
                need[i] = need[i + 1] + 1;
            }else{
                need[i] = need[i + 1];
            }
        }
        int tot = 0;
        for (int i = 1; i <= n; i++) {
            while(q.size() >= maxBoxes) q.poll();
            int port = boxes[i][0];
            int weight = boxes[i][1];
            while (!q.isEmpty() && tot + weight > maxWeight){
                int idx = q.poll();
                tot -= boxes[idx - 1][1];
            }
            if(q.size() > 0){
                int idx = q.peek();
                int val = (int)sg.query(idx, i);
                dp[i] = val - need[i];
            }else{
                dp[i] = dp[i - 1] + 2;
            }
            sg.modify(i, dp[i - 1] + need[i - 1]);
        }
        // 1 1 2 1
        // 2 2 1 0
        return dp[n];
    }

    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        int[][] dp = new int[101][101];
        int max = 0;
        for(int i = 0; i < n; i++){
            int[] a1 = new int[]{cuboids[i][1], Math.max(cuboids[i][2], cuboids[i][0]), Math.min(cuboids[i][2], cuboids[i][0])};
            int[] a2 = new int[]{cuboids[i][0], Math.max(cuboids[i][1], cuboids[i][2]), Math.min(cuboids[i][2], cuboids[i][1])};
            int[] a3 = new int[]{cuboids[i][2], Math.max(cuboids[i][1], cuboids[i][0]), Math.min(cuboids[i][1], cuboids[i][0])};
            int[][][] neo = new int[3][101][101];
            int l = 0;
            for(int[] ak : new int[][]{a1, a2, a3}){
                int h = ak[0];
                int w = ak[1];
                int d = ak[2];
                for(int j = 1; j <= 100; j++){// 2 part
                    for(int k = 1; k <= 100; k++){// 3 part
                        if(w >= j && d >= k){
                            neo[l][j][k] += h;
                        }
                    }
                }
                l++;
            }
            for (int j = 0; j <= 100 ; j++) {
                for (int k = 0; k <= 100; k++) {
                    dp[i][j] = Math.max(neo[0][i][j], Math.max(neo[1][i][j],neo[0][i][j]));
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    int[] pre;
    public int stoneGameVII(int[] stones) {
        int tot = 0;
        int n = stones.length;
        for(int x : stones){
            tot += x;
        }
        pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + stones[i - 1];
        }
        int get = dfs(stones, 0 , n - 1, 0);
        return get - (tot - get);


    }
    Integer[][][] memo = new Integer[1001][1001][2];
    int dfs(int[] arr, int i, int j, int turn){
        if(i == j){
            return arr[i];
        }
        if(memo[i][j][turn] != null) return memo[i][j][turn];
        int le = dfs(arr, i + 1, j, 1 - turn) + pre[j + 1] - pre[i + 1];
        int ri = dfs(arr, i, j - 1, 1 - turn) + pre[j] - pre[i];
        if(turn == 0){
            memo[i][j][turn] = Math.min(le, ri);
        }else{
            memo[i][j][turn] = Math.max(le, ri);
        }
        return memo[i][j][turn];
    }
    boolean[] seen = new boolean[10];
    char fb;
    List<Character> store;
    public boolean isSolvable(String[] words, String result) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            for(char c : words[i].toCharArray()){
                set.add(c);
            }
        }
        store = new ArrayList<Character>(set);
        fb = result.charAt(0);
        return dfs(0, store.size(), new int[32], words, result);
    }
    boolean dfs(int i, int n, int[] val, String[] words, String result){
        if(i == n){
            int tot = 0;
            for(String s : words){
                int cnt = 0;
                for (int j = s.length() - 1; j >= 0; j--) {
                    cnt += val[s.charAt(j) - 'a'];
                    cnt *= 10;
                }
                tot += 10;
            }
            String neo = String.valueOf(tot);
            if(neo.length() != result.length()) return false;
            for (int j = 0; j < neo.length(); j++) {
                if(val[result.charAt(j) - 'a'] == -1) continue;
                if(val[result.charAt(j) - 'a'] != Integer.valueOf(neo.charAt(j))){
                    return false;
                }
            }
            return true;
        }
        for (int j = 0; j <= 9; j++) {
            if((fb == store.get(i) && j == 0) || (seen[j])){
                continue;
            }
            val[store.get(j) - 'a'] = j;
            seen[j] = true;
            if(dfs(i + 1, n, val, words, result)){
                return true;
            }
            seen[j] = false;
        }
        return false;
    }





    public int countConsistentStrings(String allowed, String[] words) {
        Set<Character> set = new HashSet<>();
        int n1 = allowed.length();
        int cnt = 0;
        for(char c : allowed.toCharArray()){
            set.add(c);
        }
        for(String s : words){
            boolean find = true;
            for(char c : s.toCharArray()){
                if(!set.contains(c)){
                    find = false;
                    break;
                }
            }
            if (find) cnt++;
        }
        return cnt;
    }

}
