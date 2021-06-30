package DataStructure;

import java.io.PrintWriter;
import java.util.*;

public class BitMask_temp {

    static void solveF(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] arr = new int[m][2];
        boolean[][] mat = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{in.nextInt() - 1, in.nextInt() - 1};
            mat[arr[i][0]][arr[i][1]] = true;
            mat[arr[i][1]][arr[i][0]] = true;
        }
        int mask = 1 << n;
        boolean[] dp = new boolean[mask + 1];
        for (int i = 1; i < mask; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < 18; j++) {
                if(((1 << j) | i) == i){
                    set.add(j);
                }
            }
            boolean find = true;
            for(int x : set){
                for(int y : set){
                    if(x == y) continue;
                    if (!mat[x][y]){
                        find = false;
                        break;
                    }
                }
            }
            dp[i] = find;
        }

        int[] ak = new int[mask];
        Arrays.fill(ak, Integer.MAX_VALUE);
        //out.println(Arrays.toString(dp));
        for (int i = 1; i < mask; i++) {
            if (dp[i]){
                ak[i] = 1;
                continue;
            }
            //this is important
            for(int sub = i & (i - 1); sub > 0; sub = (sub - 1) & i){
                ak[i] = Math.min(ak[i], ak[sub] + ak[i ^ sub]);
            }
        }
        //int[][] aka = new int[][]{{11}}
        out.println(ak[mask - 1]);
    }
}
