package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class ZmeiGorynich {
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
        int n = in.nextInt(), x = in.nextInt();
        int[][] arr = new int[n][2];
        int maxdif = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt()};
            maxdif = Math.max(maxdif, arr[i][0] - arr[i][1]);
            max = Math.max(max, arr[i][0]);
        }
        if (maxdif == 0 && max < x){
            out.println(-1);
        }else{
            long cnt = x - max;
            if (cnt <= 0){
                out.println(1);
            }else{
                out.println(((cnt - 1) / maxdif + 2));
            }
        }
    }
}
