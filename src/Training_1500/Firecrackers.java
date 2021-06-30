package Training_1500;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Firecrackers {
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
        int n = in.nextInt(), m = in.nextInt(), a = in.nextInt(), b = in.nextInt();
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        int dist = Math.abs(b - a) - 1;
        long rem = 0;
        if (a < b){
            rem = b - 1;
        }else{
            rem = n - b;
        }
        int l = 0, r = Math.min(m, dist);
        while (l < r){
            int mid = ((l + r) >> 1) + 1;
            boolean find = true;
            for (int i = mid - 1, st = 1; i >= 0; i--, st++) {
                if (arr[i] + st > rem){
                    find = false;
                    break;
                }
            }
            if (find){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        out.println(l);
    }

}
