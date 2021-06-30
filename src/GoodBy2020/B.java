package GoodBy2020;

import java.io.PrintWriter;
import java.util.Arrays;
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
        Map<Integer, Integer> mp = new HashMap<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
           int v = in.nextInt();
           arr[i] = v;
           mp.put(v, mp.getOrDefault(v, 0) + 1);
        }
        Arrays.sort(arr);
        int cnt = 0;
        int max = 10000000;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] + 1 < max){
                cnt++;
                max = arr[i] + 1;
            }else if(arr[i] + 1 == max){
                cnt++;
                max = arr[i];
            }
        }
        out.println(cnt);
    }
}
