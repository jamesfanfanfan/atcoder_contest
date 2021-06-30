package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class MinimumArray {
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
        int n = in.nextInt();
        int[] arr = new int[n];
        int[] brr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            brr[i] = in.nextInt();
            mp.put(brr[i], mp.getOrDefault(brr[i], 0) + 1);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int t = arr[i];
            int tar = n - t;
            Integer ri = mp.ceilingKey(tar);
            if (ri != null){
                mp.put(ri, mp.getOrDefault(ri, 0) - 1);
                if (mp.get(ri) == 0){
                    mp.remove(ri);
                }
                ans[i] = (t + ri) % n;
            }else{
                Integer head = mp.firstKey();
                mp.put(head, mp.getOrDefault(head, 0) - 1);
                if (mp.get(head) == 0){
                    mp.remove(head);
                }
                ans[i] = (t + head) % n;
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(ans[i]+" ");
        }
        out.println();
    }
}
