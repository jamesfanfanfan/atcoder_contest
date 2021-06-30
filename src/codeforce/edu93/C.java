package codeforce.edu93;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
        Map<Integer, Integer> mp = new HashMap<>();
        int n = in.nextInt();
        int[] arr = new int[n];
        char[] cs = in.next().toCharArray();
//        System.out.println(Arrays.toString(cs));
        mp.put(0, 1);
        int sum = 0;
        long cnt = 0;
        for (int i = 1; i <= n; i++) {
            sum += (cs[i - 1] - '0');
            cnt += mp.getOrDefault(sum - i, 0);
            mp.put(sum - i, mp.getOrDefault(sum - i, 0) + 1);
        }
//        System.out.println(mp);
        out.println(cnt);
    }
}
