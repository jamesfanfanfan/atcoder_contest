package Training_1500;

import java.io.PrintWriter;
import java.util.*;

public class The_Rotot {

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
        String s = in.next();
        char[] cs = s.toCharArray();
        int n = cs.length;
        //int[][] dirsss = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        String ak = "LURD";
        int[][] end = new int[n + 1][2];
        int[] st = new int[]{0, 0};
        for (int i = n - 1; i >= 0 ; i--) {
            int[] dir = dirs[ak.indexOf(cs[i])];
            st = new int[]{st[0] - dir[0], st[1] - dir[1]};
            end[i] = Arrays.copyOf(st, 2);
        }
        st = new int[2];
        Map<Integer, Set<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
            }
        }
//        int[][] from = new int[n + 1][2];
//        st = new int[2];
//        for (int i = 0; i < n ; i++) {
//            int[] dir = dirs[ak.indexOf(cs[i])];
//            st = new int[]{st[0] + dir[0], st[1] + dir[1]};
//            from[i] = Arrays.copyOf(st, 2);
//        }
//        if (end[1][0] == 0 && end[1][1] == 0){
//            out.println(from[0][0]+" "+from[0][1]);
//            return;
//        }
//        for (int i = 1; i < n; i++) {
//            int[] le = from[i - 1];
//            int[] ri = end[i + 1];
//            if (Arrays.compare(le, ri) == 0){
//                out.println(from[i][0]+" "+from[i][1]);
////                return;
//            }
//        }
        out.println(0+" "+0);
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
