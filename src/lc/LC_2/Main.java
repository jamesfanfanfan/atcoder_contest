package lc.LC_2;
import java.util.*;
public class Main {
    public static void main(String[] args) {
    }
    public String findLexSmallestString(String s, int a, int b) {
        // add a to the odd indices
        // rotate s by b position
        // 3456
        // when b is even: we could only add to odd position
        //how to rotate when b is even: find GCD
        // when b is odd: we could add on odd or even position
        // how to rotate when b is odd: still find GCD
        int n = s.length();
        char[] cs = s.toCharArray();
        int limit = b%2==0?10:1;
        String ans = s;
        int rt = gcd(n,b);
        for(int i=0;i<10;i++){
            for(int j=0;j<limit;j++){
                char[] neo = s.toCharArray();
                for(int k=0;k<n;k++){
                    if(k%2==1){
                      int add = a*i;
                      int v = neo[k]-'0';
                      v = (v+add)%10;
                      neo[k] = (char) (v+'0');
                    }else{
                        int add = a*j;
                        int v = neo[k]-'0';
                        v = (v+add)%10;
                        neo[k] = (char) (v+'0');
                    }
                }
                String cmp = new String(neo);
                List<String> ls = new ArrayList<>();
                ls.add(s);
                for(int k=rt;k<n;k+=rt){
                    ls.add(cmp.substring(k)+cmp.substring(0,k));
                }
                ls.add(ans);
                Collections.sort(ls);
                ans = ls.get(0);
            }
        }
        return ans;
    }
    int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        // 1 3 4/ 5 6
        // 2 3 /5 6 7
        // 1 2/ 3
        // 1 /2
        // median means left and right should be same
        // consider even and odd
        int hf = (n+m-1)/2;

