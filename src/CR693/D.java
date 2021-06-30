package CR693;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class D {
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
        int[] arr = new int[n];
        long a = 0;
        long b = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            if (arr[i] % 2 == 0){
                a += arr[i];
            }else{
                b += arr[i];
            }
        }
        Arrays.sort(arr);
        int v = 0;
        for(int i = n - 1; i >= 0; i--){
            if (v == 0){
                a += arr[i];
            }else{
                b += arr[i];
            }
            v = 1 - v;
        }
        if (a > b){
            out.println("Alice");
        }else if (a < b){
            out.println("Bob");
        }else{
            out.println("Tie");
        }

    }
}
