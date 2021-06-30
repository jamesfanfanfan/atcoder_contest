package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;
// for this problem you should notice that sometimes that pq would have some problem, therefore
// always used fixed value for compare
public class FretsOnFire {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static class query{
        long l, r;
        int idx;
        long ans = -1;
        long len;
        public query(long l, long r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
            len = r - l;
        }
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        int qq = in.nextInt();
        query[] qs = new query[qq];
        PriorityQueue<query> pq = new PriorityQueue<>((a, b) -> {
            return Long.compare(a.len, b.len);
        });
        for (int i = 0; i < qq; i++) {
            qs[i] = new query(in.nextLong(), in.nextLong(), i);
            pq.add(qs[i]);
        }
        TreeSet<Long> set = new TreeSet<>();
        for(long x : arr){
            set.add(x);
        }
        List<Long> ls = new ArrayList<>(set);
        PriorityQueue<Long> len = new PriorityQueue<>((a, b) -> Long.compare(a, b));
        long cnt = ls.size();
        long rem = ls.size();
        for(int i = 1; i < ls.size(); i++){
            len.add(ls.get(i) - ls.get(i - 1) - 1);
        }
//        System.out.println(ls);
        while (!pq.isEmpty()){
            query q = pq.poll();
//            System.out.println(q.idx+" "+q.l+" "+q.r);
            while (!len.isEmpty() && len.peek() <= q.len){
                cnt += len.poll();
                rem--;
            }
            q.ans = cnt + rem * q.len;
        }
        for (int i = 0; i < qq; i++) {
            out.print(qs[i].ans+" ");
        }
    }
    // Use this instead of Arrays.sort() on an array of ints. Arrays.sort() is n^2
    // worst case since it uses a version of quicksort. Although this would never
    // actually show up in the real world, in codeforces, people can hack, so
    // this is needed.
    static void ruffleSort(long[] a) {
        //ruffle
        int n=a.length;
        Random r=new Random();
        for (int i=0; i<a.length; i++) {
            int oi=r.nextInt(n);
            long temp=a[i];
            a[i]=a[oi];
            a[oi]=temp;
        }

        //then sort
        Arrays.sort(a);
    }
}
