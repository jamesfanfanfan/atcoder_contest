package codeforce.edu102;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class D {
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
        int m = in.nextInt();
        char[] cs = in.next().toCharArray();
        int[][] qs = new int[m][];
        for (int i = 0; i < m; i++) {
            qs[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        int[] le = new int[n + 2];
        int[][] itl = new int[n + 2][2];
        int[] ri = new int[n + 2];
        int[][] itr = new int[n + 2][2];
        // start from the left
        int v = 0;
        int min = 0;
        int max = 0;
        for(int i = 1; i <= n; i++){
            if(cs[i - 1] == '-'){
                v--;
                max = Math.max(max, v);
                min = Math.min(min, v);
            }else{
                v++;
                max = Math.max(max, v);
                min = Math.min(min, v);
            }
            le[i] = v;
            itl[i] = new int[]{min, max};
        }
        v = 0;
        max = 0;
        min = 0;
        for (int i = n; i >= 1; i--) {
            if(cs[i - 1] == '-'){
                v--;
                max = Math.max(max, v);
                min = Math.min(min, v);
            }else{
                v++;
                max = Math.max(max, v);
                min = Math.min(min, v);
            }
            ri[i] = v;
            itr[i] = new int[]{min, max};
        }
        List<Integer> res = new ArrayList<>();
        for(int[] q : qs){
            int l = q[0];
            int r = q[1];
            int lv = le[l - 1];
            int rv = ri[r + 1];
            max = itr[r + 1][1];
            min = itr[r + 1][0];
            int[] ndis = new int[]{-max + (lv + rv), -min + (lv + rv)};
            int[] bf = itl[l - 1];
            //out.println(Arrays.toString(bf)+" "+ Arrays.toString(ndis));
            res.add(Math.max(ndis[1], bf[1]) - Math.min(ndis[0], bf[0]) + 1);
        }
        for(int x : res){
            out.println(x);
        }
    }
}