package kickStart.round_B_2021.Kick_start_2019_H;

import java.io.PrintWriter;
import java.util.Scanner;
class SegTree {
    private int N;

    // Let UNIQUE be a value which does NOT
    // and will not appear in the segment tree
    private long UNIQUE = 0;

    // Segment tree values
    private long[] tree;

    public SegTree(int size) {
        tree = new long[2 * (N = size)];
        java.util.Arrays.fill(tree, UNIQUE);
    }

    public SegTree(long[] values) {
        this(values.length);
        for (int i = 0; i < N; i++) modify(i, values[i]);
    }

    // This is the segment tree function we are using for queries.
    // The function must be an associative function, meaning
    // the following property must hold: f(f(a,b),c) = f(a,f(b,c)).
    // Common associative functions used with segment trees
    // include: min, max, sum, product, GCD, and etc...
    private long function(long a, long b) {
        if (a == UNIQUE) return b;
        else if (b == UNIQUE) return a;

        return a + b; // sum over a range
        //return (a > b) ? a : b; // maximum value over a range
        //return (a < b) ? a : b; // minimum value over a range
        // return a * b; // product over a range (watch out for overflow!)
    }

    // Adjust point i by a value, O(log(n))
    public void modify(int i, long value) {
        //tree[i + N] = function(tree[i + N], value);
        tree[i + N] += value;

        for (i += N; i > 1; i >>= 1) {
            //System.out.println((i>>1)+"i:"+i+" "+ (i ^ 1));
            tree[i >> 1] = function(tree[i], tree[i ^ 1]);
        }

    }

    // Query interval [l, r), O(log(n)) ----> notice the exclusion of r
    public long query(int l, int r) {
        long res = UNIQUE;
        for (l += N, r += N; l < r; l >>= 1, r >>= 1) {

            if ((l & 1) != 0) res = function(res, tree[l++]);
            if ((r & 1) != 0) res = function(res, tree[--r]);

        }
        if (res == UNIQUE) {
            //throw new IllegalStateException("UNIQUE should not be the return value.");
            return 0;
        }
        return res;
    }
//

}

public class Solution {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            C(sc, pw, i + 1);
        }
        pw.close();
    }
    static void B(Scanner in, PrintWriter out, int t){//. means white  # means black
        int n = in.nextInt();
        char[][] cs = new char[n][];
        for (int i = 0; i < n; i++) {
            cs[i] = in.next().toCharArray();
        }
        int[][] dir1 = new int[][]{{1, -1}, {-1, 1}};
        int[][] dir2 = new int[][]{{1, 1}, {-1, -1}};
        int[][][] dirs = new int[][][]{dir1, dir2};


    }
    static void C(Scanner in, PrintWriter out, int t){
        int[] arr = new int[9];
        int tot = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = in.nextInt();
            tot += arr[i];
        }
        if (tot < 0 || tot > 200) {
            printCase(t, out);
            out.println("YES");
            return;
        }
        int cnt = 0;
        for (int i = 0; i < 9; i++) {
            cnt += (i + 1) * arr[i];
        }
        boolean[][][] dp = new boolean[10][tot + 1][cnt + 1];
        dp[0][0][0] = true;
        for (int i = 1; i <= 9; i++) {
            int get = arr[i - 1];
            for (int j = 0; j <= tot; j++) {
                int mut = j * i;
                //dp[i][j] = Arrays.copyOf(dp[i - 1][j], dp[i][j].length);
                if (j > get) continue;
                for (int k = mut; k <= cnt; k++) {
                    for (int l = j; l <= tot; l++) {
                        dp[i][l][k] = dp[i - 1][l - j][k - mut] || dp[i][l][k];
                    }
                }
                //System.out.println("i:"+i+"  j:"+j+" "+Arrays.toString(dp[i][j]));
            }
        }
        //System.out.println(tot+" "+cnt);
        if (tot % 2 == 0){
            int k = tot / 2;

            for (int i = 0; i <= cnt; i++) {
                if (dp[9][k][i]){
                    int rem = cnt - i;
                    if ((rem - i) % 11 == 0){
                        printCase(t, out);
                        out.println("YES");
                        return;
                    }
                }
            }
            printCase(t, out);
            out.println("NO");
        }else{
            int k = tot / 2 + 1;
            //System.out.println(Arrays.toString(dp[9][k]));
            for (int i = 0; i <= cnt; i++) {
                if (dp[9][k][i]){
                    int rem = cnt - i;
                    if ((rem - i) % 11 == 0){
                        printCase(t, out);
                        out.println("YES");
                        return;
                    }
                }
            }
            printCase(t, out);
            out.println("NO");
        }
    }


    static void printCase(int t, PrintWriter pw){
        pw.print("Case #"+t+": ");
    }
    static void A(Scanner in, PrintWriter out, int t){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] store = new int[100005];
        int len = store.length;
        StringBuilder sb = new StringBuilder();
        SegTree sg = new SegTree(store.length);
        int pre = 0;
        for (int i = 1; i <= n; i++) {
            sg.modify(arr[i - 1], 1);
            long v = sg.query(pre + 1, len - 1);
            //out.println(i+" "+pre);
            if (v > pre){
                pre++;
            }
            sb.append(pre);
            sb.append(" ");
        }
        printCase(t, out);
        out.println(sb.toString());
    }
}
