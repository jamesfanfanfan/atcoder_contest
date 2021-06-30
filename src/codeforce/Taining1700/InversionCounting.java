package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class InversionCounting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
/*
*       should notice that when you see odd, even this kind of question, first think of parity
*       this is the parity thing, we know that we need to inverse even number, for one val, if you inverse even number, you would have even
*     say we have odd number of inversion, we have to reverse the length which could odd number of inversion,
*       then we have two scenarios: 1 we can odd number of inversion in this length when we reverse, the total shall be even again
*       2 we have even number of inversion
* */
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[][] qs = new int[m][];
        for (int i = 0; i < m; i++) {
            qs[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) cnt++;
            }
        }
        cnt &= 1;
        for(int[] q : qs){
            int len = (q[1] - q[0] + 1);
            int tot = (len - 1) * len / 2;
            cnt ^= (tot & 1);
            if (cnt == 0){
                out.println("even");
            }else{
                out.println("odd");
            }
        }
    }
}
