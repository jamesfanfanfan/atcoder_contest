package lc.LC_ALL;

import java.io.PrintWriter;
import java.util.*;

public class LC_3_20 {
    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            int sum = nums[i];
            while (j < n && nums[j] > nums[j - 1]){
                sum += nums[j++];
            }
            res = Math.max(res, sum);
        }
        return res;
    }
    public int getNumberOfBacklogOrders(int[][] orders) {
        int mod = (int) 1e9 + 7;
        long tot = 0;
        TreeMap<Integer, Long> buy = new TreeMap<>();
        TreeMap<Integer, Long> sell = new TreeMap<>();
        for(int[] order : orders){
            int p = order[0];
            int amount = order[1];
            int type = order[2];
            if (type == 0){// this is a buy, so we need get sell data
                Integer sl;
                while ((sl = sell.firstKey()) != null && sl <= p && amount > 0){
                    long get = sell.get(sl);
                    if (amount >= get){
                        amount -= get;
                        sell.remove(sl);
                    }else{
                        sell.put(sl, get - amount);
                        amount = 0;
                        break;
                    }
                }
                if (amount > 0){
                    buy.put(p, buy.getOrDefault(p, 0l) + amount);
                }
            }else{
                Integer by;
                while ((by = buy.lastKey()) != null && by >= p && amount > 0){
                    long get = buy.get(by);
                    if (amount >= get){
                        amount -= get;
                        buy.remove(by);
                    }else{
                        buy.put(by, get - amount);
                        amount = 0;
                        break;
                    }
                }
                if (amount > 0){
                    sell.put(p, sell.getOrDefault(p, 0l) + amount);
                }
            }
        }
        for(int k : sell.keySet()){
            tot += sell.get(k);
        }
        for (int k : buy.keySet()){
            tot += buy.get(k);
        }
        tot %= mod;
        return (int) tot;
    }
    public int maxValue(int n, int index, int maxSum) {
        long l = 1, r = maxSum;
        while (l < r){
            long mid = (l + r) / 2;
            long lmin = 0, rmin = 0;
            if (mid <= index){
                long sm = 1, lm = mid - 1;
                long rem = index - mid + 1;
                lmin = (sm + lm) * (lm - sm + 1) / 2 + rem;
            }else{
                long sm = mid - index, lm = mid - 1;
                lmin = (sm + lm) * (lm - sm + 1) / 2;
            }
            int rindex = n - index - 1;
            if (mid <= rindex){
                long sm = 1, lm = mid - 1;
                long rem = rindex - mid + 1;
                rmin = (sm + lm) * (lm - sm + 1) / 2 + rem;
            }else{
                long sm = mid - rindex, lm = mid - 1;
                rmin = (sm + lm) * (lm - sm + 1) / 2;
            }
            if (rmin + lmin <= maxSum - mid){
                l = mid;
            }else{
                r  = mid - 1;
            }
        }
        return (int)r;
    }

    class node{
        node[] nxt = new node[2];
        int val;
        boolean isEnd = false;
        int tot = 0;
        node(int v){
            val = v;
        }
    }
    public int countPairs(int[] nums, int low, int high) {
        Arrays.sort(nums);
        int n = nums.length;
        Map<Integer, Integer> mp = new TreeMap<>();
        for(int x : nums){
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        }
        node root = new node(0);
        for(int key : mp.keySet()){
//            char[] cs = Integer.toBinaryString(key).toCharArray();
            int tot = mp.get(key);
            node tra = root;
            for (int i = 15; i >= 0; i--) {
                int ak = (key & (1 << i));
                if (ak > 0){
                    if (tra.nxt[1] == null) tra.nxt[1] = new node(1);
                    tra.tot += tot;
                    tra = tra.nxt[1];
                }else{
                    if (tra.nxt[0] == null) tra.nxt[0] = new node(0);
                    tra.tot += tot;
                    tra = tra.nxt[0];
                }
            }
        }
        int res = 0;
        for (int key : mp.keySet()){
            int sum = 0;
            int hi = cntSmaller(root, n, high + 1, key);
            int lo = cntSmaller(root, n, low, key);
            int div = hi - lo;
            res += div * mp.get(key);
        }
        return res;
    }
    int cntSmaller(node root, int n, int limit, int key){
        int cnt = 0;
        node tra = root;
        for (int i = 15; i >= 0; i--) {
            if (tra == null) break;
            int v = key >> i;
            int li = limit >> i;
            if (v % 2 == 0){
                if ((v | 1) > li){
                   if (tra.nxt[1] != null) cnt += tra.nxt[1].val;
                   tra = tra.nxt[0];
                }else{
                    tra = tra.nxt[1];
                }
            }else{
                if (v > li){
                    if (tra.nxt[0] != null) cnt += tra.nxt[0].val;
                    tra = tra.nxt[1];
                }else{
                    tra = tra.nxt[0];
                }
            }
        }
        return n - cnt;
    }

    static void E(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        int[][] arr = new int[n][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[]{sc.nextInt(), sc.nextInt()};
        }
        int m = sc.nextInt();
        int[] q = new int[m];
        for (int i = 0; i < q.length; i++) {
            q[i] = sc.nextInt();
        }
        List<long[]> ls = new ArrayList<>();
        long[] div = new long[]{Long.MIN_VALUE, Long.MAX_VALUE, -1};
        ls.add(div);
        long[] tra = new long[]{Long.MIN_VALUE, Long.MAX_VALUE, -1};
        int add = 0;
        int idx = 0;
        int find = -1;
        for(int i = 0; i < n; i++){
            int[] ak = arr[i];
            int a = ak[0], t = ak[1];
            long[] neo = new long[]{tra[0], tra[1], t};
            boolean did = false;
            if(t == 1){
                add += a;
            }else if(t == 2){
                if(add + a >= tra[0]){
                    neo[0] = add + a;
                    did = true;
                }
                add = 0;
            }else{
                if(add + a <= tra[1]){
                    neo[1] = add + a;
                    did = true;
                }
                add = 0;
            }
            if(neo[1] < neo[0]) {
                find = i;
                break;
            }
            if(did){
                ls.add(neo);
                tra = neo;
            }
        }
        if(find == -1){
            Long min = null;
            Long max = null;
            for(int i = ls.size(); i > 0; i--){
                if(ls.get(i)[2] == 2 && max != null){
                    max = (Long)ls.get(i)[0];
                }else if(ls.get(i)[2] == 3 && min != null){
                    min = (Long)ls.get(i)[1];
                }
            }
            for(int v : q){
                int bs = bs(v, ls);
                long[] ak = ls.get(bs);
                if(bs == ls.size() - 1){
                    pw.println(v);
                }else{
                    ak = ls.get(bs + 1);
                    if(ak[2] == 2){
                        pw.println(max);
                    }else{
                        pw.print(min);
                    }
                }

            }
        }else{
            int v = arr[find][0];
            for(int i = find + 1; i < n; i++){
                int a = arr[i][0];
                int t = arr[i][1];
                if(t == 0){
                    v += a;
                }else if(t == 1){
                    v = Math.max(v, a);
                }else{
                    v = Math.min(v, a);
                }
            }
            while(m > 0){
                pw.println(v);
                m--;
            }
        }



    }

    static int bs(long v, List<long[]> ls){
        int n = ls.size();
        int l = 0, r = n - 1;
        while(l < r){
            int mid = (l + r + 1) / 2;
            long[] ak = ls.get(mid);
            if(v >= ak[0] && v <= ak[1]){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        return r;
    }


}
