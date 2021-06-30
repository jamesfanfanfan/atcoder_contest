package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class PerfectSecurity {
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
        int[] prr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            prr[i] = in.nextInt();
        }
        trie root = new trie();
        for(int x : prr){
            trie tra = root;
            for (int i = 30; i >= 0; i--) {
                if ((x | (1 << i)) == x){
                    if (tra.nxt[1] == null){
                        tra.nxt[1] = new trie();
                    }
                    tra = tra.nxt[1];
                }else{
                    if (tra.nxt[0] == null){
                        tra.nxt[0] = new trie();
                    }
                    tra = tra.nxt[0];
                }
                tra.cnt++;
            }
            tra.val = x;
        }
        for (int x : arr) {
            trie tra = root;
            for (int i = 30; i >= 0; i--) {
                if ((x | (1 << i)) == x){
                    if (tra.nxt[1] != null && tra.nxt[1].cnt > 0){
                        tra = tra.nxt[1];
                    }else{
                        tra = tra.nxt[0];
                    }
                }else{
                    if (tra.nxt[0] != null && tra.nxt[0].cnt > 0){
                        tra = tra.nxt[0];
                    }else{
                        tra = tra.nxt[1];
                    }
                }
            }
            out.print((tra.val ^ x)+" ");
            int v = tra.val;
            tra = root;
            for (int j = 30; j >= 0; j--) {
                if ((v | (1 << j)) == v){
                    tra = tra.nxt[1];
                }else{
                    tra = tra.nxt[0];
                }
                tra.cnt--;
            }
        }
        out.println();
    }
    static class trie{
        trie[] nxt = new trie[2];
        int cnt = 0;
        int val = 0;
    }
}
