package codeforce.ECR99;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int cnt = sc.nextInt();
        for (int i = 0; i < cnt; i++) {
            C(sc, pw);
        }
        pw.close();

    }
    static void A(Scanner in, PrintWriter out){
        String s = in.next();
        out.println(s.length());
    }
    static void B(Scanner in, PrintWriter out){
        int n = in.nextInt();
        if(n < 0){
            out.println(n);
            return;
        }
        int k = 1;
        int cnt = 0;
        int pos = n;
        int res = 0;
        while(cnt < n){
            cnt += k;
            k++;
            res++;
        }
        out.println((cnt - n + res));
    }
    static void C(Scanner in, PrintWriter out){
        int x = in.nextInt();
        int y = in.nextInt();
        if(x == 1){
            out.println((y == 0 ? 1 : 0)+" "+ y);
        }else if(x == 0){
            out.println(x +" "+y);
        }else if(y == 0){
            out.println(x+" "+y);
        }else{
            out.println((x - 1) +" "+y);
        }
    }
    static void D(Scanner in, PrintWriter out){

    }
}
