package ABC.ABC193;

import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        Main main = new Main();
        main.D(sc, pw);
//        main.D(sc, pw);
//        main.E(sc, pw);
//        main.F(sc, pw);
        pw.close();
    }

    void D(Scanner in, PrintWriter out){
        int k = in.nextInt();
        String s = in.next();
        String t = in.next();
        long ct = 0;
        double all = 0;
        int[] storet = new int[10];
        int[] storea = new int[10];
        for (int i = 0; i < 4; i++) {
            storea[t.charAt(i) - '0']++;
            storet[s.charAt(i) - '0']++;
        }
        for (int i = 1; i <= 9; i++) {
            if (storea[i] + storet[i] + 1 > k) continue;
            storea[i]++;
            long scoreA = 0;
            for (int j = 1; j <= 9; j++) {
                scoreA +=  Math.pow(10, storea[j]) * j;
            }
            for (int j = 1; j <= 9; j++) {
                if (storea[j] + storet[j] + 1 > k) continue;
                storet[j]++;
                long scoreT = 0;
                for (int p = 1; p <= 9; p++) {
                    scoreT += (long)Math.pow(10, storet[p]) * p;
                }
                long add = 0;
                if (i == j){
                    add = (long)(k - storea[i] - storet[i] + 1) * (long)(k - storea[i] - storet[i]);
                }else{
                    add = (long)(k - storea[i] - storet[i] + 1) * (long)(k - storea[j] - storet[j] + 1);
                }
                if (scoreT > scoreA) {
                    ct += add;
                }
                storet[j]--;
            }
            storea[i]--;
        }
        long div = (9 * k - 8);
        //System.out.println(ct+" "+all);
        out.println((double)ct / div / (div - 1));

    }
    void E(Scanner in, PrintWriter out){

    }
    void F(Scanner in, PrintWriter out){

        int n = in.nextInt();
        char[][] cs = new char[n][n];
        for (int i = 0; i < n; i++) {
            cs[i] = in.next().toCharArray();
        }

    }void C(Scanner in, PrintWriter out){
        List<Long> ls = new ArrayList<>();
        long n = in.nextLong();
        int cnt = 0;
        Set<Long> set = new HashSet<>();
        for (int i = 2; i <= 1e5; i++) {
            for (int j = 2; j <= 34; j++) {
                long v = (long) Math.pow(i, j);
                if (v > n) break;
                set.add(v);
            }
        }
        out.println(n - set.size());

    }
}
