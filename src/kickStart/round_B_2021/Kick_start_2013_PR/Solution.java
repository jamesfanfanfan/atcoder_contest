package kickStart.round_B_2021.Kick_start_2013_PR;

import java.io.PrintWriter;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 1; i <=t; i++){
            solveB(i, sc, pw);
        }
        pw.close();
    }
    static void solveB(int t, Scanner in, PrintWriter out){
        int v = in.nextInt();
        int d = in.nextInt();
        double get =  9.8 * d / (v * v);
        double pi = Math.acos(-1.0);
        double deg = Math.asin(get) * 90 / pi;
        out.println("Case #" + t+": "+ String.format("%.7f",deg));

    }
    static void solveC(int t, Scanner in, PrintWriter out){
        Map<String, Integer> mp = new HashMap<>();
        Map<String, List<String>> res = new HashMap<>();
        int m = in.nextInt();
        String[][] arr = new String[m][2];
        for (int i = 0; i < m; i++) {
            arr[i] = new String[]{in.next(), in.next()};
        }
        for(String[] ss : arr){
            if (!res.containsKey(ss[0])) res.put(ss[0], new ArrayList<>());
            if (!res.containsKey(ss[1])) res.put(ss[1], new ArrayList<>());
            res.get(ss[1]).add(ss[0]);
            res.get(ss[0]).add(ss[1]);
        }
        for(String s : res.keySet()){
            if (mp.containsKey(s)) continue;
            if(!helper(mp, s, 0, res)){
                out.println("Case #" + t+":"+ " No");
                return;
            }
        }
        out.println("Case #" + t+":"+ " Yes");
    }
    static boolean helper(Map<String, Integer> mp, String s, int val, Map<String, List<String>> res){
        if(mp.containsKey(s) && val != mp.get(s)) return false;
        if (mp.containsKey(s)) return true;
        mp.put(s, val);
        for(String ss : res.get(s)){
            if (!helper(mp, ss, 1 - val, res)){
                return false;
            }
        }
        return true;
    }
}
