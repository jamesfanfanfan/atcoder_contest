package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

//https://codeforces.com/contest/1367/problem/E
public class NecklaceAssembly {
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
        int n = in.nextInt(), k = in.nextInt();
        String s = in.next();
        char[] cs = s.toCharArray();
        int[] store = new int[26];
        for (int i = 0; i < n; i++) {
            store[cs[i] - 'a']++;
        }
        for(int i = n; i >= 1; i--){
            List<Integer> pos = new ArrayList<>();
            boolean[] seen = new boolean[i];
            for (int j = 0; j < i; j++) {
                if (seen[j]) continue;
                int cnt = 0;
                int ok = j;
                while (!seen[ok]){
                    seen[ok] = true;
                    ok = (ok + k) % i;
//                    System.out.println(ok+" ffff");
                    cnt++;
                }
                pos.add(cnt);
            }
            Collections.sort(pos);
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (int j = 0; j < 26; j++) {
                if (store[j] > 0) pq.add(store[j]);
            }
            boolean find = true;

            for(int j = pos.size() - 1; j >= 0; j--){
                if (!pq.isEmpty() && pq.peek() >= pos.get(j)){
                    int v = pq.poll();
                    v -= pos.get(j);
                    if (v > 0) pq.add(v);
                }else{
                    find = false;
                    break;
                }
            }
            if (find){
                out.println(i);
                return;
            }
        }

    }
}
