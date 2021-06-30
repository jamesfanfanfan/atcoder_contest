package ABC.ARC109;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        C(sc, pw);
        pw.close();
    }
    static void C(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.next();
        char[] cs = s.toCharArray();
        int[] pow = new int[102];
        pow[0] = 1;
        int len = s.length();
        for (int i = 1; i <= k; i++) {
            pow[i] = (pow[i - 1] * 2) % len;
        }
        Character[][] dp = new Character[102][102];
        out.println(dp(dp, 0, k, cs, pow));
    }

    static char dp(Character[][] memo, int n, int k, char[] c, int[] pow){
        if(memo[n][k] != null) return memo[n][k];
        if(k == 0) return c[n];
        char l = dp(memo, n, k - 1, c, pow);
        char r = dp(memo, (n + pow[k - 1]) % c.length, k - 1, c, pow);
        char win = cmp(l, r);
        return memo[n][k] = win;
    }

    static char cmp(char l, char r){
        if(l == r) return l;
        if(l == 'R' && r == 'S'){
            return l;
        }
        if(l == 'R' && r == 'P'){
            return r;
        }
        if(l == 'P' && r == 'S'){
            return r;
        }
        return cmp(r, l);
    }



    static void B(Scanner in, PrintWriter out){
        long n = in.nextLong();
        long div = n + 1;
        long r = Integer.MAX_VALUE - 1;
        long l = 0;
        while (l < r - 1){
            long mid = (r + l) / 2;
            long aka = (1 + mid) * mid / 2;
            if(aka == div){
                r = l = mid;
            }else if (aka < div){
                l = mid;
            }else{
                r = mid - 1;
            }
            //out.println(l+" "+r);
        }
        if ((1 + r) * r / 2 <= div){
            out.println((n - r + 1));
        }else if((r) * (r - 1) / 2 <= div){
            out.println((n - r + 2));
        }else{
            out.println((n - l + 1));
        }

    }
    static void A(Scanner in, PrintWriter out){
        int a = in.nextInt();
        int b = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        //if(y > 2 * x) y = 2 * x;
        if(a > b){
            int l = (a - b - 1) * y + x;
            int r = (a - b) * x + (a - b - 1) * x;
            out.println(Math.min(l, r));
        }else{
            int l = (b - a) * y + x;
            int r = (b - a) * x + (b - a + 1) * x;
            out.println(Math.min(l, r));

        }

    }

}
