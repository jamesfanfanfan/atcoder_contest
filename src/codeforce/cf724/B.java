package codeforce.cf724;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

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
        int n = in.nextInt();
        char[] cs = in.next().toCharArray();
        node root = new node();
        for (int i = 0; i < n; i++) {
            node tra = root;
            for (int j = i; j < n; j++) {
                int pos = cs[j] - 'a';
                if (tra.nxt[pos] == null)  tra.nxt[pos] = new node();
                tra = tra.nxt[pos];
            }
        }
        for (int i = 1; i <= n; i++) {
            char[] ans = new char[i];
            LinkedList<Character> q = new LinkedList<>();
            if (dfs(root, i, q, true)){
//                System.out.println("Sss");
                StringBuilder sb = new StringBuilder();
                while (!q.isEmpty()){
                    sb.append(q.poll());
                }
                out.println(sb.toString());
                return;
            }
        }
    }
    static boolean dfs(node root, int len, LinkedList<Character> q, boolean isP){
//        System.out.println(root+" "+len+" "+q);
        if (q.size() == len){
            if (!isP) return true;
            return false;
        }
        if (root == null){
            while (len < q.size()) q.add('a');
            return true;
        }
        for (int i = 0; i < 26; i++) {
            if (root.nxt[i] != null){
                q.add((char) (i + 'a'));
                if (!dfs(root.nxt[i], len, q, isP)){
                    q.removeLast();
                }else{
                    return true;
                }
            }else{
                q.add((char)(i + 'a'));
                return dfs(null, len, q, false);
            }
        }
        return false;
    }
    static class node{
        node[] nxt = new node[26];
    }
}
