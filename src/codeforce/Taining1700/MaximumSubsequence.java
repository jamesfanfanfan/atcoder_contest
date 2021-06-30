package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class MaximumSubsequence {
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
        int n = in.nextInt(), m = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        TreeSet<Integer> left = helper(Arrays.copyOfRange(arr, 0, n / 2), m);
        TreeSet<Integer> right = helper(Arrays.copyOfRange(arr, n / 2, n), m);
//        System.out.println(left);
//        System.out.println(right);
        int res = 0;
        for(Integer v : left){
            res = Math.max(res, v);
            int rem = m - v;
            Integer get = right.lower(rem);
            if (get != null){
                res = Math.max(res, get + v);
            }
        }
        out.println(res);
    }
    static TreeSet<Integer> helper(int[] arr, int m){
//        System.out.println(Arrays.toString(arr));
        TreeSet<Integer> res = new TreeSet<>();
        int n = arr.length;
        for (int i = 0; i < 1 << n; i++) {
            int add = 0;
            for (int j = 0; j < n; j++) {
                if ((i | (1 << j)) == i){
                    add = (add + arr[j]) % m;
                }
            }
            res.add(add);
        }
        return res;
    }
}
