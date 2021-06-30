package codeforce.cf697;

import java.io.PrintWriter;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, out);
        }
        out.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int v = in.nextInt();
        for (int i = 0; i * 2020 <= v; i++) {
            if ((v - (i * 2020)) % 2021 == 0){
                out.println("YES");
                return;
            }
        }
        out.println("NO");
    }
}
