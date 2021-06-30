package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;
//https://codeforces.com/contest/1295/problem/D
//maybe I would solve this problem if I know Euler's product formula before
//  I know one equation from Internet, it is that gcd(a, m) = gcd(a, a1) * gcd(a, a2) * gcd(a, a3) where a1 * a2 * a3 = m
// and a1, a2, a3 are coprime pairwise
//now we think about in this way, what makes gcd(a, m) = gcd(a + x, m). if we suppose gcd(a, a1) = y > 1, and rest of them is 1, which means
// we need to find a number a + x which should have  gcd(a + x, a1) = y and gcd(a + x, a1) = 1...
//so that a + x should have y, but cannot hve a1, a2 or a1 ^ 1 or a2 ^2 and so on
//why we should do this ? : we can see that a1 should be the power of one prime, and a2 is as well which denotes that if gcd(a + x, a1) != gcd(a, a1)
//you cannot make gcd(a, a1) * gcd(a, a2) * gcd(a, a3) = gcd(a + x, a1) * gcd(a + x, a2) * gcd(a + x, a3) because output of these gcd number is coprime
//now we know that we just need to know how many value of a + x which makes gcd(a + x, a2) = 1 and gcd(a + x, a3) = 1
//well, we know x < m and gcd(𝑎+𝑥,𝑚)=gcd(𝑎+𝑥−𝑚,𝑚) therefore we need to find out x_neo = (a + x) % mod this means each value of a + x could find a reflect
//on the range of 0 to m, such that gcd(x_neo, m) = gcd(a, m)
//with the above mentioned conclusion we just need to find how many numbers of x_neo such that gcd(x_neo / (gcd(x_neo, m)), m / (gcd(x_neo, m))) = 1
// this is just gcd(a, a2) * gcd(a, a3) = 1 => gcd(a, a2 * a3) = 1, then this is Euler's totient function
//well -----> maybe you should first know this function then you have a better chance to solve it

public class SameGCDs {
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
        long a = in.nextLong(), m = in.nextLong();
        long get = gcd(a, m);
        out.println(phi(m / get));
    }
    //    credits to  LorDick for Euler's totient function which is used to calculate the number x which is the coprime with m and also is smaller than m
    static long phi(long x) {
        long ans = x;
        for(long i = 2; i * i <= x; i++) {
            if(x % i == 0) {
                while(x % i == 0) {
                    x /= i;
                }
                ans /= i;
                ans *= (i - 1);
            }
        }
        if(x > 1) {
            ans /= x;
            ans *= (x - 1);
        }
        return ans;
    }
    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
}
