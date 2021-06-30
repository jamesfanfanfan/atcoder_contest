package codeforce.cf724;

import java.io.PrintWriter;
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
    static class node{
        node pre = null, nxt = null;
        int val;
        boolean hasVal = false;
        public node(int val) {
            this.val = val;
            hasVal = true;
        }
        public node() {
        }
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        node neo = new node(arr[0]);
        for(int i = 1; i < n; i++){
            if (arr[i] < neo.val){
                if (neo.pre == null || neo.pre.val < arr[i]){
                    neo = addright(neo, arr[i]);
                }else if (neo.pre != null && neo.pre.val == arr[i]){
                    neo = neo.pre;
                }else{
                    out.println("NO");
                    return;
                }
            }else if (arr[i] > neo.val){
                if (neo.nxt == null || neo.nxt.val > arr[i]){
                    neo = addleft(neo, arr[i]);
                }else if (neo.nxt != null && neo.nxt.val == arr[i]){
                    neo = neo.nxt;
                }else{
                    out.println("NO");
                    return;
                }
            }
        }
        out.println("YES");
    }
    static node addleft(node dumLeft, int v){
        node neo = new node(v);
        neo.nxt = dumLeft.nxt;
        if (neo.nxt != null)neo.nxt.pre = neo;
        neo.pre = dumLeft;
        dumLeft.nxt = neo;
        return neo;
    }

    static node addright(node dumright, int v){
        node neo = new node(v);
        neo.pre = dumright.pre;
        if (neo.pre != null) neo.pre.nxt = neo;
        neo.nxt = dumright;
        dumright.pre = neo;
        return neo;
    }
}
