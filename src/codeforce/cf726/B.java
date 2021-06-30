package codeforce.cf726;

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
        int n = in.nextInt(), m = in.nextInt(), i = in.nextInt(), j = in.nextInt();
        if (i == n && j == 1){
            out.println(1+" "+m+" "+1+" "+m);
        }else if (i == n && j == m){
            out.println(1+" "+1+" "+1+" "+1);
        }else if (i == 1 && j == m){
            out.println(n+" "+1+" "+n+" "+1);
        }else if (i == 1 && j == 1){
            out.println(n+" "+m+" "+n+" "+m);
        }else{
            out.println(1+" "+1+" "+n+" "+m);
        }
    }
}
