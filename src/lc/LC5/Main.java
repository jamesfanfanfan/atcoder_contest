package lc.LC5;

import java.util.*;

public class Main {
    int min = 1000000;
    int[] cnt;
    public int minimumIncompatibility(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        int seg = nums.length / k;
        Arrays.sort(nums);
        for (int i = 1; i < (1 << nums.length); i++) {
            if (Integer.bitCount(i) != seg) continue;
            List<Integer> list = new ArrayList<>();
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < 32; j++) {
                if((i | 1 << j) == i){
                    list.add(j);
                    set.add(nums[j]);
                }
            }
            if (set.size() != list.size()) continue;
            int mn = -1;
            int mx = -1;
            for(int x : list){
                if (mn == -1) mn = nums[x];
                mx = nums[x];
            }
            mp.put(i, mx - mn);
        }
        for (int i = 0; i < k; i++) {
            Map<Integer,Integer> neo = new HashMap<>();
            for (int x : mp.keySet()){
                for (int y : mp.keySet()){
                    if ((x & y) == 0){
                        int already = neo.getOrDefault((x | y), 100000);
                        if (mp.get(x) + mp.get(y) < already){
                            neo.put((x | y), mp.get(x) + mp.get(y));
                        }
                    }
                }
            }
            mp = neo;
        }
        return mp.getOrDefault((1 << nums.length - 1), -1);
    }




    public int concatenatedBinary(int n) {
        long res = 1;
        int mod = (int) 1e9 + 7;
        for (int i = 2; i <= n ; i++) {
            int k = 0;
            for (int j = 0; j < 32; j++) {
                if((i | 1 << j) == i) k = j;
            }
            for (int j = 0; j <= k; j++) {
                if ((i | 1 << j) == i){
                    i += 1;
                }
                i *= 2;
                res %= mod;
            }
        }
        return (int) res;

    }


    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int cnt = 0;
        for (int i = 0, j = n - 1; i < j ; i++) {
            if (nums[i] + nums[j] < k){
                continue;
            }else if (nums[i] + nums[j] > k){
                j--;
            }else{
                cnt++;
                j--;
            }
        }
        return cnt;
    }

    public String interpret(String command) {
        int n = command.length();
        StringBuilder sb = new StringBuilder();
        char[] cs = command.toCharArray();
        for (int i = 0; i < n; i++) {
            if (cs[i] == 'G'){
                sb.append('G');
            }else{
                if (cs[i + 1] == 'a'){
                   sb.append("al");
                   i += 3;
                }else{
                    sb.append('o');
                    i+=1;
                }
            }
        }
        return sb.toString();
    }

    public int kthFactor(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < Math.pow(n, 0.5); i++) {
            if (n % i == 0){
                k--;
                res.add(i);
                if (k == 0) return i;
            }
        }
        if (Math.pow(n, 0.5) * Math.pow(n, 0.5) == n) k++;
        return res.size() >= k ? n / res.get(res.size() - k) : -1;

    }
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        //LinkedList<Integer> q = new LinkedList<>();
        int len = flowerbed.length;
        boolean[] seen = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1){
                seen[i] = true;
                if(i < len - 1) seen[i + 1] = true;
            }else{
                if (i < len - 1 && flowerbed[i + 1] != 1){
                    n--;
                    seen[i + 1] = true;
                }else if (i == len - 1 && !seen[i]){
                    n--;
                }
            }
        }
        return n <= 0;
    }
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int max = 0;
        int currentCnt = -1;// current continous 1
        int beforeCnt = 0;// before continous 1 before one zero
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0){
                // 1 1 1 1 1 0  beforeCnt = currentCnt; currentCnt = 0
                // 1 1 1 1 0 0  same as before
                max = Math.max(max, currentCnt + beforeCnt + 1);
                beforeCnt = currentCnt;
                currentCnt = 0;
            }else{
                currentCnt+=1;
            }
        }
        max = Math.max(max, currentCnt + beforeCnt + 1);
        return max;
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return a[0] - b[0];
        });
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            int v = nums.get(i).get(0);
            pq.add(new int[]{v, i, 0});
            max = Math.max(max, v);
        }
        int[] res = new int[2];
        int len = Integer.MAX_VALUE;
        while (pq.size() == nums.size()){
            int[] arr = pq.poll();
            if(max - arr[2] < len){
                res = new int[]{arr[2], max};
            }
            arr[2]++;
            if(arr[2] < nums.get(arr[1]).size()){
                arr[0] = nums.get(arr[1]).get(arr[2]);
                max = Math.max(max, arr[0]);
                pq.add(arr);
            }
        }
        return res;
    }
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        int n = nums.length;
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(!mp.containsKey(nums[i])) q.add(nums[i]);
            mp.put(nums[i], mp.getOrDefault(nums[i], 0) + 1);
        }
        Map<Integer, Integer> dp = new HashMap<>();
        while (!q.isEmpty()){
            int v = q.poll();
            int get = mp.get(v);
            if(dp.containsKey(v - 1)){
                get -= dp.get(v - 1);
            }
            if(get > 0){
                int nxt1 = mp.get(v + 1);
                int nxt2 = mp.get(v + 2);
                if(Math.min(nxt1, nxt2) < get) return false;
                mp.put(v + 1, nxt1 - get);
                mp.put(v + 2, nxt2 - get);
                dp.put(v + 2, get);
            }
        }
        return true;

    }
    public int sumSubarrayMins(int[] arr) {
        int mod = (int) 1e9 + 7;
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        // 0 1 2 3 4
        stack.push(-1);
        int[] dpl = new int[n + 1];
        int[] dpr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && arr[stack.peek()] > arr[i]){
                stack.pop();
            }
            dpl[i] = i - stack.peek();
            stack.push(i);
        }
        stack.clear();
        stack.push(n);
        for (int i = n - 1; i >= 0; i--) {
            while (stack.peek() != n && arr[stack.peek()] > arr[i]){
                stack.pop();
            }
            dpr[i] = stack.peek() - i;
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            long get = dpl[i] * dpr[i] * (long) arr[i];
            res += get;
            res %= mod;
        }
        return (int) res;
    }
    public String smallestSubsequence(String s) {
        char[] cs = s.toCharArray();
        int[] store = new int[26];
        int n = cs.length;

        for (int i = 0; i < n; i++) {
            store[cs[i] - 'a']++;
        }
        Stack<Character> stack = new Stack<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if(!set.add(cs[i])){
                store[cs[i] - 'a']--;
                continue;
            }
            while (!stack.isEmpty() && store[(stack.peek() - 'a')] > 0 && cs[i] <= stack.peek()){
                set.remove(stack.pop());

            }
            stack.push(cs[i]);
            store[cs[i] - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
    public int minimumDeviation(int[] nums) {
        int n = nums.length;
        return  - 1;
    }
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[k];
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        // 123 n = 3 i = 1 k = 2 idx = 0
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] < stack.peek() && (stack.size() - 1 + (n - i) >= k) ){
                stack.pop();
            }
            stack.push(nums[i]);
        }
        for (int i = k; i >= 0 ; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
    class FrontMiddleBackQueue {
        LinkedList<Integer> pre = new LinkedList<>();
        LinkedList<Integer> lat = new LinkedList<>();
        public FrontMiddleBackQueue() {

        }

        public void pushFront(int val) {
            pre.addFirst(val);
            if(pre.size() > lat.size() + 1){
                lat.addFirst(pre.removeLast());
            }
        }

        public void pushMiddle(int val) {
            if(pre.size() > lat.size()) {
                lat.addFirst(pre.removeLast());
                pre.add(val);
            }else{
                pre.addLast(val);
            }
        }

        public void pushBack(int val) {
            lat.addLast(val);
            if(lat.size() > pre.size() + 1){
                pre.addLast(lat.removeFirst());
            }
        }

        public int popFront() {
            if (pre.size() + lat.size() == 0) return -1;
            int res = 0;
            if(pre.size() > 0){
                res = pre.removeFirst();
                if(lat.size() > pre.size() + 1){
                    pre.addLast(lat.removeFirst());
                }
            }else{
                res = lat.removeFirst();
            }
            return res;
        }

        public int popMiddle() {
            if (pre.size() + lat.size() == 0) return -1;
            int res = 0;
            if (lat.size() > pre.size()){
                res = lat.removeFirst();
            }else{
                res = pre.removeLast();
            }
            return res;
        }

        public int popBack() {
            if (pre.size() + lat.size() == 0) return -1;
            int res = 0;
            if(lat.size() > 0){
                res = lat.removeLast();
                if (pre.size() > lat.size() + 1){
                    lat.addFirst(pre.removeLast());
                }
            }else{
                res = pre.removeLast();
            }
            return res;
        }
    }
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;

        int[] same = new int[limit * 2 + 1];
        for (int i = 0; i < n / 2; i++) {
//            pq.add(new int[]{nums[i] + nums[n- i - 1], });
            same[nums[i] + nums[n- i - 1]] ++;
        }
        int[] arr = new int[limit * 3 + 1];
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n / 2; i++) {
            int mi = Math.min(nums[i], nums[n - i - 1]) + 1;
            int mx = Math.max(nums[i], nums[n - i - 1]) + 1;
            list.add(new int[]{mi, mx});
        }
        for(int[] a : list){
            arr[a[0]] ++;
            arr[a[1] + 1] --;
        }
        int res = 1001000;
        int tot = 0;
        for (int v = 2; v <= limit * 2; v++) {
            tot += arr[v];
            res = Math.min(res, tot - same[v] + (n / 2 - tot) * 2);
        }
        return res;
    }
    public int minFlips(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int res = 100000;
        int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        for (int i = 0; i < (1 << (n * m)); i++) {
            List<Integer> aka = new ArrayList<>();
            for (int j = 0; j < (n * m); j++) {
                if((i | (1 << j)) == i){
                    aka.add(j);
                }
            }
            int[][] mt = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int idx = j * n + k;
                    if((i | 1 << idx) == i){
                        mt[i][j]++;
                        for(int[] dir : dirs){
                            int pi = i + dir[0];
                            int pj = j + dir[1];
                            if(pi < 0 || pj < 0 || pi >= m || pj >= n){
                                mt[pi][pj]++;
                            }
                        }
                    }
                }
            }
            boolean find = true;
            for (int j = 0; j < m && find; j++) {
                for (int k = 0; k < n && find; k++) {
                    if((mt[i][j] + mat[i][j]) % 2 != 0){
                        find = false;
                        break;
                    }
                }
            }
            if (find){
                res = Math.min(res, aka.size());
            }
        }
        return res;
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int max = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return b[1] - a[1];
        });
        for (int i = 0; i < k; i++) {
            pq.add(new int[]{i, nums[i]});
        }
        ans[0] = pq.peek()[1];
        for (int i = k, j = 1; i < n; i++, j++) {
            pq.add(new int[]{i, nums[i]});
           while (!pq.isEmpty() && pq.peek()[0] <= (i - k) ){
               pq.poll();
           }
           ans[j] = pq.peek()[1];
        }
        return ans;
    }
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] dpl = new int[n + 1];
        int[] dpr = new int[n + 1];
        dpl[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[i] > nums[j]){
                    dpl[i] = Math.max(dpl[i], dpl[j] + 1);
                }
            }
        }
        dpr[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i ; j--) {
                if(nums[i] > nums[j]){
                    dpr[i] = Math.max(dpr[j] + 1, dpr[i]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int v = dpl[i] + dpr[i] - 1;
            res = Math.min(res, n - v);
        }
        return res;
    }
    class ProductOfNumbers {
        List<Long> list = new ArrayList<>();
        long tot = 1;
        public ProductOfNumbers() {
            list.add(1l);
        }

        public void add(int num) {
            tot *= num;
            list.add(tot);
        }
        // 1 3 9
        public int getProduct(int k) {
            long pre = list.get(list.size() - k);
            return (int) (tot / pre);
        }
    }
    public boolean canPartition(int[] nums) {
        int cnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            cnt += nums[i];
        }
        Arrays.sort(nums);
        boolean[] dp = new boolean[cnt + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j + nums[i] <= cnt; j++) {
                dp[j + nums[i]] |= (dp[i] && dp[nums[i]]);
            }
        }
        return dp[cnt / 2];
    }
    public int longestSubstring(String s, int k) {
        int n = s.length();
        char[] cs = s.toCharArray();
        List<Integer>[] store = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            store[i] = new ArrayList<>();
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int idx = cs[i] - 'a';
            store[idx].add(i);
            if(store[idx].size() >= k){
                int max = 0;
                for (int j = 0; j < 26; j++) {
                    int jdx  = cs[j] - 'a';
                    if(store[jdx].size() < k){
                        max = Math.max(max, store[jdx].get(store[jdx].size() - 1));
                    }
                }
                PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
                    return a[1] - b[1];
                });
                for (int j = 0; j < 26; j++) {
                    int jdx  = cs[j] - 'a';
                    if(store[jdx].size() >= k){
                        int sz = store[jdx].size();
                        pq.add(new int[]{jdx, store[jdx].get(sz - k)});
                    }
                }
                while (!pq.isEmpty() && pq.poll()[1] <= max){
                    int[] get = pq.poll();
                    int jdx = get[0];
                    max = Math.max(max, store[jdx].get(store[jdx].size() - 1));
                }
                if(max != i){
                    res = Math.max(max, res);
                }
            }

        }
        return res;
    }
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] cs = s.toCharArray();
        int n = s.length();
        int res = 0;
        char[] store = new char[26];
        int cnt = 0;
        for (int i = 0, j = 0; j < n; j++) {
            int idx = cs[j] - 'a';
            if(store[idx] == 0) {
                cnt++;
            }
            store[idx]++;
            while(cnt > 2){
                int jdx = cs[i] - 'a';
                if(store[jdx] == 1){
                    cnt--;
                }
                i++;
            }
            res = Math.max(res, j - i + 1);

        }
        return res;
    }
    public String largestMultipleOfThree(int[] digits) {
        Arrays.sort(digits);
        int n = digits.length;
        int[][] dp = new int[n + 1][3];
        int[][] par = new int[n + 1][3];//store the index
        for (int i = 1; i <= n; i++) {
            int v = digits[i - 1];
            for (int j = 0; j < 3; j++) {
                if(dp[i-1][j] + 1 >= dp[i][j + v % 3]){
                    par[i][j + v % 3] = i - 1;
                    dp[i][j + v % 3] = dp[i-1][j] + 1;
                }else{
                    par[i][j + v % 3] = -1;
                }
            }
        }
        if(dp[n][0] == 0) return "-1";
        StringBuilder sb = new StringBuilder();
        int ini = 0;
        for (int i = n; i >= 1; i--) {
            int idx = par[i][ini];
            if (idx == -1) continue;
            sb.append(digits[idx]);
        }
        return sb.toString();

    }
    public int smallestRepunitDivByK(int k) {
        // 1 1 1 1 1 1
        // 1 + 10 + 100 + 1000 + 10000 + 100000
        // ak % k and ak * 10 % k
        //15 % 7 and 150 % 7

        //Set<Integer> set = new HashSet<>();
        int ini = 1;
        int mod = 0;
        for (int i = 0; i < k; i++) {
            mod += (ini % k);
            if(mod == 0) return i + 1;
            ini %= k;
            ini *= 10;
        }
        return -1;

    }
}
