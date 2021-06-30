package codeforce.cf697;

import java.io.PrintWriter;
import java.util.*;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, out);
        }
        out.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        char[][] ca = new char[n][n];
        char[][] cb = new char[n][n];
        for (int i = 0; i < n; i++) {
            ca[i] = in.next().toCharArray();
        }
        for (int i = 0; i < n; i++) {
            cb[i] = in.next().toCharArray();
        }
        int[][] dp = new int[n][n];
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ca[i][j] != cb[i][j]){
                    res.add(new int[]{i, j});
                }
            }
        }
        if (res.size() == 0){
            out.println("YES");
        }else{
            int i = res.get(0)[0];
            int j = res.get(0)[1];
            if (row(i, j, n, ca, cb) && helper(i, j, n, ca, cb)){
                out.println("YES");
            }else{
                out.println("NO");
            }
        }

    }
    static boolean row(int i, int j, int n, char[][] ca, char[][] cb){
        int[] row = new int[n];
        for (int k = 0; k < n; k++) {
            if (ca[k][j] == cb[k][j]){
                row[k]++;
            }
        }
        for (int k = 0; k < n; k++) {
            char[] cp = new char[n];
            for (int l = 0; l < n; l++) {
                if (row[l] % 2 == 0){
                    cp[l] = ca[l][k];
                }else{
                    cp[l] = ca[l][k] == '0' ? '1' : '0';
                }
            }
            int cnt = 0;
            for (int l = 0; l < n; l++) {
                if (cp[l] != cb[l][k]){
                    cnt++;
                }
            }
            if (cnt != 0 && cnt != n) return false;
        }
        return true;
    }
    static boolean helper(int i, int j, int n, char[][] ca, char[][] cb){
        int[] col = new int[n];
        for (int k = 0; k < n; k++) {
            if (ca[i][k] == cb[i][k]){
                col[k]++;
            }
        }
        for (int k = 0; k < n; k++) {
            char[] cp = new char[n];
            for (int l = 0; l < n; l++) {
                if (col[l] % 2 == 0){
                    cp[l] = ca[k][l];
                }else{
                    cp[l] = ca[k][l] == '0'? '1' :'0';
                }
            }
            int cnt = 0;
            for (int l = 0; l < n; l++) {
                if (cp[l] != cb[k][l]){
                    cnt++;
                }
            }
            if (cnt != 0 && cnt != n) return false;
        }
        return true;
    }

}
