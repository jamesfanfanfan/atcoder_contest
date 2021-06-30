package Training_1500;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class StringDeletion {
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
        char[] cs = in.next().toCharArray();
        LinkedList<int[]> q = new LinkedList<>();
        LinkedList<int[]> ava = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && cs[i] == cs[j])j++;
            q.add(new int[]{i});
            if (j - i > 1){
                ava.add(new int[]{i, j - i - 1});
            }
            i = j - 1;
        }
        int ans = 0;
        while (!q.isEmpty()){
            int[] get = q.poll();
            ans++;
            while (!ava.isEmpty() && ava.peek()[0] < get[0]) ava.poll();
            if (!ava.isEmpty()){
                int[] rem = ava.poll();
                rem[1]--;
                if (rem[1] > 0){
                    ava.addFirst(rem);
                }
            }else{
                if (q.isEmpty()) break;
                q.poll();
            }
        }
        out.println(ans);
    }

}
