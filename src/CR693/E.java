package CR693;

import java.io.PrintWriter;
import java.util.*;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt(), i + 1};
            Arrays.sort(arr[i], 0, 2);
        }
        //List<Integer> res = new ArrayList<>();
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        Arrays.sort(arr, (a, b) -> (a[1] - b[1]));
        int[] res = new int[n + 1];
        LinkedList<int[]> q = new LinkedList<>();
        for(int i = 0; i < n ; i++){
            int[] x = arr[i];
            int l = x[0];
            if(!q.isEmpty() && x[1] > q.peek()[1]){
                while(!q.isEmpty()){
                    int[] fk = q.poll();
                    mp.put(fk[0], fk[2]);
                }
            }
            Integer get = mp.lowerKey(l);
            if (get == null){
                res[x[2]] = -1;
            }else{

                res[x[2]] = mp.get(get);
            }
            q.add(x);
        }
        for (int i = 1; i <= n; i++) {
            out.print(res[i]+" ");
        }
        out.println();


    }
}
