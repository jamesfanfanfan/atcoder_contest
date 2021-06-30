package codeforce.cf725;

import java.io.PrintWriter;
import java.util.Scanner;

public class G {
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
        int x = in.nextInt(), y = in.nextInt(), a = in.nextInt(), b = in.nextInt();
        int mx = (x + y) / (a + b);
        int l = 0, r = mx;
        if(a == b){
            out.println(Math.min(x / a, y / a));
            return;
        }
        while (l < r){
            int mid = (l + r + 1) / 2;
            if (a > b){
                double rr = (y - mid * b) * 1.0 / (a - b);
                double ll = (x - mid * a) * 1.0 / (b - a);
                rr = Math.min(mid, rr);
                if (ll > rr){
                    r = mid - 1;
                    continue;
                }
                if (((rr - ll) >= 1 || Math.floor(ll) == ll || Math.floor(rr) > Math.floor(ll)) && y - mid * b >= 0){
                    l = mid;
                }else{
                    r = mid - 1;
                }
            }else if (a < b){
                double ll = (y - mid * b) * 1.0 / (a - b);
                double rr = (x - mid * a) * 1.0 / (b - a);
                rr = Math.min(mid, rr);
                if (ll > rr){
                    r = mid - 1;
                    continue;
                }
                if (((rr - ll) >= 1 || Math.floor(ll) == ll || Math.floor(rr) > Math.floor(ll)) && x - mid * a >= 0){
                    l = mid;
                }else{
                    r = mid - 1;
                }
            }
        }
        out.println(r);
    }
}
