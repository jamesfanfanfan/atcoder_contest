package Training_1500;

import java.io.PrintWriter;
import java.util.Scanner;

public class SumOfPaths {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static long[][] dp = new long[5005][5005];
    static int mod = (int) 1e9 + 7;
    static int[] dirs = new int[]{-1, 1};

//    Let 𝑑𝑝𝑖,𝑗 denote the number of good paths of length 𝑗 that end at cell 𝑖
//    Observe that 𝑑𝑝𝑖,𝑗 is also equal to the number of good paths of length 𝑗 that start at cell 𝑖.
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), k = in.nextInt(), q = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[][] qs = new int[q][];
        for (int i = 0; i < q; i++) {
            qs[i] = new int[]{in.nextInt() - 1, in.nextInt()};
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                for(int dir : dirs){
                    int ni = dir + j;
                    if (ni < 0 || ni >= n)continue;
                    dp[j][i] = (dp[ni][i - 1] + dp[j][i]) % mod;
                }
            }
        }
        long[] ndp = new long[n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                ndp[i] = (ndp[i] + dp[i][j] * dp[i][k - j]) % mod;
            }
        }
        long tot = 0;
        for (int i = 0; i < n; i++) {
            tot = (tot + (ndp[i] * arr[i]) % mod) % mod;
        }
        for(int[] qq : qs){
            int po = qq[0], val = qq[1];
            tot = ((tot - (ndp[po] * arr[po]) % mod) + mod) % mod;
            arr[po] = val;
            tot = (tot + (ndp[po] * arr[po]) % mod) % mod;
            out.println(tot);
        }
    }


}
