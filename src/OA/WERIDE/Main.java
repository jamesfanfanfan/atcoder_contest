package OA.WERIDE;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        long[][] arr = new long[n][];
        for (int i = 0; i < n; i++) {
            String[] ss = sc.nextLine().split(", ");
            arr[i] = new long[]{Long.valueOf(ss[0]), Long.valueOf(ss[1])};
        }
//        consider value at the same x value and same y vaule
        Map<Long, Integer> SameX = new HashMap<>();
        Map<Long, Integer> SameY = new HashMap<>();
        for(long[] cor : arr){
            SameX.put(cor[0], SameX.getOrDefault(cor[0], 0) + 1);
            SameY.put(cor[1], SameY.getOrDefault(cor[1], 0) + 1);
        }
        int max = 0;
        for(long k : SameY.keySet()){
            max = Math.max(max, SameY.get(k));
        }
        for(long k : SameX.keySet()){
            max = Math.max(max, SameX.get(k));
        }
//        remaining part combinations
        for (int i = 0; i < n; i++) {
            long[] st = arr[i];
            Map<String, Integer> mp = new HashMap<>();
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                long[] ed = arr[j];
                if (st[0] == ed[0] && st[1] == ed[1]){
                    cnt++;
                    continue;
                }
//                we have considered this problem before
                if (st[0] == ed[0] || st[1] == ed[1]) continue;
                boolean fuHao = ((st[1] - ed[1]) * 1.0 / (st[0] - ed[0]) < 0);
                long difY = Math.abs(st[1] - ed[1]), difX = Math.abs(st[0] - ed[0]);
                long gcd = gcd(Math.abs(st[1] - ed[1]), Math.abs(st[0] - ed[0]));
                long l = difY / gcd, r = difX / gcd;
                String s = l +":"+r;
                if (fuHao) s = "-"+s;
                mp.put(s, mp.getOrDefault(s, 0) + 1);
            }

            for(String s : mp.keySet()){
                max = Math.max(max, mp.get(s) + cnt);
            }
        }
        System.out.println(max);
    }
    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }


}
