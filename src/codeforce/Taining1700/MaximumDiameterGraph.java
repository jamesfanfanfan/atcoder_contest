package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MaximumDiameterGraph {
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
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        LinkedList<Integer> one = new LinkedList<>();
        List<int[]> larger = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] > 1){
                larger.add(new int[]{i, arr[i]});
            }else{
                one.add(i);
            }
        }
        List<int[]> ans = new ArrayList<>();
        if (one.size() == 0){
            out.println("YES "+(larger.size() - 1));
            for (int i = 0; i < larger.size() - 1; i++) {
                int l = larger.get(i)[0] + 1, r = larger.get(i + 1)[0] + 1;
                ans.add(new int[]{l, r});
            }
            out.println(ans.size());
            for(int[] ar : ans){
                out.println(ar[0]+" "+ar[1]);
            }
        }else if (one.size() == 1){
            out.println("YES "+ ((larger.size() + 1) - 1));
            for (int i = 0; i < larger.size() - 1; i++) {
                int l = larger.get(i)[0] + 1, r = larger.get(i + 1)[0] + 1;
                ans.add(new int[]{l, r});
            }
            ans.add(new int[]{larger.get(larger.size() - 1)[0] + 1, (one.peek() + 1)});
            out.println(ans.size());
            for(int[] ar : ans){
                out.println(ar[0]+" "+ar[1]);
            }
        }else{
            int lm = one.poll(), rm = one.removeLast();
            for (int i = 0; i < larger.size(); i++) {
                if (i == larger.size() - 1){
                    int[] l = larger.get(i);
                    l[1]--;
                    ans.add(new int[]{l[0] + 1, rm + 1});
                    if (i == 0){
                        l[1]--;
                        ans.add(new int[]{(lm + 1), (l[0] + 1)});
                    }
                }else if (i == 0){
                    int[] l = larger.get(i), r = larger.get(i + 1);
                    l[1]--;
                    r[1]--;
                    ans.add(new int[]{(r[0] + 1), (l[0] + 1)});
                    l[1]--;
                    ans.add(new int[]{(lm + 1), (l[0] + 1)});
                }else{
                    int[] l = larger.get(i), r = larger.get(i + 1);
                    l[1]--;
                    r[1]--;
                    ans.add(new int[]{(r[0] + 1), (l[0] + 1)});
                }
            }
            for (int i = 0; i < larger.size(); i++) {
                int[] get = larger.get(i);
                while (get[1] > 0 && one.size() > 0){
                    get[1]--;
                    int id = one.poll();
                    ans.add(new int[]{(id + 1), (get[0] + 1)});
                }
            }
//            System.out.println(one);
            if (one.size() > 0){
                out.println("NO");
            }else{
                out.println("YES "+(larger.size() + 1));
                out.println(ans.size());
                for(int[] ar : ans){
                    out.println(ar[0]+" "+ar[1]);
                }
            }
        }
    }
}
