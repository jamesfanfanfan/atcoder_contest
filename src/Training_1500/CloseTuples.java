package Training_1500;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class CloseTuples {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static long[][] dp = new long[200006][102];
    //Initialise array elements with zero
    static long nCr(int n, int r)
    {
        if(n==r) return dp[n][r] = 1; //Base Case
        if(r==0) return dp[n][r] = 1; //Base Case
        if(r==1) return dp[n][r] = n;
        if(dp[n][r] != 0) return dp[n][r]; // Using Subproblem Result
        return dp[n][r] = (nCr(n-1,r) + nCr(n-1,r-1));
    }
    static int mod = (int) 1e9 + 7;
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), m = 3, k = 2;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        long ans = 0;
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        for(int x : arr){
            mp.put(x, mp.getOrDefault(x,0) + 1);
        }
        TreeMap<Integer, Integer> adt = new TreeMap<>();
        int add = 0;
        adt.put(-1, 0);
        for(int x : mp.keySet()){
            add += mp.get(x);
            adt.put(x, add);
        }
        for (int x : mp.keySet()) {
            int tot = mp.get(x);
            int right = adt.get(x);
            int sm = Math.max(0, x - k);
            sm--;
            int sc = adt.floorKey(sm);
            int get = adt.get(sc);
            int itv = right - get;
            if (itv < m) continue;
            int rem = itv - tot;
            for (int i = 1; i <= Math.min(tot, m); i++) {
                if (rem + i < m) continue;
                ans += ((nCr(tot, i) * nCr(rem, m - i)));
            }
        }
        out.println(ans);
    }

}
