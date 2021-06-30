package codeforce.ECR100;

import java.io.PrintWriter;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        A a = new A();
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            a.solve(sc, pw);
        }
        pw.close();
    }
    void solve(Scanner in, PrintWriter out){
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
    }
}
