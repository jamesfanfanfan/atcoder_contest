package kickStart.round_B_2021.Kick_start_2019_R_A;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        Solution sol = new Solution();
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            sol.A(sc,pw, i);
        }
        pw.close();
    }
    void A(Scanner in, PrintWriter out, int t){
        int n = in.nextInt();
        int p = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        int cnt = 0;
        for (int i = 0; i < p; i++) {
            cnt += (arr[p-1] - arr[i]);
        }
        int min = cnt;

        if(n > p){
            for (int i = p; i < n; i++) {
                cnt -= (arr[i-1] - arr[i - p]);
                //System.out.println(cnt+" yi");
                cnt += (p-2) * (arr[i] - arr[i-1]);
                //System.out.println(cnt+" er");
                cnt += (arr[i] - arr[i-1]);
                //System.out.println(cnt + " san");
                min = Math.min(min, cnt);
                //System.out.println(cnt);
            }
        }
        System.out.println("Case #"+t+": "+min);
    }
}
