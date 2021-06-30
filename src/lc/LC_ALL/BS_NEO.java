package lc.LC_ALL;

import java.util.*;

public class BS_NEO {
    public int solve(int[][] tasks, int k) {
        Arrays.sort(tasks, (a, b) -> {
            int c1 = 0;
            int c2 = 0;
            for (int i = 0; i < 3; i++) {
                if (a[i] > b[i]){
                    c1 += a[i] - b[i];
                }else{
                    c2 += b[i] - a[i];
                }
            }
            return c1 - c2;
        });
        return 2;

    }
    public int nearestValidPoint(int x, int y, int[][] points) {

          int res = Integer.MAX_VALUE;
          int ans = -1;
          int idx = 0;
          for(int[] p : points){
              if (p[0] == x || p[1] == y){
                  if(Math.abs(x - p[0]) + Math.abs(y - p[1]) < res){
                      res = Math.abs(x - p[0]) + Math.abs(y - p[1]);
                      ans = idx;
                  }
              }
              idx++;
          }
          if (res == Integer.MAX_VALUE) return -1;
          return idx;
    }

    public boolean checkPowersOfThree(int n) {

        List<Integer> ls = new ArrayList<>();
        for(int i = 0; i < n ; i++){
            int val = (int) Math.pow(3, i);
            if (val > n){
                break;
            }else{
                ls.add(val);
            }
        }
        for (int i = 0; i < 1 << ls.size(); i++) {
            long add = 0;
            for (int j = 0; j < 20; j++) {
                if ((i | (1 << j)) == i){
                    add += ls.get(i);
                }
            }
            if (add == n) return true;
        }
        return false;

    }

