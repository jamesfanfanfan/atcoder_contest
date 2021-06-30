package kickStart.round_B_2021.Kick_start_2019_C;

import java.io.PrintWriter;
import java.util.*;

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

        int r = in.nextInt(), c = in.nextInt(), k = in.nextInt();
        int[][] arr = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        int res = r;
        int[][] dp = new int[r][c];
        for (int i = 0; i < r; i++) {
            TreeMap<Integer, Integer> mp = new TreeMap<>();
            for (int j = 0, p = 0; j < c; j++) {
                int v = arr[i][j];
                mp.put(v, mp.getOrDefault(v, 0) + 1);
                while (mp.lastKey() - mp.firstKey() > k){
                    int v2 = arr[i][p++];
                    mp.put(v2, mp.get(v2) - 1);
                    if (mp.get(v2) == 0) mp.remove(v2);
                }
                res = Math.max(res, j - p + 1);
                dp[i][j] = p;
                int tp = j - p + 1;
                for(int sb = 1; sb <= i; sb++){
                    if (dp[i - sb][j] == j) break;
                    tp = Math.min(tp, j - dp[i - sb][j] + 1);
                    res = Math.max(res, (sb + 1) * tp);
                }
            }
        }
        out.println("Case #"+t+": "+res);
    }
    static void A(Scanner in, PrintWriter out, int t){
        int n = in.nextInt(), r = in.nextInt(), c = in.nextInt(), sr = in.nextInt(), sc = in.nextInt();
        char[] cs = in.next().toCharArray();
        Set<String> set = new HashSet<>();
        TreeSet<Integer>[] row = new TreeSet[r + 1];
        TreeSet<Integer>[] col = new TreeSet[c + 1];
        for (int i = 1; i <= r; i++) {
            row[i] = new TreeSet<>();
        }
        for (int i = 1; i <= c; i++) {
            col[i] = new TreeSet<>();
        }
        for (int i = 0; i < n; i++) {
            char ck = cs[i];
            row[sr].remove(sc);
            col[sc].remove(sr);
            String s = Arrays.toString(new int[]{sr, sc});
            if (set.contains(s)){
                if (ck == 'E'){// right

                }else if (ck == 'W'){// left

                }else if (ck == 'N'){// up

                }else{// down

                }
            }else{
                if (ck == 'E'){// right

                }else if (ck == 'W'){// left

                }else if (ck == 'N'){// up

                }else{// down

                }
            }

        }
    }


}