        int l = 0;
        int r = m;
        int cp;
        while(l<=r){
            int mid = (l+r)/2;
            cp = hf-mid;
            if(cp>m){
                cp = m;
                mid = hf-cp;
            }else if(cp<0){
                cp = 0;
                mid = hf-cp;
            }

            // compare
            int vnl = cp==0?Integer.MIN_VALUE:nums1[cp-1];
            int vnr = cp==n?Integer.MAX_VALUE:nums1[cp];
            int vml = cp==0?Integer.MIN_VALUE:nums2[cp-1];
            int vmr = cp==n?Integer.MAX_VALUE:nums2[cp];
            if(vmr<vnl){
                r = mid-1;
            }else if(vml>vnr){
                l = mid-1;
            }else{
                if((n+m)%2==0){
                    //return
                }else{

                }
            }

        }
        return -1;


    }
    public boolean isMatch(String s, String p) {
        // . matches single point
        // * matches zero more preceding element
        //dp[i][j]  for the i length of s and j length p, is it matching
        // for not . or not * dp[i][j] = dp[i-1][j] &&  s[i] == s[j] if(j-1=='*)|| dp[i][j-1]
        int n = s.length();
        int m = p.length();
        char[] cs = s.toCharArray();
        char[] cp = s.toCharArray();
//        boolean[][] dp = new boolean[n+1][m+1];
//        dp[0][0] = true;

        // dp[i][j]
        return dfs(n,m,cs,cp);


    }
    Boolean[][] dp = new Boolean[1001][1001];
    boolean dfs(int i, int j, char[] cs, char[] cp){
        if(i==0&&j==0){
            return true;
        }
        if(j==0) return false;
        if(i==0) return cp[j-1] == '*' && dfs(i,j-2,cs,cp);

        if(dp[i][j] != null) return dp[i][j];

        dp[i][j] = false;
        if(cp[j-1] == '*'){
            if(cp[j-2] == cs[i-1]){
                dp[i][j] = dfs(i-1,j,cs,cp);
            }
            dp[i][j] = dfs(i,j-2,cs,cp);
        }else{
            if(cs[i-1] == cp[j-1] || cp[j-1] == '.'){
                dp[i][j] = dfs(i-1,j-1,cs,cp);
            }
        }
        return dp[i][j];
    }
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dum = new ListNode(-1);
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> (a.val-b.val));
        for(ListNode node : lists){
            pq.add(node);
        }
        while(!pq.isEmpty()){
            dum.next = pq.poll();
            dum = dum.next;
            if(dum.next!=null)pq.add(dum.next);
        }
        return dum.next;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        // 1-2-3-4-5
        ListNode dum = new ListNode(-1);
        dum.next = head;
        int len = 0;
        ListNode ct = head;

        while(ct != null) {
            len++;
            ct = ct.next;
        }

        ListNode ans = dum;
        for(int i=0;i<len;i+=k){
            if(i+k>len){
                dum.next = head;
                continue;
            }
            ListNode st = new ListNode(-1);

            st = head;
            ListNode ed = head;
            for(int j=0;j<k;j++){
                ListNode get = head;
                head = head.next;
                get.next = st.next;
                st.next = get;

            }
            dum.next = st.next;
            dum = ed;
        }

        if(len%k==0)dum.next = null;
        return ans.next;
    }
    class node{
        char c;
        boolean isW = false;
        node[] next = new node[26];
        int idx;
        node(char c){
            this.c = c;
        }
    }
    node build(node root, char[] cs, int idx){
        node res = root;
        for(char c : cs){
            if(root.next[c-'a'] == null){
                root.next[c-'a'] = new node(c);
            }
            root = root.next[c-'a'];
        }
        root.isW = true;
        root.idx = idx;
        return res;
    }
    int helper(char[] cs, node root){
        for(int i=0;i<cs.length;i++){
            if(root.next[cs[i]-'a']!=null){
                root = root.next[cs[i]-'a'];
            }else{
                return -1;
            }
        }
        return root.idx;
    }
    public List<Integer> findSubstring(String s, String[] words) {
        if(words.length == 0){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int n = s.length();
        int len = words[0].length();
        Map<String,Integer> mp = new HashMap<>();
        Map<String,Integer> id = new HashMap<>();
        Map<Integer,String> sid = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            mp.put(words[i],mp.getOrDefault(words[i],0)+1);
        }
        int nm = 0;
        for(String sb : mp.keySet()){
            id.put(sb,nm);
            sid.put(nm++,sb);
        }
        int totlen = words.length*len;
        for(int j=0;j<len;j++){
            Map<String,Integer> dc = new HashMap<>();

            List<Integer> list = new ArrayList<>();
            for(int i=j;i<n;i+=len){
                if(i+len>n) continue;
                if(mp.containsKey(s.substring(i,i+len))){
                    list.add(id.get(s.substring(i,i+len)));
                }else{
                    list.add(-1);
                }
            }
            int num = 0;
            for(int i=0;i<list.size();i++){
                if(list.get(i)==-1){
                    num = 0;
                    dc.clear();
                }else{
                    String gs = sid.get(id.get(list.get(i)));
                    dc.put(gs,dc.getOrDefault(gs,0)+1);
                    num++;
                    while(dc.get(gs)>mp.get(gs)){
                        int ft = i-num+1;
                        String dg = sid.get(id.get(ft));
                        dc.put(dg,dc.getOrDefault(dg,0)+1);
                        num--;
                    }
                    if(num==words.length){
                        res.add(i-num+1);
                    }

                }
            }

        }
        return res;

    }
    public int maxRepOpt1(String text) {
        char[] cs = text.toCharArray();
        int n = cs.length;
        int len = 0;
        for(char c = 'a'; c<'z'; c++){
            List<Integer> list = new ArrayList<>();
//            LinkedList<Integer> ed = new LinkedList<>();
//            LinkedList<Integer> st = new LinkedList<>();
            int[] st = new int[n];
            for(int i=0;i<n;i++){
                if(cs[i]==c){
                    list.add(i);
                }
            }
            LinkedList<Integer> q = new LinkedList<>();
            LinkedList<int[]> ans = new LinkedList<>();
            for(int i=0;i<list.size();i++){
                int v = list.get(i);
                while(!q.isEmpty() && v-list.get(q.peek())-1>q.size()){
                    q.poll();
                }
                q.add(i);
                ans.add(new int[]{q.peek(),i,i-q.peek()});
            }
            int[] le = new int[n+1];
            int[] ri = new int[n+1];
            int cnt = 0;
            for(int i=0;i<n;i++){
                le[i] = cnt;
                if(cs[i]==c){
                    cnt++;
                }
            }
            cnt = 0;
            for(int i=n-1;i>=0;i--){
                ri[i] = cnt;
                if(cs[i]==c){
                    cnt++;
                }
            }
            for(int[] ar: ans){
                int l = list.get(ar[0]);
                int r = list.get(ar[1]);
                int nd = ar[2];
                if(nd>1){
                    if(le[l]>0||ri[r]>0){
                        len = Math.max(len,r-l+1);
                    }
                }else{
                    if(le[l]>0||ri[r]>0){
                        len = Math.max(len,r-l+2);
                    }else{
                        len = Math.max(len,r-l+1);
                    }
                }
            }

        }
        return len;
    }
