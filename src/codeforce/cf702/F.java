package codeforce.cf702;

import java.io.PrintWriter;
import java.util.*;

public class F {
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
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Map<Integer, Integer> mp = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            mp.put(arr[i], mp.getOrDefault(arr[i], 0) + 1);
        }
        int max = 0;
        for (int x : mp.keySet()) {
            max = Math.max(max, mp.get(x));
            set.add(mp.get(x));
        }
        int res = Integer.MAX_VALUE;
        for(int i : set){
            int cnt = 0;
            for(int x : mp.keySet()){
                if (mp.get(x) < i){
                    cnt += mp.get(x);
                }else{
                    cnt += (mp.get(x) - i);
                }
                if (cnt > res) break;
            }
            //System.out.println("i:"+i+" cnt:"+cnt);
            res = Math.min(cnt, res);
        }
        out.println(res);
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
