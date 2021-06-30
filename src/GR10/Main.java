package GR10;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public class Pair{
        int k;
        int v;
        Pair(int k, int v){
            this.k = k;
            this.v = v;
        }

    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        Main main = new Main();
        int cnt = sc.nextInt();
        for (int i = 0; i < cnt; i++) {
           main.B(sc, pw);
        }
        pw.close();
    }
    void B(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        List<Pair> list = new ArrayList<Pair>();
        for (int i = 0; i < n; i++) {
            list.add(new Pair(in.nextInt(), in.nextInt()));
        }
        boolean tot = false;
        for (int i = 0; i < list.size(); i++) {
            boolean find = true;
            for (int j = 0; j < list.size(); j++) {

                int x1 = list.get(i).k;
                int y1 = list.get(i).v;
                int x2 = list.get(j).k;
                int y2 = list.get(j).v;
                if (Math.abs(x1 - x2) + Math.abs(y1 - y2) > k){
                    find = false;
                }
            }
            if (find) tot = true;
        }
        if (tot){
            out.println(1);
        }else{
            out.println(-1);
        }
    }
    static void A(Scanner in, PrintWriter out){
        int n = in.nextInt();
        String s = in.next();
        char[] cs = s.toCharArray();
        int[] store = new int[26];
        for (int i = 0; i < n; i++) {
            store[cs[i] - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            while (store[i] > 0){
                sb.append((char) (i + 'a'));
                store[i] --;
            }
        }
        out.println(sb.toString());
    }
}
