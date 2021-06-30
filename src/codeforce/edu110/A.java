package codeforce.edu110;

import java.io.PrintWriter;
import java.util.Arrays;
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
        int s1 = in.nextInt(), s2 = in.nextInt(), s3 = in.nextInt(), s4 = in.nextInt();
        int[] arr = new int[]{s1, s2, s3, s4};
        int l = Math.max(s1, s2);
        int r = Math.max(s3, s4);
        Arrays.sort(arr);
        if ((l == arr[2] && r == arr[3]) || (r == arr[2] && l == arr[3])){
            out.println("YES");
        }else{
            out.println("NO");
        }
    }
}
