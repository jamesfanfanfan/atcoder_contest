package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

public class IdentifytheOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static int mod = 998244353;
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), k = in.nextInt();
        int[] arr = new int[n];
        int[] brr = new int[k];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < k; i++) {
            brr[i] = in.nextInt();
        }
        long ans = 1;
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        Map<Integer, Integer> rev = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            mp.put(i, arr[i]);
            rev.put(arr[i], i);
        }
        for(int x : brr) set.add(x);
        for (int i = 0; i < k; i++) {
            int id = rev.get(brr[i]);
            Integer hi = mp.higherKey(id);
            Integer lo = mp.lowerKey(id);
            if (hi != null && lo != null){
//                System.out.println("shit"+" "+mp+" i:"+i);
                if (set.contains(arr[hi]) && set.contains(arr[lo])){
                    out.println(0);
                    return;
                }else if (set.contains(arr[hi])){
                }else if (set.contains(arr[lo])){
                }else{
                    ans = (ans * 2) % mod;
                    mp.remove(hi);
                }
            }else if (hi != null){
                if (set.contains(arr[hi])){
                    out.println(0);
                    return;
                }
            }else if (lo != null){
                if (set.contains(arr[lo])){
                    out.println(0);
                    return;
                }
            }else{
                out.println(0);
                return;
            }
            set.remove(brr[i]);
        }
        out.println(ans);
    }

}
