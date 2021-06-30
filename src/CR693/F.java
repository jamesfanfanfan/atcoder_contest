package CR693;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class F {
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
        int m = in.nextInt();
        int[][] arr = new int[m][];
        for (int i = 0; i < m; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        for(int[] x : arr){
            if (mp.containsKey(x[1])){
                mp.put(x[1], 3);
            }else{
                mp.put(x[1], 1 << (x[0] - 1));
            }
        }
        int color = 0;
        boolean hasLast = false;
        mp.put((int)2e9, 3);
        for(int x : mp.keySet()){
            int status = mp.get(x);
            int mask = (x + status) % 2;
            if (status == 3 && hasLast){
                out.println("NO");
                return;
            }else if (status != 3 && hasLast){
                if (color == mask){
                    out.println("NO");
                    return;
                }else{
                    hasLast = false;
                }
            }else if (status != 3){
                color = (x + status) % 2;
                hasLast = true;
            }

        }
        out.println("YES");
    }
}