    public int beautySum(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[][] store = new int[n + 1][26];
        //store[0][cs[0] - 'a'] ++;
        for(int i = 1; i <= n; i++){
            store[i] = Arrays.copyOf(store[i - 1], 26);
            store[i][cs[i - 1] - 'a']++;
        }
        int add = 0;
        for(int i = 0; i < n; i++){
            for (int j = 0; j < i; j++) {
                int[] ak = new int[26];
                int min = 100000000;
                int max = 0;
                for (int k = 0; k < 26; k++) {
                    ak[k] = store[i + 1][k] - store[j][1];
                    if (ak[k] == 0) continue;
                    max = Math.max(max, ak[k]);
                    min = Math.min(min, ak[k]);
                }
                if (min < max){
                    add += (max - min);
                }
            }
        }
        return add;
    }

    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] res = new int[queries.length];
        int[] deg = new int[n];
        for (int[] e : edges) {
            deg[--e[0]]++;
            deg[--e[1]]++;
        }
        Arrays.sort(deg);
        int idx = 0;
        for(int x : queries){
            int cnt = 0;
            for (int i = 0, j = deg.length - 1; i < deg.length; i++) {
                while (j >= 1 && deg[j] + deg[i] > x) j--;
                cnt += (n - j);
            }
            for (int i = 0; i <= n; i++) {
                if (deg[i] * 2 > x) cnt--;
            }
            res[idx++] = cnt / 2;
        }
        return res;
    }

    public int solve(int[] nums, int tar) {
        int n = nums.length;
        if(tar == 0) return n / 2;
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        Arrays.sort(nums);
        int cnt = 0;
        for(int x : nums){
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        }
        for(int x : new TreeSet<Integer>(mp.descendingKeySet())){
            int tot = mp.get(x);
            if (tot == 0)continue;
            int low = tot - tar;
            Integer get;
            for (int i = 0; i < tot; i++) {
                if ((get = mp.floorKey(low)) != null && mp.get(get) > 0){
                    cnt++;
                    mp.put(get, mp.get(get) - 1);
                }else{
                    break;
                }
            }

        }
        return cnt;


    }

    public int[] solve(int[] nums) {
        int n = nums.length;
        if(n <= 1) return nums;
        int[] res = new int[n];
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        for (int i = 0; i < n; i++) {
            if (i == 0){
                if (nums[i] > nums[i + 1]) pq.add(new int[]{nums[i], i});
            }else if (i == n - 1){
                if (nums[i - 1] < nums[i]) pq.add(new int[]{nums[i], i});
            }else{
                if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) pq.add(new int[]{nums[i], i});
            }
            mp.put(i, nums[i]);
        }
        int i = 0;
        while (!pq.isEmpty()){
            int[] arr = pq.poll();
            res[i++] = arr[0];
            int idx = arr[1];
            Integer hi = mp.higherKey(idx);
            Integer lo = mp.lowerKey(idx);
            if (hi == null && lo == null){
                break;
            }else if (hi == null){
                Integer llo = mp.lowerKey(lo);

                if (llo == null || mp.get(llo) < mp.get(lo)){
                    pq.add(new int[]{mp.get(lo), lo});
                }
            }else if (lo == null){
                Integer hhi = mp.higherKey(hi);
                if (hhi == null || mp.get(hhi) < mp.get(hi)){
                    pq.add(new int[]{mp.get(hi), hhi});
                }
            }else{
                if (mp.get(hi) > mp.get(lo)){
                    Integer hhi = mp.higherKey(hi);
                    if (hhi == null || mp.get(hhi) < mp.get(hi)){
                        pq.add(new int[]{mp.get(hi), hhi});
                    }
                }else{
                    Integer llo = mp.lowerKey(lo);

                    if (llo == null || mp.get(llo) < mp.get(lo)){
                        pq.add(new int[]{mp.get(lo), lo});
                    }
                }
            }
            mp.remove(idx);
        }
        return res;
    }
    public int[][] solve(int n, int[][] relations) {
        int[][] res = new int[n][n];
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int[] lars = new int[n * n + 1];
        Map<Integer, Set<Integer>> ml = new HashMap<>();
        int[] hars = new int[n * n + 1];
        Map<Integer, Set<Integer>> mh = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for(int[] r : relations){
            if (r[2] == 0){
                lars[r[1]]++;
                if (!ml.containsKey(r[0])) ml.put(r[0], new HashSet<>());
                ml.get(r[0]).add(r[1]);
            }else if(r[2] == 1){
                lars[r[0]]++;
                if (!ml.containsKey(r[1])) ml.put(r[1], new HashSet<>());
                ml.get(r[1]).add(r[0]);
            }else if (r[2] == 2){
                hars[r[1]]++;
                if (!mh.containsKey(r[0])) mh.put(r[0], new HashSet<>());
                mh.get(r[0]).add(r[1]);
            }else{
                hars[r[0]]++;
                if (!mh.containsKey(r[1])) mh.put(r[1], new HashSet<>());
                mh.get(r[1]).add(r[0]);
            }
            set.add(r[0]);
            set.add(r[1]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
       for(int x : set){
           if (lars[x] == 0){
               pq.add(new int[]{x, hars[x]});
           }
       }
        for (int i = 0; i < n; i++) {
            int j = 0;
            PriorityQueue<int[]> neo = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
            while (!pq.isEmpty()){
                int[] arr = pq.poll();

            }
        }
        return new int[2][];

    }
    public int[] solve(int[] nums, int[][] qs) {
        int n = nums.length;
        List<Map<Integer, Integer>> ls = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            Map<Integer, Integer> mp = new HashMap<>();
            for (int x : nums){
                //set.add(x >> i);
                mp.put(x >> i, Math.min(x, mp.getOrDefault(x >> i, Integer.MAX_VALUE)));
            }
            ls.add(mp);
        }
        Arrays.sort(nums);

        int[] res = new int[qs.length];
        Arrays.fill(res, - 1);
        if(n == 0) return res;
        int cnt = 0;
        for (int[] q : qs){
            int v = q[0];
            int limit = q[1];
            int ans = 0;
            boolean find = false;
            for (int i = 31; i >= 0; i--) {
                Set<Integer> ts = ls.get(i).keySet();
                Map<Integer, Integer> mp = ls.get(i);
                //if(i == 0)System.out.println(ts);
                if ((v | (1 << i)) == v){// this is one
                    int tk = ans >> i;
                    if (!ts.contains(tk)){
                        ans |= 1 << i;
                    }

                    //System.out.println(tk+" "+i+" "+ts+" "+ans);
                }else{// we want to get index to be 1
                    int now = ans | (1 << i);
                    int tk = now >> i;
                    /// if we need this index but no that index
                    if (ts.contains(tk) && mp.get(tk) <= limit){
                        ans = now;
                    }

                    //System.out.println(tk+" "+i+" "+ts+" "+ans);
                }

            }
            if(ans <=  limit){
                res[cnt] = ans;
            }
            cnt++;
        }
        return res;

    }

    class FreqStack {

        int cnt = 0;
        TreeMap<Integer, TreeMap<Integer, Integer>> mp = new TreeMap<>();
        Map<Integer, Stack<Integer>> doc = new HashMap<>();
        //Map<Integer, Integer> neo = new HashMap<>();
        public FreqStack() {

        }

        public void push(int x) {
            if (!doc.containsKey(x)){
                doc.put(x, new Stack<>());
            }
            int sz = doc.get(x).size();
            doc.get(x).push(cnt);
            mp.get(sz).remove(x);
            if (!mp.containsKey(sz + 1)){
                mp.put(sz + 1, new TreeMap<>());
            }
            mp.get(sz + 1).put(cnt, x);
//            neo.put(cnt, x);
            cnt++;
        }

        public int pop() {
            if (mp.size() == 0) return -1;
            Integer get = mp.lastKey();
            TreeMap<Integer, Integer> tran = mp.get(get);
            int ans = tran.get(tran.lastKey());
            tran.remove(tran.lastKey());
            doc.get(ans).pop();
            int sz = doc.get(ans).size();
            if (sz == 0){
                doc.remove(ans);
            }else{
                if (!mp.containsKey(sz)){
                    mp.put(sz, new TreeMap<>());
                }
                mp.get(sz).put(doc.get(ans).peek(), ans);
            }
            return ans;
        }
    }

    public int solve(String s, int zeroone, int onezero) {
        char[] cs = s.toCharArray();
        int n = s.length();
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int v = cs[i] - '0';
            if (stack.size() == 0){
                stack.push(v);
            }else{
                if (stack.peek() == v){
                    stack.push(v);
                }else{
                    if (v == 1 && onezero > zeroone){
                        stack.push(v);
                    }else{
                        stack.pop();
                        if (v == 1){
                            cnt += zeroone;
                        }else{
                            cnt += onezero;
                        }
                    }
                }
            }
        }
        if (stack.size() > 0){
            int[] ct = new int[2];
            if (stack.peek() == 1){
                while (stack.size() > 0) ct[stack.pop()]++;
                cnt += (Math.min(ct[0], ct[1])) * zeroone;
            }else{
                while (stack.size() > 0) ct[stack.pop()]++;
                cnt += (Math.min(ct[0], ct[1])) * onezero;
            }
        }
        return cnt;

    }



}
