import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LC_CN_Spring_contest {
    static class pair<T, C> {

        T first;
        C second;
        public pair(T first, C second){
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            pair<?, ?> pair = (pair<?, ?>) o;
            return Objects.equals(first, pair.first) &&
                    Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 4, 5};
        int[] c = new int[]{1, 2, 4, 5};
        List<Integer> dq = new LinkedList<>();
        List<int[]> d = Arrays.stream(arr).map(x -> new int[]{x, x * x}).collect(Collectors.toList());
        pair<Integer, Integer> p1 = new pair<>(1, 2);
        pair<Integer, Integer> p2 = new pair<>(1, 2);
//        Stream<String> ak = dq.paral
        System.out.println(p1.hashCode()+" "+p2.hashCode());
    }
    public int[] findRedundantConnection(int[][] edges) {
        int max = 0;
        for(int[] edge : edges){
            max = Math.max(Math.max(edge[0], edge[1]), max);
        }
        int[] pars = new int[max + 1];
        Arrays.fill(pars, -1);
        int[] ans = new int[2];
        for(int[] e : edges){
            if (!union(e[0], e[1], pars)){
                ans = e;
            }
        }
        return ans;
    }
    static int find(int i, int[] par){
        if (par[i] < 0) return i;
        return par[i] = find(par[i], par);
    }
    static boolean union(int i, int j, int[] par){
        int pi = find(i, par);
        int pj = find(j, par);
        if (pi == pj) return false;
        if (par[pi] < par[pj]){
            par[pi] += par[pj];
            par[pj] = pi;
        }else{
            par[pj] += par[pi];
            par[pi] = pj;
        }
        return true;
    }
//    if s1[i] == s2[j] dp[i][j] = dp[]
    public String minWindow(String s1, String s2) {
        int n = s1.length(), m = s1.length();
        int[][] dp = new int[n + 1][m + 1];
        boolean[] lastJ = new boolean[m + 1];
        int[] pos = new int[m + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp, -1);
        }
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(0)){
                dp[i][0] = i;
            }
        }
        int ans = -1;
        int len = m + 1;
        for(int j = 1; j < m; j++){
            int idx = -1;
            for (int i = j; i < n; i++) {
                if (s1.charAt(i) == s2.charAt(j)){
                    if (dp[i - 1][j - 1] != -1){
                        dp[i][j] = dp[i - 1][j - 1];
                        idx = dp[i - 1][j - 1];
                    }
                }else if(idx != -1){
                    dp[i][j] = idx;
                }
            }
        }
        for (int i = m - 1; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(m - 1)){
                if (dp[i][m - 1] != -1){
                    int nowLen = i - dp[i][m - 1] + 1;
                    if (nowLen < len){
                        ans = dp[i][m - 1];
                        len = nowLen;
                    }
                }
            }
        }
        if (ans == -1){
            return "";
        }else{
            return s1.substring(ans, ans + len);
        }
    }
}
