package codeforce.cf702;

import java.io.PrintWriter;
import java.util.*;

public class G {
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
        int m = in.nextInt();
        int[] arr = new int[n];
        int[] xrr = new int[m];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            xrr[i] = in.nextInt();
        }
        long[] pre = new long[n + 1];
        TreeMap<Long,Integer> mp = new TreeMap<>();
        //mp.put(0L, 0);
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + arr[i - 1];
            if (mp.size() == 0 || pre[i] > mp.lastKey()) mp.put(pre[i], i - 1);
        }
        List<Long> res = new ArrayList<>();
        long max = mp.lastKey();
        //System.out.println(Arrays.toString(pre)+" "+mp);
        for(long x : xrr){
            if (x > max && pre[n] <= 0){
                res.add(-1L);
            }else{
                long r = 0;
                if (x > max) r = (x - max + pre[n] - 1) / pre[n];
                x = x - r * pre[n];
                //System.out.println(x);
                res.add(r * n + mp.get(mp.ceilingKey(x)));
                //System.out.println(x+" r:"+r);
            }
        }
        for(long x : res){
            out.print(x+" ");
        }
        out.println();
    }
    static boolean isPrime(long n)
    {
        // Corner cases
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }

    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // method to return LCM of two numbers
    static long lcm(long a, long b)
    {
        return (a / gcd(a, b)) * b;
    }
}

