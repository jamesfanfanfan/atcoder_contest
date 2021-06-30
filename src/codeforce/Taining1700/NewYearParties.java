package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;

public class NewYearParties {
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
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }
        out.println(findMIN(arr, n, set)+" "+findLarge(arr, n));
    }
    static int findLarge(int[] arr, int n){
        Map<Integer, Integer> mp = new HashMap<>();
        for(int x : arr) mp.put(x, mp.getOrDefault(x, 0) + 1);
        int cnt = 0;
        int left = -1;
        for (int i = 1; i <= n; i++) {
            int j = i;
            int tot = 0;
            while (mp.containsKey(j)){
                tot += mp.get(j);
                j++;
            }
            int len = j - i;
            if (len == 0) continue;
            if (tot == len){
                cnt += len;
            }else if (tot == len + 1){
                if (left == i - 1){
                    left = j;
                }
                cnt += len + 1;
            }else{
                if (left == i - 1){
                    cnt += len + 1;
                }else{
                    cnt += len + 2;
                }
                left = j;
            }
            i = j - 1;
        }
        return cnt;
    }
    static int findMIN(int[] arr, int n, Set<Integer> set){
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            int j = i;
            if (!set.contains(j)) continue;
            while (set.contains(j)) j++;
            int len = j - i;
            int rem = len % 3;
            cnt += (len / 3);
            if (rem == 1){
                if (set.contains(j + 1)){
                    set.remove(j + 1);
                }
                cnt++;
            }else if(rem > 1){
               cnt++;
            }
            i = j - 1;
        }
        return cnt;
    }
}
