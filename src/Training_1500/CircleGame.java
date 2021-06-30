package Training_1500;

import java.io.PrintWriter;
import java.util.Scanner;

public class CircleGame {
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
        long d = in.nextInt(), k = in.nextInt();
        int cur = 0;
        while (isInCycle(cur + 1, cur + 1, k, d * d)) cur++;
        if (isInCycle(cur + 1, cur, k, d * d)){
            out.println("Ashish");
        }else{
            out.println("Utkarsh");
        }
    }
    static boolean isInCycle(int l, int r, long k, long dis){
        long sz = k * l * k * l + k * r * k * r;
        return dis >= sz;
    }

}
