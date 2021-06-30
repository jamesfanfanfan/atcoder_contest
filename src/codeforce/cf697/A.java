package codeforce.cf697;

import java.io.PrintWriter;
import java.util.Scanner;

public class A {
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
        long get = in.nextLong();
        while (get > 1){
            if (get % 2 == 1){
                out.println("YES");
                return;
            }
            get /= 2;
        }
        out.println("NO");
    }
}