//    public boolean isMatch(String s, String p) {
//        // dp[i][j] means length of i and length of j is good or not
//        //
//        int n = s.length();
//        int m = p.length();
//
//        boolean[][] dp = new boolean[n+1][m+1];
//        dp[0][0] = true;
//        for(int i=1;i<=n;i++){
//            for(int j=1;j<=m;j++){
//                if(p.charAt(j-1)=='*'){// use or not use
//                    dp[i][j] = dp[i][j-1] || dp[i-1][j-1] || dp[i-1][j];
//
//                }else{
//                    if(p.charAt(j-1)=='?' || p.charAt(j-1)==p.charAt(i-1)){
//                        dp[i][j] = dp[i-1][j-1];
//                    }
//                }
//            }
//        }
//        return dp[n][m];
//
//    }
    public int bestTeamScore(int[] scores, int[] ages) {
        // dp[i] means the largest score when the eldest is i
        int n = ages.length;
        int[][] arr = new int[n][2];
        for(int i=0;i<n;i++){
            arr[i] = new int[]{scores[i],ages[i]};
        }
        Arrays.sort(arr, (a,b) -> {
            if(a[1]==b[1]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        int[] dp = new int[n+1];
        int max = 0;
        for(int i=0;i<n;i++){
            dp[i] = arr[i][0];
            for(int j=0;j<i;j++){
                if(arr[i][0]>=arr[j][0]){
                    dp[i] = Math.max(dp[i], dp[j] + arr[i][0]);
                }
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }
    public int maxNonOverlapping(int[] nums, int t) {
        int n = nums.length;
        Set<Long> set = new HashSet<>();
        set.add((long)0);
        long sum = 0;
        int cnt = 0;

        // a b c d e f g
        for(int i=0;i<n;i++){
            sum += nums[i];
            long fd = sum-t;
            if(set.contains(fd)){
                cnt++;
                set.clear();
                set.add((long)0);
                sum = 0;
            }else{
                set.add(sum);
            }
        }
        return cnt;

    }
//    public int bagOfTokensScore(int[] tokens, int p) {
//        LinkedList<Integer> q = new LinkedList<>();
//        Arrays.sort(tokens);
//        for (int i = 0; i < tokens.length; i++) {
//            q.add(tokens[i]);
//        }
//        int cnt = 0;
//        int ans = 0;
//        while(!q.isEmpty()){
//            if(p<q.peek()&&cnt>0){
//                cnt--;
//                p += q.removeLast();
//            }else if(p>=q.peek()){
//                cnt++;
//                p-=q.pop();
//            }else{
//                break;
//            }
//            ans = Math.max(ans,cnt);
//        }
//        return ans;
//    }
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] min = new int[n];
        min[0] = Integer.MAX_VALUE;

        int cmp = nums[0];
        for (int i = 1; i < n; i++) {
            min[i] = cmp;
            cmp = Math.min(nums[i],cmp);
        }
        for (int i = 0; i < n; i++) {
            if(!stack.isEmpty()){
                stack.push(i);
            }else{
                while(!stack.isEmpty() && nums[i]>=nums[stack.peek()]){
                    stack.pop();
                }
                if(!stack.isEmpty() && min[stack.peek()] < nums[i]){
                    return true;
                }
                stack.push(i);
            }
        }
        return false;
    }
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();
        Map<String,Integer> mp = new HashMap<>();
        for(int i=0;i<n;i++){
            if(i+10>n) break;
            String t = s.substring(i,i+10);
            mp.put(t,mp.getOrDefault(t,0)+1);
        }
        for(String sb : mp.keySet()){
            if(mp.get(sb)>1){
                res.add(sb);
            }
        }
        return res;
    }
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char[] cs = keysPressed.toCharArray();
        int n = keysPressed.length();
        int[][] arr = new int[n][2];
        int ms = 0;
        for(int i=0;i<n;i++){
            arr[i] = new int[]{releaseTimes[i]-ms,cs[i]-'a'};
            ms = releaseTimes[i];
        }
        Arrays.sort(arr, (a,b) ->{
            if(a[0]==b[0]){
                return a[1]-b[1];
            }
            return a[0]-b[0];
        });
        return (char)(arr[n-1][1]+'a');
    }
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        int n = nums.length;
        int m = l.length;
        for (int i = 0; i < m; i++){
            int li = l[i];
            int ri = r[i];
            List<Integer> aka = new ArrayList<>();
            for(int j=li;j<ri;j++){
                aka.add(nums[j]);
            }
            Collections.sort(aka);
            boolean ari = true;
            if(aka.size()>1){
                int dif = aka.get(1)-aka.get(0);
                for(int j=0;j<aka.size()-1;j++){
                    if(aka.get(j+1)-aka.get(j)!=dif){
                        ari = false;
                        break;
                    }
                }
            }else{
                ari = false;
            }
            res.add(ari);

        }
        return res;
    }
