package lc.LC_ALL;

import java.util.*;
class fa{
    public void add(){
        System.out.println("father did this");
    }
    private int val = 3;
}
class son extends fa{
    @Override
    public void add() {
        System.out.println("son did this");
    }
}
public class LC_1_26 {

    public static void main(String[] args) {
        fa fa = new son();
        fa.add();
    }
    public int countBalls(int l, int h) {
        int[] arr = new int[(int) 4e5];
        for (int i = l; i <= h; i++) {
            char[] cs = String.valueOf(i).toCharArray();
            int cnt =  0;
            for (char c : cs){
                cnt += (c - '0');
            }
            arr[cnt]++;
        }
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }

    public boolean[] canEat(int[] cs, int[][] queries) {
        int n = queries.length;
        boolean[] ans = new boolean[n];
        int[] pre = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            pre[i] = pre[i - 1] + cs[i - 1];
        }
        for (int i = 0; i < queries.length; i++) {
            int[] arr = queries[i];
            int type = arr[0];
            int day = arr[1];
            int max = arr[2];
            int how = pre[type];
            int min = day - 1;
            double get = pre[type] * 1.0 / day;
            if (get >= min && get <= max){
                ans[i] = true;
            }
        }
        return ans;
    }

    public boolean checkPartitioning(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        boolean[][] dp = new boolean[n + 1][n + 1];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                if (len == 1){
                    dp[i][j] = true;
                }else if (len == 2){
                    dp[i][j] = cs[i] == cs[j];
                }else{
                    dp[i][j] = (dp[i + 1][j - 1] && cs[i] == cs[j]);
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (dp[0][i] && dp[i + 1][j] && dp[j][n - 1]){
                    return true;
                }
            }
        }
        return false;
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer,Set<Integer>> mp = new HashMap<>();
        int n = adjacentPairs.length + 1;
        boolean[] seen = new boolean[n + 1];
        int[] find = new int[n + 1];
        for(int[] ak : adjacentPairs){
            int f = ak[0];
            int t = ak[1];
            find[f]++;
            find[t]++;
            if (!mp.containsKey(f)) mp.put(f, new HashSet<>());
            if (!mp.containsKey(t)) mp.put(t, new HashSet<>());
            mp.get(f).add(t);
            mp.get(t).add(f);
        }
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (find[i] != 2){
                q.add(i);
            }
        }
        int first = q.poll();
        int[] res = new int[n];
        dfs(res, 0, first, -1, mp);
        return res;
    }
    void dfs(int[] arr, int idx, int val, int par, Map<Integer, Set<Integer>> mp){
        arr[idx] = val;
        mp.get(val);
        for(int x : mp.get(val)){
            if (x == par) continue;
            dfs(arr, idx + 1, x, val, mp);
        }
    }


    public String makeLargestSpecial(String S) {
        int n = S.length();
        Map<Integer, List<Integer>> mp = new HashMap<>();
        char[] cs = S.toCharArray();
        String[][] dp = new String[51][51];
        for (int i = 0; i < n; i++) {
            mp.put(i, new ArrayList<>());
            int c0 = 0;
            int c1 = 0;
            for (int j = i; j < n; j++) {
                if (cs[i] == '0'){
                    c0++;
                }else{
                    c1++;
                }
                if (c0 == c1){
                    mp.get(i).add(j);
                }
            }
        }
        int[][] pre = new int[51][2];// 0 and 1
        if (cs[0] == '1'){
            pre[0] = new int[]{0, 1};
        }else{
            pre[0] = new int[]{1, 0};
        }
        boolean[][] isS = new boolean[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            int ct0 = 0;
            int ct1 = 0;
            boolean ak = true;
            for (int j = i; j < n; j++) {
                if (cs[j] == '1'){
                    ct1++;
                }else{
                    ct0++;
                }
                if (ct1 < ct0){
                    ak = false;
                }
                if (ct1 == ct0){
                    isS[i][j] = ak;
                }
            }
        }
        for(int len = 1; len <= n; len ++){
            for (int i = 0; i + len <= n ; i++) {
                int j = i + len - 1;
                String cmp = S.substring(i, i + len);
                for (int k = i + 1; k < j - 1; k += 2) {
                    if (isS[i][j] && isS[j + 1][k]){
                        String l = S.substring(i, k + 1);
                        String r = S.substring(k + 1, j);
                        if ((l + r).compareTo(r + l) < 0){
                            cmp = r + l;
                        }
                    }
                }
                dp[i][j] = cmp;
            }
        }
        return dp[0][n - 1];
    }

    public List<List<String>> groupStrings(String[] strings) {
        int n = strings.length;
        boolean[] seen = new boolean[n];
        List<List<String>> res = new ArrayList<>();
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(strings[i], i);
        }
        for (int i = 0; i < n; i++) {
            if (seen[i]) continue;
            List<String> aka = new ArrayList<>();
            aka.add(strings[i]);
            for (int j = 1; j < 26; j++) {
                char[] cs = strings[i].toCharArray();
                for (int k = 0; k < strings[i].length(); k++) {
                    int v = cs[k] - 'a';
                    v = (v + j) % 26;
                    cs[k] = (char) (v + 'a');
                }
                String find = new String(cs);
                if (mp.containsKey(find)){
                    seen[mp.get(find)] = true;
                    aka.add(find);
                }
            }
            res.add(aka);
        }
        Map<Integer, Integer> sss = new HashMap<>();
        sss.put(null, null);
        return res;
    }
}
