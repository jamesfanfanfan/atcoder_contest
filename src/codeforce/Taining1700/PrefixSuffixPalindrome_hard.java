package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;
//https://codeforces.com/contest/1326/problem/D2
public class PrefixSuffixPalindrome_hard {
    final static int[] MOD =  {1000000007, 2117566807,1000000007,1000000037};
    final static int[] BASE = {1572872831, 1971536491,1000003,31};
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
        char[] cs = in.next().toCharArray();
        String s = new String(cs);
        int n = cs.length;
        int l = 0, r = n - 1;
        while (l < r && cs[l] == cs[r]){
            l++;r--;
        }
        if (l >= r){
            out.println(new String(cs));
        }else{
            String left = s.substring(l, r+1);
            StringHash s1 = new StringHash();
            StringHash s2 = new StringHash();
            s1.GenerateFH(left,Long.valueOf(BASE[0]),Long.valueOf(MOD[0]));
            s2.GenerateRH(left,Long.valueOf(BASE[0]),Long.valueOf(MOD[0]));
            String temp = "";
            for(int i= left.length()-1; i>=0;i--) {
                if(s1.Fhash(0, i).equals(s2.Rhash(0, i))) {
                    temp = left.substring(0,i+1);
                    break;
                }
            }
            for(int i= 0;i<left.length();i++) {
                if(s1.Fhash(i,left.length()-1) == s2.Rhash(i,left.length()-1)) {
                    if(left.substring(i,left.length()).length() > temp.length()) {
                        temp = left.substring(i,left.length());
                    }
                    break;
                }
            }
            out.println(s.substring(0, l) + temp + s.substring(r + 1, n));
        }
    }

//  credits to Moonlit
    static class StringHash{
        Long base, mod; int len;
        Long ar[], p[];

        public void GenerateFH(String s, Long b, Long m) {
            len = s.length();
            p = new Long[len];
            ar = new Long[len];
            p[0] = 1L;

            base = b;
            mod = m;
            for(int i = 1; i<len;i++) {
                p[i] = p[i-1] * base;
                p[i]%=m;
            }

            long h = 0L;
            for(int i = 0;i<len;i++) {
                h = (h*base) + s.charAt(i);
                h%=mod;
                ar[i] = h;
            }
        }

        public Long Fhash(int x,int y){
            Long h=ar[y];
            if(x>0){
                h=(h-p[y-x+1]*ar[x-1])%mod;
                if(h<0) h+=mod;
            }
            return h;
        }

//        generate the hash from the back of the string
        public void GenerateRH(String s, Long b, Long m) {
            len = s.length();
            p = new Long[len+1];
            ar = new Long[len+1];
            p[0] = Long.valueOf(1);
            base = b;
            mod = m;

            for(int i = 1; i<len;i++) {
                p[i] = p[i-1] * base;
                p[i]%=m;
            }

            Long h = 0L;

            for(int i = len-1;i>=0;i--) {
                h = (h*base) + s.charAt(i);
                h%=mod;
                ar[i] = h;
            }
        }

        public long Rhash(int x, int y){
            Long h=ar[x];
            if(y<len-1){
                h=(h-p[y-x+1] *ar[y+1])%mod;
                if(h<0) h+=mod;
            }
            return h;
        }
    }
}
