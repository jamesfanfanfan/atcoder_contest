package lc;

import java.util.*;

public class BI54 {
    public boolean isCovered(int[][] ranges, int left, int right) {
        for(int i = left; i <= right; i++){
            boolean find = false;
            for(int[] r : ranges){
                if (i >= r[0] && i <= r[1]){
                    find = true;
                    break;
                }
            }
            if (!find) return false;
        }
        return true;
    }

    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long sum = 0;
        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + chalk[i];
        }
        for(int x : chalk){
            sum += x;
        }
        k %= sum;
        for (int i = 0; i < n; i++) {
            if (k < chalk[i]) return i;
            k -= chalk[i];
        }
        return -1;
    }

    public int largestMagicSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int max = 1;
        int[][] pre = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pre[i][j + 1] = pre[i][j] + grid[i][j];
            }
        }
        int[][] cols = new int[n + 1][m];
        for(int j = 0; j < m; j++){
            for (int i = 0; i < n; i++) {
                cols[i + 1][j] = cols[i][j] + grid[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = max + 1; k <= Math.min(i, j); k++) {
                    int lx = j - k, ly = i - k;
                    boolean find = true;
                    int tar = pre[lx][j + 1] - pre[lx + 1][ly + 1];
                    for (int l = lx + 1; l <= j && find; l++) {
                        int now = pre[l][j + 1] - pre[l][ly + 1];
                        if (now != tar) find = false;
                    }
                    for(int l = ly + 1; l <= i && find; l++){
                        int now = cols[i + 1][l] - cols[lx + 1][l];
                        if (now != tar) find = false;
                    }
                    int dia = 0;
                    for (int l = lx + 1, r = ly + 1; l <= i && find; l++, r++) {
                        dia += grid[l][r];
                    }
                    if (dia != tar) find = false;
                    dia = 0;
                    for (int l = lx + 1, r = j; l <= i && find; l++, r--) {
                        dia += grid[l][r];
                    }
                    if (dia != tar) find = false;
                    if (find) max = k;
                }
            }
        }
        return max;
    }

    public int minOperationsToFlip(String expression) {
        int n = expression.length();
        char[] cs = expression.toCharArray();
        int[] dp = new int[2];
        Map<Integer, LinkedList<Integer>> mp = new HashMap<>();
        int level = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '(') {
                level++;
            }else if (cs[i] == ')'){
                level--;
            }else{
                if (!mp.containsKey(level)) mp.put(level, new LinkedList<>());
                mp.get(level).add(i);
            }
        }
        return 2;
    }
    int[] dfs(int level, Map<Integer, LinkedList<Integer>> mp, char[] cs, int l, int r){
        LinkedList<Integer> q = mp.get(level);
        int[] ok = new int[2];
        int pre = l;
        boolean fuhao = true;
        int[] dp = new int[2];
        int ini = 0;
        char pc = ' ';
        while (!q.isEmpty() && q.peek() < r){
            int v = q.poll();
            char now = cs[v];
            if (now == '1'){
                fuhao = false;
                int pi = ini;
                if (pc == '&'){
                    ini &= 1;
                    if (ini == 0){
                        dp = new int[]{0, 1};
                    }else{
                        dp = new int[]{1, 0};
                    }
                }else if (pc == '|'){
                    ini |= 1;
                    dp = new int[]{dp[0] + 1, 0};
                }else{
                    dp = new int[]{1, 0};
                }
            }else if (now == '0'){
                fuhao = false;
                if (pc == '&'){
                    ini &= 0;
                    dp = new int[]{0, Math.min(dp[1] + 1, dp[0] + 2)};
                }else if (pc == '|'){
                    ini |= 0;
                    if (ini == 0){
                        dp = new int[]{0, 1};
                    }else{
                        dp = new int[]{1, 0};
                    }
                }else{
                    dp = new int[]{1, 0};
                }
            }else if (now == '&'){
                if (fuhao){
                    int[] get = dfs(level + 1, mp, cs, l, v);
                    if (pc == '|'){
                        if (get[0] == 0){
                            ini |= 0;
                            dp = new int[]{};
                        }else{

                        }
                    }else{

                    }
                }
                pc = '&';
                fuhao = true;
            }else{
                if (fuhao){

                }
                pc = '|';
                fuhao = true;
            }
        }
        return new int[2];
    }

}
