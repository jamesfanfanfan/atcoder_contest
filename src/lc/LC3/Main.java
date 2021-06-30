package lc.LC3;

import java.util.*;

public class Main {
    int mod = (int) 1e9 + 7;


    public int maxSumDivThree(int[] nums) {
        // 0 1 2
       // int[] arr = new int[3];
        int n = nums.length;
        int tot = 0;
        List<Integer>[] arr = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            arr[nums[i] % 3].add(nums[i]);
            tot += nums[i];
        }
        Collections.sort(arr[nums[1]]);
        Collections.sort(arr[nums[2]]);

        if(tot % 3 == 1){
            if(arr[1].size() > 0){
                tot -= arr[1].get(0);
            }else if(arr[2].size() > 1){
                tot -= arr[2].get(0);
                tot -= arr[2].get(1);
            }
        }else if(tot % 3 == 2){
            if(arr[2].size() > 0){
                tot -= arr[2].get(0);
            }else if(arr[1].size() > 0){
                tot -= arr[1].get(0);
                tot -= arr[1].get(1);
            }
        }
        return tot;
    }
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public int[] frequencySort(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        List<int[]> aka = new ArrayList<>();
        Map<Integer,Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            //if(!mp.containsKey(nums[i])) mp.put(i,0);
            mp.put(nums[i],mp.getOrDefault(nums[i],0)+1);
        }
        for(int k : mp.keySet()){
            aka.add(new int[]{k,mp.get(k)});
        }
        Collections.sort(aka,(a,b) -> {

            if(a[1]==b[1]){
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        for(int i=0;i<aka.size();i++){
            int[] get = aka.get(i);
            for(int j=0;j<get[1];j++){
                res.add(get[0]);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;


    }
    public int maxWidthOfVerticalArea(int[][] points) {
        int n = points.length;
        Arrays.sort(points, (a,b) -> (a[0]-b[0]));
        int max = 0;
        for(int i=1;i<n;i++){
            max = Math.max(max, points[i][0]-points[i-1][0]);
        }
        return max;
    }
    public int countSubstrings(String s, String t) {
        int cnt = 0;
        int cs = s.length();
        int ct = t.length();
        int[][] dpl = new int[cs+1][ct+1];
        int[][] dpr = new int[cs+1][ct+1];
        //start from the left
        for (int i = 1; i <= cs; i++) {
            for(int j = 1; j <= ct; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    dpl[i][j] = dpl[i - 1][j - 1] + 1;
                }
            }
        }
        String rs = new StringBuilder(s).reverse().toString();
        String rt = new StringBuilder(t).reverse().toString();
        for (int i = 1; i <= cs; i++) {
            for(int j = 1; j <= ct; j++){
                if(rs.charAt(i - 1) == rt.charAt(j - 1)) {
                    dpr[i][j] = dpr[i - 1][j - 1] + 1;
                }
            }
        }
        int res = 0;
        for(int i = 0; i < cs; i++){
            for(int j = 0; j < ct; j++){
                int ri = ct - i - 1;
                int rj = ct - j - 1;
                int l = dpl[i-1][j-1];
                int r = dpr[ri-1][rj-1];
                if(s.charAt(i) != t.charAt(j)){
                    res += ((l + 1) * (r + 1));
                }
            }
        }
        return res;
    }
    public int numWays(String[] words, String t) {
        Map<Character,List<Integer>> mp = new HashMap<>();
        char[] ct = t.toCharArray();
        int nt = t.length();
        for(int i=0;i<nt;i++){
            if(!mp.containsKey(ct[i])) mp.put(ct[i],new ArrayList<>());
            mp.get(ct[i]).add(i);
        }
        long[] dp = new long[nt+1];
        dp[0] = 1;
        for(int i=0;i<words[0].length();i++){
            int[] cnt = new int[26];
            for(int j=0;j<words.length;j++){
                cnt[words[j].charAt(i)-'a']++;
            }
            long[] ndp = Arrays.copyOf(dp,nt+1);
            for(int j=0;j<26;j++){
                if(cnt[j]==0) continue;
                for(int get : mp.get((char)(j+'a'))){
                    if(get>i) break;
                    ndp[get+1] += dp[get]*cnt[j];
                    ndp[get+1]%=mod;
                }
            }
            dp = ndp;
        }
        return (int) dp[nt];

    }
    class RandomizedCollection {
        Map<Integer, Set<Integer>> mp = new HashMap<>();
        Random rd = new Random();
        List<Integer> ct = new ArrayList<>();

        /** Initialize your data structure here. */
        public RandomizedCollection() {

        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            if(!mp.containsKey(val)){
                mp.put(val, new HashSet<>());
                ct.add(val);
            }
            mp.get(val).add(ct.size()-1);

            return true;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if(!mp.containsKey(val)){
                return false;
            }else{
                Set<Integer> q = mp.get(val);
                int idx = q.iterator().next();
                ct.set(idx,ct.get(ct.size()-1));
                mp.get(ct.get(ct.size()-1)).remove(ct.size()-1);
                mp.get(ct.get(ct.size()-1)).add(idx);
                ct.remove(ct.size()-1);
            }
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            int v = rd.nextInt(ct.size());
            return ct.get(v);
        }
    }
    public ListNode insertionSortList(ListNode head) {
        ListNode dum = new ListNode(-1);
        dum.next = head;
        ListNode tra = head;
        while(tra != null){
            ListNode find = dum.next;
            ListNode findbefore = dum;
            while(find.val < tra.val){
                findbefore = find;
                find = find.next;
            }
            if(find == tra){
                tra = tra.next;
                find.next = null;
            }else{
                findbefore.next = tra;
                tra = tra.next;
                findbefore.next.next = find;
            }
        }
        return dum.next;
    }
    public String kthSmallestPath(int[] destination, int k) {
        int[][] dp = new int[16][16];
        int x = destination[0];
        int y = destination[1];
        int[][] ncr = new int[15 + 1][15 + 1];

        for (int i = x; i >= 0 ; i--) {
            for (int j = y; j >= 0 ; j--) {
                if(i == x && j == y){
                    dp[i][j] = 1;
                }else if(i == x){
                    dp[i][j] = dp[i][j+1];
                }else if(y == j){
                    dp[i][j] = dp[i+1][j];
                }else{
                    dp[i][j] = dp[i][j+1] + dp[i+1][j];
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        dfs(0,0, dp, k-1, sb,x,y);
        return sb.toString();

    }
    void dfs(int x, int y, int[][] dp, int k, StringBuilder sb, int r, int c){
        if(x==r && y==c){
            return;
        }
        if(x==r){
            while(y<c){
                sb.append("H");
                y++;
            }
            return;
        }else if(y==c){
            while(x<r){
                sb.append("V");
                x++;
            }
            return;
        }
        if(k < dp[x][y+1]){
            sb.append("H");
            dfs(x, y+1, dp, k, sb, r, c);
        }else{
            sb.append("V");
            dfs(x+1, y, dp, k-dp[x][y+1], sb, r, c);
        }

    }
    public int maxPower(String s) {
        char[] cs = s.toCharArray();
        char aka = '1';
        int n = cs.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            while(j < n && cs[i] == cs[j]){
                j++;
            }
            ans = Math.max(ans, j-i);
            i = j-1;
        }
        return ans;

    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer,List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            int l = edge[0];
            int r = edge[1];
            mp.get(l).add(r);
            mp.get(r).add(l);
        }
        int[] get = dfs(mp, -1,0);
        List<Integer> ans = ndfs(mp, -1, get[0]);
        List<Integer> res = new ArrayList<>();
        if(ans.size() % 2 == 0){
            res.add((ans.size()-1)/2);
            res.add(ans.size()/2);
        }else{
            res.add(ans.size()/2);
        }
        return res;
    }
    int[] dfs(Map<Integer,List<Integer>> mp, int par, int i){
        int res = 0;
        int[] ans = new int[2];
        for(int k : mp.get(i)){
            if(i == par) continue;
            int[] get = dfs(mp,i,k);
            if(get[1] > ans[1])  ans[1] = get[1];
        }
        ans[0] = i;
        ans[1]++;
        return ans;
    }
    List<Integer> ndfs(Map<Integer,List<Integer>> mp, int par, int i){
        List<Integer> res = new ArrayList<>();
        for(int k : mp.get(i)){
            if(i == par) continue;
            List<Integer> get = ndfs(mp,k,i);
            if(get.size() > res.size()) res = get;
        }
        res.add(i);
        return  res;
    }
    public int countVowelStrings(int n) {
        int[][] dp = new int[6][n+1];
        //dp[i][j] how many ways we could get from total j place with ith char
        //dp[i][j] from 0 to i add up dp[k][j-1]
        for (int i = 0; i < 6; i++) {
            dp[i][1] = 1;
        }
        int[] tot = new int[n+1];
        tot[1] = 5;
        for(int ch = 0; ch < 5; ch++){
            int[] neo = Arrays.copyOf(tot,n+1);
            for (int i = 2; i <= n; i++) {
                //dp[ch][i] = tot[i-1];
                neo[i] += tot[i-1];
            }
            tot = neo;
        }
        return tot[n];

    }
    int[][] binomialCoeff(int n, int k) {
        int[][] ncr = new int[n+1][n+1];
        int i, j;


        for (i = 0; i <= n; i++)
        {
            for (j = 0; j <= Math.min(i, k); j++)
            {
                if (j == 0 || j == i)
                    ncr[i][j] = 1;
                else
                    ncr[i][j] = ncr[i-1][j-1] + ncr[i-1][j];
            }
        }

        return ncr;
    }
    public int minCostToMoveChips(int[] position) {
        int n = position.length;
        int cnt = 0;
        int o = 0;
        int e = 0;
        for (int i = 0; i < n; i++) {
            if(position[i] % 2 == 0){
                e++;
            }else{
                o++;
            }
        }
        return Math.min(e,o);

    }
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        long tot = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> (b-a));
        PriorityQueue<Integer> used = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            if(heights[i] > heights[i-1]){
                int h = (heights[i] - heights[i-1]);
                tot += h;
                if(tot > bricks){
                    if(!used.isEmpty() && used.peek() < h){
                        tot -= (h-used.peek());
                        used.add(h);
                        pq.add(used.poll());
                    }
                    while (!pq.isEmpty() && ladders > 0 && tot > bricks){
                        used.add(pq.peek());
                        tot -= pq.poll();
                        ladders--;
                    }
                    if(tot > bricks){
                        return i-1;
                    }
                }else{
                    pq.add(h);
                }
            }else{
                continue;
            }

        }
        return n - 1;

    }
    public int smallestDivisor(int[] nums, int threshold) {
        int n = nums.length;
        Arrays.sort(nums);
        int l = 0, r = Integer.MAX_VALUE/2;
        while (l < r){
            int mid = (l + r) / 2;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                cnt += ((nums[i] - 1)/ mid + 1);
            }
            if(cnt <= threshold){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }
    public String alienOrder(String[] words) {
        int[] par = new int[26];
        Arrays.fill(par, -1);

        Map<Character, List<Character>> mp = new HashMap<>();
        Set<Character> set = new HashSet<>();
        int n = words.length;
        for (int i = 1; i < n; i++) {
            char[] cs1 = words[i-1].toCharArray();
            char[] cs2 = words[i].toCharArray();
            for(char c : cs1) set.add(c);
            for(char c : cs2) set.add(c);
            for (int j = 0; j < Math.min(cs1.length, cs2.length); j++) {
                if(cs1[j] != cs2[j]){
                    if(!mp.containsKey(cs2[j])) mp.put(cs2[j], new ArrayList<>());
                    mp.get(cs2[j]).add(cs1[j]);
                    par[cs1[j] - '0']++;
                }
            }
        }
        LinkedList<Character> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for(char c : mp.keySet()){
            if(par[c-'a'] == 0) q.add(c);
        }
        boolean[] seen = new boolean[26];
        while (!q.isEmpty()){
            char c = q.poll();
            if(seen[c='a']) return "";
            set.remove(c);
            seen[c='a'] = true;
            sb.append(c);
            for(char nxt : mp.get(c)){
                if(par[nxt-'a'] == 1) q.add(nxt);
                par[nxt - 'a']--;
            }
        }
        for (int i = 0; i < 26; i++) {
            if(par[i] > 0) return "";
        }
        for(char c: set){
            sb.append(c);
        }
        return sb.toString();

    }
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a,b) -> (a[0] - b[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[1] - b[1]));
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if(!pq.isEmpty() && pq.poll()[1] <= intervals[i][0]){
                pq.poll();
            }
            cnt = Math.max(cnt, pq.size());
            pq.add(intervals[i]);
        }
        return cnt;
    }

//    public List<String> findWords(char[][] board, String[] words) {
//
//    }
}
