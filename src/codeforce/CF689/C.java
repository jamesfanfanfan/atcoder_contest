package codeforce.CF689;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        C c = new C();
        while (t > 0){
            t--;
            c.solve(sc, pw);
        }
        pw.close();

    }
    class Node{
        int len;
        double prob;
        Node(int len, double prob){
            this.len = len;
            this.prob = prob;
        }
    }
     void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt(), m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
         List<Node> list = new ArrayList<>();
         for (int i = 0; i < m; i++) {
             list.add(new Node(sc.nextInt(), sc.nextDouble()));
         }
         int max = 0;
         int idx = 0;
         double res = 1;
         int[] le = new int[n];
         int[] ri = new int[n];
         for (int i = 0; i < n; i++) {
             max = Math.max(max, arr[i]);
             le[i] = max;
         }
         max = Integer.MAX_VALUE;
         for (int i = n - 1; i >= 0; i--) {
             max = Math.min(max, arr[i]);
             ri[i] = max;
         }
         int end = n;
         for (int i = n - 1; i >= 0; i--) {
             if(arr[i] == end){
                 end --;
             }else{
                 break;
             }
         }
         for(Node nd : list){
             if(nd.len >= end){
                 res *= (1 - nd.prob);
             }
         }
         if(end == 0){
             pw.println(1.0);
         }else{
             pw.println((1 - res));
         }
    }
}
