package ABC.AGC_50_51;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        solve(sc, pw);
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            out.println((((2 * i) % n) + 1) +" "+(((2 * i + 1) % n) + 1));
        }
    }
}
