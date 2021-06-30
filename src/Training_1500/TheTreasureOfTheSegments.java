package Training_1500;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class TheTreasureOfTheSegments {
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
            arr[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        TreeMap<Integer, Integer> lat = new TreeMap<>();
        TreeMap<Integer, Integer> fom = new TreeMap<>();
        Arrays.sort(arr, (a, b) -> (a[1] - b[1]));
        int sum = 0;
        for(int[] it : arr){
            sum += 1;
            lat.put(it[1], sum);
        }
        lat.put(-1, 0);
        sum = 0;
        Arrays.sort(arr, (a, b) -> (b[0] - a[0]));
        fom.put(Integer.MAX_VALUE, 0);
        for(int[] it : arr){
            sum += 1;
            fom.put(it[0], sum);
        }
        int max = 0;
        for(int[] it : arr){
            int l = it[0], r = it[1];
            Integer ll = lat.lowerKey(l);
            Integer rr = fom.higherKey(r);
            max = Math.max(max, n - lat.get(ll) - fom.get(rr));
        }
        out.println((n - max));
    }

}
