package codeforce.Edu_49;

import java.io.PrintWriter;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
//        int t = sc.nextInt();
//        for (int i = 0; i < t; i++) {
//            solve(sc, pw);
//        }
        solve(sc, pw);
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int q = in.nextInt();
        int[][] arr = new int[q][2];
        for (int i = 0; i < q; i++) {
            arr[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
        }
        for(int[] ak : arr){
            long res = 0;
            long x = ak[0];
            long y = ak[1];
            long dm = (n * n - 1) / 2 + 1;
            if ((x + y) % 2 == 0){
                long hf = 1;
                if (n % 2 == 0){
                    long tot = x * (n / 2) + y / 2 + 1;
                    //out.println(tot+" "+x+" "+y);
                    res = tot + hf - 1;
                }else{
                    long us = (x - 1) / 2 + 1;
                    long oth = x - us;
                    long tot = us * (n / 2 + 1) + oth * (n / 2) + (y) / 2 + 1;
                    //out.println(tot+" "+us+" "+oth);
                    res = tot + hf - 1;
                }
            }else{
                long hf = dm + 1;
                if (n % 2 == 0){
                    long tot = x * (n / 2) + (y) / 2 + 1;
                    //out.println(tot +" "+x+" "+y);
                    res = tot + hf - 1;
                }else{
                    long us = x / 2;
                    long oth = x - us;
                    long tot = us * (n / 2 + 1) + oth * (n / 2) + (y - 1) / 2 + 1;
                    res = tot + hf - 1;
                }
            }
            out.println(res);
        }

    }
}
