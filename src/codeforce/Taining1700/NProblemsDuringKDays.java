package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Scanner;

public class NProblemsDuringKDays {
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
        long n = in.nextInt(), k = in.nextInt();
        long maxTot = 1;
        long st = 1;
        for (int i = 0; i < k - 1; i++) {
            st *= 2;
            maxTot += st;
            if (maxTot >= n) break;
        }
        long mintot = (1l + k) * k / 2;
//        System.out.println(mintot);
        if (mintot > n){
            out.println("NO");
        }else{
            if (k == 1){
                out.println("YES");
                out.println(n);
                return;
            }
            long[] ans = new long[(int)k];

            for(int i = 0; i < k; i++){
                n -= (i + 1);
                ans[i] = (i + 1);
            }
            long per = n / (k);
            long rem = n % (k);
            for(int i = 0; i < k; i++){
                n -= per;
                ans[i] += per;
            }
            long ok = ans[0];
            for (int i = 1; i < Math.min(31, k); i++) {
                ok *= 2;
                if (ans[i] > ok){
                    rem += (ans[i] - ok);
                    ans[i] = ok;
                }
            }
//            System.out.println(Arrays.toString(ans)+" per:"+per+" n:"+n+" rem:"+rem);
            for (int i = (int)k - 1; i >= 1 && rem > 0; i--) {
                long add = 2 * ans[i - 1] - ans[i];
                if (add >= rem){
                    ans[i] += rem;
                    rem = 0;
                }else{
                    ans[i] += add;
                    rem -= add;
                }
            }
            long tot = 0;
            for (int i = 0; i < k; i++) {
                tot += ans[i];
            }
//            System.out.println(tot+" tot:"+tot);
            if (rem > 0){
                out.println("NO");
            }else{
                out.println("YES");
                for (int i = 0; i < k; i++) {
                    out.print(ans[i]+" ");
                }
            }
        }
    }
}
