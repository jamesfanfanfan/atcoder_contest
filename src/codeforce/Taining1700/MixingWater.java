package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class MixingWater {
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
        int h = in.nextInt(), c = in.nextInt(), t = in.nextInt();
        if ((h + c) / 2.0 >= t){
            out.println(2);
            return;
        }
        long l = 0, r = (long) 1e9;
        while (l < r - 1){
            long mid = (l + r) >> 1;
            double ok1 = ((mid + 1.0) * h + mid * c) / (2 * mid + 1);
            if (ok1 < t){
                r = mid;
            }else{
                l = mid;
            }
        }
//        System.out.println((l *2 + 1)+" "+(r * 2 + 1));
//        double ll = ((l + 1) * h + l * c) / (2 * l + 1);
//        double rr = ((r + 1.0) * h + r * c) / (2 * r + 1);
        long lmax = ((l + 1) * h + l * c) * (2 * r + 1);
        long rmax = ((r + 1) * h + r * c) * (2 * l + 1);
        long cmp = t * (2 * l + 1) * (2 * r + 1);
//        System.out.println(Math.abs(lmax - cmp)+" "+Math.abs(rmax - cmp));
        if (Math.abs(lmax - cmp) <= Math.abs(rmax - cmp)){
            out.println(l * 2 + 1);
        }else{
            out.println(r * 2 + 1);
        }
    }
}
