package OA.WERIDE;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> ls = new ArrayList<>();
        String sk;
        for (int i = 0; i < 1e7 + 2; i++) {
            sk = sc.nextLine();
            if (sk.length() == 0) break;
            String[] ss = sk.split(",");
            if (ss.length == 0) break;
            for(String s : ss){
                if (s.length() > 0){
                    ls.add(Integer.valueOf(s.trim()));
                }
            }
        }
//        while ((sk = sc.nextLine()).length() > 0){
//            String[] ss = sk.split(",");
//            if (ss.length == 0) break;
//            for(String s : ss){
//                if (s.length() > 0){
//                    ls.add(Integer.valueOf(s.trim()));
//                }
//            }
////            System.out.println(ls);
//        }
        System.out.println(ls);
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid1.length;
        int m = grid1[0].length;
        boolean[][] seen = new boolean[n][m];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 1 && !seen[i][j]){
                    if (dfs(grid1, grid2, i, j, seen)) cnt++;
                }
            }
        }
        return cnt;
    }
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean dfs(int[][] grid1, int[][] grid2, int i, int j, boolean[][] seen){
        seen[i][j] = true;
        if (seen[i][j]) return true;
        if (grid1[i][j] == 0 && grid1[i][j] == 1) return false;
        boolean ans = true;
        for(int[] dir : dirs){
            int pi = i + dir[0];
            int pj = j + dir[1];
            if (pi < 0 || pj < 0 || pi >= grid1.length || pj >= grid1[0].length || grid2[pi][pj] == 0) continue;
            ans &= dfs(grid1, grid2, pi, pj, seen);
        }
        return ans;
    }
}
