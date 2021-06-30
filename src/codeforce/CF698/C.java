package codeforce.CF698;

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
        long[] arr  = new long[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = in.nextLong();
        }
       // long[][] see = new long[n][2];
        Map<Long, Integer> mp = new HashMap<>();
        for(long x : arr){
            mp.put(x, mp.getOrDefault(x, 0) + 1);
            if (x % 2 != 0){
                out.println("NO");
                return;
            }
        }
        List<Long> res = new ArrayList<>();
        for(long v : mp.keySet()){
            if (mp.get(v) != 2){
                out.println("NO");
                return;
            }
            res.add(v);
        }
        Collections.sort(res);
        long add = 0;
        long pre = 0;
        //System.out.println(res);
        List<Long> see = new ArrayList<>();
        for (int i = 1; i < res.size(); i++) {
            long dif = res.get(i) - res.get(i - 1);
            if(dif % (2 * i) != 0 || dif == 0){
                out.println("NO");
                return;
            }else{
                see.add(dif / i);
                //add += (dif / i);
            }
        }
        add = 0;
        long tot = 0;
        for (int i = 0; i < see.size(); i++) {
            long dif = see.get(i);
            add += dif;
            tot += add;
        }
        //System.out.println(res +" "+see);
        tot = res.get(0) - tot;
        //System.out.println(tot+" "+see);
        if (tot <= 0){
            out.println("NO");
        }else{
            if (tot % (2 * n) != 0){
                out.println("NO");
            }else{
                out.println("YES");
            }
        }


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
