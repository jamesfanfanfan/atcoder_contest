package OA.WERIDE;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C {
    static double max = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] cor = new int[n][2];
        for (int i = 0; i < n; i++) {
            cor[i] = new int[]{sc.nextInt(), sc.nextInt()};
        }
        dfs(0, n, cor, new ArrayList<>());
        DecimalFormat format = new DecimalFormat("#.00");
        System.out.println(format.format(max));
//        System.out.println(String.format("%.2f", 12.119));
    }
    static void dfs(int state, int n, int[][] cor, List<int[]> now){
        if (now.size() == n){
            isConvexHull(now);
            return;
        }
        for (int i = 0; i < n; i++) {
            if ((state | 1 << i) != state){
                now.add(cor[i]);
                dfs(state | 1 << i, n, cor, now);
                now.remove(now.size() - 1);
            }
        }
    }
    static double dis(int[] a , int[] b){
        return Math.sqrt(mut(a[0] - b[0]) + mut(a[1] - b[1]));
    }
    static double mut(int v){
        return v * v;
    }
    static boolean isConvexHull(List<int[]> ls){
        double ak = 0;
        for (int i = 0; i < ls.size(); i++) {
            int[] a = ls.get(i), b = ls.get((i + 1) % ls.size());
            ak += dis(a, b);
        }
        max = Math.max(max, ak);
        return true;
    }

}
