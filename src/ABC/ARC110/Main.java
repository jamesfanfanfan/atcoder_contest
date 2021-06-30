package ABC.ARC110;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        B(sc, pw);
        pw.close();
    }
    static void B(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n+1];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        LinkedList<Integer> q = new LinkedList<>();
        boolean[] seen = new boolean[n + 1];
        for (int i = 0; i < n - 1; i++) {
            if(arr[i] > arr[i + 1]){
                q.add(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (q.isEmpty()){
                out.println(-1);
                return;
            }
            int v = q.poll();
            if (arr[v] < arr[v + 1])continue;
            if (seen[v]){
                out.println(-1);
                return;
            }
            res.add(v);
            seen[v] = true;
            // we need to swap v and v + 1
            int trans = arr[v];
            arr[v] = arr[v + 1];
            arr[v + 1] = trans;
            if(v == 0){
                if(n > 2){
                    if(arr[v + 1] > arr[v + 2]) q.add(v + 1);
                }
            }else if (v == n - 2){
                if(n > 2){
                    if(arr[v - 1] > arr[v]) q.add(v - 1);
                }
            }else{
                if(n > 2){
                    if(arr[v - 1] > arr[v]) q.add(v - 1);
                    if(arr[v + 1] > arr[v + 2]) q.add(v + 1);
                }
            }

        }
        for (int i = 0; i < n - 1; i++) {
            if(arr[i] > arr[i + 1]){
                out.println(-1);
                return;
            }
        }
        for(int v : res){
            out.println(v + 1);
        }
    }
    static void A(Scanner in, PrintWriter out){
        int n = in.nextInt();
        String s = in.next();
        // 110110110110110110110
        //  1011
        //110110110
        // 11 10 01 11 10 01 11 10
        char[] cs = s.toCharArray();
        int startIndex = -1;
        if(cs[0] == '1' && cs[1] == '1'){
            startIndex = 0;
        }else if(cs[0] == '1' && cs[1] == '0'){
            startIndex = 1;
        }else if(cs[0] == '0' && cs[1] == '1'){
            startIndex = 2;
        }
        boolean find = false;
        if (startIndex != -1){
            find = true;
            for (int i = 0, j = startIndex; i < n; i++, j = (j + 1) % 3) {
                if(cs[i] != "110".charAt(j)){
                    find = false;
                    break;
                }
            }
        }
        long tot = (long) 1e10;
        //"110110110110"
        if (find){
            if(cs[0] == '0' && cs[1] == '1'){
                int t = (n - (6 - startIndex) - 1) / 3 + 1;
                int amnt = t + 2;
                out.println(tot/amnt);
            }else{
                int t = (n - (6 - startIndex) - 1) / 3 + 1;
                int amnt = t + 2;
                out.println((tot * 2/amnt));
            }
        }else{
            out.println(0);
        }
    }
}
