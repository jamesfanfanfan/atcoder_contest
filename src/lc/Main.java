package lc;

import java.util.*;

public class Main {
    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i+1],1000000);
            for(int j=0;j<m;j++){
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+arr[i][j]);
                if(j>0) dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]+arr[i][j]);
                if(j<m-1) dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]+arr[i][j]);
            }
        }
        int res = 1000000;
        for(int i=0;i<m;i++){
            res = Math.min(res,dp[n][i]);
        }
        return res;
    }
    public int maxSumRangeQuery(int[] nums, int[][] rs) {
        int n = nums.length;
        int[] arr = new int[n+1];
        int m = rs.length;
        for(int i=0;i<m;i++){
            int l = rs[i][0];
            int r = rs[i][1]+1;
            arr[l]++;
            arr[r]--;
        }
        int mod = (int)1e9+7;
        int sum = 0;
        int[] ans = new int[n];
        for(int i=0;i<n;i++){
            sum+=arr[i];
            ans[i] = sum;
        }
        Arrays.sort(nums);
        Arrays.sort(ans);
        long as = 1;
        for(int i=n-1;i>=0;i--){
            as += (long)arr[i] * (long) nums[i];
            as%=mod;
        }
        return (int)as;
    }

    // insert

    public int minOperationsMaxProfit(int[] cs, int bc, int rc) {
        int n = cs.length;
        int res = Integer.MIN_VALUE;
        int tot = 0;
        int rem = 0;
        int i=0;
        int ans = 0;
        for(;i<n;i++){
            tot-=rc;
            rem+=cs[i];
            int v = Math.min(4,rem);
            tot+=v*bc;
            rem-=v;
            if(tot>res) ans = i;
            res = Math.max(res,tot);
        }
        while(rem>0){
            i++;
            tot-=rc;
            int v = Math.min(4,rem);
            tot+=v*bc;
            rem-=v;
            if(tot>res) ans = i;
            res = Math.max(res,tot);
        }
        if(res<0) return -1;
        return res;
    }
    int find(int[] par, int i){
        if(par[i]<0) return i;
        return par[i] = find(par,par[i]);
    }
    void union(int[] par, int i, int j){
        int pi = par[i];
        int pj = par[j];
        if(pi==pj) return;
        if(par[pi]<par[pj]){
            par[pi]+=par[pj];
            par[pj] = pi;
        }else{
            par[pj]+=par[pi];
            par[pi] = pj;
        }
    }





    public int maximumRequests(int n, int[][] requests) {
        int m = requests.length;
        int N = 1<<m;
        int ans = 0;
        for(int i=0;i<N;i++){
            List<Integer> res = new ArrayList<>();
            int[] arr = new int[n];
            int cnt = 0;
            for(int j=0;j<20;j++){
                if((1<<j|i)==i){
                    int[] get = requests[j];
                    arr[get[0]]--;
                    arr[get[1]]++;
                    cnt++;
                }
            }
            boolean aka = true;
            for(int j=0;j<n;i++){
                if(arr[i]!=0){
                    aka = false;
                    break;
                }
            }
            if(aka) ans = Math.max(ans,cnt);
        }
        return ans;
    }


    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] arr = new String[n];
        for(int i=0;i<n;i++){
            arr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(int i=n-1;i>=0;i--){
            sb.append(arr[i]);
        }
        return sb.toString();
    }
    public double[] calcEquation(List<List<String>> eqs, double[] vs, List<List<String>> qs) {
        Map<String,Map<String,Double>> mp = new HashMap<>();
        int n = vs.length;
        Set<String> set = new HashSet<>();
        int ct = 0;
        for(List<String> ls:eqs){
            String l = ls.get(0);
            String r = ls.get(1);
            set.add(l);
            set.add(r);
            if(!mp.containsKey(l))mp.put(l,new HashMap<>());
            if(!mp.containsKey(r))mp.put(r,new HashMap<>());
            mp.get(l).put(r,vs[ct]);
            mp.get(r).put(l,1.0/vs[ct++]);
        }
        for(String k:set){
            for(String i:set){
                if(k.equals(i)||!mp.get(i).containsKey(k)) continue;
                for(String j:set){
                    if(i.equals(j)||!mp.get(j).containsKey(k)||mp.get(i).containsKey(j)) continue;
                    double ak = mp.get(i).get(k);
                    double nxt = mp.get(k).get(j);
                    mp.get(i).put(j,ak*nxt);
                    mp.get(j).put(i,1/(ak*nxt));
                }
            }
        }
        double[] res = new double[n];
        ct = 0;
        for(List<String> q:qs){
            String l = q.get(0);
            String r = q.get(1);
            if(!set.contains(l)||!set.contains(r)){
                res[ct] = -1.0;
            }else{
                res[ct] = mp.get(l).get(r);
            }
            ct++;
        }
        return res;
    }

    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        boolean[] store = new boolean[n];
        /// do the first part
        for(int i=0;i<n-1;i++){
            if(i%2==0){
                store[i] = arr[i]<arr[i+1];
            }else{
                store[i] = arr[i]>arr[i+1];
            }
        }
        int st = -1;
        int max = 0;
        for(int i=0;i<n;i++){
            if(store[i]){
                max = Math.max(max,i-st);
            }else{
                st = i;
            }
        }
        store = new boolean[n];
        for(int i=0;i<n-1;i++){
            if(i%2==0){
                store[i] = arr[i]<arr[i+1];
            }else{
                store[i] = arr[i]>arr[i+1];
            }
        }
        st = -1;

        for(int i=0;i<n;i++){
            if(store[i]){
                max = Math.max(max,i-st);
            }else{
                st = i;
            }
        }
        return max;
    }

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n+1];
        Map<Integer,Integer> mp = new HashMap<>();
        int ans = 0;
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                if(mp.containsKey(arr[i]-arr[j])){
                    if(arr[j]>arr[i]){
                        if(mp.containsKey(arr[j]-arr[i])){
                            dp[i] = Math.max(dp[i],mp.get(arr[i])+2);
                        }
                    }else if(arr[j]>arr[i]){
                        if(mp.containsKey(arr[i]-arr[j])){
                            dp[i] = Math.max(dp[i],mp.get(arr[j])+2);
                        }
                    }
                }
            }
            dp[i] = Math.max(1,dp[i]);
            ans = Math.max(ans,dp[i]);
            mp.put(arr[i],dp[i]);
        }
        return ans;
    }


    public boolean canJump(int[] nums) {
        int n = nums.length;
        int now = 0;
        int fut = 0;
        for(int i=0;i<n;i++){
            fut = Math.max(fut,i+nums[i]);
            if(now<=i){
                now = fut;
            }
            if(now<=i) return false;
        }
        return now>=n-1;
    }

    public double new21Game(int N, int K, int W) {
        double[] dp = new double[N];
        dp[0] = 1;// the possibility at 0th try
        double sum = 0.0;
        int st = 0;
        for(int i=1;i<=N;i++){
            if(i-1<K) {
                sum += dp[i - 1];
            }
            while(i-st>W){
                sum-=dp[st++];
            }
            dp[i] = sum*1.0/W;
        }
        double ans = 0;
        for(int i=K;i<=N;i++){
            ans+=dp[i];
        }
        return ans;
    }



    public class Node{
        boolean isW = false;
        char c;
        Node[] nxt = new Node[26];
        Node(char a){
            c = a;
        }
    }
    Node build(char[] cs,Node root){
        Node aka = root;
        int n = cs.length;
        for(int i=0;i<n;i++){
            int idx = cs[i]-'a';
            if(aka.nxt[idx]==null){
                aka.nxt[idx] = new Node(cs[i]);
            }
            aka = aka.nxt[idx];
        }
        aka.isW = true;
        return root;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Node root = new Node('a');
        for(String ss: wordDict){
            build(ss.toCharArray(),root);
        }
        return dfs(s.toCharArray(),0,root);
    }
    boolean dfs(char[] cs, int idx, Node root){
        if(idx==cs.length) return true;
        Node aka = root;
        for(int i=idx;i<cs.length;i++){
            if(aka.nxt[cs[i]-'a']!=null){
                aka = aka.nxt[cs[i]-'a'];
            }else{
                return false;
            }
            if(aka.isW&&dfs(cs,i+1,root)){
                return true;
            }
        }
        return aka.isW;
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        int n = words.length;
        Node root = new Node('a');
        for(int i=0;i<n;i++){
            root = build(words[i].toCharArray(),root);
        }

        for(int i=0;i<n;i++){
            char[] cs = words[i].toCharArray();
        }
        dfs(new ArrayList<>(),root,res);
        return res;
    }
    void dfs(List<String> res, Node root, List<List<String>> aka){
        if(res.size()==res.get(0).length()){
            aka.add(new ArrayList<>(res));
            return;
        }
        int idx = res.size();
        StringBuilder sb = new StringBuilder();
        for(String s:res){
            sb.append(s.charAt(idx));
        }
        Node nxt = root;
        for(char c:sb.toString().toCharArray()){
            if(root.nxt[c-'a']==null){
                return;
            }
            root = root.nxt[c-'a'];
        }
        List<String> get = new ArrayList<>();
        find(get,root,new StringBuilder());
        for(String s: get){
            res.add(s);
            dfs(res,nxt,aka);
            res.remove(res.size()-1);
        }

    }
    void find(List<String> res, Node root, StringBuilder sb){
        if(root.isW){
            res.add(sb.toString());
            return;
        }
        sb.append(root.c);
        for(Node nt:root.nxt){
            if(root!=null){
                find(res,nt,sb);
            }
        }
        sb.deleteCharAt(sb.length()-1);
    }


    public int jump(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        int nxt = 0;
        int fut = 0;
        for(int i=0;i<n;i++){
            int v = nums[i];
            if(nxt==i){
                nxt = fut;
                cnt++;
            }
            fut = nums[i]+i;
            if(fut>=n-1) return cnt;
        }
        return cnt;
    }

