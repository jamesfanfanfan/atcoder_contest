package lc.LC_ALL;

import java.util.*;

public class Dec_14 {

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            while (!stack.isEmpty() && nums[idx] > nums[stack.peek()]){
                res[stack.pop()] = nums[idx];
            }
            if (stack.peek() == idx){
                res[i] = -1;
                return res;
            }
            stack.push(idx);
        }
        return res;
    }


    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int idx = n - 1;
        // 1 2
        while (idx >= 1){
            if (nums[idx] <= nums[idx - 1]){
                idx --;
            }else{
                break;
            }
        }
        if (idx == 0){
            reverse(nums, 0, n);
        }
        int end = idx - 1;
        while (idx + 1 < n && nums[idx + 1] > end){
            idx++;
        }
        int tran = nums[end];
        nums[end] = nums[idx];
        nums[idx] = tran;
        reverse(nums, end + 1, n);
    }
    void reverse(int[] arr, int i, int j){
        while (i < j){
            int tran = arr[i];
            arr[i] = arr[j];
            arr[j] = tran;
            i++;
            j--;
        }
    }


    public int nextGreaterElement(int n) {
        char[] cs = String.valueOf(n).toCharArray();
        int len = cs.length;
        Map<Character, Integer> mp = new HashMap<>();
        for(int i = len - 1; i >= 0; i--){
            for(char c = (char)(cs[i] + 1); c <= '9'; c++){
                if(mp.containsKey(c)){
                    cs[mp.get(c)] = cs[i];
                    cs[i] = c;
                    Arrays.sort(cs, i, n);
                    reverse(cs, i, n);
                    return Integer.valueOf(new String(cs));
                }
            }
            mp.put(cs[i], i);
        }
        return -1;
    }
    void reverse(char[] cs, int i, int j){
        for (; i < j ;) {
            char tran = cs[i];
            cs[i] = cs[j];
            cs[j] = tran;
            i++;
            j--;
        }
    }

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        List<int[]>[] arr = new List[2001];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int dis = matDis(workers[i], bikes[i]);
                if (arr[dis] == null) arr[dis] = new ArrayList<>();
                arr[dis].add(new int[]{i, j});
            }
        }
        int[] res = new int[n];
        boolean[] seen = new boolean[m];
        Arrays.fill(res, -1);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].size(); j++) {
                int idx = arr[i].get(j)[0];
                int car = arr[i].get(j)[1];
                if (seen[idx] || res[idx] != -1){
                    continue;
                }
                res[idx] = car;
                seen[car] = true;
            }
        }
        return res;
    }
    int matDis(int[] a1, int[] a2){
        return (Math.abs(a1[0] - a2[0]) + Math.abs(a1[1] - a2[1]));
    }


    public int longestSubstring(String s, int k) {
        int n = s.length();
        int res = 0;
        char[] cs = s.toCharArray();
        for (int i = k; i <= 26; i++) {
            Map<Character, Integer> mp = new HashMap<>();
            int cnt = 0;
            for (int j = 0, st = 0; j < n; j++) {
                mp.put(cs[j], mp.getOrDefault(cs[j], 0) + 1);
                if (mp.get(cs[j]) >= k) cnt++;
                if (mp.size() > i){
                    while (st < j && mp.size() > i){
                        mp.put(cs[st], mp.getOrDefault(cs[st], 0) - 1);
                        if (mp.get(cs[st]) < k) cnt --;
                        if (mp.get(cs[st]) == 0) mp.remove(cs[st]);
                        st++;
                    }
                    if (cnt == mp.size()){
                        res = Math.max(res, j - st + 1);
                    }
                }
            }

        }
        return res;
    }
    public int smallestRangeII(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        if (n <= 1) return 0;
        int res = arr[n - 1] - arr[0];
        for (int i = 1; i < n; i++) {
            int l = arr[i - 1];
            int r = arr[i];
            int max = Math.max(arr[n - 1], l + k);
            int min = Math.min(arr[0], r - k);
            res = Math.min(res, max - min);
        }
        return res;
    }



    Integer[][][] dp;
    int[][] gd;
    int n;
    int m;
    public int cherryPickup(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        dp = new Integer[m + 1][m + 1][n + 1];
        gd = grid;
        return dfs(0, m - 1, 0);
    }
    int[] dirs = new int[]{-1, 0, 1};
    int dfs(int i, int j, int l){
        if (l == n) return 0;
        if (dp[i][j][l] != null) return dp[i][j][l];
        int add = i == j? gd[l][j] : gd[l][i] + gd[l][j];
        int res = 0;
        for(int x : dirs){
            int pi = i + x;
            if(pi < 0 || pi >= m) continue;
            for(int y : dirs){
                int pj = j + y;
                if(pj < 0 || pj >= m) continue;
                res = Math.max(res, dfs(pi, pj, l + 1));
            }
        }
        return (dp[i][j][l] = (res + add));
    }


    public String decodeAtIndex(String s, int k) {
        k--;
        return (dfs(s.toCharArray(), 0, k) + "");
    }

    char dfs(char[] cs, int idx, int k){
        if(k == 0) return cs[idx];
        long cnt = 0;
        long bf = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = idx; i < cs.length; i++){
            if(cs[i] >= '0' && cs[i] <= '9'){
                bf = cnt;
                cnt *= (cs[i] - '0');
            }else{
                sb.append(cs[i]);
            }
            if(k < cnt){
                while (k < cnt) cnt -= bf;
            }
            if(k == cnt) return sb.charAt(sb.length() - 1);
            return dfs(cs,0, (int) (k - cnt));
        }
        return '0';

    }

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        boolean[] res = new boolean[n];
        Arrays.sort(edgeList, (a,b) -> (a[2] - b[2]));
        int[] par = new int[n + 1];
        Arrays.fill(par, -1);
        LinkedList<int[]> aka = new LinkedList<>();
        for (int i = 0; i < queries.length; i++) {
            int[] x = queries[i];
            aka.add(new int[]{x[0], x[1], x[2], i});
        }
        Collections.sort(aka, (a,b) -> (a[2] - b[2]));
        int idx = 0;
        while (aka.size() > 0){
            int[] get = aka.poll();
            while (idx < edgeList.length && edgeList[idx][2] < get[2]){
                union(par, edgeList[idx][0], edgeList[idx][1]);
                idx ++;
            }
            res[get[3]] = find(par, get[0]) == find(par, get[1]);
        }
        return res;
    }
    int find(int[] par, int x){
        if(par[x] < 0) return x;
        return par[x] = find(par, x);
    }
    void union(int[] par, int i, int j){
        int pi = find(par, i);
        int pj = find(par, j);
        if(pi == pj) return;
        if(par[pi] < par[pj]){
            par[pi] += par[pj];
            par[pj] = pi;
        }else{
            par[pj] += par[pi];
            par[pi] = pj;
        }
    }


    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        //LinkedList<Integer> q = new LinkedList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (b[1] - a[1]));
        int max = 0;
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            while (pq.size() > 0 && pq.peek()[0] < (i - k)){
                pq.poll();
            }
            if (!pq.isEmpty()) v += pq.peek()[1];
            pq.add(new int[]{i, v});
            max = v;
        }
        return max;
    }

    public int maximumUniqueSubarray(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        //Set<Integer> set = new HashSet<>();
        int n = nums.length;
        int res = 0;
        int sum = 0;
        for (int i = 0, j = 0; i < n; i++) {
            sum += nums[i];
            if (mp.containsKey(nums[i])){
                int idx = mp.get(nums[i]);
                while (j <= idx){
                    mp.remove(nums[j]);
                    sum -= nums[j];
                    j ++;
                }
            }
            mp.put(nums[i], i);
            res = Math.max(res, sum);
        }
        return res;
    }

    public String reformatNumber(String number) {
        char[] cs = number.toCharArray();
        LinkedList<Character> ls = new LinkedList<>();
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            if(cs[i] != '-' && cs[i] != ' '){
                ls.add(cs[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (ls.size() >= 5){
            for (int i = 0; i < 3; i++) {
                sb.append(ls.poll());
            }
            sb.append("-");
        }
        if (ls.size() == 4){
            sb.append(ls.poll());
            sb.append(ls.poll());
            sb.append("-");
            sb.append(ls.poll());
            sb.append(ls.poll());
        }else{
            while (ls.size() > 0){
                sb.append(ls.poll());
            }
        }
        return sb.toString();
    }
    class Node{
        int key;
        int val;
        Node next;
        Node before;
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    class LRUCache {
        Map<Integer, Node> mp;
        int cnt;
        Node dummyHead;
        Node dummyEnd;
        public LRUCache(int capacity) {
            mp = new HashMap<>();
            cnt = 0;
            dummyHead = new Node(-1,-1);
            dummyEnd = new Node(-1, -1);
            dummyEnd.before = dummyHead;
            dummyHead.next = dummyEnd;
        }

        public int get(int key) {
            if (!mp.containsKey(key)) return -1;
            int val = mp.get(key).val;
            remove(key);
            add(key, val);
            return key;
        }
        void remove(int key){
            Node dele = mp.get(key);
            dele.before = dele.next;
            dele.next.before = dele.before;
            mp.remove(dele);
        }
        void add(int key, int val){
            Node neo = new Node(key, val);
            mp.put(key, neo);
            dummyEnd.next.next = neo;
            neo.next = dummyEnd;
            neo.before = dummyEnd.before;
            dummyEnd.before = neo;
        }

        public void put(int key, int value) {
            add(key, value);
        }
    }
}
