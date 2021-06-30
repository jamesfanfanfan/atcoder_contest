package ABC.abc192;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        F(sc, pw);
        pw.close();
    }

    static void C(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        int k = sc.nextInt();
        int v = n;
        //System.out.println(new StringBuilder(v));
        for (int i = 0; i < k; i++) {
            char[] cs = String.valueOf(v).toCharArray();
            char[] rv ;
            Arrays.sort(cs);
            rv = reverse(cs);
            int sm = 0;
            //System.out.println(i+" "+Arrays.toString(cs)+" "+Arrays.toString(rv)+" "+v);
            int lg = Integer.valueOf(new String(rv));
            int j = 0;
            for (; j < cs.length; j++) {
                if (cs[j] != '0') break;
            }
            StringBuilder sb = new StringBuilder();
            while (j < cs.length) sb.append(cs[j++]);
            if (sb.length() > 0) sm = Integer.valueOf(sb.toString());
            int nxt = lg - sm;
            if (nxt == 0) {
                pw.println(0);
                return;
            }
            v = nxt;
        }
        pw.println(v);

    }
    static char[] reverse(char[] fk){
        char[] neo = new char[fk.length];
        for (int i = 0; i < fk.length; i++) {
            neo[i] = fk[fk.length - 1 - i];
        }
        return neo;
    }
    static void D(Scanner sc, PrintWriter pw){
        String x = sc.next();
        long M = sc.nextLong();
        int len = String.valueOf(M).length();
        int st = 0;
        char[] cx = x.toCharArray();
        for(char c : cx){
            st = Math.max(st, c - '0' + 1);
        }
        int cnt = 0;
        BigInteger mg = new BigInteger(String.valueOf(M));
        //System.out.println(st);
        while (true){
            BigInteger bg = new BigInteger(x, st);
            String k = bg.toString(10);
            BigInteger ak = new BigInteger(k);
            if (ak.compareTo(mg) > 0){
                break;
            }
            st++;
            cnt++;
        }
        pw.println(cnt);
    }

    static void E(Scanner sc, PrintWriter pw){
        int n = sc.nextInt(), m = sc.nextInt(), x = sc.nextInt() - 1, y = sc.nextInt() - 1;
        int[][] arr = new int[m][];
        Map<Integer, List<int[]>> mp = new HashMap<>();
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt(), sc.nextInt()};
            if (!mp.containsKey(arr[i][0])) mp.put(arr[i][0], new ArrayList<>());
            if (!mp.containsKey(arr[i][1])) mp.put(arr[i][1], new ArrayList<>());
            mp.get(arr[i][0]).add(new int[]{arr[i][1], arr[i][2], arr[i][3]});
            mp.get(arr[i][1]).add(new int[]{arr[i][0], arr[i][2], arr[i][3]});
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            return Long.compare(a[1], b[1]);
        });
        //long[] ans = new long[n + 1];
        boolean[] seen = new boolean[n + 1];
        pq.add(new long[]{x, 0});
        while (!pq.isEmpty()){
            long[] ar = pq.poll();
            //System.out.println(Arrays.toString(ar));
            int idx = (int) ar[0];
            if (seen[idx]){
                continue;
            }

            if (idx == y) {
                pw.println(ar[1]);
                return;
            }
            seen[idx] = true;
            if (mp.containsKey(idx)){
                for(int[] nxt : mp.get(idx)){
                    //System.out.println(Arrays.toString(nxt));
                    int nid = nxt[0];
                    int time = nxt[1];
                    int mt = nxt[2];
                    if (seen[nid]) continue;
                    if (ar[1] % mt == 0){
                        long nxttime = ar[1];
                        pq.add(new long[]{nid, nxttime + time});
                    }else{
                        long nxttime = mt * (ar[1] / mt + 1);
                        pq.add(new long[]{nid, nxttime + time});
                    }
                }
            }
        }
        pw.println(-1);



    }
    static void F(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        long x = sc.nextLong();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        //Arrays.sort(arr);

        long res = Long.MAX_VALUE;
        for (int l = 1; l <= n; l++) {
            int mod = (int) (x % l);
            if (l == 1){
                res = x - arr[n - 1];
            }else{
                long[][][] dp = new long[n + 1][n + 1][n + 1];// dp[i][j] means the largest value we could have for first i values and mod is j
                //Arrays.fill(dp[0][0], Long.MIN_VALUE / 2);
                for (int i = 0; i <= n; i++) {
                    for (int j = 0; j <= n; j++) {
                        Arrays.fill(dp[i][j], -1);
                    }
                    dp[i][0][0] = 0;
                }
                dp[0][0][0] = 0;
                for (int i = 0; i < n; i++) {
                    int v = arr[i];
                    int mv = arr[i] % l;
                    int ni = i + 1;
                    for (int j = 0; j <= i; j++) {
                        //Arrays.fill(dp[i][j], Long.MIN_VALUE / 2);
                        int nj = j + 1;
                        for (int k = 0; k < l; k++) {
                            if (dp[i][j][k] < 0) continue;
                            int nk = (int) (k + arr[i]) % l;
                            dp[ni][j][k] = Math.max(dp[ni][j][k], dp[i][j][k]);
                            dp[ni][nj][nk] = Math.max(dp[ni][nj][nk], dp[i][j][k] + arr[i]);
//
                        }
                    }
                }
                if(dp[n][l][mod] >= 0) res = Math.min(res, (x - dp[n][l][mod]) / l);
//                System.out.println((x - dp[n][l][mod]) / l+" l:"+l+" mod:"+mod+" V:"+ dp[n][l][mod]);
            }
        }
        pw.println(res);
    }

}
