package codeforce.CF689;

import java.io.PrintWriter;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        A a = new A();
        while (t > 0){
            a.solve(sc, pw);
            t--;
        }
        pw.close();
    }
    void solve(Scanner sc, PrintWriter pw){
        //acbacbacb
        int n = sc.nextInt();
        int k = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (k  > 0){
            sb.append('a');
            k--;
            n--;
        }
        while (n > 0){
            char[] cs = new char[]{'b', 'c', 'a'};
            int v = n;
            for(int i = 0; i < Math.min(3, v); i++){
                sb.append(cs[i]);
                n--;
            }
        }
        pw.println(sb.toString());

    }

}
