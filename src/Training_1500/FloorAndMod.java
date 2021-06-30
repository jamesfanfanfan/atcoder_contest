package Training_1500;

import java.io.PrintWriter;
import java.util.Scanner;

public class FloorAndMod {
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
        int x = in.nextInt(), y = in.nextInt();
        long ans = 0;
        for (int i = 1; i < Math.sqrt(x); i++) {
            int add = Math.min(y, (x - i) / i) - i;
            ans += Math.max(0, add);
        }
        out.println(ans);
    }

}
