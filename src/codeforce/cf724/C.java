package codeforce.cf724;

import java.io.PrintWriter;
import java.util.*;

public class C {
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
        char[] cs = in.next().toCharArray();
        int cd = 0, ck = 0;
        Map<String, Integer> mp = new HashMap<>();
        for(int i = 0; i < n; i++){
            if (cs[i] == 'K'){
                ck++;
            }else{
                cd++;
            }
            long get = gcd(ck, cd);
            long le = ck / get;
            long ri = cd / get;
            String sc = le+":"+ri;
            out.print(mp.getOrDefault(sc, 0) + 1+" ");
            mp.put(sc, mp.getOrDefault(sc, 0) + 1);
        }
        out.println();
    }
    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

}
