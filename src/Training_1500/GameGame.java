package Training_1500;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class GameGame {
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
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 31; i >= 0; i--) {
            int cnt = 0;
            for(int x : arr){
                if ((x | (1 << i)) == x){
                    cnt++;
                }
            }
            if (cnt % 2 == 0){
                continue;
            }else{
                if(cnt % 4 == 3 && (n - cnt) % 2 == 0){
                    out.println("LOSE");
                }else{
                    out.println("WIN");
                }
                return;
            }
        }
        out.println("DRAW");
    }
}
