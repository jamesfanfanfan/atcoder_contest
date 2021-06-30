package codeforce.cf702;

import java.io.PrintWriter;
import java.util.*;

public class E {
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
        int max = 0;
        int min = Integer.MAX_VALUE;
        List<Integer> st = new ArrayList<>();
        int[][] fd = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
            st.add(arr[i]);
            fd[i] = new int[]{arr[i], i};
        }
        Collections.sort(st);
        Arrays.sort(fd, (a, b) -> {
            return a[0] - b[0];
        });
        int l = 0, r = n;
        while (l < r){
            int mid = (l + r) >> 1;

            int[] ak = fd[mid];
            long cnt = ak[0];
            for(int[] x : fd){
                if (ak[1] == x[1]) continue;
                if (cnt < x[0]) break;
                cnt += x[0];
            }
            //System.out.println("l:"+l+" r:"+r+" "+cnt+" mid:"+mid);
            if (cnt >= st.get(st.size() - 1)){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        //System.out.println(r+ "ff");
        List<Integer> res = new ArrayList<>();
        for (int i = r; i < n; i++) {
            res.add(fd[i][1] + 1);
        }
        Collections.sort(res);
        out.println(res.size());
        for(int x : res){
            out.print(x+ " ");
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
