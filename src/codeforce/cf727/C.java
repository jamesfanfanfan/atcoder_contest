package codeforce.cf727;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class C {
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
        long k = in.nextLong(), x = in.nextLong();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        Arrays.sort(arr);
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] - arr[i] > x){
                pq.add((arr[i + 1] - arr[i] - 1) / x);
            }
        }

        while (!pq.isEmpty() && k >= pq.peek()){
            k -= pq.poll();
        }
        out.println((pq.size() + 1));
    }
}
