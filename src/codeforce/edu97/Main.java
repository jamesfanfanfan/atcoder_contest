package codeforce.edu97;

import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        Main main = new Main();
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            main.D(sc,pw);
        }
        pw.close();
    }

    void D(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        LinkedList<Integer> q = new LinkedList<>();
        q.add(1);
        int cnt = 1;
        LinkedList<Integer> lv = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            if(! lv.isEmpty() && arr[i] >= lv.getLast()){
                lv.add(arr[i]);
                //System.out.println("lv:"+lv);
            }else if(q.size() > 0){
                lv.add(arr[i]);
                q.poll();
                //System.out.println("qsize():"+lv + q);
            }else{
                q = lv;
                lv = new LinkedList<>();
                lv.add(arr[i]);
                q.poll();
                //System.out.println(q);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
    void C(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        long[][] dp = new long[305][305];
        long min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for(int j = i; j <= 300; j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = j - 1 ; k>=i-1; k--){
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][k] + Math.abs(arr[i-1] - j));
                }
                if(i == n){
                    min = Math.min(min, dp[i][j]);
                }
            }
        }
        System.out.println(min);
    }
    void B(Scanner in, PrintWriter out){
        int n = in.nextInt();
        char[] cs = in.next().toCharArray();
        int[] dp0 = new int[n+1];
        int[] dp1 = new int[n+1];
        List<Integer> d0 = new ArrayList<>();
        List<Integer> d1 = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (cs[i - 1] == '0'){
                dp0[i] = dp0[i-1] + 1;
                dp1[i] = 0;
                if(dp1[i-1] >1){
                    d1.add(dp1[i-1]);
                }
            }else{
                dp1[i] = dp1[i-1] + 1;
                dp0[i] = 0;
                if(dp0[i-1] > 1){
                    d0.add(dp0[i-1]);
                }
            }
        }
        int c1 = 0, c2 = 0;
        for (int k : d1) {
            c1 += (k-1);
        }
        for (int k : d0) {
            c2 += (k-1);
        }

        System.out.println(Math.max(c1, c2));
    }
    void A(Scanner in, PrintWriter out){
        long l = in.nextInt();
        long r = in.nextInt();
        if(r >= 2 * l){
            out.println("NO");
        }else{
            out.println("YES");
        }

    }

}
