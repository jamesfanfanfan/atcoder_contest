package GoodBy2020;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
        String s = in.next();
        int n = s.length();
        int pre = -1;
        char[] cs = s.toCharArray();
        int cnt = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (set.contains(i)) continue;
            if (n - i >= 2){
                if (cs[i] == cs[i + 1]){
                    set.add(i + 1);
                    cnt++;
                }
            }
            if (n - i >= 3){
                if (cs[i] == cs[i + 2]){
                    set.add(i + 2);
                    cnt++;
                }
            }
        }
        out.println(cnt);
    }
}
