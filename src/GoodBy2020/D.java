package GoodBy2020;

import java.io.PrintWriter;
import java.util.*;

public class D {

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
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            cnt += arr[i];
        }
        int[] par = new int[n + 1];
        int[][] rd = new int[n - 1][];
        for (int i = 0; i < n - 1; i++) {
            rd[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
        }
        Map<Integer, Set<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(i, new HashSet<>());
        }
        for(int[] x : rd){
            //if (!mp.containsKey(x[0]))mp.put(x[0], new HashSet<>());
            //if (!mp.containsKey(x[1]))mp.put(x[1], new HashSet<>());
            mp.get(x[0]).add(x[1]);
            mp.get(x[1]).add(x[0]);
            par[x[0]]++;
            par[x[1]]++;
        }
        boolean[] seen = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(arr[b[1]], arr[a[1]])));
        //out.println(mp);
        for(int x : mp.keySet()){
            if (mp.get(x).size() == 1){
                int v = 0;
                for(int y : mp.get(x)){
                    v = y;
                }
                pq.add(new int[]{x,v});
            }
            seen[x] = true;
        }
        List<Long> res = new ArrayList<>();
        res.add(cnt);
        //out.println(pq+" "+mp);
        while (!pq.isEmpty()){
            int[] get = pq.poll();
            int idx = get[1];
            mp.get(idx).remove(get[0]);
            par[get[1]] --;
            if (res.size() == n - 1) break;
            cnt+=arr[idx];
            res.add(cnt);
            //out.println(idx+" "+pq);
            if (par[idx] == 1){
                int v = 0;
                for(int y : mp.get(idx)){
                    v = y;
                }
                mp.get(v).remove(idx);
                pq.add(new int[]{idx, v});
            }
        }
        StringBuilder sb = new StringBuilder();
        for(long x : res){
            sb.append(x+" ");
        }
        out.println(sb.toString());
    }
}
