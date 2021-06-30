package kickStart.round_B_2021.Kick_start_2019_E;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            B(sc, pw, i + 1);
        }
        pw.close();
    }
    static void B(Scanner in, PrintWriter out, int t){
        int d = in.nextInt(), s = in.nextInt();
        int[][] arr = new int[s][];
        long code = 0;
        for (int i = 0; i < s; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt()};
            code += arr[i][0];

        }
        int[][] qs = new int[d][];

        for (int i = 0; i < d; i++) {
            qs[i] = new int[]{in.nextInt(), in.nextInt()};

        }
        TreeSet<Long> set = new TreeSet<>();
        TreeMap<Long, Integer> mp = new TreeMap<>();
        Arrays.sort(arr, (a, b) -> {
            double a1 = a[1] * 1.0 / a[0];
            double b1 = b[1] * 1.0 / b[0];
            return Double.compare(b1, a1);
        });
        long cnt = 0;
        long inner = 0;
        long[] preE = new long[s + 2];
        for (int i = 0; i < arr.length; i++) {
            cnt += arr[i][0];
            //System.out.println(arr[i][0]+" dfff");
            preE[i + 1] = preE[i] + arr[i][1];
            mp.put(cnt, i);
        }
        StringBuilder sb = new StringBuilder();
        //System.out.println(mp);
        for (int[] q : qs){

            int a = q[0];// code
            int b = q[1];// eat
            long totC = code;
            long rem = code - a;
            if (rem < 0 || b > preE[s]){
                sb.append("N");
                continue;
            }
            Long val = mp.floorKey(rem);
            int idx = -1;
            if (val != null) idx = mp.get(val);
            //System.out.println(" s:"+s+" "+idx);
            double totEat = preE[idx + 1] + (rem - (val == null ? 0 : val)) * (idx == s - 1 ? 0 : (arr[idx + 1][1] * 1.0 / arr[idx + 1][0]));
           // System.out.println(Arrays.toString(q) +" "+ totEat);
            if (totEat >= b){
                sb.append("Y");
            }else{
                sb.append("N");
            }
        }

        out.println("Case #"+ t +": "+sb.toString());
    }
    static void A(Scanner in, PrintWriter out, int t){
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] pairs = new int[m][];
        for (int i = 0; i < m; i++) {
            pairs[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
        }
        int[] par = new int[n + 1];
        Arrays.fill(par, -1);
        int cnt = 0;
        for(int[] pair : pairs){
            if (find(pair[0], par) == find(pair[1], par)){
                continue;
            }else{
                union(pair[0], pair[1], par);
                //out.println("fgff");
                cnt++;
            }
        }
        //out.println(Arrays.toString(par));
        for (int i = 0; i < n; i++) {
            if (par[i] < 0) cnt += 2;
        }
        cnt-=2;
        out.println("Case #"+t+": "+cnt);
    }
    static int find(int i, int[] par){
        if (par[i] < 0) return i;
        return par[i] = find(par[i], par);
    }
    static void union(int i, int j, int[]par){
        int pi = find(i, par);
        int pj = find(j, par);
        if (pi == pj) return;
        if (par[pi] < par[pj]){
            par[pi] += par[pj];
            par[pj] = pi;
        }else{
            par[pj] += par[pi];
            par[pi] = pj;
        }
    }
}