//    List<Integer> ans;
    int min = 0;
    Map<Integer,List<Integer>> rd;
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        Map<String,Integer> mp = new HashMap<>();
        for(int i=0;i<names.length;i++){
            mp.put(names[i],i);
        }
        rd = new HashMap<>();
        for(int i=0;i<n;i++){
            rd.put(i,new ArrayList<>());
        }
        for(int[] r:roads){
            rd.get(r[0]).add(r[1]);
            rd.get(r[1]).add(r[0]);
        }

        int len = targetPath.length;
        int[] tar = new int[len];
        for(int i=0;i<len;i++){
            if(mp.containsKey(targetPath[i])){
                tar[i] = mp.get(targetPath[i]);
            }else{
                tar[i] = -1;
            }
        }

        Integer[][] dp = new Integer[101][101];
        //ans = new ArrayList<>();
        System.out.println(mp);
        min = Integer.MAX_VALUE-2;
        int min = Integer.MAX_VALUE;
        int st = -1;
        int[][] nxt = new int[101][101];
        for(int i=0;i<n;i++){
            //int fk = dfs(tar,dp,1,i,nxt);
            int get = dfs(targetPath,dp,1,i,nxt,names)+(i==tar[0]?0:1);
            //System.out.println(get+" fk:"+fk);
            if(get<min){
                st = i;
                min = get;
            }
            System.out.println(get+" i:"+i);
        }
        //st = 0;
        List<Integer> res = new ArrayList<>();
        for(int i=1;i<=tar.length;i++){
            res.add(st);
            st = nxt[st][i];
        }
        int fff = 0;
