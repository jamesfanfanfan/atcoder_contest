package codeforce.Edu_49;

import java.io.PrintWriter;
import java.util.*;

public class C {
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
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Map<Integer,Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(arr[i], mp.getOrDefault(arr[i], 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        double sm = Double.MAX_VALUE;
        for(Map.Entry e : mp.entrySet()){
            int k = (int) e.getKey();
            int v = (int) e.getValue();
            if (v >= 2){
                res.add(k);
                if (v >= 4){
                    for (int i = 0; i < 4; i++) {
                        sb.append(k);
                        sb.append(" ");
                    }
                    out.println((sb.toString().trim()));
                    return;
                }
            }
        }
        Collections.sort(res);
        for (int i = 0; i < res.size() - 1; i++) {
            double l = res.get(i);
            double r = res.get(i + 1);
            if (l / r + r / l < sm){
                sb = new StringBuilder();
                for (int j = 0; j < 2; j++) {
                    sb.append(res.get(i));
                    sb.append(" ");
                }
                for (int j = 0; j < 2; j++) {
                    sb.append(res.get(i + 1));
                    sb.append(" ");
                }
                sm = l / r + r / l;
            }
        }
        out.println((sb.toString().trim()));
    }
}
