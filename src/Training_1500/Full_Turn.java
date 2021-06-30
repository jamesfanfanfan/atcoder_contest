package Training_1500;

import java.io.PrintWriter;
import java.util.*;

public class Full_Turn {

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
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()};
        }
        int[][] neo = new int[n][2];
        Map<Integer, Map<Integer, Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] x = arr[i];
            neo[i] = new int[]{x[2] - x[0], x[3] - x[1]};
            int gcd = (int)gcd(neo[i][0], neo[i][1]);
            //System.out.println(Arrays.toString(neo[i])+" "+gcd);
            neo[i] = new int[]{neo[i][0] / Math.abs(gcd), neo[i][1] / Math.abs(gcd)};
            if (!mp.containsKey(neo[i][0])) mp.put(neo[i][0], new HashMap<>());
            mp.get(neo[i][0]).put(neo[i][1], mp.get(neo[i][0]).getOrDefault(neo[i][1], 0) + 1);
        }
        long cnt = 0;
        //System.out.println(mp);
        for (int[] x : neo) {
            int[] rev = new int[]{-x[0], -x[1]};
            if (mp.containsKey(rev[0]) && mp.get(rev[0]).containsKey(rev[1])){
                cnt += mp.get(rev[0]).get(rev[1]);
            }
        }
        out.println((cnt / 2));

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
