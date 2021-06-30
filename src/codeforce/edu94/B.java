package codeforce.edu94;

import java.io.PrintWriter;
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
        int p = in.nextInt(), f = in.nextInt();
        int cnts = in.nextInt(), cntw = in.nextInt();
        int s = in.nextInt(), w = in.nextInt();
        int best = 0;
        if (s <= w){
            int tmpcnt = cnts;
            cnts = cntw;
            cntw = tmpcnt;
            int tmp = s;
            s = w;
            w = tmp;
        }
        if (s >= w){
            for(int i = 0; i <= cntw; i++){
                if (i * w > p) break;
                int remP = p - i * w;
                int remW = cntw - i;
                int fGoW = Math.min(remW, f / w);
                int remf = f - fGoW * w;
                int shit = Math.min(cnts, remP / s);
                int fshit = Math.min(cnts - shit, remf / s);
                best = Math.max(best, i + fGoW + shit + fshit);
            }
        }
        out.println(best);
    }
}