//    public int minimumEffortPath(int[][] heights) {
//        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[2]-b[2]));
//        int n = heights.length;
//        int m = heights[0].length;
//        Integer[][] seen = new Integer[n][m];
//        pq.add(new int[]{0,0,0});
//        int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
//        while(!pq.isEmpty()){
//            int[] arr = pq.poll();
//
//        if(seen[arr[0]][arr[1]]!=null){
//                continue;
//            }
//            seen[arr[0]][arr[1]] = arr[2];
//            for(int[] dir : dirs){
//                int pi = dir[0]+arr[0];
//                int pj = dir[1]+arr[1];
//                if(pi<0||pj<0||pi>=n||pj>=m||seen[pi][pj]!=null) continue;
//                int dif = Math.abs(heights[arr[0]][arr[1]] - heights[pi][pj]);
//                pq.add(new int[]{pi,pj,Math.max(dif,arr[2])});
//            }
//        }
//        return seen[n-1][m-1];
//    }
//    public int[][] matrixRankTransform(int[][] matrix) {
//        int n = matrix.length;
//        int m = matrix[0].length;
//        int[][] arr = new int[n*m][3];
//        int cnt = 0;
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                arr[cnt] = new int[]{i,j,matrix[i][j]};
//            }
//        }
//        Arrays.sort(arr, Comparator.comparingInt(a -> a[2]));
//
//
//    }
    Map<Integer,Integer> par = new HashMap<>();
    Map<Integer,List<Integer>> r = new HashMap<>();
    Map<Integer,List<Integer>> c = new HashMap<>();
    public int removeStones(int[][] stones) {

        int n = stones.length;
        for(int i=0;i<n;i++){
            int x = stones[i][0];
            int y = stones[i][1];
            par.put(x+y*10001,-1);
            if(!r.containsKey(x)) r.put(x,new ArrayList<>());
            if(!c.containsKey(y)) c.put(y,new ArrayList<>());
            r.get(x).add(x+y*10001);
            c.get(y).add(x+y*10001);
        }

        for(int k : r.keySet()){
            List<Integer> get = r.get(k);
            for(int i=1;i<get.size();i++){
                union(get.get(0),get.get(i));
            }
        }
        for(int k : c.keySet()){
            List<Integer> get = c.get(k);
            for(int i=1;i<get.size();i++){
                union(get.get(0),get.get(i));
            }
        }
        int cnt = 0;
        for(int i : par.keySet()){
            if(par.get(i)<0) cnt++;
        }
        return n-cnt;


    }
    int find(int i){
        if(par.get(i)<0) return i;
        int get = find(par.get(i));
        par.put(i,get);
        return get;
    }
    void union(int i, int j){
        int pi = find(i);
        int pj = find(j);
        if(pi==pj) return;
        if(par.get(pi)<par.get(pj)){
            par.put(pi,par.get(pi) + par.get(pj));
            par.put(pj,pi);
        }else{
            par.put(pj,par.get(pi) + par.get(pj));
            par.put(pi,pj);
        }
    }
    public int maxProfit(int k, int[] prices) {
        // dp[i][k] as the maximum profit at kth transaction
        // the largest means that we buy at day any day before j and sell it
        int n = prices.length;
        int[] dp = new int[n+1];
        for(int i=1;i<=n;i++){
            dp[i] = -prices[i-1];
        }
        int res = 0;
        for(int i=0;i<Math.min(k,1000);i++){
            int[] neo = new int[n+1];
            int max = -prices[0];
            for(int j=2;j<=n;j++){
                neo[j] = Math.max(dp[j], max+prices[j-1]);
                max = Math.max(max, dp[j]-prices[j-1]);
                res = Math.max(res,neo[j]);
            }
            dp = neo;
        }
        return res;
    }
    public int bagOfTokensScore(int[] tokens, int p) {
        int n = tokens.length;
        Arrays.sort(tokens);
        // 100 200 300 400 500 600
        int i=0, j = n-1;
        int cnt = 0;
        int res = 0;
        while(i<=j){
            if(p>=tokens[i]){
                p -= tokens[i++];
                cnt++;
            }else if(cnt>0){
                p +=  tokens[j--];
                cnt--;
            }else{
                break;
            }
            res = Math.max(res,cnt);
        }
        return res;
    }
    public double champagneTower(int tot, int query_row, int query_glass) {
        int n = query_row;
        double[][] dp = new double[101][101];
        //dp[i][j] = (dp[i-1][j]-glass)/2 + (dp[i-1][j-1]-glass)/2
        dp[0][0] = tot*250.0;
        int size = 250;
        for(int i=1;i<=n;i++){
            for(int j=0;j<=i;j++){
                if(j==0){
                    dp[i][j] = Math.max(0, dp[i-1][j]-size)/2;
                }else if(j==i){
                    dp[i][j] = Math.max(0, dp[i-1][j-1]-size)/2;
                }else{
                    dp[i][j] = Math.max(0, dp[i-1][j]-size)/2 + Math.max(0, dp[i-1][j-1]-size)/2;
                }
            }
        }
        return Math.min(1.0,dp[query_row][query_glass]/size);
    }
