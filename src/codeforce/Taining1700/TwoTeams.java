package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class TwoTeams {
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
        int n = in.nextInt(), k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        TreeMap<Integer, Integer> idx = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(arr[i], i);
            idx.put(i, arr[i]);
        }
        int[] ans = new int[n];
        int st = 0;
        while (mp.size() > 0){
            int last = mp.lastKey();
            int id = mp.get(last);
            Integer le;
            int cnt = 0;
            ans[id] = st;
            idx.remove(id);
            mp.remove(last);
            while ((le = idx.lowerKey(id)) != null && cnt < k){
                int val = idx.get(le);
                idx.remove(le);
                mp.remove(val);
                ans[le] = st;
                cnt++;
            }
            cnt = 0;
            while ((le = idx.higherKey(id)) != null && cnt < k){
                int val = idx.get(le);
                idx.remove(le);
                mp.remove(val);
                ans[le] = st;
                cnt++;
            }
            st = 1 - st;
//            System.out.println(idx);
        }
        for (int i = 0; i < n; i++) {
            out.print((ans[i] + 1));
        }
        out.println();
    }

}
