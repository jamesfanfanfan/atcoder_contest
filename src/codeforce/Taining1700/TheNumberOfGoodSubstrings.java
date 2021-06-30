package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class TheNumberOfGoodSubstrings {
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
        char[] cs = in.next().toCharArray();
        String s = new String(cs);
        int n = cs.length;
        int cnt = 0;
        int left = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0'){
                left++;
            }else{
                int j = i;
                long val = 0;
                while (j < n && val <= cs.length){
                    val *= 2;
                    if (cs[j] == '1'){
                        val += 1;
                    }
                    if (j - i + 1 + left >= val){
                        cnt++;
                    }
                    j++;
                }
                left = 0;
            }
        }
        out.println(cnt);
    }

}
