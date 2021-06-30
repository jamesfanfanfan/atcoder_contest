package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//https://codeforces.com/contest/1508/problem/B

// Well for this kind of problem use table to print out the limit number of case would be conducive to the answer
// for this kind of problem, first you should think about how many permutation for length l, maybe i! or 2^ n or kind of dp
// answer credits to Apfeloxid
//cannot solve it by my self
//seeing this problem, you should notice that the sequence is either increasing of decreasing (strictly by 1)
//*** this is the hard part, you should notice that for a sequence length of n you 2 ^ n - 1 permutation
//why : you can think the end of each decreasing sequence as 0, we could see that the last index is always 0, and the rest is
// not fixed, each position could be 1 or 0 which means except for a specific length, except the last position, every other position could be 1 or 0!!!!!!!!
public class AlmostSorted {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        pow2s[0] = 1;
        for (int i = 1; i < 61; i++) {
            pow2s[i] = pow2s[i-1] * 2;
        }
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static long[] pow2s = new long[61];

    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        long k = in.nextLong();
        int pow = n-1;

        if (pow <= 59 && k > (long) Math.pow(2, pow)) {
            out.println(-1);
            return;
        }
        List<Integer> q = new ArrayList<>();
        int tot = 0;
        for (int i = 0; i < n; i++) {
            long y = 1l;
            if ((k | y << i) == k){
                q.add(i + 1);
                tot += (i + 1);
            }
        }
        System.out.println(tot);
        int start = 1;
        while (n > tot){
            out.print(start+" ");
            n--;
            start++;
        }
        for(int x : q){
            int high = start + x - 1;
            while (high >= start){
                out.print(high+" ");
                high--;
            }
            start += x;
        }

    }

}
