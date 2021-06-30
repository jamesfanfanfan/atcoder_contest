package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
//https://codeforces.com/problemset/problem/1209/D
public class CowandSnacks {
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
        int n = in.nextInt(), k = in.nextInt();
        int[][] arr = new int[k][];
        for (int i = 0; i < k; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        int[] pars = new int[n + 5];
        Arrays.fill(pars, - 1);
        int cnt = 0;
        for(int[] ok : arr){
            int l = ok[0], r = ok[1];
            if (find(l, pars) != find(r, pars)){
                cnt++;
            }
            union(l, r, pars);
        }
        out.println(k - cnt);
    }
    static int find(int i, int[] par){
        if (par[i] < 0) return i;
        return par[i] = find(par[i], par);
    }
    static boolean union(int i, int j, int[] par){
        int pi = find(i, par);
        int pj = find(j, par);
        if (pi == pj) return false;
        if (par[pi] < par[pj]){
            par[pi] += par[pj];
            par[pj] = pi;
        }else{
            par[pj] += par[pi];
            par[pi] = pj;
        }
        return true;
    }
}
