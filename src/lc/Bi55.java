package lc;

import java.util.*;

public class Bi55 {
    public boolean canBeIncreasing(int[] arr) {
        int n = arr.length;
        boolean find = true;
        int i = 0;
        for (; i < n - 1; i++) {
            if (arr[i] >= arr[i + 1]){
                find = false;
                break;
            }
        }
        if (find) return true;
        List<Integer> ls = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if (j == i) continue;
            ls.add(arr[j]);
        }
        if (check(ls)) return true;
        ls.clear();
        for (int j = 0; j < n; j++) {
            if (j == i + 1) continue;
            ls.add(arr[j]);
        }
        if (check(ls)) return true;
        return false;
    }
    boolean check(List<Integer> res){
        for (int i = 0; i < res.size() - 1; i++) {
            if (res.get(i) >= res.get(i + 1)) return false;
        }
        return true;
    }
    public String removeOccurrences(String s, String part) {
        int n = s.length(), m = part.length();
        int idx = -1;
//        boolean[] ans = new boolean[n + 1];
        while ((idx = s.indexOf(part)) != -1){
            s = s.substring(0, idx) + s.substring(idx + m, n);
        }
        return s;
    }
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][3];
        dp[0] = new int[]{nums[0], 0, 0};
        int max = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + nums[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] - nums[i], dp[i - 1][1]);
            max = Math.max(dp[i][0], max);
        }
        return max;
    }


    class MovieRentingSystem {

        Map<Integer, PriorityQueue<int[]>> mp = new HashMap<>();
        TreeSet<Integer>[] sps;
        Map<Integer, Map<Integer, Integer>> seen = new HashMap<>();
        Map<Integer, Set<Integer>> rented = new HashMap<>();
        Map<Integer, Set<Integer>> removed = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
            if (a[0] == b[0]){
                if (a[1] == b[1]) return a[2] - b[2];
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        public MovieRentingSystem(int n, int[][] entries) {
            sps = new TreeSet[n];
            for (int i = 0; i < n; i++) {
                sps[i] = new TreeSet<>();
            }
            for(int[] e : entries){
                int sp = e[0], mv = e[1], pr = e[2];
                if (!mp.containsKey(mv)) mp.put(mv, new PriorityQueue<>((a, b) -> {
                    if (a[0] == b[0]) return a[1] - b[1];
                    return a[0] - b[0];
                }));
                mp.get(mv).add(new int[]{pr, sp});
                sps[sp].add(mv);
                if (!seen.containsKey(sp)) seen.put(sp, new HashMap<>());
                seen.get(sp).put(mv, pr);
            }
        }

        public List<Integer> search(int movie) {
            PriorityQueue<int[]> pq = mp.get(movie);
            List<Integer> ls = new ArrayList<>();
            LinkedList<int[]> q = new LinkedList<>();
            for (; ls.size() < 5 && pq.size() > 0; ) {
                int[] ok = pq.poll();
                if (!sps[ok[1]].contains(movie)) {
                    if (!removed.containsKey(ok[1])) removed.put(ok[1], new HashSet<>());
                    removed.get(ok[1]).add(movie);
                    continue;
                }
                q.add(ok);
                ls.add(ok[1]);
            }
            while (!q.isEmpty()) pq.add(q.poll());
            return ls;
        }

        public void rent(int shop, int movie) {
            sps[shop].remove(movie);
            if (!rented.containsKey(shop)) rented.put(shop, new HashSet<>());
            rented.get(shop).add(movie);
            pq.add(new int[]{seen.get(shop).get(movie), shop, movie});
        }

        public void drop(int shop, int movie) {
            sps[shop].add(movie);
            if (removed.containsKey(shop) && removed.get(shop).contains(movie)){
                mp.get(movie).add(new int[]{seen.get(shop).get(movie), shop});
                removed.get(shop).remove(movie);
            }
            rented.get(shop).remove(movie);
        }

        public List<List<Integer>> report() {
            List<List<Integer>> res = new ArrayList<>();
            for (; res.size() < 5 && pq.size() > 0;) {
                int[] get = pq.poll();
                if (rented.containsKey(get[1]) && rented.get(get[1]).contains(get[2])){
                    res.add(Arrays.asList(get[1], get[2]));
                }
            }
            return res;
        }
    }
}