//        int[] neo = new int[]{0,1,0,1,0,3,1,0,1,0,3,1,0,3,1,0,1,0,1,0,3,1,0,1,0,1,0,1,0,1,0};
//        for(int i=0;i<res.size();i++){
//            if(neo[i]!=mp.get(targetPath[i])){
//                fff++;
//            }
//        }
//        System.out.println("fff:"+fff);
        return res;
    }
    int dfs(String[] tar, Integer[][] dp, int rem, int i, int[][] nxt,String[] names){
        if(rem==tar.length){
            return 0;
        }
        if(dp[i][rem]!=null) return dp[i][rem];
        int ans = Integer.MAX_VALUE/2;
        int son = -1;
        for(int nt : rd.get(i)){
            String s = names[nt];
            int ak = dfs(tar,dp,rem+1,nt,nxt,names)+(s.equals(tar[rem])?0:1);
            if(ak<ans){
                son = nt;
                ans = ak;
            }
        }
        nxt[i][rem] = son;
        return dp[i][rem] = ans;
    }
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int res = 1;
        int j=n-1;
        for(int i=0;i<j;i++){
            while(j>=i&&nums[j]<0) j--;
            if(nums[i]<0){
                swap(nums,i,j);
            }
        }
        int st = 1;
        if(nums[j]<0) j--;
        for(int i=0;i<=j;i++){
            if(st==nums[i]){
                st++;
                while(nums[i]-1<=j&&nums[nums[i]-1]<0) st++;
            }else{
                if(nums[i]-1<=j){
                    nums[nums[i]-1]*=-1;
                }
            }
        }
        return st;
    }
    void swap(int[] arr, int i, int j){
        int mid = arr[i];
        arr[i] = arr[j];
        arr[j]= mid;
    }

    public int minJumps(int[] arr) {
        int n = arr.length;
        int[] seen = new int[n];
        Arrays.fill(seen,Integer.MAX_VALUE/2);
        LinkedList<int[]> pq = new LinkedList<>();
        pq.add(new int[]{0,0});
        Map<Integer,List<Integer>> mp = new HashMap<>();
        for(int i=0;i<n;i++){
            if(!mp.containsKey(arr[i])) mp.put(arr[i],new ArrayList<>());
            mp.get(arr[i]).add(i);
        }
        Set<Integer> set = new HashSet<>();
        boolean[] aka = new boolean[n];
        aka[0] = true;
        for(int d = 0;d<n;d++){
            for(int sz=pq.size();sz>0;sz--){
                int[] get = pq.poll();
                if(n-1==get[0]) return get[1];
                if(get[0]-1>=0){
                    pq.add(new int[]{get[0]-1,get[1]+1});
                    aka[get[0]-1] = true;
                }
                if(get[0]+1<n){
                    pq.add(new int[]{get[0]+1,get[1]+1});
                    aka[get[0]+1] = false;
                }
                if(!set.add(arr[get[0]])) continue;
                for(int nxt: mp.get(arr[get[0]])){
                    pq.add(new int[]{nxt,get[1]+1});
                    aka[nxt] = true;
                }
            }
        }
        while(!pq.isEmpty()){
            int[] get = pq.poll();
            if(n-1==get[0]) return get[1];
            if(!set.add(arr[get[0]])) continue;
            for(int nxt: mp.get(arr[get[0]])){
                pq.add(new int[]{nxt,get[1]+1});
                aka[nxt] = true;
            }
            if(get[0]-1>=0){
                pq.add(new int[]{get[0]-1,get[1]+1});
                aka[get[0]-1] = true;
            }
            if(get[0]+1<n){
                pq.add(new int[]{get[0]+1,get[1]+1});
                aka[get[0]+1] = false;
            }
        }
        return -1;
    }


    public int maxDistance(List<List<Integer>> arrays) {
        int max = 0;
        int l1 = Integer.MAX_VALUE;
        int l2 = Integer.MAX_VALUE;
        int r1 = Integer.MIN_VALUE;
        int r2 = Integer.MIN_VALUE;
        for(List<Integer> arr:arrays){
            if(l1>arr.get(0)){
                l2 = l1;
                l1 = arr.get(0);
            }else if(l2>arr.get(0)){
                l2 = arr.get(0);
            }

            if(r1<arr.get(arr.size()-1)){
                r2 = r1;
                r1 = arr.get(arr.size()-1);
            }else if(r2<arr.get(arr.size()-1)){
                r2 = arr.get(arr.size()-1);
            }
        }
        for(List<Integer> arr:arrays){
            int l = arr.get(0);
            int r = arr.get(arr.size()-1);
            if(r==r1){
                max = Math.max(max,r2-l);
            }else{
                max = Math.max(max,r1-l);
            }
            if(l==l1){
                max = Math.max(max,r-l2);
            }else{
                max = Math.max(max,r-l2);
            }
        }
        return max;
    }




    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> rev = new Stack<>();
        int max = 0;
        int l = -1;
        for(int i=0;i<=n;i++){
            int v = i==n?Integer.MAX_VALUE:arr[i];
            while(!stack.isEmpty()&&v>arr[i]){
                int bf = stack.pop();
                while(!stack.isEmpty()&&stack.peek()==bf){
                    int j = stack.pop();
                    if(j+d>=i) dp[i] = Math.max(dp[i],dp[j]+1);
                    rev.push(j);
                }
                while(!stack.isEmpty()&&!rev.isEmpty()){
                    int k = stack.peek();
                    int j = rev.pop();
                    if(k+d>=j) dp[k] = Math.max(dp[k],dp[j]+1);
                }
            }
        }

        for(int i=0;i<n;i++) max = Math.max(max,dp[i]);
        return max;
    }

    public int findSubstringInWraproundString(String p) {
        int[] dp = new int[26];
        char[] cs = p.toCharArray();
        char[] st = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int n = p.length();
        for(int i=0;i<n;i++){
            int j = i;
            int idx = cs[i]-'a';
            while(j<n&&cs[j]==st[idx]){
                j++;
                idx = (idx+1)%26;
            }
            int get = i;
            while(get<Math.min(i+26,j)){
                dp[cs[get]-'a'] = Math.max(dp[cs[get]-'a'],j-get);
            }
            i = j-1;
        }
        int cnt = 0;
        for (int i=0;i<26;i++){
            cnt+=dp[i];
        }
        return cnt;
    }
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int n = rowSum.length;
        int m = colSum.length;
        int[][] res = new int[n][m];
        for(int i=0;i<n-1;i++){
            for(int j=0;j<m-1;j++){
                res[i][j] = 1;
                rowSum[i]--;
                colSum[j]--;
            }
        }
        for(int i=0;i<n-1;i++){
            res[i][m-1] = rowSum[i];
            colSum[m-1] -= res[i][m-1];
        }
        for(int i=0;i<m;i++){
            res[n-1][i] = colSum[i];
        }
        return res;
    }

    public class LineTree {
        int[] tree;
        int size;

        public LineTree(int n) {
            tree = new int[n * 4 + 1]; // 抛弃掉0点 方便index的扩张 e.g 1的子节点为2， 3 2的子节点为3 4
            size = n;
        }

        public void init() throws Exception {
            if (tree == null) {
                throw new Exception("tree 未初始化");
            }
            for (int i = 0; i < tree.length; i++) {
                tree[i] = 0;
            }
            size = 0;
        }

        // 在这条线段中的n节点 插入权值value；
        public void Update(int n, int value) {
            Update(1, 1, size, n, value);
        }

        private void Update(int index, int left, int right, int n, int value) {
            tree[index] += value;
            if (left == right) {
                return;
            } else {
                int mid = (left + right) >> 1;
                if (mid >= n) {

                    Update(index << 1, left, mid, n, value);
                } else {
                    Update(index << 1 | 1, mid + 1, right, n, value);
                }
            }
        }

        // 差选线段起止之间的权值和
        public int Query(int start, int end) {
            return Query(1, 1, size, start, end);
        }

        private int Query(int index, int left, int right, int start, int end) {
//        System.out.println(left +" "+ right);
            int sum = 0;
            if (left >= start && right <= end) {
                return tree[index];
            }
            int mid = (left + right) >> 1;
            if (mid >= start && left<= end) {
                sum += Query(index << 1, left, mid, start, end);
            }
            if (mid <= end &&  right >= start) {
                sum += Query(index >> 1 | 1, mid + 1, right, start, end);
            }
            return sum;
        }

    }

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        LineTree seg = new LineTree(k+1);
        int n = arrival.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            if(i<k){
                arr[i]++;
                seg.Update(i,arrival[i]+load[i]);
            }else{
                int idx = i%k;
                if(arrival[i]>=seg.Query(idx,n)){
                    int get = helper(seg,idx,n,arrival[i]);
                    arr[get]++;
                    seg.Update(get,arrival[i]+load[i]);
                }else if(arrival[i]>=seg.Query(0,idx)){
                    int get = helper(seg,0,idx,arrival[i]);
                    arr[get]++;
                    seg.Update(get,arrival[i]+load[i]);
                }else{
                    continue;
                }
            }

        }
        int max = 0;
        for(int i=0;i<n;i++){
            max = Math.max(max,arr[i]);
        }
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(arr[i]==max) res.add(i);
        }
        return res;
    }
    int helper(LineTree seg, int l, int r, int ari){
        while(l<r){
            int mid = (l+r)/2;
            if(seg.Query(l,mid+1)<=ari){
                r = mid;
            }else{
                l = mid+1;
            }
        }
        return l;
    }
    public int specialArray(int[] nums) {
        int n = nums.length;
        for(int i=0;i<=1000;i++){
            int cnt = 0;
            for(int j=0;j<n;j++){
                if(nums[j]>=i) cnt++;
            }
            if(cnt==i) return i;
        }
        return -1;
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
    public boolean isEvenOddTree(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        //if(root.val%2==0) return false;
        for(int dep = 0;q.size()>0 ;dep++){
            int pre = dep==0?-1:10000000;
            for(int sz = q.size();sz>0;sz--){
                TreeNode tr = q.poll();
                if(dep%2==0){
                    if(sz%2==0||tr.val<=pre) return false;
                    pre = tr.val;
                }else{
                    if(sz%2==1||tr.val>=pre) return false;
                    pre = tr.val;
                }
                if(tr.left!=null) q.add(tr.left);
                if(tr.right!=null) q.add(tr.right);
            }
        }
        return true;
    }

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> ls = new ArrayList<>();
        for(List<Integer> l:points){
            ls.add(getAngle(new int[]{location.get(0),location.get(1)}, new int[]{l.get(0),l.get(1)}));
        }
        Collections.sort(ls);
        LinkedList<Double> q = new LinkedList<>();
        int res = 0;
        for(int i=0;i<ls.size()*2;i++){
            if(i>=ls.size()){
                int p = i%ls.size();
                double ag = ls.get(p)+360;
                while(!q.isEmpty()&&q.peek()+angle<ag) q.poll();
                q.add(ag);
            }else{
                while(!q.isEmpty()&&q.peek()+angle<ls.get(i)) q.poll();
                q.add(ls.get(i));
            }
            res = Math.max(res,q.size());

        }
        return res;
    }
    public double getAngle(int[] st, int[] ed) {
        double[] s = new double[]{st[0]*1.0,st[1]*1.0};
        double[] e = new double[]{ed[0]*1,0,ed[1]*1.0};
        double angle = (float) Math.toDegrees(Math.atan((e[1]-s[1])/(e[0]-s[0])));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        //int v = arr[n-1];

        TreeMap<Integer,Integer> mp = new TreeMap<>();
        for(int i=n-1;i>=0;i--){
            Integer get = mp.lowerKey(arr[i]);
            if(get!=null){
                int v = arr[i];
                arr[i] = get;
                arr[mp.get(get)] = v;
                return arr;
            }
            mp.put(arr[i],i);
        }
        return arr;
    }
    class ExamRoom {

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1]){
                    return o1[0]-o2[0];
                }
                return o2[1]-o1[1];
            }
        });
        int n;
        TreeSet<Integer> set = new TreeSet<>();
        public ExamRoom(int N) {
            pq.add(new int[]{0,Integer.MAX_VALUE-1});
            n = N;
        }

        public int seat() {
            int[] arr = pq.poll();
            Integer r = set.higher(arr[0]);
            Integer l = set.lower(arr[0]);
            int lmin = l==null?Integer.MAX_VALUE-1:arr[0]-l;
            int rmin = r==null?Integer.MAX_VALUE-1:r-arr[0];
            if(l!=null&&arr[0]-l>1){
                int mid = (arr[0]+l)/2;
                pq.add(new int[]{mid,Math.min(Math.abs(mid-arr[0]),Math.abs(mid-l))});
            }
            if(r!=null&&r-arr[0]>1){
                int mid = (arr[0]+r)/2;
                pq.add(new int[]{mid,Math.min(Math.abs(mid-arr[0]),Math.abs(mid-r))});
            }
            if(r==null&&arr[0]!=n-1){
                pq.add(new int[]{n-1,n-1-arr[0]});
            }

            set.add(arr[0]);
            return arr[0];
        }

        public void leave(int p) {
            pq.clear();
            set.remove(p);
            

        }
    }

