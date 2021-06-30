package ABC.abc130;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main m = new Main();
        m.solve(sc);
    }

    int mod = (int)1e9+7;
    void solve(Scanner in){
        int n = in.nextInt();
        int m = in.nextInt();
        int[] as = new int[n];
        int[] at = new int[m];
        for(int i=0;i<n;i++){
            as[i] = in.nextInt();
        }
        for(int i=0;i<m;i++){
            at[i] = in.nextInt();
        }
        long[] dp = new long[m+1];
        for(int i=1;i<=n;i++){
            int v = as[i-1];
            long max = dp[0];
            for(int j=1;j<=m;j++){
                long st = dp[j];
                if(v==at[j-1]){
                    dp[j] = (dp[j]+max+1)%mod;
                }
                max = (max+st)%mod;
            }
        }
        long ans = 0;
        for(int i=1;i<=m;i++){
            ans = (ans+dp[i])%mod;
        }

        System.out.println((ans+1));
    }


}

