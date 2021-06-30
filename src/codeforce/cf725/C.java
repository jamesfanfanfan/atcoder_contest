package codeforce.cf725;

import java.io.PrintWriter;
import java.util.*;


// think about the corner case, sort is fucking slow in java so carefully use that
//
public class C {
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
        int n = in.nextInt(), l = in.nextInt(), r = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        TreeMap<Integer, Integer> mp = new TreeMap<>();
//        TreeMap<Integer, Integer> neo = new TreeMap<>();
        Map<Integer, Integer> dc = new HashMap<>();
        for(int x : arr){
            mp.put(x, mp.getOrDefault(x, 0) + 1);
        }
        mp.put(-1, 0);
        dc.put(-1 , 0);
        int tot = 0;
        for(int k : mp.keySet()){
            tot += mp.get(k);
            dc.put(k, tot);
        }
//        System.out.println(neo+" "+mp);
        long ans = 0;
        for(int k : mp.keySet()){
            int ll = Math.max(0, l - k);
            int rr = Math.min(k, r - k);
            if (ll > rr) continue;
            long vk = mp.get(k);
            rr = Math.min(k - 1, r - k);
            Integer sl = mp.lowerKey(ll);
            Integer sr = mp.floorKey(rr);
            ans += ((dc.get(sr) - dc.get(sl)) * vk);
            if (r - k >= k) ans += (vk * (vk - 1)) / 2;
        }
        out.println(ans);
    }
}