package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.*;
//https://codeforces.com/contest/1325/problem/D
//I like this problem
//first : you should from the start since you may need to borrow something from the above,
//the hard thing is borrowing, how, if I am at index 3 and the one who can borrow at index 6...... -> we can push that value downward (this is the important part)
//if I cannot borrow, then you should return -1,
//if I cannot have, we seed it down, but at index 0 you cannot send down
//---------------
//𝑎+𝑏=𝑎⊕𝑏+2∗(𝑎&𝑏)
//Let 𝑥= （𝑣−𝑢） / 2. The array [𝑢,𝑥,𝑥] satisfies the conditions, so the length is at most 3

public class EhabtheXorcist {
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
        long u = in.nextLong(), v = in.nextLong();
        if (v % 2 != u % 2){
            out.println(-1);
            return;
        }
        int i = 61;
        while (i > 0){
            int ubit = ((u | (1l << i)) == u) ? 1 : 0;
            int vbit = ((v | (1l << i)) == v) ? 1 : 0;
            if (ubit == 0 && vbit == 0){
                i--;
            }else{
                break;
            }
        }
        Map<Integer, Long> mp = new HashMap<>();
        long[] pow = new long[62];
        pow[0] = 1l;
        for (int j = 1; j < 62; j++) {
            pow[j] = pow[j - 1] * 2l;
        }
        int lastIndex = -1;
        for (; i >= 0; i--) {
            int ubit = ((u | (1l << i)) == u) ? 1 : 0;
            int vbit = ((v | (1l << i)) == v) ? 1 : 0;
            if (ubit == 1 && vbit == 1){
                mp.put(i, mp.getOrDefault(i, 0l) + 1);
                if (mp.get(i) >= 2) lastIndex = i;
            }else if (ubit == 0 && vbit == 1){
                mp.put(i - 1, mp.getOrDefault(i - 1, 0l) + 2);
                lastIndex = i - 1;
            }else if (ubit == 1 && vbit == 0){
                if (lastIndex == -1){
                    out.println(-1);
                    return;
                }else{
                    while (lastIndex > i){
                        long many = 4;
                        mp.put(lastIndex, mp.get(lastIndex) - 2);
                        mp.put(lastIndex - 1, mp.getOrDefault(lastIndex - 1, 0l) + many);
                        lastIndex--;
                    }
                    mp.put(i, mp.get(i) - 1);
                    mp.put(i - 1, 2l);
                    lastIndex = i - 1;
                }
            }
        }
        long max = 0;
        for(int k : mp.keySet()){
            max = Math.max(max, mp.get(k));
        }
        out.println(max);
        long[] arr = new long[(int)max];
        for(int k : mp.keySet()){
            long len = mp.get(k);
            for (int j = 0; j < len; j++) {
                arr[j] |= (1l << k);
            }
        }
        for (int j = 0; j < max; j++) {
            out.print(arr[j]+" ");
        }
        out.println();
    }
}
