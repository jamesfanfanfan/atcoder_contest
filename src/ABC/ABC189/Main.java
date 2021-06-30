package ABC.ABC189;

import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        E(sc, pw);
        pw.close();
    }

    static void F(Scanner in, PrintWriter out){
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = in.nextInt();
        }
    }


    static void E(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        int m = in.nextInt();
        int[][] ops = new int[m][2];
        for (int i = 0; i < m; i++) {
            ops[i][0] = in.nextInt();
            if (ops[i][0] > 2) ops[i][1] = in.nextInt();
        }
        int qqss = in.nextInt();
        int[][] qs = new int[qqss][];
        for (int i = 0; i < qqss; i++) {
            qs[i] = new int[]{in.nextInt(), in.nextInt()};
        }

        long[][] dp = new long[2][3];
        // we define dp[i] as the x value after transfer from the origin
        List<long[][]> res = new ArrayList<>();
        dp[0] = new long[]{1, 0, 0};
        dp[1] = new long[]{0, 1, 0};
        res.add(dp);
        for(int[] op : ops){
            long[][] neo = new long[2][3];
            int index = op[0];
            int p = op[1];
            if (index <= 2){
                if (index == 1){// clockwise x y -> y , -x
                    for (int i = 0; i < 3; i++) {
                        neo[1][i] = dp[0][i] * -1;
                    }
                    neo[0] = Arrays.copyOf(dp[1], 3);
                }else{// anti  x y -> -y, x
                    for (int i = 0; i < 3; i++) {
                        neo[0][i] = dp[1][i] * -1;
                    }
                    neo[1] = Arrays.copyOf(dp[0], 3);
                }
            }else{
                if (index == 4){ // change y value
                    neo[0] = Arrays.copyOf(dp[0], 3);
                    neo[1] = new long[]{-1 * dp[1][0], -1 * dp[1][1], -1 * dp[1][2] + 2 * p};
                }else{  // change x value
                    neo[0] = new long[]{-1 * dp[0][0], -1 * dp[0][1], -1 * dp[0][2] + 2 * p};
                    neo[1] = Arrays.copyOf(dp[1], 3);
                }
            }

            res.add(neo);
            dp = neo;
        }
        List<long[]> ans = new ArrayList<>();
        for (int[] q : qs){
            int index = q[0];
            int[] cor = arr[q[1] - 1];
            long[][] tab = res.get(index);
            long[] aka = new long[]{tab[0][0] * cor[0] + tab[0][1] * cor[1] + tab[0][2],  tab[1][0] * cor[0] + tab[1][1] * cor[1] + tab[1][2]};
            ans.add(aka);
        }
        for(long[] a : ans){
            out.println(a[0]+" "+a[1]);
        }

    }
    static void D(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n]; // 0 equals or 1 equals and
        for (int i = 0; i < n; i++) {
            String s = in.next();
            if (s.equals("AND")){
                arr[i] = 1;
            }else{
                arr[i] = 0;
            }
        }

        out.println(dfs(arr, n - 1));

    }
    static long dfs(int[] arr, int idx){
        if(idx == 0){
            if (arr[idx] == 0){
                return 3;
            }else{
                return 1;
            }
        }
        long res = 0;
        if (arr[idx] == 0){ // means this is or
            res += (1L << (idx + 1));
        }
        res += dfs(arr, idx - 1);
        return res;
    }


    static void C(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        stack.push( -1);
        int res = 0;
        for (int i = 0; i <= n; i++) {
            int val = i == n ? 0 : arr[i];
            while (stack.size() > 1 && arr[stack.peek()] > val){
                int hei = arr[stack.pop()];
                res = Math.max(res, (i - stack.peek() - 1) * hei);
            }
            stack.push(i);
        }
        out.println(res);
    }
}
