import java.math.BigInteger;
import java.util.*;

public class LC {
    public int numSubmatrixSumTarget(int[][] mat, int tar) {
        int n = mat.length;
        int m = mat[0].length;
        Map<Long, Integer>[][] mps = new HashMap[n + 1][m + 1];
        long[][] le = new long[n + 1][m + 1];
        long[][] ri = new long[n + 1][m + 1];
        long[][] ct = new long[n + 1][m + 1];
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                le[i][j] = le[i][j - 1] + mat[i][j];
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                ri[i][j] = ri[i - 1][j] + mat[i][j];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                long tot = le[i][j - 1] + ct[i - 1][j - 1] + ri[i - 1][j];
                ct[i][j] = tot;
                for (int k = 0; k < j; k++) {
                    Map<Long, Integer> mp = mps[k + 1][j];
                    long del = ct[i][k];
                    cnt += mp.getOrDefault(tot - (del + tar), 0);
                }
                for (int k = 0; k < j; k++) {
                    Map<Long, Integer> mp = mps[k + 1][j];
                    long add = le[i][j] - le[i][k];
                    add += (ct[i - 1][j] - ct[i - 1][k]);
                    mp.put(add, mp.getOrDefault(add, 0) + 1);
                }
            }
        }
        return cnt;
    }
    public boolean checkIfPangram(String sentence) {
        char[] cs = sentence.toCharArray();
        int[] arr = new int[26];
        for(char c : cs){
            arr[c - 'a']++;
        }
        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (arr[i] > 0) cnt++;
        }
        return cnt == 26;
    }
    public int maxIceCream(int[] costs, int coins) {
        int n = costs.length;
        Arrays.sort(costs);
        int cnt = 0;
        int tot = 0;
        for (int i = 0; i < n; i++) {
            if(tot + costs[i] > coins) return cnt;
            cnt++;
            tot += costs[i];
        }
        return cnt;
    }
    public int[] getOrder(int[][] tasks) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]){
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int[][] neo = new int[tasks.length][];
        for (int i = 0; i < tasks.length; i++) {
            neo[i] = new int[]{tasks[i][0], tasks[i][1], i};
        }
        tasks = neo;
        Arrays.sort(tasks, (a, b) -> (a[0] - b[0]));
        int cur = tasks[0][0];
        int n = tasks.length;
        int[] ans = new int[n];
        int id = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && tasks[j][0] <= cur){
                pq.add(new int[]{tasks[j][1], tasks[j][2]});
                j++;
            }
            int[] arr = pq.poll();
            ans[id++] = arr[1];
            cur += arr[0];
        }
        while (!pq.isEmpty()){
            int[] arr = pq.poll();
            ans[id++] = arr[1];
        }
        return ans;
    }
    public int getXORSum(int[] arr1, int[] arr2) {
        int[] cnt = new int[31];
        for(int x : arr2){
            for (int i = 0; i < 31; i++) {
                if ((x| (1 << i)) == x){
                    cnt[i]++;
                }
            }
        }
        int[] ans = new int[31];
        for(int x : arr1){
            for (int i = 0; i < 31; i++) {
                if ((x| (1 << i)) == x){
                    ans[i] += cnt[i];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 31; i++) {
            if (ans[i] % 2 == 1){
                res += (1 << i);
            }
        }
        return res;
    }
    public boolean solve(int[] nums) {
        int n = nums.length;
        Set<Integer>[][] dp = new HashSet[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = new HashSet<>();
            }
        }
        for(int i = 0; i < n; i++) dp[i][i].add(i);
        for(int len = 2; len <= n; len++){
            for (int i = 0; i + len <= n ; i++) {
                int j = i + len - 1;
                for (int k = i; k < j; k++) {
                    Set<Integer> L = dp[i][k];
                    Set<Integer> R = dp[k + 1][j];
                    for(int l : L){
                        for(int r : R){
                            dp[i][j].add(l * r);
                            dp[i][j].add(l - r);
                            dp[i][j].add(l + r);
                            if (r != 0) dp[i][j].add(l / r);
                        }
                    }
                }
            }
        }
        return dp[0][n - 1].contains(24);
    }
    public int sumBase(int n, int k) {
        String s = Integer.toString(n, k);
        int v = Integer.valueOf(s);
        int cnt = 0;
        while (v > 0) {
            cnt += v / 10;
            v /= 10;
        }
        return cnt;
    }
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int tot = 0;
        int max = 0;
        for (int i = 0, l = 0; i < n; i++) {
            if (i > 0){
                while ((i - l) * nums[i] - tot > k){
                    tot -= nums[i];
                    l++;
                }
            }
            int j = i;
            while (j < n && nums[j] == nums[i]) j++;
            max = Math.max(max, j - l);
            tot += ((j - i) * nums[i]);
            i = j - 1;
        }
        return max;
    }
    public int longestBeautifulSubstring(String word) {
        int n = word.length();
        char[] cs = word.toCharArray();
        String s = "aeiou";
        int max = 0;
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(cs[i]) == -1) continue;
            int j = i;
            while (j < n && cs[j] == cs[i]) j++;
            res.add(new int[]{s.charAt(cs[i]), i, j - 1});
        }
        for (int i = 0; i < res.size(); i++) {
            int cnt = 0;
            int j = i;
            boolean find = true;
            while (j < res.size()){
                int[] get = res.get(j);
                if (get[0] != cnt){
                    find = false;
                    break;
                }else{
                    cnt++;
                }
                j++;
            }
            if (cnt == 5){
                int st = res.get(i)[1];
                int ed = res.get(j - 1)[2];
                max = Math.max(max, ed - st + 1);
            }
        }
        return max;
    }
    public int maxBuilding(int n, int[][] rs) {
        Arrays.sort(rs, (a, b) -> (a[0] - b[0]));
        int len = rs.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < len - 1; i++) {
            int[] l = rs[i], r = rs[i + 1];
            if (l[1] > r[1]){
                pq.add(new int[]{r[1], i, i + 1});
            }
        }
        while (!pq.isEmpty()){
            int[] get = pq.poll();
            int[] l = rs[get[1]], r = rs[get[2]];
            int max = r[1];
            if (mp.containsKey(r[0])){
                max = mp.get(r);
            }
            int mL = max + r[0] - l[0];
            mp.put(l[0], Math.min(mL, l[1]));
        }
        int ans = 0;
        int st = 0;
        int id = 1;
        for (int i = 0; i < len; i++) {
            int[] get = rs[i];
            int mx = get[1];
            if (mp.containsKey(get[0])) mx = mp.get(get[0]);
            int dis = get[0] - id;
            if (st < mx){
                if (st + dis <= mx){
                    ans = Math.max(st + dis, ans);
                    st = st + dis;
                    id = get[0];
                }else{
                    int left = st + (dis + 1) / 2 - mx;
                    ans = Math.max(ans, left + mx);
                    st = mx;
                    id = get[0];
                }
            } else if (st > mx) {
                int left = st - (dis + 1) / 2 - mx;
                ans = Math.max(ans, left + st);
                st = mx;
                id = get[0];
            }else{
                ans = Math.max(ans, (st + (dis + 1) / 2));
                id = get[0];
            }
        }
        if (n > rs[len - 1][0]){
            st += n - rs[len - 1][0];
            ans = Math.max(ans, st);
        }
        return ans;
    }
    public String replaceDigits(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; i += 2) {
            char c = cs[i];
            cs[i + 1] = (char) (c + (cs[i + 1] - '0'));
        }
        return new String(cs);
    }
    class SeatManager {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int up;
        public SeatManager(int n) {
            up = n;
            for (int i = 1; i <= n; i++) {
                pq.add(i);
            }
        }

        public int reserve() {
            return pq.poll();
        }

        public void unreserve(int seatNumber) {
            pq.add(seatNumber);
        }
    }
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int cur = arr[0];
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            cur++;
            cur = Math.min(cur, arr[i]);
        }
        return cur;
    }
    public int[] closestRoom(int[][] rs, int[][] qs) {
        int n = rs.length;
        int len = qs.length;
        int[] ans = new int[len];
        int[][] neo = new int[len][3];
        for (int i = 0; i < len; i++) {
            neo[i] = new int[]{qs[i][0], qs[i][1], i};
        }
        Arrays.sort(neo, (a, b) -> {
            return b[1] - a[1];
        });
        Arrays.sort(rs, (a, b) -> {
            if (a[1] == b[1]){
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        Arrays.fill(ans, - 1);
        int max = rs[0][1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0, j = 0; i < len; i++) {
            if (neo[i][1] > max) continue;
            int k = j;
            while (k < n && rs[j][1] >= neo[i][1]){
                pq.add(rs[j][0]);
            }
            j = k;
            ans[neo[i][2]] = pq.peek();
        }
        return ans;
    }

    public int getMinDistance(int[] nums, int target, int start) {
        int v = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target){
                if (Math.abs(i - start) < res){
                    res = Math.abs(i - start);
                }
            }
        }
        return res;
    }
    public int[] minInterval(int[][] is, int[] qs) {
        int n = is.length, m = qs.length;

        int[][] nqs = new int[m][];
        for (int i = 0; i < m; i++) {
            nqs[i] = new int[]{qs[i], i};
        }
        Arrays.sort(nqs, (a, b) -> (a[0] - b[0]));
        Arrays.sort(is, (a, b) -> (a[0] - b[0]));
        TreeMap<Integer, Set<Integer>> mp = new TreeMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        int[] ans = new int[m];
        for(int i = 0, j = 0; j < m; j++){
            int q = nqs[j][0];
            int idx = nqs[j][1];
            while (i < n && is[i][0] <= q){
                int len = is[i][1] - is[i][0] + 1;
                if (!mp.containsKey(len)) mp.put(len, new HashSet<>());
                mp.get(len).add(i);
                pq.add(new int[]{is[i][0], is[i][1], i});
                i++;
            }
            while (!pq.isEmpty() && pq.peek()[1] < q){
                int[] ok = pq.poll();
                int len = ok[1] - ok[0] + 1;
                mp.get(len).remove(ok[2]);
                if (mp.get(len).size() == 0) mp.remove(len);
            }
            if (mp.size() == 0){
                ans[idx] = -1;
            }else{
                ans[idx] = mp.firstKey();
            }
        }
        return ans;
    }
    public boolean splitString(String s) {
        int n = s.length();
        for (int i = 1; i < 1 << n; i++) {
            int pre = 0;
            long prev = -1;
            BigInteger bg = new BigInteger("-1");
            boolean find = true;
            for (int j = 0; j < n; j++) {
                if ((i | (1 << j)) == i || j == n - 1){
                    String sb = s.substring(pre, j + 1);
                    long ok = Long.valueOf(sb);
                    BigInteger sf = new BigInteger(String.valueOf(ok));
                    if (bg.compareTo(new BigInteger("-1")) == 0 && bg.subtract(new BigInteger("1")).compareTo(sf) == 0){
                        find = false;
                        break;
                    }
//                    prev = ok;
                    bg = sf;
                    pre = j + 1;
                }
            }
            // System.out.println(Integer.toBinaryString(i));
            if (find && i != (1 << (n - 1))) return true;
        }
        return false;
    }

    public int getMinSwaps(String num, int k) {
        int n = num.length();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = num.charAt(i) - '0';
        }
        int[] neo = Arrays.copyOf(ans, n);
        for (int i = 0; i < k; i++) {
            nextPermutation(ans);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (ans[i] == neo[i]) continue;
            int j = i + 1;
            while (neo[j] != ans[i])j++;
            while (j > i) {
                res += swap(neo, j, j - 1);
                j--;
            }
        }
        return res;
    }
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public int swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return 1;
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int mod = (int) 1e9 + 7;
        long ans = 0;
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{speed[i], efficiency[i]};
        }
        Arrays.sort(arr, (a, b) -> (b[1] - a[1]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        long sum = 0;
        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
            sum += arr[i][0];
            if (pq.size() > k){
                int[] fk = pq.poll();
                sum -= fk[0];
            }
            ans = Math.max(ans, sum * arr[i][1]);
        }
        ans %= mod;
        return (int) ans;
    }
}
