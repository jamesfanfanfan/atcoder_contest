package codeforce.edu102;

import java.io.PrintWriter;
import java.util.Scanner;
public class C {
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
        int k = in.nextInt();
        int[] arr = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            arr[i] = i;
        }
        int i = (k - n + k);
        int j = k;
        while (i < j){
            arr[i] = j;
            arr[j] = i;
            i++;
            j--;
        }
        StringBuilder sb = new StringBuilder();
        for(i = 1; i <= k; i++){
            sb.append(arr[i]);
            sb.append(" ");
        }
        out.println(sb.toString());
    }
}

