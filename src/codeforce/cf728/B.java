package codeforce.cf728;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Map<Integer, Integer> mp = new HashMap<>();
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = arr[i]; j < 2 * n; j += arr[i]){
                int div = j / arr[i];
                if (mp.containsKey(div) && mp.get(div) + i + 2 == j){
                    cnt++;
                }
            }
            mp.put(arr[i], i);
        }
        out.println(cnt);
    }
}
