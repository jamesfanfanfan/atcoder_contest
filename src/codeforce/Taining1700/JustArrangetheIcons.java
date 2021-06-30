package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JustArrangetheIcons {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }

    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Map<Integer, Integer> mp = new HashMap<>();
        for(int x : arr){
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        }
        int min = n + 1;
        for(int v : mp.values()){
            min = Math.min(v, min);
        }
        int ans = n;
        for (int i = min + 1; i >= 1; i--) {
            ans = Math.min(helper(i, mp, min), ans);
            if (ans < n){
                out.println(ans);
                return;
            }
        }
        out.println(ans);
    }
    static int helper(int i, Map<Integer, Integer> mp, int min){
        int sc = (min - 1) / i + 1;
        int l = sc * (i - 1), r = sc * i;
        if (min >= l && min <= r){
            boolean find = true;
            int cnt = 0;
            for(int v : mp.values()){
                sc = (v - 1) / i + 1;
                l = sc * (i - 1);
                r = sc * i;
                if (v >= l && v <= r){
//                    System.out.println(v+" here");
                    cnt += sc;
                }else{
                    find = false;
                    break;
                }
            }
            if (find) {
//                System.out.println(i+" "+cnt);
                return cnt;
            }
        }
        return Integer.MAX_VALUE;
    }
}
