package lc.LC_ALL;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class LCweekly247 {
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return (nums[n - 1] * nums[n - 2] - nums[0] * nums[1]);
    }


    public int candy(int[] ratings) {
        int n = ratings.length;
        int min = 100000000;
        for(int x : ratings){
            min = Math.min(min, x);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        for (int i = 0; i < n; i++) {
            ratings[i] -= min;
            if (ratings[i] == 0){
                pq.add(new int[]{i, 1});
            }
        }
        boolean[] seen = new boolean[n + 1];
        int[] ans = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && ratings[j] != ratings[j - 1]) j++;
            helper(i, j - 1, ratings, ans, seen);
            i = j - 1;
        }
        int sum = 0;
        for(int x : ans){
            sum += x;
        }
        return sum;
    }
    void helper(int l, int r, int[] arr, int[] ans, boolean[] seen){
        int min = 100000000;
        for(int i = l; i <= r; i++){
            min = Math.min(min, arr[i]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        for (int i = l; i <= r; i++) {
            arr[i] -= min;
            if (arr[i] == 0){
                pq.add(new int[]{i, 1});
            }
        }
        int n = arr.length;
        while (!pq.isEmpty()){
            int[] get = pq.poll();
            int val = get[1];
            int idx = get[0];
            seen[idx] = true;
            ans[idx] = val;
            for(int dir : new int[]{-1, 1}){
                int nid = idx + dir;
                if (nid < 0 || nid >= n || seen[nid]) continue;
                boolean left = nid == 0 ? true : (seen[nid - 1] || arr[nid] <= arr[nid - 1]);
                boolean right = nid == n - 1 ? true : (seen[nid + 1] || arr[nid] <= arr[nid + 1]);
                if (left && right){
                    int neo = 1;
                    for(int ndir : new int[]{-1, 1}){
                        int nnid = nid + ndir;
                        if (nnid < 0 || nnid >= n || arr[nnid] == arr[nid]) continue;
                        neo = Math.max(neo, ans[nnid] + 1);
                    }
                    pq.add(new int[]{nid, neo});
                }
            }
        }
    }

    public static void main(String[] args) {
        LCweekly247 lc = new LCweekly247();
        int[] arr = new int[10000];
        Scanner sc = new Scanner(System.in);
        String[] ss = sc.nextLine().split(",");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.valueOf(ss[i]);
        }
        Instant start = Instant.now();
        // time passes

        long get = lc.waysToBuildRooms(arr);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println(timeElapsed.toMillis());
    }

    public long wonderfulSubstrings(String word) {
        int n = word.length();
        char[] cs = word.toCharArray();
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        int state = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int v = 1 << (cs[i] - 'a');
            if ((state | v) == state){
                state -= v;
            }else{
                state += v;
            }
            cnt += mp.getOrDefault(state, 0);
            for (int j = 0; j < 10; j++) {
                int neo = state;
                int vv = 1 << j;
                if ((neo | vv) == neo){
                    neo -= vv;
                }else{
                    neo += vv;
                }
                cnt += mp.getOrDefault(neo, 0);
            }
            mp.put(state, mp.getOrDefault(state, 0) + 1);
        }
        return cnt;
    }
    int mod = (int) 1e9 + 7;
    public int waysToBuildRooms(int[] arr) {
        int n = arr.length;
        InverseofNumber(mod);
        InverseofFactorial(mod);
        factorial(mod);
        List<Integer>[] ls = new List[n + 1];
        for (int i = 1; i < n; i++) {
            if (ls[arr[i]] == null) ls[arr[i]] = new ArrayList<>();
            ls[arr[i]].add(i);
        }
        long[] get = dfs(0, ls);
        return (int) get[1];
    }
    long[] dfs(int i, List<Integer>[] ls){
        long tot = 1;
        long cntRes = 0;
        long cnt = 1;
        long comb = 1;
        if(ls[i] == null){
            return new long[]{1, 1};
        }
        List<long[]> get = new ArrayList<>();
        for(int nxt : ls[i]){
            long[] ok = dfs(nxt, ls);
            get.add(dfs(nxt, ls));
            cntRes += ok[0];
            cnt = (cnt * ok[1]) % mod;
        }
        tot += cntRes;
        for (long[] ok : get) {
            comb = (comb * Binomial((int)cntRes, (int)ok[0], mod)) % mod;
            cntRes -= ok[0];
        }
        long res = (comb * cnt) % mod;
        return new long[]{tot, res};
    }

    static int N = 10005;

    // Array to store inverse of 1 to N
    static long[] factorialNumInverse = new long[N + 1];

    // Array to precompute inverse of 1! to N!
    static long[] naturalNumInverse = new long[N + 1];

    // Array to store factorial of first N numbers
    static long[] fact = new long[N + 1];

    // Function to precompute inverse of numbers
    public static void InverseofNumber(int p)
    {
        naturalNumInverse[0] = naturalNumInverse[1] = 1;

        for(int i = 2; i <= N; i++)
            naturalNumInverse[i] = naturalNumInverse[p % i] *
                    (long)(p - p / i) % p;
    }

    // Function to precompute inverse of factorials
    public static void InverseofFactorial(int p)
    {
        factorialNumInverse[0] = factorialNumInverse[1] = 1;

        // Precompute inverse of natural numbers
        for(int i = 2; i <= N; i++)
            factorialNumInverse[i] = (naturalNumInverse[i] *
                    factorialNumInverse[i - 1]) % p;
    }

    // Function to calculate factorial of 1 to N
    public static void factorial(int p)
    {
        fact[0] = 1;

        // Precompute factorials
        for(int i = 1; i <= N; i++)
        {
            fact[i] = (fact[i - 1] * (long)i) % p;
        }
    }

    // Function to return nCr % p in O(1) time
    public static long Binomial(int N, int R, int p)
    {

        // n C r = n!*inverse(r!)*inverse((n-r)!)
        long ans = ((fact[N] * factorialNumInverse[R]) %
                p * factorialNumInverse[N - R]) % p;

        return ans;
    }
}
