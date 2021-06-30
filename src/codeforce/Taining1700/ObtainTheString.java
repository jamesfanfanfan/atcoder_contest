package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class ObtainTheString {
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
        char[] cs = in.next().toCharArray();
        char[] ct = in.next().toCharArray();
        TreeSet<Integer>[] sets = new TreeSet[26];
        for (int i = 0; i < 26; i++) {
            sets[i] = new TreeSet<>();
        }
        int[] store = new int[26];
        int n = cs.length;
        for(int i = 0; i < n; i++){
            store[cs[i] - 'a']++;
            sets[cs[i] - 'a'].add(i);
        }
        for(char c : ct){
            if (store[c - 'a'] == 0){
                out.println(-1);
                return;
            }
        }
        int cnt = 1;
        int start = -1;
        for (char c : ct) {
            Integer get = sets[c - 'a'].higher(start);
//            System.out.println(c+" "+get+" start:"+start);
            if (get == null){
                start = sets[c - 'a'].first();
                cnt++;
            }else{
                start = get;
            }
        }
        out.println(cnt);
    }
}
