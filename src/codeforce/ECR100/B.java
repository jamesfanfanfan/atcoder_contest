package codeforce.ECR100;

import java.io.PrintWriter;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        B a = new B();
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            a.solve(sc, pw);
        }
        pw.close();
    }
    void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        long[] res = new long[n];
        res[0] = arr[0];
        for(int i = 1; i < n; i++){
            if(res[i - 1] * 2 < res[i]){

            }
        }
        for(long x : res){
            out.print(x+" ");
        }
        out.println();
    }
}
