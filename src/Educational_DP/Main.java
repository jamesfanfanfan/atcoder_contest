package Educational_DP;
//https://atcoder.jp/contests/dp/
import java.util.*;

public class Main {
    int mod = (int) 1e9+7;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        main.abc103_C(sc);
    }

    void abc103_C(Scanner in){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Integer[] mx_odd = new Integer[2];
        Integer[] mx_even = new Integer[2];// store the max and the next max
        Map<Integer, Integer> odd = new HashMap<>();
        Map<Integer, Integer> even = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if(i%2==0){
                even.put(arr[i],even.getOrDefault(arr[i],0)+1);
            }else{
                odd.put(arr[i],odd.getOrDefault(arr[i],0)+1);
            }
        }
        for(int k : even.keySet()){
            if(mx_even[0] == null){
                mx_even[0] = k;
            }else if(even.get(mx_even[0]) <= even.get(k)){
                mx_even[1] = mx_even[0];
                mx_even[0] = k;
            }else if(mx_even[1] == null || even.get(mx_even[1]) < even.get(k)){
                mx_even[1] = k;
            }
        }
        for(int k : odd.keySet()){
            if(mx_odd[0] == null){
                mx_odd[0] = k;
            }else if(odd.get(mx_odd[0]) <= odd.get(k)){
                mx_odd[1] = mx_odd[0];
                mx_odd[0] = k;
            }else if(mx_odd[1] == null || odd.get(mx_odd[1]) < odd.get(k)){
                mx_odd[1] = k;
            }
        }

        if(odd.get(mx_odd[0]) == odd.get(mx_odd[1]) && mx_odd[0] == mx_even[0]){
            mx_even[0] = mx_even[1];
        }else if(even.get(mx_even[0]) == even.get(mx_even[1]) && mx_even[0] == mx_odd[0]){
            mx_odd[0] = mx_odd[1];
        }

