package codeforce.cf697;

import java.io.PrintWriter;
import java.util.*;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, out);
        }
        out.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] arr = new int[n][2];
        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr[i][0] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            arr[i][1] = in.nextInt();
            if (arr[i][1] == 1){
                one.add(arr[i][0]);
            }else{
                two.add(arr[i][0]);
            }
        }
        Collections.sort(one, Comparator.reverseOrder());
        Collections.sort(two, Comparator.reverseOrder());
        long d1 = 0;
        long ct1 = 0;
        long d2 = 0;
        long ct2 = 0;
        for (int x : one){
            d1 += x;
            ct1++;
            if (d1 >= m) break;;
        }
        for (int x : two){
            d2 += x;
            ct2 += 2;
            if (d2 >= m) break;;
        }
        long ans = Integer.MAX_VALUE;
        if (d1 >= m) ans = Math.min(ans, ct1);
        if (d2 >= m) ans = Math.min(ans, ct2);
        //System.out.println(ans);
        long cto = 0;
        long ctw = 0;

        for (int i = 0, j = 0; i < one.size(); i++) {
            cto += one.get(i);
            while(j < two.size() && cto + ctw < m){
                ctw += two.get(j++);
            }

            while (j > 0 && (cto + ctw - two.get(j - 1)) >= m){
                ctw -= two.get(--j);
            }
            if (cto + ctw >= m){
                //System.out.println(cto+" "+ctw+" i:"+i+" j:"+j);
                ans = Math.min(ans, (i + 1) * 1 + j * 2);
                //System.out.println(cto+" "+ctw+" i:"+i+" j:"+j+" "+ans);
            }
        }

        if (ans == Integer.MAX_VALUE){
            out.println(-1);
        }else{
            out.println(ans);
        }
    }
}
