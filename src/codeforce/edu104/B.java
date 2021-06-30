package codeforce.edu104;

import java.io.PrintWriter;
import java.util.Scanner;
//should remember that the final position would total number of time plus total number of conflict(bomb in my case)
public class B {
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
        int n = in.nextInt(), k = in.nextInt();
        if (n % 2 == 0){
            out.println(((k - 1) % n + 1));
        }else{
//            each loop it should take n - 1 time, we could see how many loops it take and after that many loop, where is the position
//            why k - 1 ?  we need to consider the length of the time when they meet, which turns out interval is same
//            since the first time is 1, every round it takes n / 2 time to form a need round, since the first position is 1, we shall see
//            even though final time is k, since we make first time as 0, then final time should minus 1
            int bomb = (k - 1) / (n / 2);
//            System.out.println(bomb);
            out.println((k - 1 + bomb) % n + 1);
        }
    }
}
