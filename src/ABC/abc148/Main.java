package ABC.abc148;

import java.util.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main m = new Main();
        m.solve(sc);
    }

    List<Integer>[] arr;
    void solve(Scanner in){
        int n = in.nextInt();
        arr = new List[n+1];
        for(int i=0;i<n;i++){
            arr[i] = new ArrayList<>();
        }
        int u = in.nextInt()-1;
        int v = in.nextInt()-1;
        for(int i=0;i<n-1;i++){
            int l = in.nextInt();
            int r = in.nextInt();
            arr[l-1].add(r-1);
            arr[r-1].add(l-1);
        }
        int[] s1 = new int[n+1];
        int[] s2 = new int[n+1];
        s1[n] = -1;
        s2[n] = -1;
        dfs(u,n,s1);
        dfs(v,n,s2);
        int res = 0;
        for(int i=0;i<n;i++){
            if(s2[i]>s1[i]){
                res = Math.max(res,s2[i]);
            }
        }
        System.out.println(res-1);
    }
    void dfs(int v, int p, int[] st){
        st[v] = st[p]+1;
        for(int nxt:arr[v]){
            if(nxt==p) continue;
            dfs(nxt,v,st);
        }
    }


}
