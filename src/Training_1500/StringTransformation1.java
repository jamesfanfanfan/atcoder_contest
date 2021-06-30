package Training_1500;

import java.io.PrintWriter;
import java.util.*;

public class StringTransformation1 {
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
        char[] ca = in.next().toCharArray();
        char[] cb = in.next().toCharArray();
        for (int i = 0; i < n; i++) {
            if (ca[i] > cb[i]){
                out.println(-1);
                return;
            }
        }
        boolean[][] seen = new boolean[26][26];
        boolean[] act = new boolean[26];
        for (int i = 0; i < n; i++) {
            if (ca[i] == cb[i]) continue;
            seen[ca[i] - 'a'][cb[i] - 'a'] = true;
            act[ca[i] - 'a'] = true;
        }
        int cnt = 0;
        for (int i = 0; i < 20; i++) {
            if (act[i]){
                for (int j = i + 1; j < 20; j++) {
                    if (seen[i][j]){
                        act[j] = true;
                        cnt++;
                        for (int k = i + 1; k < 20; k++) {
                            if (seen[i][k]) seen[j][k] = true;
                        }
                        seen[j][j] = false;
                        break;
                    }
                }
            }
        }
        out.println(cnt);
    }


}