//    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
//        List<Double> q = new ArrayList<>();
//        int[] pos = new int[]{location.get(0),location.get(1)};
//        for(List<Integer> p: points){
//            double theta = CountTheta(new int[]{p.get(0)-pos[0],p.get(1)-pos[1]});
//            double ag = (theta>0?theta:2*Math.PI+theta)*360/Math.PI;
//            q.add(ag);
//        }
//        for(Double d: q){
//            q.add(d+360);
//        }
//        int ans = 0;
//        LinkedList<Double> ak = new LinkedList<>();
//        for(int i=0;i<q.size();i++){
//            while (!ak.isEmpty()&&q.get(i)-angle>ak.peek()){
//                ak.poll();
//            }
//            ak.add(q.get(i));
//            ans = Math.max(ans,ak.size());
//        }
//        return ans;
//    }
//
//    double CountTheta(int[] pos){
//        return Math.atan2(pos[0],pos[1]);
//    }

    Map<String,Integer> mp = new HashMap<>();
    Map<String,Integer> mp2 = new HashMap<>();
    public int minimumOneBitOperations(int n) {
        int[] arr = new int[32];
        int idx = 0;
        for (int i = 0; i < 32; i++) {
            if((1<<i|n)==n){
                idx = n;
                arr[i]++;
                idx = i;
            }
        }
        //System.out.println(Arrays.toString(arr));
        return dfs(arr,idx);
    }

    int dfs(int[] arr, int idx){// turn everything into zero
        if(idx==0){
            return arr[idx];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <=idx; i++) {
            sb.append(arr[i]);
        }
        String s = sb.toString();
        if(mp.containsKey(s)) return mp.get(s);

        if(arr[idx]==0){
            return dfs(arr,idx-1);
        }
        int[] neo = new int[32];
        neo[idx-1] = 1;

        //System.out.println(idx+" "+Arrays.toString(neo));
        int res = (helper(arr,idx-1) + 1 + dfs(neo,idx-1));;
        mp.put(s,res);
        return res;

    }
    int helper(int[] arr, int idx){//turn everything into 100000
        if(idx==0){//
            return 1-arr[idx];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <=idx; i++) {
            sb.append(arr[i]);
        }
        String s = sb.toString();
        if(mp2.containsKey(s)) return mp.get(s);

        if(arr[idx]==0){
            return dfs(arr,idx-1);
        }
        int[] neo = new int[32];
        neo[idx-1] = 1;
        int res = 0;

        if(arr[idx]==0){//0101010   we need to convert 101010 into 100000
            res = (1 + helper(arr,idx-1)+dfs(neo,idx-1));
        }else{//1101010 we need to get 0000000
            res = dfs(arr,idx-1);
        }
        mp2.put(s,res);
        return res;
    }

    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int add = 0;
        for(int i=0;i<n;i++){
            add = (add+nums[i])%p;
        }
        Map<Long,Integer> mp = new HashMap<>();// store mod value and the position
        int res = n+1;
        long tot = 0;
        for(int i=0;i<n;i++){
            tot = (tot+nums[i])%p;
            if(mp.containsKey(tot-add)){
                int get = mp.get(tot-add);
                res = Math.min(res,i-get);
            }
            if(mp.containsKey(tot+p-add)){
                int get = mp.get(tot-add+p);
                res = Math.min(res,i-get);
            }
            mp.put(tot,i);
        }
        if(res>n) return -1;
        return res;
    }

    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int min = -1;
        int max = -1;
        int mut = 1;
        int res = 0;
        for (int i = 0; i < n; i++) {
            mut*=nums[i];
            if(mut==0){
                min = -1;
                max = i;
            }else if(mut<0){
                if(min!=-1){
                    res = Math.max(res,i-min);
                }else{
                    min = i;
                }
            }else{
                res = Math.max(res,i-max);
            }

        }
        return res;
    }
    public int maxDepth(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        Stack<Character> sk = new Stack<>();
        int max = 0;
        for(int i=0;i<n;i++){
            if(cs[i]!='('||cs[i]!=')') continue;
            if(cs[i]=='('){
                sk.push('(');
            }else{
                sk.pop();
            }
            max = Math.max(max,sk.size());
        }
        return max;
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        int[] dp = new int[n];
        boolean[][] seen = new boolean[n][n];
        int m = roads.length;
        for(int i=0;i<m;i++){
            int[] get = roads[i];
            seen[get[0]][get[1]] = true;
            seen[get[0]][get[1]] = true;
            dp[get[0]]++;
            dp[get[1]]++;
        }
        int max = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(seen[i][j]){
                    max = Math.max(max,dp[i]+dp[j]-1);
                }else{
                    max = Math.max(max,dp[i]+dp[j]);
                }
            }
        }
        return max;
    }

    boolean helper(String s){
        int need = s.length()/2;
        int n = s.length();
        int[] dp = new int[n];
        int v = 0;
        char[] cs = s.toCharArray();
        for(int i=0;i<n/2;i++){
            int pos = cs[i]-'a';
            v^=(1<<pos);
            dp[i] = v;
        }
        for(int i=n/2;i<n;i++){
            int pos = cs[i]-'a';
            v^=(1<<pos);
            int k = dp[i-need];
            int cnt = 0;
            for(int j=0;j<26;j++){
                int l = ((v|(1<<j))==v)?1:0;
                int r = ((k|(1<<j))==k)?1:0;
                if(l!=r) cnt++;
            }
//            if(cnt<=1&&isPalindrome(s.substring(l,r+1))){
//                return true;
//            }
        }
        return false;
    }
    boolean isPalindrome(String str)
    {

        // Pointers pointing to the beginning
        // and the end of the string
        int i = 0, j = str.length() - 1;

        // While there are characters toc compare
        while (i < j) {

            // If there is a mismatch
            if (str.charAt(i) != str.charAt(j))
                return false;

            // Increment first pointer and
            // decrement the other
            i++;
            j--;
        }

        // Given string is a palindrome
        return true;
    }

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[] res = new int[n-1];
        int len = edges.length;
        Integer[][] dp = new Integer[n+1][n+1];
        for(int i=0;i<len;i++){
            int[] get = edges[i];
            dp[get[0]][get[1]] = 1;
            dp[get[1]][get[0]] = 1;
        }
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                if(dp[k][i]==null) continue;
                for(int j=1;j<=n;j++){
                    if(dp[k][j]==null) continue;
                    if(i==k||i==j||k==j) continue;
                    dp[i][j] = dp[j][i] = dp[i][k]+dp[k][j];
                }
            }
        }
        int N = 1<<15;
        int[] st = new int[N];
        for(int i=1;i<N;i++){
            List<Integer> aka = new ArrayList<>();
            for(int j=0;j<15;j++){
                if((i|1<<j)==i){
                    aka.add(j+1);
                }
            }
            boolean isTree = true;
            int max = 0;
            dp = new Integer[n+1][n+1];
//            for(int i=0;i<len;i++){
//                int[] get = edges[i];
//                dp[get[0]][get[1]] = 1;
//                dp[get[1]][get[0]] = 1;
//            }
            for(int k:aka){
                for(int t:aka){
                    if(k==t||dp[k][t]==null) continue;
                    for(int j:aka){
                        if(j==k||j==t||dp[j][k]==null) continue;
                        dp[t][j] = dp[j][t] = dp[t][k]+dp[j][k];

                    }
                }
            }
            for(int p:aka){
                for(int j:aka){
                    if(p==j) continue;
                    if(dp[p][j]==null){
                        isTree = false;
                        break;
                    }else{
                        max = Math.max(dp[p][j],max);
                    }
                }
            }

            if(!isTree) continue;
            res[max-1]++;
        }
        return res;
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if(sum%2!=0) return false;
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        Arrays.sort(nums);
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                dp[j+nums[i]] |= dp[j];
            }
        }
        return dp[sum/2];
    }

    public boolean checkPalindromeFormation(String a, String b) {
        return check(a,b)||check(b,a);
    }

    boolean check(String a, String b){
        int n = a.length();
        int i=0,j=n-1;
        for(;i<n;i++,j--){
            if(a.charAt(i)!=b.charAt(j)){
                break;
            }
        }
        while(i<j&&b.charAt(i)==b.charAt(j)){
            i++;
            j--;
        }
        return i==j;
    }

    public int rob(int[] nums) {
        // dp[i] = largest amount getting from this house
        int[] neo = new int[nums.length-1];
        for(int i=0;i<neo.length;i++) neo[i] = nums[i+1];
        return Math.max(helper(Arrays.copyOf(nums,nums.length-1)),helper(neo));
    }
    int helper(int[] arr){
        int n = arr.length;
        int pre = 0;
        int now = arr[0];
        //int max = arr[0];
        for(int i=1;i<n;i++){
            pre = now;
            now = Math.max(now,pre+arr[i]);
        }
        return now;
    }
    public boolean buddyStrings(String a, String b) {
        char[] cs = a.toCharArray();
        char[] cb = b.toCharArray();
        int n = a.length();
        int cnt = 0;
        int[] dp = new int[26];
        for(int i=0;i<n;i++){
            if(cs[i]!=cb[i]) {
                cnt++;
            }else{
                dp[cs[i]-'a']++;
            }
            if(cnt>2) return false;
        }
        boolean equal = false;
        for(int i=0;i<26;i++){
            if(dp[i]>1) equal = true;
        }
        return cnt==2||equal;
    }
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public ListNode rotateRight(ListNode head, int k) {
        if(head==null) return head;
        ListNode go = head;
        int len = 1;
        while (go.next != null){
            go = go.next;
            len++;
        }
        go.next = head;
        int rem = k-k%len;
        for(int i=0;i<rem;i++){
            go = go.next;
        }
        head = go.next;
        go.next = null;
        return head;

    }

    public int maxLengthBetweenEqualCharacters(String s) {
        String[] arr = new String[26];
        int[][] st = new int[26][2];
        for(int i=0;i<26;i++){
            st[i] = new int[]{-1,-1};
        }
        int n = s.length();
        List<String> res = new ArrayList<>();
        char[] cs = s.toCharArray();
        int len = 0;
        for(int i=0;i<n;i++){
            int idx = cs[i]-'a';
            if(st[idx][0]==-1){
                st[idx][0] = i;
            }else{
                st[idx][1]= i;
                len = Math.max(len,st[i][1]-st[i][0]-1);
            }
        }
        return len;

    }
    public int bestTeamScore(int[] scores, int[] ages) {
        List<Integer> ls = new ArrayList<>();
        int n = scores.length;
        for(int i=0;i<n;i++){
            ls.add(i);
        }
        Collections.sort(ls,(a,b)->{
            return ages[a]-ages[b];
        });

        int[] dp = new int[n+1];
        int ans = 0;
        for(int i=0;i<n;i++){
            int idx = ls.get(i);
            int si = scores[idx];
            int max = si;
            int ai = ages[idx];
            int tot = 0;
            for(int j=0;j<i;j++){
                int idj = ls.get(j);
                if(si>scores[idj]||ai==ages[idj]){
                    max = Math.max(max,si+dp[j]);
                }
            }
            dp[i] = max;
            ans = Math.max(max,ans);
        }
        return ans;
    }

    int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        return gcd(q, p % q);
    }

    public List<Boolean> areConnected(int n, int t, int[][] queries) {
        int[] par = new int[n+1];
        List<Boolean> res = new ArrayList<>();
        Arrays.fill(par,-1);
        for(int i=t;i<=n;i++){
            for(int j=1;j*i<=n;j++){
                un(par,i,i*j);
                un(par,j,i*j);
            }

        }
        for(int[] q:queries){
            if(fd(par,q[0])==fd(par,q[1])){
                res.add(true);
            }else{
                res.add(false);
            }
        }
        return res;
    }
    int fd(int[] par, int i){
        if(par[i]<0) return i;
        return par[i] = find(par,par[i]);
    }

    void un(int[] par, int i, int j){
        int pi = fd(par,i);
        int pj = fd(par,j);
        if(pi==pj) return;
        if(par[pi]<par[pj]){
            par[pi]+=par[pj];
            par[pj]= pi;
        }else{
            par[pj]+=par[pi];
            par[pi] = pj;
        }

    }


    public String findLexSmallestString(String s, int a, int b) {
        // a is add
        // b is rotate
        char[] cs = s.toCharArray();
        int n = s.length();
        String ans = s;
        if(b%2==0){
            for(int cnt=0;cnt<20;cnt++){
                char[] get = Arrays.copyOf(cs,n);
                int add = a*20;
                for(int i=0;i<n;i+=2){
                    int v = get[i]-'0';
                    v = (v+add)%10;
                    get[i] = (char)(v+'0');
                }
                List<String> aka = new ArrayList<>();
                aka.add(s);
                String fm = new String(get);
                for(int i=b;i<n;i+=b){
                    aka.add(fm.substring(n-i)+fm.substring(0,n-i));
                }
                Collections.sort(aka);
                ans = aka.get(0);
            }
        }else{
            for(int i=0;i<10;i++){
                char[] get = Arrays.copyOf(cs,n);
                int add = a*i;
                for(int f=0;f<n;f+=2){
                    int v = get[f]-'0';
                    v = (v+add)%10;
                    get[f] = (char)(v+'0');
                }
                for(int j=0;j<10;j++){
                    char[] ak = Arrays.copyOf(get,n);
                    add = a*j;
                    for(int f=1;f<n;f+=2){
                        int v = ak[f]-'0';
                        v = (v+add)%10;
                        ak[f] = (char)(v+'0');
                    }
                    List<String> aka = new ArrayList<>();
                    aka.add(s);
                    String fm = new String(get);
                    for(int o=b;o<n;o+=b){
                        aka.add(fm.substring(n-o)+fm.substring(0,n-o));
                    }
                    Collections.sort(aka);
                    ans = aka.get(0);

                }
            }
        }
        return ans;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fs = head;
        ListNode sl = head;
        while(fs!=null&&fs.next!=null){
            fs = fs.next.next;
            sl = sl.next;
            if(fs==sl){
                ListNode st = head;
                while(st!=sl){
                    st = st.next;
                    sl = sl.next;
                }
                return st;
            }
        }
        // if there is no cycle
        return null;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // reverse:  we could have two dummy node
        // 1 -> 2 -> 3 -> 4-> 0
        // (-1) -> 2
        //(-1) -> 3 -> 2
        int cnt = 1;
        ListNode st = new ListNode(-1);
        ListNode ed = new ListNode(-1);
        ListNode beg = new ListNode(-1);
        beg.next = head;
        ListNode res = beg;
        while(cnt<m){
            beg = beg.next;
            head = head.next;
            cnt++;
        }
        st = beg;
        //

        beg = beg.next;
        while(cnt<=n){
            ListNode trans = ed.next;
            ed.next = head;
            head = head.next;
            ed.next.next = trans;
            cnt++;
        }
        st.next = ed.next;
        beg.next = head;
        return res.next;
    }

    int mod = (int) 1e9+7;

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[n+1][n+1];
        for(int i=0;i<=n;i++) dp[i][0] = 1;
        long[] tot = new long[n+1];
        for(int i=1;i<=n;i++){
            long[] neo = Arrays.copyOf(tot,n+1);
            for(int j=Math.min(k,i-1);j>0;j--){
                dp[i][j] = (tot[j-1]+dp[i-1][j])%mod;
                neo[j] = (tot[j]+dp[i][j])%mod;
            }
            tot = neo;
        }
        long res = 0;
        for(int i=0;i<=n;i++){
            res = (res+dp[i][k])%mod;
        }
        return (int)res;
    }