//        System.out.println(Arrays.toString(mx_odd));
//        System.out.println(Arrays.toString(mx_even));
        int me = mx_even[0];
        int mo = mx_odd[0];
        if(me == mo){
            //System.out.println(mx_even[0]+" "+mx_odd[0]+" "+(mx_even[0] == mx_odd[0]));
            if(mx_even[1] == null && mx_odd[1] == null){
                //System.out.println("fff");
                System.out.println(n/2);
            }else if(mx_even[1] == null){
                System.out.println((n/2-odd.get(mx_odd[1])));
            }else if(mx_odd[1] == null){
                System.out.println((n/2-odd.get(mx_even[1])));
            }else{
                int l = n - even.get(mx_even[0]) - odd.get(mx_odd[1]);
                int r = n - even.get(mx_even[1]) - odd.get(mx_odd[0]);
                System.out.println(Math.min(r,l));
            }
        }else{
            System.out.println((n - even.get(mx_even[0]) - odd.get(mx_odd[0])));
        }


    }

    void P(Scanner in){
        int n = in.nextInt();
        int[][] arr = new int[n-1][2];
        for (int i = 0; i < n-1; i++) {
            arr[i] = new int[]{in.nextInt()-1, in.nextInt()-1};
        }
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(i,new ArrayList<>());
        }
        for(int[] ed : arr){
            mp.get(ed[0]).add(ed[1]);
            mp.get(ed[1]).add(ed[0]);
        }
        long[] get = dfs(0,-1,mp);
        System.out.println(((get[0]+get[1])%mod));

    }
    long ksc(long a, long b){
        long res = 0;
        while(b>0){
            //System.out.println(b);
            if((b&1)!=0){
                 res = ((res+a)%mod);
            }
            a<<=1;
            a = (a % mod);
            b>>=1;
        }
        //System.out.println("a:"+a+" b:"+b+" res:"+res);
        return res;
    }
    long[] dfs(int rt, int par, Map<Integer,List<Integer>> mp){
        long[] ans = new long[2];// ans[0] denotes how many black this can be
        List<Long> b = new ArrayList<>();
        List<Long> w = new ArrayList<>();
        for(int nxt : mp.get(rt)){
            if(nxt == par) continue;
            long[] get = dfs(nxt,rt,mp);
            b.add(get[0]);
            w.add(get[1]);
        }
        if(b.size() == 0){
            return new long[]{1,1};
        }
        long mut = 1;
        for(long aka : w) mut = ksc(mut, aka);
        ans[0] = mut;
        mut = 1;
        for (int i = 0; i < w.size(); i++) {
//            mut *= (b.get(i) + w.get(i));
            mut = ksc(mut, b.get(i) + w.get(i));
        }
        ans[1] = mut;
        return ans;
    }





    void N(Scanner in){// O(N^3) thought it might be too large
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        long[] pre = new long[n+1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i-1] + arr[i-1];
        }
        long[][] dp = new long[n+1][n+1];
        for(int len = 2; len <= n; len++){
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = Long.MAX_VALUE;
                if(len == 2){
                    dp[i][j] = arr[i] + arr[j];
                }else{
                    for(int k = i; k < j; k++){
                        dp[i][j] = Math.min(dp[i][j], pre[k+1] - pre[i] + pre[j+1] - pre[k+1] + dp[i][k] + dp[k+1][j]);
                    }
                }

            }
        }
        System.out.println(dp[0][n-1]);



    }
    void M(Scanner in){// well, I did it
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        //dp[i] how many number of ways to get i candies
        long[] dp = new long[k+5];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            long[] neo = new long[k+5];
            long[] cnt = new long[k+5];
            long tot = 0;
            for(int j = 0; j <= k; j++){
                tot = (tot + dp[j]) % mod;
                cnt[j] = tot;
            }
            neo[0] = 1;
            for(int j = 1; j <= k; j++){
                neo[j] = (cnt[j] - (j <= arr[i]? 0: cnt[j-arr[i]-1]) + mod) % mod;
            }
            //System.out.println(Arrays.toString(cnt)+Arrays.toString(neo));
            dp = neo;
        }
        System.out.println(dp[k]);

    }// good problem, proud to be able to solve it
     void L(Scanner in){
        int n = in.nextInt();
        int[] arr = new int[n];
         for (int i = 0; i < n; i++) {
             arr[i] = in.nextInt();
         }
         System.out.println(dfs(0,n-1,0,new Long[n+1][n+1][2],arr));
     }
     long dfs(int i, int j, int r, Long[][][] memo, int[] arr){//r means round: 0 is for taro and 1 for jiro
        if(i==j){
            return (arr[i]*(r==0?1:-1));
        }
        if(memo[i][j][r]!=null) return memo[i][j][r];
        long ans = 0;
        if(r==0){
            long lc = dfs(i+1,j,1-r,memo,arr)+arr[i];
            long rc = dfs(i,j-1,1-r,memo,arr)+arr[j];
            ans = Math.max(lc,rc);
        }else{
            long lc = dfs(i+1,j,1-r,memo,arr)-arr[i];
            long rc = dfs(i,j-1,1-r,memo,arr)-arr[j];
            ans = Math.min(lc,rc);
        }
        return memo[i][j][r] = ans;
     }
    void W(Scanner in){//failed
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] arr = new int[m][3];
        //int[][] cp = new int[m][3];
        for(int i = 0; i<m ; i++){
            arr[i] = new int[]{in.nextInt()-1, in.nextInt()-1, in.nextInt()};
            //cp[i] = Arrays.copyOf(arr[i] , 3);
        }

        Arrays.sort(arr,(a,b)->{
            if(a[1] == b[1]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        int[] seen = new int[n+1];

        for(int[] a : arr){
            int l = a[0];
            int r = a[1];
            int v = a[2];
            seen[l] += v;
            seen[r+1] -= v;
        }
        int[] tot = new int[n+1];
        int val = 0;
        for(int i=0;i<n;i++){
            val += seen[i];
            tot[i] = val;
        }

        int[] dp = new int[n+1];
        TreeMap<Integer,Long> mpl = new TreeMap<>();
        TreeMap<Integer,Long> mpr = new TreeMap<>();
        System.out.println(Arrays.toString(tot)+" "+ Arrays.toString(seen));
        long maxl = 0;
        long maxr = 0;
        for(int[] a : arr){
            int l = a[0];
            int r = a[1];
            int v = a[2];
            // we are in the right to q
            Integer lower = mpr.lowerKey(l);
            long get = lower==null?0:mpr.get(lower);
            if(get+seen[l]>maxr){
                mpl.put(r,get+seen[l]);
                maxr = get+seen[l];
            }

            lower = mpl.lowerKey(l);
            get = lower==null?0:mpl.get(lower);
            if(get+seen[r]>maxl){
                mpr.put(r,get+seen[r]);
                maxl = get+seen[r];
            }
        }
        System.out.println(Math.max(maxr,maxl));
    }//failed
    void K(Scanner in){
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        int min = 0;
        for(int i=0;i<n;i++){
            arr[i] = in.nextInt();
            min = Math.min(min,arr[i]);
        }
        boolean[][] dp = new boolean[100005][2];
        for(int i=1;i<=k;i++){
            if(i<min){
                dp[i][0] = false;
                dp[i][1] = false;
                continue;
            }
            for(int p=0;p<2;p++){
                for(int j=0;j<n;j++){
                    if(i>=arr[j]){
                        dp[i][p] |= !dp[i-arr[j]][1-p];
                    }
                }
            }
        }

        boolean ans = false;
        if(dp[k][0]){
            System.out.println("First");
        }else{
            System.out.println("Second");
        }
    }
    void X(Scanner in){
        //不等式排序
        //dp[i] the weight i with largest score v
        // say we have a box, we could use equation to estimate the last and the next last
        // then we could know what to sort
        int n = in.nextInt();
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt(), in.nextInt()};
        }

        long[] dp = new long[10001];
        Arrays.sort(arr,(a,b)->(a[0]+a[1]-b[0]-b[1]));
        Map<Integer,Long> mp = new HashMap<>();
        for(int i=1;i<=n;i++){
            int w = arr[i-1][0];
            int s = arr[i-1][1];
            int v = arr[i-1][2];
            //System.out.println("---------------");
            //System.out.println("w:"+w+" s:"+s+" v:"+v);
            Map<Integer,Long> neo = new HashMap<>(mp);
            for(int k : neo.keySet()){
                long tot = neo.get(k);
                if(s<k) continue;
                long nxt = neo.containsKey(w+k)?neo.get(w+k):0;
                //System.out.println("k:"+k+" s:"+s+" v:"+v+" tot:"+tot);

                mp.put(w+k,Math.max(nxt,tot+v));
                //System.out.println("shit:"+mp);
            }
            mp.put(w,Math.max(mp.getOrDefault(w,(long)0),(long)v));
            //System.out.println(mp);
        }

        long ans = 0;
        for(int k : mp.keySet()){
            ans = Math.max(ans,mp.get(k));
        }
        System.out.println(ans);
    }
    void I(Scanner in){

        int n = in.nextInt();
        double[] data = new double[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextDouble();
        }

        double[] dp = new double[n+1];
        dp[0] = 1;
        for(int i=1;i<=n;i++){
            dp[i] = dp[i-1]*data[i-1];
            for(int j=i-1;j>=0;j--){
                if(j>0)dp[j] = dp[j-1]*data[i-1] + dp[j]*(1-data[i-1]);
                if(j==0) dp[j] =dp[j]*(1-data[i-1]);
            }
            //System.out.println(Arrays.toString(dp));
        }
        //System.out.println(Arrays.toString(dp));
        double ans = 0;
        for(int i=1;i<=n;i++){
            if(i>n-i){
                ans+=dp[i];
            }
        }
        System.out.println(ans);

    }
    void Y(Scanner in){
        int h = in.nextInt();
        int w = in.nextInt();
        int n = in.nextInt();
        int[][] arr = new int[n][2];
        for(int i =0;i<n;i++){
            arr[i] = new int[]{in.nextInt()-1,in.nextInt()-1};
        }
    }
    void G(Scanner in){
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            edges[i] = new int[]{in.nextInt()-1,in.nextInt()-1};
        }
        int[] par = new int[n+1];
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(i,new ArrayList<>());
        }
        for(int[] e : edges){
            int l = e[0];
            int r = e[1];
            mp.get(r).add(l);
            par[l]++;
        }
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(par[i]==0) q.add(i);
        }
        int max = 0;
        int[] dp = new int[n+1];
        while(!q.isEmpty()){
            int idx = q.poll();
            for(int nxt : mp.get(idx)){
                dp[nxt] = Math.max(dp[nxt],dp[idx]+1);
                par[nxt]--;
                if(par[nxt]==0) q.add(nxt);
            }
        }
        for(int v : dp){
            max = Math.max(max,v);
        }
        System.out.println(max);
    }
}
