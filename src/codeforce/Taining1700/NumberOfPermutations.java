package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NumberOfPermutations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static int mod = 998244353;
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt()};
        }
//        long ans = 0;
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> second = new HashMap<>();
        for (int i = 0; i < n; i++) {
            first.put(arr[i][0], first.getOrDefault(arr[i][0], 0) + 1);
            second.put(arr[i][1], second.getOrDefault(arr[i][1], 0) + 1);
        }
        long[] pre = new long[n + 1];
        pre[0] = 1;
        for (int i = 1; i <= n; i++) {
            pre[i] = (pre[i - 1] * i) % mod;
        }
        long tot = 1;
        for (int i = n; i >= 1; i--) {
            tot = (tot * i) % mod;
        }
        long fs = 1, sc = 1, cb = 1;
        for (int i = 1; i <= n; i++) {
            if (first.containsKey(i)){
                fs = (fs * pre[first.get(i)]) % mod;
            }
            if (second.containsKey(i)){
                sc = (sc * pre[second.get(i)]) % mod;
            }
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        boolean find = true;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i][1] > arr[i + 1][1]){
                find = false;
                break;
            }
        }
        if (find){
            for (int i = 0; i < n; i++) {
                int j = i;
                while (j < n && arr[j][0] == arr[i][0] && arr[j][1] == arr[i][1]) j++;
                int cnt = j - i;
                cb = (cb * pre[cnt]) % mod;
                i = j - 1;
            }
        }else{
            cb = 0;
        }
        tot = (((tot - fs - sc + cb) % mod) + mod) % mod;
        out.println(tot);
    }
}
