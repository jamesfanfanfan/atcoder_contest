package CR696;

import java.io.PrintWriter;
import java.util.Scanner;

public class D {
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
        int[] le = new int[n + 2];
        int[] ri = new int[n + 2];
        boolean[] validp = new boolean[n + 3];
        boolean[] valids = new boolean[n + 3];

        int[] p = new int[n + 3];
        int[] s = new int[n + 3];
        p[0] = arr[0];
        validp[0] = true;
        for (int i = 1; i < n; i++) {
            p[i] = arr[i] - p[i - 1];
            validp[i] = validp[i - 1] && p[i] >= 0;
        }
        s[n - 1] = arr[n - 1];
        valids[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            s[i] = arr[i] - s[i + 1];
            valids[i] = valids[i + 1] && s[i] >= 0;
        }
        //System.out.println(Arrays.toString(validp)+" "+Arrays.toString(p));
        if (validp[n - 1] && p[n - 1] == 0){
            out.println("YES");
            return;
        }
        //System.out.println("fjhsbfhjbshjfbd");
        for (int i = 0; i < n - 1; i++) {
            if (i > 0 && !validp[i - 1])continue;
            if (i + 1 < n - 1 && !valids[i + 2])continue;
            int l = i == 0 ? 0 : p[i - 1];
            int r = i == n - 2 ? 0 : s[i + 2];
            int a1 = arr[i + 1] - l;
            if (a1 < 0) continue;
            int a2 = arr[i] - a1;
            if (a2 < 0) continue;
            if (a2 == r){
                out.println("YES");
                return;
            }

        }
        out.println("NO");
//        if (arr[0] > arr[1]){
//            int cnt = arr[0] - arr[1];
//            for (int i = 2; i < n; i++) {
//                if (cnt > arr[i]){
//                    out.println("NO");
//                    return;
//                }else{
//                    cnt = arr[i] - cnt;
//                }
//            }
//            if (cnt == 0){
//                out.println("YES");
//            }else{
//                out.println("NO");
//            }
//        }else if (arr[n - 1] > arr[n - 2]){
//            int cnt = arr[n - 1] - arr[n - 2];
//            for (int i = n - 3; i >= 0 ; i--) {
//                if (cnt > arr[i]){
//                    out.println("NO");
//                    return;
//                }else{
//                    cnt = arr[i] - cnt;
//                }
//            }
//            if (cnt == 0){
//                out.println("YES");
//            }else{
//                out.println("NO");
//            }
//        }else{
//            boolean[] validp = new boolean[n + 3];
//            boolean[] valids = new boolean[n + 3];
//
//            int[] p = new int[n + 3];
//            int[] s = new int[n + 3];
//            p[0] = arr[0];
//            validp[0] = true;
//            for (int i = 1; i < n; i++) {
//                p[i] = arr[i] - p[i - 1];
//                validp[i] = validp[i - 1] && p[i] >= 0;
//            }
//            s[n - 1] = arr[n - 1];
//            valids[n - 1] = true;
//            for (int i = n - 2; i >= 0; i--) {
//                s[i] = arr[i] - s[i + 1];
//                valids[i] = valids[i + 1] && s[i] >= 0;
//            }
//            //System.out.println(Arrays.toString(validp)+" "+Arrays.toString(p));
//            if (validp[n - 1] && p[n - 1] == 0){
//                out.println("YES");
//                return;
//            }
//            //System.out.println("fjhsbfhjbshjfbd");
//            for (int i = 0; i < n - 1; i++) {
//                if (i > 0 && !validp[i - 1])continue;
//                if (i + 1 < n - 1 && !valids[i + 2])continue;
//                int l = i == 0 ? 0 : p[i - 1];
//                int r = i == n - 2 ? 0 : s[i + 2];
//                int a1 = arr[i + 1] - l;
//                if (a1 < 0) continue;
//                int a2 = arr[i] - a1;
//                if (a2 < 0) continue;
//                if (a2 == r){
//                    out.println("YES");
//                    return;
//                }
//
//            }
//            out.println("NO");
//        }

    }
}
