package Training_1500;

import java.io.PrintWriter;
import java.util.Scanner;

public class HillsAndValleys {
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
        int[] arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int cnt = 0;
       int[] le = new int[n + 2];
       int[] ri = new int[n + 2];
        for (int i = 1; i < n - 1; i++) {
            int l = arr[i - 1], r = arr[i + 1];
            le[i + 1] = le[i];
            if ((arr[i] > l && arr[i] > r )|| (arr[i] < l && arr[i] < r)){
                le[i + 1]++;
            }
        }
        for (int i = n - 2; i >= 1; i--) {
            int l = arr[i - 1], r = arr[i + 1];
            ri[i] = ri[i + 1];
            if ((arr[i] > l && arr[i] > r )|| (arr[i] < l && arr[i] < r)){
                ri[i]++;
            }
        }
        int min = ri[1];
        for (int i = 1; i < n - 1; i++) {
            int l = arr[i - 1], r = arr[i + 1];
            int v = arr[i];
            if ((arr[i] > l && arr[i] > r )|| (arr[i] < l && arr[i] < r)){
                arr[i] = l;
                int tot = 0;
                for (int j = i - 1; j <= i + 1; j++) {
                    if (j == 0 || j == n - 1) continue;
                    int ll = arr[j - 1], rr = arr[j + 1];
                    if ((arr[j] > ll && arr[j] > rr)|| (arr[j] < ll && arr[j] < rr)){
                        tot++;
                    }
                }
                min = Math.min(min, tot + le[i - 1] + ri[i + 2]);
                tot = 0;
                arr[i] = r;
                for (int j = i - 1; j <= i + 1; j++) {
                    if (j == 0 || j == n - 1) continue;
                    int ll = arr[j - 1], rr = arr[j + 1];
                    if ((arr[j] > ll && arr[j] > rr)|| (arr[j] < ll && arr[j] < rr)){
                        tot++;
                    }
                }
                min = Math.min(min, tot + le[i - 1] + ri[i + 2]);
            }
            arr[i] = v;
        }
        out.println(min);
    }

}
