package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class BinaryLiterature {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
//    https://codeforces.com/contest/1508/problem/A
// when you think about three, you should remember that each position has at least 2 of 3 should be same,
// think about problem in this way, since we only have 0 and 1, we could only care about the number of 1 and 0, say if both
//    string have n or larger than n number of 1 or 0, they could be made as a string
//    however if they are not, the rest of the string must have a pair that they share larger than n number of 1 or 0
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        char[] c1 = in.next().toCharArray();
        char[] c2 = in.next().toCharArray();
        char[] c3 = in.next().toCharArray();
    }

}
