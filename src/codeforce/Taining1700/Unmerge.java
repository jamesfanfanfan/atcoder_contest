package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;
//https://codeforces.com/problemset/problem/1381/B
//this problem is not a typical dp problem, you should change it first,
//first we should notice that the answer should be connected by several blocks,
// the we could see that we could actually find out these blocks
//since a1 can be placed before b1 because a1 < b1, say we find the largest value of the array, which means each value appears later that this value should be in the
//the same block as him,
public class Unmerge {
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
        int tot = in.nextInt();
        int n = 2 * tot;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Set<Integer> set = new HashSet<>();
        List<Integer> ls = new ArrayList<>();
        int max = n;
        int last = n;
        for (int i = n - 1; i >= 0; i--) {
            set.add(arr[i]);
            if (arr[i] == max) {
                ls.add(last - i);
                while (set.contains(max))max--;
                last = i;
            }
        }
        boolean[] dp = new boolean[tot + 1];
        dp[0] = true;
        for (int i = 0; i < ls.size(); i++) {
            boolean[] ndp = Arrays.copyOf(dp, tot + 1);
            for (int j = ls.get(i); j <= tot; j++) {
                ndp[j] |= dp[j - ls.get(i)];
            }

            dp = ndp;
        }
        if (dp[tot]){
            out.println("YES");
        }else{
            out.println("NO");
        }
    }

}
