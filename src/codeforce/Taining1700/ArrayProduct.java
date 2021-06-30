package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
//rubbish problem
public class ArrayProduct {
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
        int n = in.nextInt();
        int[] arr = new int[n];
        int[] aka = new int[3];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            if (arr[i] == 0) {
                aka[0]++;
            }else if (arr[i] > 0){
                aka[1]++;
            }else{
                aka[2]++;
            }
        }
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (a, b) -> {
            return arr[a] - arr[b];
        });
        if (aka[2] % 2 == 1 && aka[0] >= 1){
            int st = 0;
            while (st < n - 1 && arr[idx[st + 1]] < 0) st++;
            for (int i = 1; i < st; i++) {
                out.println(1+" "+(idx[i] + 1)+" "+(idx[0] + 1));
            }
            int ed = st + 1;
            while (ed < n && arr[idx[ed]] == 0) ed++;
            for (int i = st + 1; i < ed; i++) {
                out.println(1+" "+(idx[i] + 1)+" "+(idx[st] + 1));
            }
            if(ed < n || st > 0) out.println(2+" "+(idx[st] + 1));
            for(int i = n - 2; i >= ed; i--){
                out.println(1+" "+(idx[i] + 1)+" "+(idx[n - 1] + 1));
            }
        }else if (aka[2] % 2 == 1){
            int st = 0;
            while (st < n - 1 && arr[idx[st]] < 0 && arr[idx[st + 1]] < 0) st++;
            for (int i = 1; i < st; i++) {
                out.println(1+" "+(idx[i] + 1)+" "+(idx[0] + 1));
            }
            out.println(2+" "+(idx[st] + 1));
            for(int i = n - 2; i > st; i--){
                out.println(1+" "+(idx[i] + 1)+" "+(idx[n - 1] + 1));
            }
            if (st > 0) out.println(1+" "+(idx[0] + 1)+" "+(idx[n - 1] + 1));
        }else{
            int st = 0;
            while (st < n && arr[idx[st]] < 0) st++;
            for (int i = 1; i < st; i++) {
                out.println(1+" "+(idx[i] + 1)+" "+(idx[0] + 1));
            }
            if (arr[idx[st]] == 0){
                int ed = st;
                while (ed < n && arr[idx[ed]] == 0) ed++;
                for (int i = st + 1; i < ed; i++) {
                    out.println(1+" "+(idx[i] + 1)+" "+(idx[st] + 1));
                }
                if (ed < n) out.println(2+" "+(idx[st] + 1));
                for(int i = n - 2; i >= ed; i--){
                    out.println(1+" "+(idx[i] + 1)+" "+(idx[n - 1] + 1));
                }
                if (st > 0) out.println(1+" "+(idx[0] + 1)+" "+(idx[n - 1] + 1));
            }else{
                for(int i = n - 2; i >= st; i--){
                    out.println(1+" "+(idx[i] + 1)+" "+(idx[n - 1] + 1));
                }
                if (st > 0) out.println(1+" "+(idx[0] + 1)+" "+(idx[n - 1] + 1));
            }
        }

    }
}
