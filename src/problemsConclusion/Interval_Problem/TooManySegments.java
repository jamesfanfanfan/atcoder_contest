package problemsConclusion.Interval_Problem;

import java.io.PrintWriter;
import java.util.*;
//https://codeforces.com/problemset/problem/1249/D2
public class TooManySegments {
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
        int n = in.nextInt(), k = in.nextInt();
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt(), i};
        }
        Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
        int cnt = 0;
        TreeMap<Integer, List<Integer>> mp = new TreeMap<>();
        List<Integer> ans = new ArrayList<>();
        int tot = 0;
        for (int i = 0; i < n; i++) {
            tot++;
            if (mp.size() == 0){
                List<Integer> ls = new ArrayList<>();
                ls.add(arr[i][2]);
                mp.put(arr[i][1], ls);
            }else{
                while (mp.size() > 0 && mp.firstKey() < arr[i][0]) {
                    tot -= mp.get(mp.firstKey()).size();
                    mp.remove(mp.firstKey());
                }
                if (mp.containsKey(arr[i][1])){
                    mp.get(arr[i][1]).add(arr[i][2]);
                }else{
                    List<Integer> ls = new ArrayList<>();
                    ls.add(arr[i][2]);
                    mp.put(arr[i][1], ls);
                }
                if (tot > k){
                    Integer key = mp.lastKey();
                    List<Integer> ls = mp.get(key);
                    int idx = ls.get(ls.size() - 1);
                    ans.add(idx);
                    ls.remove(ls.size() - 1);
                    if (ls.size() == 0) mp.remove(key);
                    cnt++;
                    tot--;
                }
            }
        }
        out.println(cnt);
        for(int x : ans){
            out.print((x + 1)+" ");
        }
    }
}
