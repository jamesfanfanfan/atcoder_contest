package codeforce.cf721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    private static final FastReader in = new FastReader();
    private static final PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            solve();
        }
        out.flush();
    }
    static int[][][][] dp = new int[501][501][2][2];
    static int[][][][] vis = new int[501][501][2][2];

    static void solve(){
        int n = in.nextInt();
        char[] cs = in.next().toCharArray();
        int[] get = new int[3];
        for (int i = 0; i < n / 2; i++) {
            char c = cs[i];
            char rc = cs[n - i - 1];

            if (c == rc){
                if (c == '0'){
                    get[1]++;
                }
            }else{
                get[0]++;
            }
        }
        if (n % 2 == 1){
            if (cs[n / 2] == '0'){
                get[2]++;
            }
        }

        int res = dfs(get[0], get[1], get[2], 1);
//        System.out.println(res);
        if (res > 0){
            out.println("BOB");
        }else if (res < 0){
            out.println("ALICE");
        }else{
            out.println("DRAW");
        }
    }
    static int dfs(int pay1, int pay2, int mid, int reverse){
        if (pay1 + pay2 + mid == 0) return 0;
        if (vis[pay1][pay2][mid][reverse] != 0) return dp[pay1][pay2][mid][reverse];
        int res = 10000000;
        if (pay1 > 0 && reverse == 1) res = Math.min(res, -dfs(pay1, pay2, mid, 0));
        if (pay1 > 0) res = Math.min(res, -dfs(pay1 - 1, pay2, mid, 1) + 1);
        if (pay2 > 0) res = Math.min(res, -dfs(pay1 + 1, pay2 - 1, mid, 1) + 1);
        if (mid > 0) res = Math.min(res, -dfs(pay1, pay2, 0, 1) + 1);
        vis[pay1][pay2][mid][reverse] = 1;
        return dp[pay1][pay2][mid][reverse] = res;
    }

}
