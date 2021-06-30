package codeforce.edu93;

import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B {
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
        String s = in.next();
        char[] cs = s.toCharArray();
        int n = cs.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0') continue;
            int j = i;
            while (j < n && cs[j] == cs[i]) j++;
            pq.add(i - j);
            i = j - 1;
        }
        int score = 0;
        while (!pq.isEmpty()){
            score += pq.poll();
            if (!pq.isEmpty()) pq.poll();
        }
        out.println(-score);
    }
}
