package codeforce.ECR98;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        Main main = new Main();
        for (int i = 0; i < t; i++) {
            main.C(sc, pw);
        }
        pw.close();
    }
    void C(Scanner in, PrintWriter out){
        String s = in.next();
        int n = s.length();
        int tot = 0;
        int y = 0;
        int x = 0;
        for (int i = 0; i < n; i++) {
            if(s.charAt(i) == '('){
                y++;
            }else if(s.charAt(i) == ')'){
                if(y > 0){
                    y --;
                    tot ++;
                }
            }else if(s.charAt(i) == '['){
                x ++;
            }else{
                if(x > 0){
                    x--;
                    tot ++;
                }
            }
        }
        out.println(tot);

    }
    void B(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            cnt += arr[i];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int ak = cnt - arr[i];
            res = Math.max(res, ak - (ak % (n - 1)));
        }
        out.println(res);
    }

    void A(Scanner in, PrintWriter out){
        int x = in.nextInt();
        int y = in.nextInt();
        out.println((x + y + Math.max(0, Math.abs(x - y) - 1)));

    }
}
