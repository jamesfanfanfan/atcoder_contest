package codeforce.edu110;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class C {
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
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        long ans = 0;
        int[] one = new int[2];
        Arrays.fill(one, -1);
//        one[0] means the last position that we have 1 in odd position,
        int[] zero = new int[2];
        Arrays.fill(zero, -1);
        for (int i = 0; i < n; i++) {
            if (cs[i] == '1'){
                if (i % 2 == 0){
                    int o = one[1];
                    int e = zero[0];
                    ans += (i - Math.max(o, e));
                    if (cs[i] != '?')one[0] = i;
                }else{
                    int o = one[0];
                    int e = zero[1];
                    ans += (i - Math.max(o, e));
                    if (cs[i] != '?')one[1] = i;
                }
            }else if (cs[i] == '0'){
                if (i % 2 == 0){
                    int o = one[0];
                    int e = zero[1];
                    ans += (i - Math.max(o, e));
                    if (cs[i] != '?')zero[0] = i;
                }else{
                    int o = one[1];
                    int e = zero[0];
                    ans += (i - Math.max(o, e));
                    if (cs[i] != '?')zero[1] = i;
                }
            }else{
                int o1 = Math.max(one[0], zero[1]);
                int o2 = Math.max(one[1], zero[0]);
                ans += (i - Math.min(o1, o2));
            }
        }
        out.println(ans);
    }
}