//    public int[][] matrixRankTransform(int[][] matrix) {
//        int n = matrix.length;
//        int m = matrix[0].length;
//        int[][] arr = new int[n*m][3];
//        int cnt = 0;
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                arr[cnt++] = new int[]{i,j,matrix[i][j]};
//            }
//        }
//        Arrays.sort(arr, Comparator.comparingInt(a -> a[2]));
//        int[] par = new int[n*m+1];
//        int[] val = new int[n*m+1];
//        HashMap<Integer,List<int[]>>[] row = new HashMap[n];
//        HashMap<Integer,List<int[]>>[] col = new HashMap[n];
//        for(int i=0;i<n;i++){
//            row[i] = new HashMap<>();
//            for(int j=0;j<m;j++){
//                col[j] = new HashMap<>();
//                if(!col[j].containsKey(matrix[i][j])) col[j].put(matrix[i][j],new ArrayList<>());
//                if(!row[i].containsKey(matrix[i][j])) row[i].put(matrix[i][j],new ArrayList<>());
//                col[j].get(matrix[i][j]).add(new int[]{i,j});
//                row[i].get(matrix[i][j]).add(new int[]{i,j});
//            }
//        }
//        Arrays.fill(par,-1);
//        for(HashMap<Integer,List<int[]>> mp : row){
//            for(int v : mp.keySet()){
//                List<int[]> ls = mp.get(v);
//                int[] fs = ls.get(0);
//                for(int j=1;j<ls.size();j++){
//                    int[] get = ls.get(j);
//                    union(par,fs[0]*m+fs[1],get[0]*m+get[1]);
//                }
//            }
//        }
//        for(HashMap<Integer,List<int[]>> mp : col){
//            for(int v : mp.keySet()){
//                List<int[]> ls = mp.get(v);
//                int[] fs = ls.get(0);
//                for(int j=1;j<ls.size();j++){
//                    int[] get = ls.get(j);
//                    union(par,fs[0]*m+fs[1],get[0]*m+get[1]);
//                }
//            }
//        }
//        int[] row_id = new int[n];
//        int[] col_id = new int[m];
//        int[] rowv = new int[n];
//        int[] colv = new int[m];
//        for(int i=0;i<n*m;i++){
//           int[] get = arr[i];
//           int x = get[0];
//           int y = get[1];
//           int v = get[2];
//           int idx = 1;
//           if(row_id[x]==0&&col_id[y]==0){
//               idx = 1;
//           }
//           if(row_id[x]!=0&&rowv[x]<v){
//               idx = Math.max(idx,row_id[x]+1);
//           }else if(row_id[x]!=0&&rowv[x]==v){
//               idx = Math.max(idx,row_id[x]);
//           }
//
//            if(col_id[y]!=0&&colv[y]<v){
//                idx = Math.max(idx,col_id[y]+1);
//            }else if(col_id[y]!=0&&colv[y]==v){
//                idx = Math.max(idx,col_id[y]+1);
//            }
//            col_id[y] = row_id[x] = idx;
//            colv[y] = rowv[x] = v;
//            int ps = find(par,x*m+n);
//            val[ps] = Math.max(val[ps],idx);
//        }
//        int[][] res = new int[n][m];
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                res[i][j] = val[find(par,i*m+j)];
//            }
//        }
//        return res;
//    }
//
//    int find(int[] par, int i){
//        if(par[i]<0) return i;
//        return (par[i] = find(par,par[i]));
//    }
//
//    void union(int[] par, int i, int j){
//        int pi = find(par,i);
//        int pj = find(par,j);
//        if(pi==pj) return;
//        if(par[pi]<par[pj]){
//            par[pi]+=par[pj];
//            par[pj] = pi;
//        }else{
//            par[pj]+=par[pi];
//            par[pi] = pj;
//        }
//    }
    public ListNode detectCycle(ListNode head) {
        ListNode fs = head;
        ListNode sl = head;
        while(fs!=null&&fs.next!=null){
            fs = fs.next.next;
            sl = sl.next;
            if(fs==sl) break;
        }
        if(fs==null||fs.next==null) return null;
        ListNode st = head;
        while(st!=fs){
            fs = fs.next;
            st = st.next;
        }
        return st;

    }
    public int minimumEffortPath(int[][] heights) {
        int l = 0;
        int r = (int) 1e6;
        while(l<r){
            int mid = (l+r)/2;
            if(find(heights,mid)){
                r = mid;
            }else{
                l = mid+1;
            }
        }
        return l;
    }
    int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    boolean find(int[][] heights, int lim){
        int n = heights.length;
        int m = heights[0].length;
        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        boolean[][] find = new boolean[n][m];
        while(!q.isEmpty()){
            int[] get =  q.poll();
            int x = get[0];
            int y = get[1];
            if(x==n-1&&y==m-1){
                return true;
            }
            for(int[] dir : dirs){
                int pi = x+dir[0];
                int pj = y+dir[1];
                if(pi<0||pj<0||pi>=n||pj>=m||find[pi][pj]){
                    continue;
                }
                if(Math.abs(heights[x][y]-heights[pi][pj])>lim) continue;
                find[pi][pj] = true;
                q.add(new int[]{pi,pj});
            }
        }
        return false;
    }
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        List<String> res = new ArrayList<>();
        for(int i=0;i<n;i++){
            int le = nums[i];
            int j = i;
            int pre = le;
            while(j+1<n && nums[j+1]==pre+1){
                pre = nums[j+1];
                j++;
            }
            if(le==nums[j]){
                res.add(String.valueOf(nums[j]));
            }else{
                res.add(String.valueOf(le)+"->"+String.valueOf(nums[j]));
            }
            i = j;
        }
        return res;
    }
    public int minCost(int n, int[] cuts) {
        int len = cuts.length;
        Arrays.sort(cuts);
        int[] cs = new int[len+2];
        for(int i=0;i<len;i++) cs[i+1] = cuts[i];
        cs[0] = 0;
        cs[len+1] = n;
        int[][] dp = new int[len+3][len+3];
        for(int i=0;i<len+3;i++) Arrays.fill(dp[i],Integer.MAX_VALUE/2);
        for(int l=3;l<=len;l++){
            for(int i=0;i+l<=len;i++){
                int j = i+l-1;
                if(l==3){
                    dp[i][j] = cs[j]-cs[i];
                    continue;
                }
                for(int k=i+1;k<j;k++){
                    dp[i][j] = Math.min(dp[i][j],dp[i][k]+dp[k][j]+cs[j]-cs[i]);
                }
            }
        }
        return dp[0][len+2];
    }
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int[] l = new int[n];
        int[] r = new int[n];
        l[0] = seats[0]==0?n+1:-1;
        for(int i=1;i<n;i++){
            if(seats[i]==0){
                l[i] = l[i-1]>0?l[i-1]+1:1;
            }else{
                l[i] = -1;
            }
        }
        r[n-1] = seats[n-1]==0?n+1:-1;
        for(int i=n-2;i>=0;i--){
            if(seats[i]==0){
                r[i] = r[i+1]>0?r[i+1]+1:1;
            }else{
                r[i] = -1;
            }
        }
        int ans = 0;
        for(int i=0;i<n;i++){
            ans = Math.max(ans,Math.min(l[i],r[i]));
        }
        return ans;

    }
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        //dp[i][j] as the max dot product from 0-i and 0 to j
        //dp[i][j] = Math.max(dp[i-1][j-1]+nums[j-1]*nums[i-1], dp[i][j-1],dp[i-1][j])
        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for(int j = 1; j<= m; j++){
                dp[i][j] = Math.max(Math.max(dp[i][j-1],dp[i-1][j]),dp[i-1][j-1] + nums1[i-1] * nums2[j-1]);
            }
        }
        return dp[n][m];
    }
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        //dp[i][j] as the max number of line from 0-i and 0 to j
        //dp[i][j] = Math.max(dp[i-1][j-1]+(nums1[i-1]==nums2[j-1])?1:0,Math.max(dp[i-1][j],dp[i][j-1]);
        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for(int j = 1; j<= m; j++){
                dp[i][j] = Math.max(dp[i-1][j-1]+((nums1[i-1]==nums2[j-1])?1:0),Math.max(dp[i-1][j],dp[i][j-1]));
            }
        }
        return dp[n][m];
    }
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        int[] cnt = new int[n+1];
        int max = 0;
        for(int i = 1; i <= n; i++){
            dp[i] = 1;
            cnt[i] = 1;
            for(int j=1;j<i;j++){
                if(nums[i-1]>nums[j-1]){
                    if(dp[i]<dp[j]+1){
                        cnt[i] = 1;
                        dp[i] = dp[j]+1;
                    }else if(dp[i]==dp[j]+1){
                        cnt[i]++;
                    }

                }
            }
            max = Math.max(max,dp[i]);
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if(dp[i] == max){
                ans += cnt[i];
            }
        }
        return ans;
    }
    public int minCost(String s, int[] cost) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            int j = i;
            //List<Integer> res = new ArrayList<>();
            int tot = 0;
            int max = 0;
            while(j < n && c == cs[j]){
                tot += cost[j];
                max = Math.max(max, cost[j]);
                j ++;
            }
            ans += (tot - max);
            i = j-1;

        }
        return ans;

    }


















}
