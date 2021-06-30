package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class DreamoonLikesColoring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }

    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), m = in.nextInt();
        int[][] arr = new int[m][];
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{in.nextInt(), i};
        }
        int[] res = new int[m];
        LinkedList<int[]> q = new LinkedList<>();
        for (int i = 0, j = n; i <= m - 1; i++, j--) {
            int pos = j - arr[i][0] + 1;
            q.addFirst(new int[]{pos, arr[i][0], arr[i][1]});
            if (pos < 1){
                out.println("-1");
                return;
            }
        }
        int first = 1;
        boolean find = false;
        while (q.size() > 1){
            int[] get = q.poll();
            if (get[0] <= first) {
                res[get[2]] = get[0];
                find = true;
                break;
            }
            int idx = get[2];
            res[idx] = first;
            first += get[1];
        }
        if (q.size() == 1){
            if (find || first >= q.peek()[0]){
                res[q.peek()[2]] = q.peek()[0];
            }else{
                out.println("-1");
                return;
            }
        }else{
            while (!q.isEmpty()){
                int[] get = q.poll();
                res[get[2]] = get[0];
            }
        }
        for (int i = 0; i < m; i++) {
            out.print(res[i]+" ");
        }
    }
}
