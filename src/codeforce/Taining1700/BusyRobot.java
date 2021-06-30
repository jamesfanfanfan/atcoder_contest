package codeforce.Taining1700;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BusyRobot {
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
        long[][] arr = new long[n + 1][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new long[]{in.nextInt(), in.nextInt()};
        }
        arr[n] = new long[]{Long.MAX_VALUE / 2, 0};
        Arrays.sort(arr, (a, b) -> {
            return Long.compare(a[0], b[0]);
        });
        long cur = 0;
        long stPos = 0, stTime = 0, len = 0, tarPos = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            long t = arr[i][0], pos = arr[i][1];
            if (stTime + len <= t){
                stTime = t;
                len = Math.abs(pos - tarPos);
                stPos = tarPos;
                tarPos = pos;
                if (stTime + len <= arr[i + 1][0]) {
                    cnt++;
                }
            }else{
                long expectedLen = t - stTime;
                long nextTime = arr[i + 1][0];
                if (stPos < tarPos){
                    if (pos >= stPos && pos <= tarPos){
                        if (stPos + expectedLen <= pos && stPos + (nextTime - stTime) >= pos){
                            cnt++;
                        }
                    }
                }else{
                    if (pos <= stPos && pos >= tarPos){
                        if (stPos - expectedLen >= pos && stPos - (nextTime - stTime) <= pos){
                            cnt++;
                        }
                    }
                }
            }
        }
        out.println(cnt);
    }
}
