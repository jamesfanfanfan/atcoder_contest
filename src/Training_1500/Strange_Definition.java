package Training_1500;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Strange_Definition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        precomp();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static boolean[] isPrime=new boolean[1_000_001];
    static int[] factorOf=new int[1_000_001];

    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int q = in.nextInt();
        long[] qs = new long[q];
        for (int i = 0; i < q; i++) {
            qs[i] = in.nextLong();
        }
        Map<Integer, Integer> mp = new HashMap<>();
        for (int x : arr) {
            int get = getOddFactors(x);
            mp.put(get, mp.getOrDefault(get, 0) + 1);
        }
//        System.out.println(mp);
        int cnt = 0;
        int max = 0;
        for(int k : mp.keySet()){
            if (mp.get(k) % 2 == 0 && k != 1){
                cnt += mp.get(k);
            }
            max = Math.max(max, mp.get(k));
        }
        for(long qq : qs){
            if (qq > 0){
                out.println(Math.max(max, cnt + mp.getOrDefault(1, 0)));
            }else{
                out.println(max);
            }
        }
    }

    static void precomp() {
        Arrays.fill(isPrime, true);
        for (int i=2; i<isPrime.length; i++) {
            if (!isPrime[i]) continue;
            factorOf[i]=i;
            for (int j=i*2; j<isPrime.length; j+=i) {
                isPrime[j]=false;
                factorOf[j]=i;
            }
        }
    }

    static int getOddFactors(int n) {
        int res=1;
        if (n==1) return res;
        while (n!=1) {
            int factor=factorOf[n];
            int nTimes=0;
            while (n%factor==0) {
                nTimes++;
                n/=factor;
            }
            if (nTimes%2!=0) res*=factor;
        }
        return res;
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
