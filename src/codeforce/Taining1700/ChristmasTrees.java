package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

//https://codeforces.com/problemset/problem/1283/D
public class ChristmasTrees {
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
        Set<Integer> set = new HashSet<>();
        LinkedList<Integer> q = new LinkedList<>();
//        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }
        for(int v : arr){
            int l = v - 1, r = v + 1;
            if (set.add(l)) q.add(l);
            if (set.add(r)) q.add(r);
        }
        LinkedList<Integer> ans = new LinkedList<>();
        long tot = 0;
        for (int len = 1; !q.isEmpty() && ans.size() < m; len++){
            for(int sz = q.size(); sz > 0 && ans.size() < m; sz--){
                int v = q.poll();
                ans.add(v);
                tot += len;
                if (set.add(v - 1)) q.add(v - 1);
                if (set.add(v + 1)) q.add(v + 1);
            }
        }
        out.println(tot);
        while (!ans.isEmpty()){
            out.print(ans.poll()+" ");
        }
    }
}
