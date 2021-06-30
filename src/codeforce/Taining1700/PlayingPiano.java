package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class PlayingPiano {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
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
        boolean[] dp = new boolean[5];
        Arrays.fill(dp, true);
        int[][] par = new int[n + 1][5];
        for (int i = 1; i < n; i++) {
            boolean[] ndp = new boolean[5];
            if (arr[i - 1] > arr[i]){
                for (int j = 0; j < 5; j++) {
                    for (int k = j + 1; k < 5; k++) {
                        ndp[j] |= dp[k];
                        if (dp[k]){
                            par[i][j] = k;
                            break;
                        }
                    }
                }
            }else if (arr[i - 1] < arr[i]){
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < j; k++) {
                        ndp[j] |= dp[k];
                        if (dp[k]){
                            par[i][j] = k;
                            break;
                        }
                    }
                }
            }else{
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        if (k == j) continue;
                        ndp[j] |= dp[k];
                        if (dp[k]){
                            par[i][j] = k;
                            break;
                        }
                    }
                }
            }
            dp = ndp;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            if (dp[i]){
                stack.push(i);
                int pa = par[n - 1][i];
                for(int j = n - 2; j >= 0; j--){
                    stack.push(pa);
                    pa = par[j][pa];
                }
                break;
            }
        }
        if (stack.size() == 0){
            out.println(-1);
        }else{
            while (!stack.isEmpty()){
                out.print((stack.pop() + 1)+" ");
            }
        }
    }
}
