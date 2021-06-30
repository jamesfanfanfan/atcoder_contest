package CR696;

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
        int n = in.nextInt();
        char[] cs = in.next().toCharArray();
        int[] arr = new int[n];
        int pre = -1;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0'){
                if (pre == 1){
                    arr[i] = 0;
                    pre = 0;
                }else{
                    arr[i] = 1;
                    pre = 1;
                }
            }else{
                if (pre == 2){
                    arr[i] = 0;
                    pre = 1;
                }else{
                    arr[i] = 1;
                    pre = 2;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]);
        }
        out.println(sb.toString());
    }
}
