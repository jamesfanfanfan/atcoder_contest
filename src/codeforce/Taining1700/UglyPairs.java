package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

public class UglyPairs {
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
        String s = in.next();
        char[] cs = s.toCharArray();
        int n = cs.length;
        TreeMap<Character, Integer> mp = new TreeMap<>();
        for(char c : cs){
            mp.put(c, mp.getOrDefault(c, 0) + 1);
        }
        LinkedList<Character> q = new LinkedList<>();
        LinkedList<Character> dq = new LinkedList<>();
        for(char c : mp.keySet()) q.add(c);
        StringBuilder sb = new StringBuilder();
        if (q.size() == 3){
            int[] neo = new int[]{123, 132, 312, 321, 213, 231};
            List<Character> ls = new ArrayList<>(q);
            for(int v : neo){
                String ss = String.valueOf(v);
                char c1 = ls.get(ss.charAt(0) - '1');
                char c2 = ls.get(ss.charAt(1) - '1');
                char c3 = ls.get(ss.charAt(2) - '1');
                if (Math.abs(c1 - c2) != 1 && Math.abs(c2 - c3) != 1){
                    for(char c : new char[]{c1, c2, c3}){
                        for (int i = 0; i < mp.get(c); i++) {
                            sb.append(c);
                        }
                    }
                    out.println(sb.toString());
                    return;
                }
            }
            out.println("No answer");
            return;
        }
        while (q.size() > 1){
            char c1 = q.poll();
            char c2 = q.poll();
            dq.add(c2);
            dq.add(c1);
        }
        dq.addAll(q);
        q = dq;
        if (!q.isEmpty()){
            char c = q.poll();
            for (int i = 0; i < mp.get(c); i++) {
                sb.append(c);
            }
        }
        while (q.size() > 1){
            char c = q.poll();
            char cc = q.poll();
            for (int i = 0; i < mp.get(cc); i++) {
                sb.append(cc);
            }
            for (int i = 0; i < mp.get(c); i++) {
                sb.append(c);
            }
        }
        if (!q.isEmpty()){
            char c = q.poll();
            for (int i = 0; i < mp.get(c); i++) {
                sb.append(c);
            }
        }
        String ans = sb.toString();
        for (int i = 0; i < n - 1; i++) {
            if (Math.abs(ans.charAt(i) - ans.charAt(i + 1)) == 1){
                out.println("No answer");
                return;
            }
        }
        out.println(sb.toString());
    }
}
