package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

public class MarcinandTrainingCamp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static Map<Long, Integer> mp = new HashMap<>();
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        long[][] arr = new long[n][2];
        Set<Integer> db =new HashSet<>();
        for (int i = 0; i < n; i++) {
            arr[i][0] = in.nextLong();
            mp.put(arr[i][0], mp.getOrDefault(arr[i][0], 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            arr[i][1] = in.nextLong();
        }
        Arrays.sort(arr, (a, b) ->{
            return Long.compare(a[0], b[0]);
        });
        for (int i = 0; i < n; i++) {
            if (mp.get(arr[i][0]) > 1) db.add(i);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (db.contains(i)){
                ans += arr[i][1];
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (db.contains(j) && (arr[i][0] | arr[j][0]) == arr[j][0]){
                    ans += arr[i][1];
                    break;
                }
            }
        }

        out.println(ans);
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int n = maze.length;
        int m = maze[0].length;
        TreeSet<Integer>[] rows = new TreeSet[n];
        TreeSet<Integer>[] cols = new TreeSet[m];
        for (int i = 0; i < n; i++) {
            rows[i] = new TreeSet<>();
            rows[i].add(-1);
            rows[i].add(m);
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) rows[i].add(j);
            }
        }
        for(int j = 0; j < m; j++){
            cols[j] = new TreeSet<>();
            rows[j].add(-1);
            rows[j].add(n);
            for (int i = 0; i < n; i++) {
                if (maze[i][j] == 1) cols[j].add(i);
            }
        }
        int[][] seen = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(seen[i], -1);
        }
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (seen[a[0]][a[1]] - seen[b[0]][b[1]]));
        q.add(start);
        seen[start[0]][start[1]] = 0;
        while (!q.isEmpty()){
            int[] arr = q.poll();
            int x = arr[0], y = arr[1];
            int v = seen[x][y];
            Integer le = rows[x].floor(x);
            Integer ri = rows[x].ceiling(x);
            Integer up = cols[x].ceiling(y);
            Integer dn = cols[x].floor(y);
            if (seen[x][le + 1] != -1){
                seen[x][le + 1] = v + Math.abs(y - (le + 1));
            }
            if (seen[x][ri - 1] != -1){
                seen[x][ri - 1] = v + Math.abs(y - (ri - 1));
            }
            if (seen[up + 1][y] != -1){
                seen[up + 1][y] = v + Math.abs(x - (up + 1));
            }
            if (seen[dn - 1][y] != -1){
                seen[dn - 1][y] = v + Math.abs(x - (dn + 1));
            }
        }
        return seen[destination[0]][destination[1]];
    }

}
