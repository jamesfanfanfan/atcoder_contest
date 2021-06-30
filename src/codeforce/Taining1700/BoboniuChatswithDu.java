package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

public class BoboniuChatswithDu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
// remember in mind, always better to think about ways in brute force, especially these two-item scenarios we just iterate through all the situation
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), d = in.nextInt(), m = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        long ans = 0;
        long smallSum = 0;
        long largeSum = 0;
        PriorityQueue<Long> pqh = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Long> pql = new PriorityQueue<>();
        for(int x : arr){
            if (x > m){
                pqh.add(x * 1l);
            }else{
                pql.add(x * 1l);
                ans += x;
                smallSum += x;
            }
        }
        if (pqh.size() > 0){
            n--;
            largeSum += pqh.peek();
            ans += pqh.poll();
        }
        long blocks = n / (d + 1);
        while (pql.size() > n){
            smallSum -= pql.peek();
            ans -= pql.poll();
        }

        for (int i = 0; i < blocks && pqh.size() > 0 && n > d; i++) {
            largeSum += pqh.poll();
            n -= (d + 1);
            while (pql.size() > n){
                smallSum -= pql.poll();
            }
            ans = Math.max(ans, smallSum + largeSum);
        }
        out.println(ans);
    }

}
