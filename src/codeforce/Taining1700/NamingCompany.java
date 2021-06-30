package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
//https://codeforces.com/problemset/problem/794/C
//good problem anyway : first you got to first collect first n / 2 and second n / 2 elements, because we can prove
// that using remaining element could always be replaced the chosen part
// the next scenario would be when a' s largest element is same as the b 's smallest element,
//this is hard : for a : since the remaining part would all be better then my own, what would we do:
// since we need smallest string, we just put largest element into last, so that I could let those smaller element all come
// before me which is good,
public class NamingCompany {
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
//        f in the last position means comes first and s in the last position means comes second
//        int n = in.nextInt();
        char[] cf = in.next().toCharArray();
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        Arrays.sort(cf);
        Arrays.sort(cs);
        cs = new StringBuilder(new String(cs)).reverse().toString().toCharArray();
        LinkedList<Character> qf = new LinkedList<>();
        LinkedList<Character> qs = new LinkedList<>();
        for (int i = 0; i < (n + 1) / 2; i++) {
            qf.add(cf[i]);
        }
        for (int i = 0; i < n / 2; i++) {
            qs.add(cs[i]);
        }
//        System.out.println(qf+" "+qs);
        cs = new StringBuilder(new String(cs)).reverse().toString().toCharArray();
        StringBuilder bf = new StringBuilder();
        StringBuilder lat = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0){
                if (qs.size() == 0){
                    if (lat.length() > 0){
                        lat.append(qf.poll());
                    }else{
                        bf.append(qf.poll());
                    }
                }else{
                    if (qs.peek() <= qf.peek()){
                        lat.append(qf.removeLast());
                    }else{
                        bf.append(qf.poll());
                    }
                }
            }else{
                if (qf.size() == 0){
                    if (lat.length() > 0){
                        lat.append(qs.poll());
                    }else{
                        bf.append(qs.poll());
                    }
                }else{
                    if (qs.peek() <= qf.peek()){
                        lat.append(qs.removeLast());
                    }else{
                        bf.append(qs.poll());
                    }
                }
            }
        }
        bf.append(lat.reverse());
        out.println(bf.toString());
    }
}
