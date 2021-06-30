package lc.LC_ALL;

import java.util.*;

public class LC_1_15 {
    class SegTree {
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
            //tree[i + N] = function(tree[i + N], value);
            tree[i + N] += value;

            for (i += N; i > 1; i >>= 1) {
                //System.out.println((i>>1)+"i:"+i+" "+ (i ^ 1));
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


    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        // 0 means start and 1 means end
        int pre = 0;
        for(int[] pos : positions){
            int start = pos[0];
            int len = pos[1];
            int tot = 0;
            int max = 0;
            for(int x : mp.keySet()){
                tot += mp.get(x);
                if (tot >= start && tot < start + len){
                    max = Math.max(max, tot + len);
                }
            }
            TreeMap<Integer, Integer> neo = new TreeMap<>();
            for(int x : mp.keySet()){
                if (x >= start && x < start + len){

                }else{
                    neo.put(x, mp.get(x));
                }
            }
        }
        return new ArrayList<>();
    }

    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MIN_VALUE / 2);
        dp[0] = 0;
        int[][] arr = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] cs = stickers[i].toCharArray();
            for (int j = 0; j < cs.length; j++) {
                arr[i][cs[j] - 'a']++;
            }
        }
        char[] cs = target.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1 << n; j++) {
                if (dp[j] < 0) continue;
                int[] cp = arr[i];
                int state = j;
                for (int k = 0; k < n; k++) {
                    if ((j | (1 << k)) == j) continue;
                    if (cp[cs[k] - 'a'] > 0){
                        cp[cs[k] - 'a']--;
                        j |= (1 << k);
                    }
                }
                dp[state] = Math.min(dp[state], dp[j] + 1);
            }
        }
        return dp[1 << n - 1] < 0 ? -1 : dp[1 << n - 1];
    }
    int dfs(int idx, int[] tar, int[][] arr){
        if (idx == arr.length){
            for (int i = 0; i < 26; i++) {
                if (tar[i] > 0){
                    return -1;
                }
            }
            return 0;
        }
        int test = helper(arr[idx], tar);
        int res = 100000000;
        boolean find = false;
        for (int i = 1; i <= test; i++){
            for (int j = 0; j < 26; j++) {
                if (arr[idx][j] > 0 && tar[j] > 0){
                    int max = Math.min(i * arr[idx][j], tar[j]);
                    tar[j] -= Math.max(0, tar[j] - i * arr[idx][j]);
                    int get = dfs(idx + 1, tar, arr);
                    if (get != -1){
                        find = true;
                        res = Math.min(res, i + get);
                    }
                    tar[j] += max;
                }
            }
        }
        if(!find){
            return -1;
        }
        return res;
    }
    int helper(int[] now, int[] tar){
        int n = now.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (now[i] > 0 && tar[i] > 0){
                max = Math.max(tar[i] / now[i] + 1, max);
            }
        }
        return max;
    }


    int mod = (int) 1e9 + 7;
    public int[] waysToFillArray(int[][] queries){
        Set<Integer> set = sieveOfEratosthenes(10000);
        int C[][] = new int[1000][1000];

        // Calculate  value of Binomial
        // Coefficient in bottom up manner
        for (int i = 0, j; i < 1000; i++) {
            for (j = 0; j <= Math.min(i, 1000); j++) {
                // Base Cases
                if (j == 0 || j == i)
                    C[i][j] = 1;
                else
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
        int[] res = new int[queries.length];
        int cnt = 0;
        for (int[] q : queries){
            int pos = q[0];
            int tar = q[1];
            long rs = 1;
            for (int prime : set){
                int tot = 0;
                while (tar % prime == 0){
                    tot ++;
                    tar /= prime;
                }
                rs = rs * C[pos + tot - 1][tot];
                rs %= mod;
            }
            res[cnt++] = (int)rs;
        }
        return res;
    }
    Set<Integer> sieveOfEratosthenes(int n)
    {
        Set<Integer> set = new HashSet<>();
        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++)
        {
            if (prime[p] == true)
            {
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for (int i = 2; i <= n; i++)
        {
            if (prime[i] == true)
                set.add(i);
        }
        return set;
    }

    static String canReach(int x1, int y1, int x2, int y2){
        while (x1 != x2 || y1 != y2){
            if (x2 == 0 || y2 == 0) break;
            if (y2 > x2){
                y2 -= x2;
            }else{
                x2 -= y2;
            }
        }
        if (x1 == x2 && y1 == y2){
            return "Yes";
        }else{
            return "No";
        }
    }

    static int exam(List<Integer> v){
        int size = v.size();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = v.get(i);
            if (arr[i] == 0){
                arr[i] = -1;
            }
        }
        int[] pre = new int[size + 1];
        int[] end = new int[size + 1];
        pre[0] = arr[0];
        for (int i = 1; i < size; i++) {
            pre[i] = arr[i] + pre[i - 1];
        }
        end[size] = 0;
        for (int i = size - 1; i >= 0 ; i--) {
            end[i] = end[i + 1] + arr[i];
        }
        int min = size;
        boolean find = false;
        if (end[0] < 0){
            min = 0;
            find = true;
        }
        for (int i = 0; i < size && ! find; i++) {
            if (pre[i] > end[i + 1]){
                find = true;
                min = i + 1;
            }
        }
        return min;
    }

    static long canParkingRoof(List<Long> cars, int k){
        long[] arr = new long[cars.size()];
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = cars.get(i);

        }
        long min = Long.MAX_VALUE;
        Arrays.sort(arr);
        for (int i = 0, j = 0; i < n; i++) {
            while (j - i + 1 < k && j < n){
                j++;
            }
            min = Math.min(min, arr[j] - arr[i] + 1);
            if (j == n) break;
        }
        return min;
    }

    public int minimumBoxes(int n) {
        long l = 1, r = Integer.MAX_VALUE;
        TreeMap<Long, Long> mp = new TreeMap<>();
        TreeMap<Long, Long> hp = new TreeMap<>();
        long start = 1;
        long cnt = 1;
        while (start < 1e9){
            long aka = (start + 1) * (cnt) / 2;
            mp.put(start, aka);
            hp.put(start, cnt);
            cnt++;
            start += cnt;
        }
        while (l < r){
            long mid = l + r >> 1;
            if (check(mid, n, mp, hp)){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return (int) l;
    }
    boolean check(long base, long tot, TreeMap<Long, Long> mp, TreeMap<Long, Long> hp){
        Long get = mp.floorKey(base);
        long fk = mp.get(get);
        tot -= fk;
        if (tot <= 0) return true;
        if (mp.get(mp.higherKey(get)) < tot) return false;
        long dif = base - get;
        //long cnt = hp.get(get);
        long cnt = 0;
        while (dif > 0){
            Long neo = mp.floorKey(dif);
            long sum = mp.get(neo);
            if (tot <= sum) return true;
            dif -= neo;
            tot -= sum;
        }
        return false;
    }











    public String maximumTime(String time) {

        char[] cs = time.toCharArray();
        if (cs[0] == '?'){
            if (cs[1] != '?'){
                if (cs[1] <= '3'){
                    cs[0] = '2';
                }else{
                    cs[0] = 1;
                }
            }
        }
        if (cs[1] == '?'){
            if (cs[0] == '2'){
                cs[1] = '3';
            }else{
                cs[1] = '9';
            }
        }
        if (cs[3] == '?'){
            cs[3] = '5';
        }
        if (cs[4] == '?'){
            cs[4] = '9';
        }
        return new String(cs);

    }
    public int minCharacters(String a, String b) {
        int cnt = 0;
        char[] ca = a.toCharArray();
        char[] cb = a.toCharArray();
        int n = a.length();
        int[] aka1 = new int[26];
        int[] aka2 = new int[26];
        for (int i = 0; i < ca.length; i++) {
            aka1[ca[i] - 'a']++;
        }
        for (int i = 0; i < cb.length; i++) {
            aka2[cb[i] - 'a']++;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            res = Math.min(res, a.length() + b.length() - aka1[i] - aka2[i]);
        }
        return Math.min(cnt, Math.min(helper(ca, cb), helper(cb, ca)));
    }
    int helper(char[] c1, char[] c2){
        TreeMap<Integer, Integer> mp1 = new TreeMap<>();
        TreeMap<Integer, Integer> mp2 = new TreeMap<>();
        int[] aka1 = new int[26];
        int[] aka2 = new int[26];
        for (int i = 0; i < c1.length; i++) {
            aka1[c1[i] - 'a']++;
        }
        for (int i = 0; i < c2.length; i++) {
            aka2[c2[i] - 'a']++;
        }
        int cnt = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (aka1[i] > 0){
                cnt += aka1[i];
                mp1.put((i + 'a'), cnt);
            }
        }
        for (int i = 0; i < 26; i++) {
            if (aka2[i] > 0){
                cnt += aka2[i];
                mp2.put( (i + 'a'), cnt);
            }
        }
        for(int i = 'c'; i <= 'z'; i++){
            Integer v1 = mp1.floorKey(i);
            Integer v2 = mp2.floorKey(i);
            int cp1 = v1 == null? c1.length : c1.length - v1;
            int cp2 = v2 == null? 0 : c2.length - v2;
            res = Math.min(res, cp1 + cp2);
        }
        return res;
    }
    public int kthLargestValue(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int[][] up = new int[n][m];
        int[][] left = new int[n][m];
        for (int i = 0; i < n; i++) {
            left[i][0] = 0;
            for (int j = 1; j < m; j++) {
                left[i][j] = matrix[i][j - 1] ^ left[i][j - 1];
            }
        }
        for (int j = 0; j < m; j++) {
            up[0][j] = 0;
            for (int i = 1; i < n; i++) {
                up[i][j] = up[i - 1][j] ^ matrix[i - 1][j];
            }
        }
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0){
                    dp[i][j] = matrix[0][0];
                }else if (i == 0){
                    dp[i][j] = matrix[i][j] ^ dp[i][j - 1];
                }else if (j == 0){
                    dp[i][j] = matrix[i][j] ^ dp[i - 1][j];
                }else{
                    dp[i][j] = dp[i - 1][j - 1] ^ matrix[i][j] ^ up[i][j] ^ left[i][j];
                }
                pq.add(dp[i][j]);
            }
        }
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }
        return pq.peek();
    }
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        int[][] dp = new int[m + 1][m + 1];
        Map<Integer, Set<Integer>> mp = new HashMap<>();
        for (int i = 0; i < m; i++) {
            mp.put(i + 1, new HashSet<>());
            for (int j = 0; j < languages[i].length; j++) {
                mp.get(i + 1).add(languages[i][j]);
            }
        }
        for (int[] fr : friendships){
            int l = fr[0];
            int r = fr[1];
            Set<Integer> s1 = mp.get(l);
            Set<Integer> s2 = mp.get(r);
            for(int x : s1){
                if (s2.contains(x)){
                    dp[l][r] = 1;
                    break;
                }
            }
        }
        int res = 0;
        for(int i = 1; i < n ;i++){
            Set<Integer> cnt = new HashSet<>();
            for(int[] fr : friendships){
                int l = fr[0];
                int r = fr[1];
                if (dp[l][r] == 1) continue;
                if (!mp.get(l).contains(i)) cnt.add(l);
                if (!mp.get(r).contains(i)) cnt.add(r);
            }
            res = Math.max(res, cnt.size());
        }
        return res;
    }
    public int minimumTeachings(int n, int[][] languages, int[][] friendships, int k) {
        Map<Integer, Set<Integer>> mp = new HashMap<>();
        for (int[] fri : friendships){
            if (!mp.containsKey(fri[0])) mp.put(fri[0], new HashSet<>());
            if (!mp.containsKey(fri[1])) mp.put(fri[1], new HashSet<>());
            mp.get(fri[0]).add(fri[1]);
            mp.get(fri[1]).add(fri[0]);
        }
        Map<Integer, Set<Integer>> read = new HashMap<>();
        for(int i = 0; i < languages.length; i++){
            read.put(i + 1, new HashSet<>());
            for(int x : languages[i]){
                read.get(i + 1).add(x);
            }
        }
        int res = n + 1;
        for (int i = 1; i < n; i++) {
            int cnt = 0;
            for(int user : read.keySet()){
                if (read.get(user).contains(i)) continue;
                for(int p : mp.get(user)){
                    if (read.get(p).contains(i)){
                        cnt++;
                        break;
                    }
                }
            }
            res = Math.min(cnt, res);
        }
        return res;
    }



    Integer[][] dp;
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        dp = new Integer[n + 1][n + 1];
        int[] pre = new int[n + 1];
        pre[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + stones[i];
        }
        int get = dfs(0, n - 1, stones, k, pre);
        if (get == Integer.MAX_VALUE / 2){
            return -1;
        }
        return get;

    }
    int dfs(int l, int r, int[] stones, int k,int[] pre){
        if (r - l + 1 < k) return Integer.MAX_VALUE / 2;
        if (dp[l][r] != null) return dp[l][r];
        int res = Integer.MAX_VALUE / 2;
        int len = r - l + 1 - k + 1;
        int before = 0;
        int latter = pre[r] - pre[l + len - 1];
        for (int i = l; i + len <= r; i++) {
            int tot = pre[i + len] - pre[i] + stones[i];
            res = Math.min(res, tot + dfs(i, i + len, stones, k, pre) + before + latter);
            before += stones[i];
            latter -= stones[i + len];
        }
        return dp[l][r] = res;
    }

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        // we set mouse turn as 1 and cat turn as 2
        int[][][][][] memo = new int[9][9][9][9][2];
        int n = grid.length;
        int m = grid[0].length();
        int[] catStart = new int[2];
        int[] mouseStart = new int[2];
        int[] food = new int[2];
        char[][] cs = new char[n][];
        for (int i = 0; i < n; i++) {
            cs[i] = grid[i].toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cs[i][j] == 'M'){
                    mouseStart = new int[]{i, j};
                }else if (cs[i][j] == 'C'){
                    catStart = new int[]{i, j};
                }else if (cs[i][j] == 'F'){
                    food = new int[]{i, j};
                }
            }
        }
        LinkedList<int[]> q = new LinkedList<>();
        // find the start status
        //cat reach the food or mouse reach the food
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cs[i][j] == '#' ) continue;
                if (i == food[0] && j == food[1]) continue;
                memo[food[0]][food[1]][i][j][1] = 1; // mouse reach the place of food
                memo[i][j][food[0]][food[1]][2] = 2; // cat reach the place of food
                q.add(new int[]{food[0], food[1], i, j, 1});
                q.add(new int[]{i, j, food[0], food[1], 2});
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cs[i][j] == '#') continue;
                memo[i][j][i][j][2] = 2;
                q.add(new int[]{i, j, i, j, 2});
            }
        }
        int cnt = 0;
        while (cnt++ < 2000){
            int len = q.size();
            while (len-- > 0){
                int[] get = q.poll();
                int mi = get[0];
                int mj = get[1];
                int ci = get[2];
                int cj = get[3];
                int turn = get[4];
                int nxtTurn = turn == 2 ? 1 : 2;
                for(int[] nxt : findAd(mi, mj, ci , cj, cs, catJump, mouseJump, nxtTurn)){
                    int nmi = nxt[0];
                    int nmj = nxt[1];
                    int nci = nxt[2];
                    int ncj = nxt[3];
                    if (memo[nmi][nmj][nci][ncj][nxtTurn] != 0) continue;
                    if (memo[mi][mj][ci][cj][turn] == nxtTurn){
                        q.add(new int[]{nmi, nmj, nci,ncj, nxtTurn});
                        memo[nmi][nmj][nci][ncj][nxtTurn] = nxtTurn;
                    }else if (isLose(nmi, nmj, nci,ncj,cs, catJump, mouseJump, memo, nxtTurn)){
                        memo[nmi][nmj][nci][ncj][nxtTurn] = turn;
                    }

                }

            }

        }
        return memo[catStart[0]][catStart[1]][mouseStart[0]][mouseStart[1]][1] == 1;
    }
    boolean isLose(int mi, int mj, int ci, int cj, char[][] grid, int catJump, int mouseJump, int[][][][][] memo, int turn){
        int nxtTurn = turn == 1 ? 2 : 1;
        for(int[] all : findAd(mi, mj, ci , cj, grid, catJump, mouseJump, nxtTurn)){

            int val = memo[all[0]][all[1]][all[2]][all[3]][nxtTurn];
            if (val == 0 || (val != nxtTurn)) return false;
        }
        return true;
    }
    List<int[]> findAd(int mi, int mj, int ci, int cj, char[][] grid, int catJump, int mouseJump, int turn){
        List<int[]> res = new ArrayList<>();
        if (turn == 1) {
            catJump = 0;
        }else{
            mouseJump = 1;
        }

        for (int i = -catJump; i <= catJump; i++) {
            for (int j = -catJump; j <= catJump; j++) {
                for (int k = -mouseJump; k <= mouseJump; k++) {
                    for (int l = -mouseJump; l <= mouseJump; l++) {
                        int nmi = mi + i;
                        int nmj = mj + j;
                        int nci = ci + k;
                        int ncj = cj + k;
                        if (nmi < 0 || nmi >= grid.length || nmj < 0 || nmj >= grid[0].length) continue;
                        if (nci < 0 || nci >= grid.length || ncj < 0 || ncj >= grid[0].length) continue;
                        if (grid[nci][ncj] == '#' || grid[nmi][nmj] == '#') continue;
                        res.add(new int[]{nmi, nmj, nci, ncj});
                    }
                }
            }
        }
        return res;
    }
    public int countGoodRectangles(int[][] rectangles) {
        int n = rectangles.length;
        int max = 0;
        int cnt = 0;
        for(int[] rec : rectangles){
            int len = Math.min(rec[0], rec[1]);
            if (max == len){
                cnt++;
            }else if (len > max){
                cnt = 1;
                max = len;
            }
        }
        return max;
    }
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int mut = nums[i] * nums[j];
                mp.put(mut, mp.getOrDefault(mut, 0) + 1);
            }
        }
        int cnt = 0;
        for(int key : mp.keySet()){
            int val = mp.get(key);
            cnt += (val * (val - 1));
        }
        return cnt;
    }
    public int largestSubmatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1){
                    if (i > 0) dp[i][j] = dp[i - 1][j];
                    dp[i][j]++;
                    pq.add(dp[i][j]);
                }
            }
            int cnt = 0;
            int he = 100000000;
            while (!pq.isEmpty()){
                cnt ++;
                he = pq.poll();
                res = Math.max(res, he * cnt);
            }
        }
        return res;
    }
}
