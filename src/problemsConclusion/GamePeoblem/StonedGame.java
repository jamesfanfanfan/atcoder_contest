package problemsConclusion.GamePeoblem;

import java.io.PrintWriter;
import java.util.Scanner;
// how to think about situation: we should find out for each player and then for the whole play
// for this problem, you could find when you have a pile larger than half of total stone, first people would win
// then we consider the opposite, it all the pile did not out number the half of total stone, we can see
// that each time total stone would reduce by 2, therefore, for second player, he can always force the play to be
// 1 1  if total number is even
//if total number is odd then we can see that when first player choose one it comes back to the second situation

//https://codeforces.com/problemset/problem/1396/B
public class StonedGame {
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
        int tot = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            tot += arr[i];
            max = Math.max(max, arr[i]);
        }
        if (max > tot - max){
            out.println("T");
        }else{
            if (tot % 2 == 0){
                out.println("HL");
            }else{
                out.println("T");
            }
        }

    }
}
