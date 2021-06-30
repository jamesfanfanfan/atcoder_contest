package Training_1500;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SquareFreeDivision_easy_ {
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
    static boolean[] isPrime=new boolean[(int)1e7 + 1];
    static int[] factorOf=new int[(int)1e7 + 1];
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

    static void solve(Scanner in, PrintWriter out){
        int n = in.nextInt(), k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] neo = new int[n];
        for (int i = 0; i < n; i++) {
            neo[i] = getOddFactors(arr[i]);
        }
        Set<Integer> set = new HashSet<>();
        int cnt = 1;

        for(int i = 0; i < n; i++){
            if (set.contains(neo[i])){
                cnt++;
                set.clear();
            }
            set.add(neo[i]);
        }
        out.println(cnt);
    }

}
