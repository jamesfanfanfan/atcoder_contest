package codeforce.edu110;

import java.io.PrintWriter;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static class query{
        char c;
        int v;
        int idx;
        public query(char c, int v, int idx) {
            this.c = c;
            this.v = v;
            this.idx = idx;
        }
    }
    static class Tree{
        Tree par, left, right;
        boolean isleaf = false;
        char c;
        int val = 0;
    }
    static void solve(Scanner in, PrintWriter out){
        int k = in.nextInt();
        int n = 1 << k;
        char[] cs = in.next().toCharArray();
        int q = in.nextInt();
        query[] qs = new query[q];
        for (int i = 0; i < q; i++) {
            int v = in.nextInt();
            char c = in.next().charAt(0);
            qs[i] = new query(c, v, i);
        }
        char[] neo = new StringBuilder(new String(cs)).reverse().toString().toCharArray();
        Tree[] ts = new Tree[n];
        for (int i = 0; i < n - 1; i++) {
            ts[i] = new Tree();
            ts[i].c = neo[i];
        }
        for (int i = 0; i < n - 1; i++) {
            if (2 * i + 2 >= n) {
                ts[i].isleaf = true;
                continue;
            }
            ts[i].left = ts[2 * i + 1];
            ts[i].right = ts[2 * i + 2];
            ts[2 * i + 1].par = ts[i];
            ts[2 * i + 2].par = ts[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ts[i].isleaf){
                if (neo[i] == '?'){
                    ts[i].val = 2;
                }else{
                    ts[i].val = 1;
                }
            }else{
                if (neo[i] == '?'){
                    ts[i].val = ts[i].left.val + ts[i].right.val;
                }else if (neo[i] == '0'){
                    ts[i].val = ts[i].right.val;
                }else{
                    ts[i].val = ts[i].left.val;
                }
            }
        }

        for (int i = 0; i < q; i++) {
            int v = qs[i].v - 1;
            char c = qs[i].c;
            int rv = n - v - 2;

            int former = ts[rv].val;
            ts[rv].c = c;
            if (c == '?'){
                if (ts[rv].isleaf){
                    ts[rv].val = 2;
                }else{
                    ts[rv].val = ts[rv].left.val + ts[rv].right.val;
                }
            }else if (c == '0'){
                if (ts[rv].isleaf){
                    ts[rv].val = 1;
                }else{
                    ts[rv].val = ts[rv].right.val;
                }
            }else{
                if (ts[rv].isleaf){
                    ts[rv].val = 1;
                }else{
                    ts[rv].val = ts[rv].left.val;
                }
            }
            if (ts[rv].par == null){
                out.println(ts[rv].val);
            }else{
                out.println(dfs(ts[rv].par));
            }
//            ts[rv].val = former;
        }
    }
    static int dfs(Tree root){
        int former = root.val;
        if (root.c == '?'){
            root.val = root.left.val + root.right.val;
        }else if (root.c == '0'){
            root.val = root.right.val;
        }else{
            root.val = root.left.val;
        }
        int ans = 0;
        if (root.par == null){
            ans = root.val;
        }else{
            ans = dfs(root.par);
        }
//        root.val = former;
        return ans;
    }
}
