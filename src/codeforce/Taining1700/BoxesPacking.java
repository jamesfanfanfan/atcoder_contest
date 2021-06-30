package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class BoxesPacking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }

    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int l = 0, r = n - 1;
        while (l < r){
            int mid = (l + r) / 2;
            if (canpack(arr, n, m, mid, k)){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
//        System.out.println();
        out.println(n - l);
    }
    static boolean canpack(int[] arr, int n, int m, int start, int k){
        long cur = 0;
        int i = start;
        m--;
        for (; i < n; i++) {
            if (cur + arr[i] <= k){
                cur += arr[i];
            }else{
                if (m == 0) return false;
                m--;
                cur = arr[i];
            }
        }
//        System.out.println(start+" i:"+i+" cur:"+cur+" m:"+m);/**/
        return i == n;
    }
}
