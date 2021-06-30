package codeforce.edu104;

import java.io.PrintWriter;
import java.util.Arrays;
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
        int[] arr = new int[n * (n - 1) / 2 + 1];
        int[] ans = new int[n * (n - 1) / 2 + 1];
        Arrays.fill(ans, -2);
        int tot = n * (n - 1) / 2;
        for (int i = 0; i <= n * (n - 1) / 2; i++) {
            int point = (tot - i) * 3 + i * 2;
            if (point % n == 0){
                Arrays.fill(arr, point / n);
                int win = tot - i;
                for (int j = 0; j < n; j++) {
                    int needWin = Math.min(win, arr[j] / 3);
                    int neeDraw = arr[j] - needWin * 3;
                    int v = 0;
                    for (int k = j + 1; k < n; k++) {
                        if (needWin > 0){
                            win--;
                            needWin--;
                            v = 1;
                        }else if (neeDraw > 0 && arr[k] > 0){
                            arr[k]--;
                            neeDraw--;
                            v = 0;
                        }else{
                            win--;
                            v = -1;
                            arr[k] -= 3;
                        }
                        out.print(v+" ");
                    }
                }
                out.println();
                return;
            }
        }
    }
}
