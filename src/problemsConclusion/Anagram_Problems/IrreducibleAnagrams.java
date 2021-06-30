package problemsConclusion.Anagram_Problems;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
//https://codeforces.com/problemset/problem/1290/B
public class IrreducibleAnagrams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    //three situations
//    when len is 1 so that you do not have irre anagram
//    when leftmost pos and rightmost pos is same, we could see that is we move all the character at rightmost pos to the left, that is a good situation
//    when left and right pos are same, then we need at least three character, the logic is almost same as above mentioned
    static void solve(Scanner in, PrintWriter out){
        String s = in.next();
        char[] cs = s.toCharArray();
        int n = cs.length;
        int qq = in.nextInt();
        int[][] qs = new int[qq][];
        for (int i = 0; i < qq; i++) {
            qs[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
        }
        int[][] dp = new int[n + 1][26];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = Arrays.copyOf(dp[i], 26);
            dp[i + 1][cs[i] - 'a']++;
        }
        for(int[] q : qs){
            int l = q[0], r = q[1];
            int[] neo = new int[26];
            int cnt = 0;
            int max = 0;
            for (int i = 0; i < 26; i++) {
                neo[i] = dp[r + 1][i] - dp[l][i];
                if (neo[i] > 0) cnt++;
                max = Math.max(max, neo[i]);
            }
            if (r - l + 1 == 1){
                out.println("YES");
            }else if (cs[l] != cs[r]){
                out.println("YES");
            }else if (cs[l] == cs[r] && cnt > 2){
                out.println("YES");
            }else{
                out.println("NO");
            }

        }
    }
}
