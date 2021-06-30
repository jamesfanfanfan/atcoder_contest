package codeforce.Taining1700;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
// should remember how to inversion of 2
public class ProblemforNazar {
    static int mod = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.close();
    }
    static void solve(Scanner in, PrintWriter out){
        long l = in.nextLong(), r = in.nextLong();
        out.println((((helper(r) - helper(l - 1)) % mod) + mod) % mod);
    }
    static long helper(long val){
        long o = 0, e = 0;
        long p = 1, cnt = 0;
        boolean odd = true;
        while (p + cnt <= val){
            cnt += p;
            if (odd){
                o += p;
            }else{
                e += p;
            }
            p *= 2;
            odd = !odd;
        }
        if (odd){
            o += (val - cnt);
        }else{
            e += (val - cnt);
        }
//        System.out.println("odd:"+o+" even:"+e);
        BigInteger od = new BigInteger(String.valueOf(o));
        BigInteger even = new BigInteger(String.valueOf(e));
        od = od.multiply(od.multiply(new BigInteger("2"))).divide(new BigInteger("2")).remainder(new BigInteger(String.valueOf(mod)));
        even = even.multiply(new BigInteger("2").add(even.multiply(new BigInteger("2")))).divide(new BigInteger("2")).remainder(new BigInteger(String.valueOf(mod)));
        od = od.add(even).remainder(new BigInteger(String.valueOf(mod)));
//        System.out.println(od.longValue()+" ebe:"+even.longValue());
        return od.longValue();
    }

    // Use this instead of Arrays.sort() on an array of ints. Arrays.sort() is n^2
    // worst case since it uses a version of quicksort. Although this would never
    // actually show up in the real world, in codeforces, people can hack, so
    // this is needed.
    static void ruffleSort(int[] a) {
        //ruffle
        int n=a.length;
        Random r=new Random();
        for (int i=0; i<a.length; i++) {
            int oi=r.nextInt(n), temp=a[i];
            a[i]=a[oi];
            a[oi]=temp;
        }

        //then sort
        Arrays.sort(a);
    }
}
