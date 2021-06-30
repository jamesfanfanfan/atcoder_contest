package codeforce.cf726;

import java.io.PrintWriter;
import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
//    static int cur = 0;
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), k = in.nextInt();
        String s = in.next();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append("z");
        }
        for (int i = 0; i < n; i++) {
            StringBuilder neo = new StringBuilder(s.substring(0, i + 1));
            while (neo.length() < k){
                neo = neo.append(neo);
            }
//            System.out.println(neo.toString());
//            System.out.println(neo.toString().substring(0, k).compareTo(sb.toString()));
            if (neo.toString().substring(0, k).compareTo(sb.toString()) < 0){
                sb = neo;
            }
        }
        out.println(sb.toString().substring(0, k));
    }

}
