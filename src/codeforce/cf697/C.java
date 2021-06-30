package codeforce.cf697;

import java.io.PrintWriter;
import java.util.*;



public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, out);
        }
        out.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int a = in.nextInt(), b = in.nextInt(), k = in.nextInt();
        int[][] arr = new int[k][2];
        for (int i = 0; i < k; i++) {
            arr[i][0] = in.nextInt();
        }
        for (int i = 0; i < k; i++) {
            arr[i][1] = in.nextInt();
        }
       // SegTree sg = new SegTree(b + 5);
        Arrays.sort(arr, (as, bs) -> (as[0] - bs[0]));
        LinkedList<int[]> pq = new LinkedList<>();
        Map<Integer, Integer> mp = new HashMap<>();
        int add = 0;
        long cnt = 0;
        for(int[] ak : arr){
            if (pq.size() > 0 && ak[0] > pq.peek()[0]){
                while (!pq.isEmpty()){
                    mp.put(pq.peek()[1], mp.getOrDefault(pq.peek()[1], 0) + 1);
                    add++;
                    pq.poll();
                }
            }
            cnt += (add - mp.getOrDefault(ak[1], 0));
            pq.add(ak);
            //System.out.println(Arrays.toString(ak)+" "+mp+" "+cnt+" "+add);
        }
        out.println(cnt);
    }
}
