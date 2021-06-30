package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//https://codeforces.com/contest/1295/problem/B
// just remember that x = ? * difference + k  ---->  (x - k) and dif should have same sign
//when you have 0, you should add 1 into that
//when your difference is 0 which means you have infinite

public class InfinitePrefixes {
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
        int n = in.nextInt(), x = in.nextInt();
        char[] cs = in.next().toCharArray();
        int cnt = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        int zero = 0, one = 0;
//        mp.put(0, 0);
        for(char c : cs){
            if (c == '0'){
                zero++;
            }else{
                one++;
            }
            int get = zero - one;
            mp.put(get, mp.getOrDefault(get, 0) + 1);
        }
        int dif = zero - one;
        long ans = 0;
        if (dif == 0){
            if (mp.containsKey(x)){
                out.println(-1);
            }else{
                out.println(0);
            }
        }else{
            if (x == 0) ans++;
            for(int k : mp.keySet()){
                int add = mp.get(k);
                if ((x - k) % dif == 0){
                    int req = (x - k) / dif;
                    if (req >= 0){
                        ans += add;
                    }
                }
            }
            out.println(ans);
        }
    }
}
