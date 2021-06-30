package CR693;

import java.io.PrintWriter;
import java.util.Scanner;

public class A {
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
        int w = in.nextInt();
        int h = in.nextInt();
        int n = in.nextInt();
        int cnt = 1;
        while (h % 2 == 0 || w % 2 == 0){
            if (h % 2 == 0){
                cnt*=2;
                h /= 2;
            }else if (w % 2 == 0){
                cnt*=2;
                w /= 2;
            }

        }

        if (cnt >= n){
            out.println("YES");
        }else{
            out.println("NO");
        }

    }
}
