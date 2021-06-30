package lc.LC_ALL;

import java.util.*;

public class LC_4_10 {
    public int arraySign(int[] nums) {
        int v = 1;
        for(int x : nums){
            if (x == 0) return 0;
            if (x < 0){
                v = -v;
            }else{
                v = v;
            }
        }
        return v;
    }

    public int findTheWinner(int n, int k) {

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(i);
        }
        int start = 0;
        while (arr.size() > 1){
            int sz = arr.size();
            int idx = (start + k) % sz;
            arr.remove(idx);
            start = idx;
        }
        return arr.get(0) + 1;
    }

    public int minSideJumps(int[] obs) {
        int[] dp = new int[4];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[2] = 0;
        int n = obs.length;
        for (int i = 1; i < n; i++) {
            int[] neo = Arrays.copyOf(dp, 4);
            if (obs[i] != 0){
                dp[obs[i]] = Integer.MAX_VALUE / 2;
            }
            neo[1] = Math.min(dp[1], Math.min(dp[2], dp[3]));
            neo[2] = Math.min(dp[2], Math.min(dp[1], dp[3]));
            neo[3] = Math.min(dp[3], Math.min(dp[2], dp[1]));
            if (obs[i] != 0){
                neo[obs[i]] = Integer.MAX_VALUE / 2;
            }
            dp = neo;
        }
        return Math.min(dp[1], Math.min(dp[2], dp[3]));
    }


    class MKAverage {
        TreeMap<Integer, Integer> mpl = new TreeMap<>();
        TreeMap<Integer, Integer> mpr = new TreeMap<>();
        TreeMap<Integer, Integer> ct = new TreeMap<>();
        LinkedList<Integer> q = new LinkedList<>();
        long tot = 0;
        int m,k, members;
        public MKAverage(int mm, int kk) {
            m = mm;
            k = kk;
        }

        public void addElement(int num) {
            members ++;
            q.add(num);
            if (members == m){
                List<Integer> neo = new ArrayList<>(q);
                Collections.sort(neo);
                for(int i = 0; i < k; i++){
                    mpl.put(neo.get(i), mpl.getOrDefault(neo.get(i), 0) + 1);
                }
                for (int i = k; i < m - k; i++) {
                    ct.put(neo.get(i), ct.getOrDefault(neo.get(i), 0) + 1);
                    tot += neo.get(i);
                }
                for (int i = m - k; i < m; i++) {
                    mpr.put(neo.get(i), mpr.getOrDefault(neo.get(i), 0) + 1);
                }
            }else if (members > m){
                int v = q.poll();
                if (ct.containsKey(v)){
                    ct.put(v, ct.getOrDefault(v, 0) - 1);
                    if (ct.get(v) == 0) ct.remove(v);
                    tot -= v;
                    if (num < ct.firstKey()){
                        Integer last = mpl.lastKey();
                        ct.put(last, ct.getOrDefault(last, 0) + 1);
                        mpl.put(last, mpl.getOrDefault(last, 0) - 1);
                        if (mpl.get(last) == 0) mpl.remove(last);
                        mpl.put(num, mpl.getOrDefault(num, 0) + 1);
                        tot += last;
                    }else if (num > ct.lastKey()){
                        Integer first = mpr.firstKey();
                        ct.put(first, ct.getOrDefault(first, 0) + 1);
                        mpr.put(first, mpr.getOrDefault(first, 0) - 1);
                        if (mpr.get(first) == 0) mpr.remove(first);
                        mpr.put(num, mpl.getOrDefault(num, 0) + 1);
                        tot += first;
                    }else{
                        ct.put(num, ct.getOrDefault(num, 0) + 1);
                        tot += num;
                    }
                }else if (mpl.containsKey(v)){
                    mpl.put(v, mpl.getOrDefault(v, 0) - 1);
                    if (mpl.get(v) == 0) mpl.remove(v);
                    if (num < ct.firstKey()){
                        mpl.put(num, mpl.getOrDefault(num, 0) + 1);
                    }else if (num > ct.lastKey()){
                        mpr.put(num, mpr.getOrDefault(num, 0) + 1);
                        Integer first = mpr.firstKey();
                        ct.put(first, ct.getOrDefault(first, 0) + 1);
                        mpr.put(first, mpr.getOrDefault(first, 0) - 1);
                        if (mpr.get(first) == 0) mpr.remove(first);
                        Integer lmin = ct.firstKey();
                        mpl.put(lmin, mpl.getOrDefault(lmin, 0) + 1);
                        ct.put(lmin, ct.getOrDefault(lmin, 0) - 1);
                        if (ct.get(lmin) == 0) ct.remove(lmin);
                        tot += (first - lmin);
                    }else{
                        Integer lmin = ct.firstKey();
                        mpl.put(lmin, mpl.getOrDefault(lmin, 0) + 1);
                        ct.put(lmin, ct.getOrDefault(lmin, 0) - 1);
                        ct.put(num, ct.getOrDefault(num, 0) + 1);
                        if (ct.get(lmin) == 0) ct.remove(lmin);
                        tot += (num - lmin);
                    }
                }else{
                    mpr.put(v, mpr.getOrDefault(v, 0) - 1);
                    if (mpl.get(v) == 0) mpl.remove(v);
                    if (num < ct.firstKey()){
                        mpl.put(num, mpl.getOrDefault(num, 0) + 1);
                        Integer last = mpl.lastKey();
                        ct.put(last, ct.getOrDefault(last, 0) + 1);
                        mpl.put(last, mpl.getOrDefault(last, 0) - 1);
                        if (mpl.get(last) == 0) mpl.remove(last);
                        Integer rmax = ct.lastKey();
                        mpr.put(rmax, mpr.getOrDefault(rmax, 0) + 1);
                        ct.put(rmax, ct.getOrDefault(rmax, 0) - 1);
                        if (ct.get(rmax) == 0) ct.remove(rmax);
                        tot += (last - rmax);
                    }else if (num > ct.lastKey()){
                       mpr.put(num, mpr.getOrDefault(num, 0) + 1);
                    }else{
                        Integer rmax = ct.lastKey();
                        mpr.put(rmax, mpr.getOrDefault(rmax, 0) + 1);
                        ct.put(rmax, ct.getOrDefault(rmax, 0) - 1);
                        if (ct.get(rmax) == 0) ct.remove(rmax);
                        ct.put(num, ct.getOrDefault(num, 0) + 1);
                        tot += (num - rmax);
                    }
                }
            }

        }

        public int calculateMKAverage() {
            if (members < m) return -1;
            return (int) (tot / (m - 2 * k));
        }
    }

}
