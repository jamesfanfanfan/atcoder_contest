package codeforce.cf727;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;
//seems hard and do not know how to solve at this point, we should notice one thing, it is we need to remove one thing
//then we need to consider which one we move is always good, or we can say which one have a better chance to be deducted as 1
//when you think about in this way, you can see that item with smaller limitation would always have a better to be deducted as 1,
//BTW, we should still find out that actually we do not care about the value if they have same limitation, they are same, so we can combine them
//and remove from last, then we should think how many should we deduct, when out deduction reaches the limitation of the smallest one, we can leave it
//aside, this time we can remove smallest with 1, which is always good to remove something as 1, after removal, then move on to the
//next stage
public class D {
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
        long[][] arr = new long[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new long[]{in.nextLong(), in.nextLong()};
        }
        TreeMap<Long, Long> mp = new TreeMap<>();
        for(long[] a : arr){
            mp.put(a[1], mp.getOrDefault(a[1], 0l) + a[0]);
        }
        long cnt = 0;
        long tot = 0;
        while (mp.size() > 0){
            Long l = mp.firstKey();
            while (tot >= l){
                cnt += mp.get(l);
                tot += mp.get(l);
                mp.remove(l);
                if (mp.size() == 0) break;
                l = mp.firstKey();
            }
            if (mp.size() == 1){
                Long last = mp.lastKey();
                long val = mp.get(last);
                if (val + tot < last){
                    cnt += val * 2;
                }else{
                    cnt += (last - tot) * 2;
                    cnt += (val + tot - last);
                }
                mp.clear();
            }else if (mp.size() > 1){
                Long first = mp.firstKey();
                Long Last = mp.lastKey();
                long totV = mp.get(Last);
                if (totV + tot <= first){
                    tot += totV;
                    cnt += totV * 2;
                    mp.remove(Last);
                }else{
                    totV -= (first - tot);
                    cnt += (first - tot) * 2;
                    tot = first;
                    mp.put(Last, totV);
                }
            }
        }
        out.println(cnt);
    }
}
