package codeforce.edu102;

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
        String s = in.next();
        String t = in.next();
        StringBuilder sb = new StringBuilder();
        //out.println("----------"+" "+t+" "+s);
        if(t.length() > s.length()){
            String p = s;
            s = t;
            t = p;
        }
        sb.append(s);
        for (int i = 1; i <= t.length(); i++, sb.append(s)) {
            if((i * s.length()) % t.length() == 0){
                //out.println(sb.toString()+" "+t+" "+s);
                if(check(sb.toString(), t)){
                    out.println(sb.toString());
                }else{
                    out.println(-1);
                }
                return;
            }
        }
        out.println(sb.toString());
    }
    static boolean check(String tar, String s){
        for(int i = 0; i < tar.length(); i+=s.length()){
            if(!s.equals(tar.substring(i, i + s.length()))) return false;
        }
        return true;
    }
}