//    Integer[][] dp = new Integer[1001][1001];
//    int dfs(int n, int k){
//        if(k==0) return 1;
//        if(n==0) return 0;
//        if(dp[n][k]!=null) return dp[n][k];
//        long res = 0;
//        for(int j=n-1;j>=0;j--){
//            res += dfs(j,k-1)*(n-j);
//            res%=mod;
//        }
//        return (dp[n][k] = (int) res);
//
//    }

    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n+1][m+1];
        int[][] row = new int[n][m];
        int[][] col = new int[n][m];
        for(int i=0;i<n;i++){
            row[i][0] = matrix[i][0];
            for(int j=1;j<m;j++){
                row[i][j] = matrix[i][j]==1?row[i][j-1]+1:0;
            }
        }
        for(int j=0;j<m;j++){
            dp[0][j] = matrix[0][j];
            for(int i=1;i<n;i++){
                col[i][j] = matrix[i][j]==1?col[i-1][j]+1:0;
            }
        }
        for(int i=0;i<m;i++) dp[0][i] = matrix[0][i];
        for(int i=0;i<n;i++) dp[i][0] = matrix[i][0];
        int max = 1;
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                int v = dp[i-1][j-1];
                if(row[i][j]>=v&&col[i][j]>=v){
                    dp[i][j] = v+1;
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max*max;
    }





}
